package com.revature.backend.util;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.backend.model.api.ApiAssociateAssignment;
import com.revature.backend.model.api.ApiAssociateTemplate;
import com.revature.backend.model.api.ApiBatchTemplate;


/**
 * This class will handle retrieving associates and batches from the Caliper
 * API.
 * 
 * Methods: (1) retrieveNewlyStagingAssociates(): returns a list of associates
 * who have recently been promoted to staging and who need to be assigned to a
 * manager
 * 
 * (2) retrieveNewlyStagingBatches(): returns a list of batches who have
 * recently been promoted to staging and who need to be assigned to a manager
 * 
 * @author Azhya Knox
 **/
@Component
public class BatchRetrieverImpl implements BatchRetriever {

	public static Logger logger = Logger.getLogger(BatchRetrieverImpl.class);
	
	@Autowired
	StagingListener stagingListener;

	// If using Jackson's ObjectMapper, have a static reference here
	public static ObjectMapper om = new ObjectMapper();

	public BatchRetrieverImpl() {
		logger.info("In BatchRetriever no-args constructor");
	}

	@Override
	public List<ApiAssociateTemplate> retrieveNewlyStagingAssociates() {
		// start logging activity
		logger.trace("In BatchRetriever: gathering newly staging associates...");

		List<ApiAssociateTemplate> associateList = new ArrayList<>();

		List<ApiBatchTemplate> batchList = new ArrayList<>();
		//stagingListener.checkForNewBatches();
		try{
			batchList = stagingListener.getLatestBatches();
			//from the batchList, extract out the associates into their own separate list
			for (ApiBatchTemplate apiBatchTemplate : batchList) {
				System.out.println(apiBatchTemplate);
				ApiAssociateAssignment[] arr = apiBatchTemplate.getAssociateAssignments();
				for (ApiAssociateAssignment assignment : arr) {
					ApiAssociateTemplate associate = assignment.getAssociate();
					associateList.add(associate);
				}
			}
		} catch (Exception e) {
			logger.warn("Error getting info from Staging Listener", e);
		}
		// ending logging activity
		logger.trace("Gathering associate list is complete. Leaving BatchRetriever...");

		// send found information back to the controller
		return associateList;
	}

	@Override
	public List<ApiBatchTemplate> retrieveNewlyStagingBatches() {
		// start logging activity
		logger.trace("In BatchRetriever: gathering newly staging batches...");
		List<ApiBatchTemplate> batchList = new ArrayList<>();
		//stagingListener.checkForNewBatches();
		logger.info(stagingListener);

		try{
			batchList = stagingListener.getLatestBatches();
			for (ApiBatchTemplate apiBatchTemplate : batchList) {
				System.out.println(apiBatchTemplate);
			}
		} catch (Exception e) {
			logger.warn("Error getting info from Staging Listener", e);
		}
		// ending logging activity
		logger.trace("Gathering batch list is complete. Leaving BatchRetriever...");

		// send found information back to the controller
		return batchList;
	}
}
