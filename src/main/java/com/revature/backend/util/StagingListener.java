package com.revature.backend.util;

import java.util.List;

import com.revature.backend.model.api.ApiBatchTemplate;


public interface StagingListener {

	public void startListening();

	public void checkForNewBatches();

	public boolean triggerUpdate();
	public List<ApiBatchTemplate> getLatestBatches();
	public void setShouldUpdate(boolean shouldUpdate);
	public void mockCheckForNewBatches(boolean insertValue);
}
