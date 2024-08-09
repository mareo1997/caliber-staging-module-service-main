package com.revature.backend.util;

import java.util.List;

import com.revature.backend.model.api.ApiAssociateTemplate;
import com.revature.backend.model.api.ApiBatchTemplate;

public interface BatchRetriever {

	public List<ApiAssociateTemplate> retrieveNewlyStagingAssociates();
	
	public List<ApiBatchTemplate> retrieveNewlyStagingBatches();
}
