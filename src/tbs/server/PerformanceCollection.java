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
        for (Performance e: _PerformanceCollection) {
            if (e.getActID().equals(actID)) {
                performancesForAct.add(e);
            }
        }
        return performancesForAct;
    }

    public Performance getPerformanceFromServer(String performanceID) {
        for (Performance e: _PerformanceCollection) {
            if (e.getPerformanceID().equals(performanceID)) {
                return e;
            }
        }
        return null;
    }
}
