package com.revature.backend.util;

import java.util.List;

import com.revature.backend.model.Associate;
import com.revature.backend.model.Batch;

public interface BatchWriter {
    public List<Batch> writeNewlyStagingBatches(List<Batch> bList);
    public List<Associate> writeNewlyStagingAssociates(List<Associate> aList);
}
