package com.revature.backend.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.backend.controller.AssignmentController;
import com.revature.backend.model.api.ApiBatchTemplate;
import com.revature.backend.service.ManagerBalancer;

/**
 * This class checks the API weekly for new batches and then saves them to a list for further processing.
 * This list will be grabbed by the {@link BatchRetriever} before being sent to the {@link ManagerBalancer}
 * @author Ben Johnston
 */

@Component
public class StagingListenerImpl implements StagingListener {

	private LocalDateTime nextDateToWaitFor;
	// Used to pass new batches to the next part of the project to be balanced
	// between all managers and stored in the DB
	private List<ApiBatchTemplate> latestBatches = new ArrayList<>();
	public static Logger log = Logger.getLogger(StagingListenerImpl.class);
	// Day that the update will occur each week.
	private DayOfWeek weeklyUpdateDay = DayOfWeek.SUNDAY;
	private boolean shouldUpdate;
	private boolean startUp =true;
	
	//private String caliberUrl = "https://caliber2-mock.revaturelabs.com/mock/training/batch?year=";
	
	@Autowired
	AssignmentController controller;
	
	public StagingListenerImpl() {
		super();
	}

	/**
	 * Method MUST run on server startup & repeat after each batch check.
	 *  Configures timer to check for new batches on a specified day of the week every week. If
	 * this method does not run, the entire sorting system will fail to operate.
	 */
	@Override
	public void startListening() {
		if(startUp)
		{
			log.info("In Startup");
			startUp=false;
			checkForNewBatches();
		}
		else {
			nextDateToWaitFor = LocalDateTime.now().with(TemporalAdjusters.next(weeklyUpdateDay));
			Date d = Date.from(nextDateToWaitFor.atZone((ZoneId.systemDefault())).toInstant());
			log.info("Restarting timer on StagingListener with next check at " + d +".");
			
			Timer t = new Timer();
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					log.info("Timer expired");
					checkForNewBatches();
				}
			}, d);
		}
		

	}

	/**
	 * Pulls ALL batches from the last year then filters to see only batches with an
	 * ending date between two specified parameters. If a new batch is found,
	 * {@link shouldUpdate} will be set to true & {@link latestBatches} will be populated with the new batches
	 */
	@Override
	public void checkForNewBatches() {
		log.info("Checking for new batches.....");
		//Reset the latestBatches list
		latestBatches = new ArrayList<>();
		int year = LocalDateTime.now().getYear();
		try {
			//Create API GET URL
			URL url = new URL("https://caliber2-mock.revaturelabs.com/mock/training/batch?year=" + year);
			//Open connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//Set request headers
			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "application/json");
			connection.connect();
			//Read response headers
			int respCode = connection.getResponseCode();
			if (respCode != 200) {
				log.error("Caliber API did not respond with a response code of 200!");
				throw new RuntimeException("HttpResonseCode: " + respCode);
			} else {
				log.info("Got response!");
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				//Convert stream into single String
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				log.info(inline);
				// Parse JSON into Java objects
				ObjectMapper mapper = new ObjectMapper();
				ApiBatchTemplate[] myBatches = mapper.readValue(inline, ApiBatchTemplate[].class);
				// Find the last day that the update should have been run.
				LocalDateTime lastDayChecked = LocalDateTime.now().with(TemporalAdjusters.firstInMonth(weeklyUpdateDay));
				//Get the correct date format.
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				for (ApiBatchTemplate batch : myBatches) {
					// Find the end date of the batch.
					LocalDate ld = LocalDate.parse(batch.getEndDate(), formatter);
					LocalDateTime batchDate = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
					// If the end date is between the last date the method was run and the current time, it's a new batch.
					if (batchDate.isAfter(lastDayChecked) && batchDate.isBefore(LocalDateTime.now())
							|| batchDate.isEqual(LocalDateTime.now())) {
						// Batch will be added to a list of the latest batches for easy reference across the server.
						latestBatches.add(batch);
						log.info("New batch found, adding to latestBatches list....");
					}
				}
				//Set parameter for updating the Batch DB.
				if (latestBatches.size() == 0) {
					log.info("No new batches found.");
					shouldUpdate = false;
				} else {
					log.info("There are batches ready to be assigned to a Staging Manager.");
					shouldUpdate = true;
					
					controller.addNewBatches();
					
				}

			}
			//Close the connection
			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to retrieve info from Caliber API", e);
		} finally {
			// After all code has been executed restart the timer.
			startListening();
			startUp=false;
		}
	}

	/**
	 * Should be fed into any if statement that needs to execute when there are new
	 * batches
	 */
	@Override
	public boolean triggerUpdate() {
		return shouldUpdate;
	}

	/**
	 * Returns {@link latestBatches}, a list of {@link ApiBatchTemplate} 
	 * This list contains all batches from the latest {@link checkForNewBatches()} execution. 
	 */
	@Override
	public List<ApiBatchTemplate> getLatestBatches() {
		log.info("Retrieving the latest batches");
		return latestBatches;
	}

	/**
	 * Used to update the {@link shouldUpdate} boolean AFTER the new batches are balanced between managers to prevent duplicate data from being
	 * submitted to the database.
	 */
	@Override
	public void setShouldUpdate(boolean shouldUpdate) {
		log.info("shouldUpdate is being updated");
		this.shouldUpdate = shouldUpdate;
	}

	/**
	 * Used to mock a retrieval of batches for testing. Will test if the logic within Staging Listener is working POST retrieving data from the API.
	 * SHOULD NOT BE CALLED IN PRODUCTION
	 */
	@Override
	public void mockCheckForNewBatches(boolean insertValue) {
		if(insertValue)
		{
			latestBatches.add(new ApiBatchTemplate());
			latestBatches.add(new ApiBatchTemplate());
			latestBatches.add(new ApiBatchTemplate());

			shouldUpdate=true;

		}
		else {
			latestBatches = new ArrayList<>();
			shouldUpdate=false;
		}
	}
	
	

}
