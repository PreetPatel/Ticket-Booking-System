package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class ActCollection {
    private List<Act> _ActCollection;

    public ActCollection() {
        _ActCollection = new ArrayList<>();
    }

    public List<Act> getActCollection() {
        return _ActCollection;
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

    public boolean doesSameActByArtistExist(String artistID, String title) {
            for (Act e : _ActCollection) {
                if (e.getArtistIDForAct().equals(artistID)) {
                    if (e.getTitle().equals(title)) {
                        return true;
                    }
                }
            }
            return false;
    }
}
