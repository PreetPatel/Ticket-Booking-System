package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PerformanceCollection implements Iterable{
    private List<Performance> _PerformanceCollection;

    public  PerformanceCollection() {
        _PerformanceCollection = new ArrayList<>();
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

    public List<String> getPerformanceIDsForAct(String actID) {
        List<String> performanceIDsForAct = new ArrayList<>();
        //Loops through all performances in collection and adds any performance IDs with the matching act ID to the return
        // list
        for (Performance e: _PerformanceCollection) {
            if (e.getActID().equals(actID)) {
                performanceIDsForAct.add(e.getPerformanceID());
            }
        }
        Collections.sort(performanceIDsForAct);
        return performanceIDsForAct;
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

    @Override
    public Iterator<Performance> iterator() {
        Iterator<Performance> it = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < _PerformanceCollection.size() && _PerformanceCollection.get(currentIndex) != null;
            }

            @Override
            public Performance next() {
                return _PerformanceCollection.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
