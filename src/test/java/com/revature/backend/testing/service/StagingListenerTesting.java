package com.revature.backend.testing.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.revature.backend.model.api.ApiBatchTemplate;
import com.revature.backend.util.StagingListenerImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class StagingListenerTesting {

	@Autowired
	StagingListenerImpl stagingListener;

	@Test
	void testGetNewBatches() {
		stagingListener.checkForNewBatches();
		List<ApiBatchTemplate> batches = stagingListener.getLatestBatches();
		for (ApiBatchTemplate apiBatchTemplate : batches) {
			System.out.println(apiBatchTemplate.toString());
		}
		if (stagingListener.triggerUpdate()) {
			assertTrue(batches.size() > 0);
		} else {
			assertTrue(batches.size() == 0);
		}
	}

	@Test
	void testGetLatestBatches() {
		stagingListener.checkForNewBatches();
		List<ApiBatchTemplate> batches = stagingListener.getLatestBatches();
		if (stagingListener.triggerUpdate()) {
			assertTrue(batches.size() > 0);
		} else {
			assertTrue(batches.size() == 0);
		}
	}

	@Test
	void testGetLatestBatchesWithFalseBatchesInserted() {
		stagingListener.mockCheckForNewBatches(true);
		List<ApiBatchTemplate> batches = stagingListener.getLatestBatches();

		assertTrue(batches.size() > 0);

	}

	@Test
	void testGetLatestBatchesWithNoBatchesInserted() {
		stagingListener.mockCheckForNewBatches(false);
		List<ApiBatchTemplate> batches = stagingListener.getLatestBatches();
		assertTrue(batches.size() == 0);

	}

}
