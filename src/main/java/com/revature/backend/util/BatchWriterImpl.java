package com.revature.backend.util;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.backend.model.Associate;
import com.revature.backend.model.Batch;
import com.revature.backend.service.AssociateService;
import com.revature.backend.service.BatchService;


/**
 * This class will handle writing new batches and associates to the project DB
 * 
 * Methods: (1) writeNewlyStagingBatches(): takes a list of Batch objects and
 * persists them in the project db. Should only be Batches who are not already
 * in the db and assigned to a manager.
 * 
 * (2) writeNewlyStagingAssociates(): takes a list of Associate objects and
 * persists them in the project db. Should only be associates who are not
 * already in the db and assigned to a manager.
 * 
 * @author Azhya Knox
 **/

@Service("batchWriter")
public class BatchWriterImpl implements BatchWriter {
    public static Logger logger = Logger.getLogger(BatchRetrieverImpl.class);

    @Autowired
    private BatchService batchService;
    @Autowired
    private AssociateService associateService;

    public BatchWriterImpl() {
		logger.info("In BatchWriter no-args constructor");
    }
    
    @Override
    public List<Batch> writeNewlyStagingBatches(List<Batch> bList){
        //start logging
        logger.debug("Beginning to save batches to DB. Calling BatchService now.");
        //call the appropriate service to save into DB
        List<Batch> list = batchService.saveBatches(bList);
        //end logging
        logger.debug("Batches have been saved to DB. Leaving BatchWriter.");
        return list;
    }

    @Override
    public List<Associate> writeNewlyStagingAssociates(List<Associate> aList){
        //start logging
        logger.debug("Beginning to save associates to DB. Calling BatchService now.");
        //call the appropriate service to save into DB
        List<Associate> list = associateService.saveAssociates(aList);
        //end logging
        logger.debug("Associates have been saved to DB. Leaving BatchWriter.");
        return list;
    }
}
