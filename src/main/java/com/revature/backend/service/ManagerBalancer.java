/**
 * This class impelements the method(s) necessary to assign new batches of associates to
 * staging managers, and to ensure that this assignment is done as evenly as possible.
 *
 * @author Andrew Curry
 */
package com.revature.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.revature.backend.model.Associate;
import com.revature.backend.model.Batch;
import com.revature.backend.model.Manager;

import org.springframework.stereotype.Service;

@Service
public class ManagerBalancer {
    /**
     * This method takes the given associates and assigns them to staging
     * managers. It uses the following criteria:
     *  - associates from a given batch should all be assigned to the same manager
     *  - each manager should end up with a (roughly) equivalent amount of associates.
     *
     * There is no return value; the Associate objects will be updated directly with their
     * assignments.
     *
     * @param managerMap : should map each Staging Manager to the number of associates
     * they already have assigned to them.
     * @param newAssociates : should be a list of new, unassigned associates. Each
     * of these associates should be in one of the given batches.
     */
    public void balanceNewBatches(
        Map<Manager, Integer> managerMap, List<Associate> newAssociates) {

        Associate[][] batches = groupIntoBatches(newAssociates);
        sortBatchesBySize(batches);
        assignAssociatesEvenly(managerMap, batches);
    }

    // ----------
    // HELPER METHODS
    // ----------

    /**
     * Splits a List of associates into batches.
     *
     * @param associates
     * @return a 2-dimensional array of Associates, where each sub-array contains
     *         all of, and only, the associates from a single batch
     */
    public Associate[][] groupIntoBatches(List<Associate> associates) {
        // maybe this map should have been the end results rather than a 2d array...
        Map<Batch, List<Associate>> batchMap = new HashMap<>();

        for (Associate a : associates){
            Batch b = a.getBatch();
            List<Associate> batchList = batchMap.get(b);
            if (batchList == null) batchList = new ArrayList<>();
            batchList.add(a);
            batchMap.put(b, batchList);
        }

        // now put this data into a 2D array
        Set<Batch> batchSet = batchMap.keySet();
        Associate[][] result = new Associate[batchSet.size()][]; // not the best name...

        int i = 0; // clumsy but it should work
        for (Batch b : batchSet){
            List<Associate> associateList = batchMap.get(b);
            Associate[] associateArray = new Associate[associateList.size()];
            associateArray = associateList.toArray(associateArray);
            result[i] = associateArray;
            i += 1;
        }

        return result;
    }

    /**
     * Sorts the given 2D array of batches in decreasing order by size
     * (largest array to smallest array).
     *
     * @param batches
     */
    public void sortBatchesBySize(Associate[][] batches) {
        Arrays.sort(batches, new java.util.Comparator<Associate[]>() {
            public int compare(Associate[] a, Associate[] b) {
                return Integer.compare(b.length, a.length);
            }
        });
    }

    /**
     * Assigns batches in order, always assigning to the manager with the currently
     * smallest amount of associates.
     *
     * @param managerMap : associate counts WILL be updated
     * @param batches : associate objects WILL be updated
     */
    public void assignAssociatesEvenly(
        Map<Manager, Integer> managerMap, Associate[][] batches){
        for (Associate[] batch : batches){
            Manager managerWithLeast = findManagerWithLeast(managerMap);
            // update this manager's count to reflect this new batch
            int oldCount = managerMap.get(managerWithLeast);
            managerMap.put(managerWithLeast, oldCount + batch.length);

            // update each associate with the assignment
            for (Associate associate : batch){
                associate.setManager(managerWithLeast);
            } // end inner for loop (only current batch)
        } // end outer for loop (all batches)
    }

    /**
     * Searches the given map of managers/integers for the manager key with the smallest
     * Integer value (that is, the manager with the least assigned associates).
     *
     * If there is a tie, the first tied Manager in the map's key set will be returned.
     * Given an empty map, returns null
     *
     * @param managerMap
     * @return
     */
    public Manager findManagerWithLeast(Map<Manager, Integer> managerMap) {
        Manager currentManagerWithLeast = null;
        int currentLeastAssociates = -1; // flagged to always accept first manager's count

        for (Manager manager : managerMap.keySet()){
            int numAssociates = managerMap.get(manager);
            if (numAssociates < currentLeastAssociates || currentLeastAssociates == -1)
            {
                currentLeastAssociates = numAssociates;
                currentManagerWithLeast = manager;
            }
        }

        return currentManagerWithLeast;
    }
}
