package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class PerformanceCollection {
    private List<Performance> _PerformanceCollection;

    public  PerformanceCollection() {
        _PerformanceCollection = new ArrayList<>();
    }

    public List<Performance> getPerformanceCollection() {
        return _PerformanceCollection;
    }

    public boolean add (Performance e) {
        return _PerformanceCollection.add(e);
    }

    public ArrayList<Performance> getPerformancesForAct(String actID) {
        ArrayList<Performance> performancesForAct = new ArrayList<>();
        //Loops through all performances in collection and adds any performance with the matching act ID to the return
        // list
        for (Performance e: _PerformanceCollection) {
            if (e.getActID().equals(actID)) {
                performancesForAct.add(e);
            }
        }
        return performancesForAct;
    }

    //Returns a specific performance from the performance collection
    public Performance getPerformanceFromServer(String performanceID) {
        for (Performance e: _PerformanceCollection) {
            if (e.getPerformanceID().equals(performanceID)) {
                return e;
            }
        }
        return null;
    }

    //Checks if a performance exists already in the collection list
    public boolean DoesPerformanceExist(Performance o) {
        for (Performance e: getPerformancesForAct(o.getActID())) {
            if (e.getTheatreID().equals(o.getTheatreID()) && e.getStartTime().equals(o.getStartTime())) {
                return true;
            }
        }
        return false;
    }
}
