package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActCollection {
    private List<Act> _ActCollection;

    public ActCollection() {
        _ActCollection = new ArrayList<>();
    }

    public boolean add (Act e) {
        return _ActCollection.add(e);
    }

    public Act getActFromServer(String actID) {
        for (Act e: _ActCollection) {
            if (e.getActID().equals(actID)) {
                return e;
            }
        }
        return null;
    }

    public boolean doesActExistInServer(String artistID, String title) {
            for (Act e : _ActCollection) {
                if (e.getArtistForAct().getArtistID().equals(artistID)) {
                    if (e.getTitle().equals(title)) {
                        return true;
                    }
                }
            }
            return false;
    }
    //Loops though all acts and adds Act Ids for all acts that match the specified artistID
    public List<String> getActIdsForArtist(String artistID) {
        List<String> actIDsForArtist = new ArrayList<>();
        for(Act e: _ActCollection) {
            if (e.getArtistForAct().getArtistID().equals(artistID)) {
                actIDsForArtist.add(e.getActID());
            }
        }
        //Sorts the list by alphabetical order
        Collections.sort(actIDsForArtist);
        return actIDsForArtist;
    }
}
