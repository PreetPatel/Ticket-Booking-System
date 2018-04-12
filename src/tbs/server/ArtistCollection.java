package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ArtistCollection {
    private HashSet<Artist> _ArtistCollection;

    public ArtistCollection() {
        _ArtistCollection = new HashSet<>();
    }

    public boolean add (Artist e) {
        return _ArtistCollection.add(e);
    }

    //Returns all artist IDs sorted in lexicographical order
    public List<String> getAllArtistIDs() {
        List<String> results = new ArrayList<>();
        for (Artist e: _ArtistCollection) {
                results.add(e.getArtistID());
        }
        Collections.sort(results);
        return results;
    }

    //Returns all artist names sorted in lexicographical order
    public List<String> getAllArtistNames() {
        List<String> results = new ArrayList<>();
        for (Artist e: _ArtistCollection) {
                results.add(e.getName());
        }
        Collections.sort(results);
        return results;
    }

    //Returns artist object that matches the artistID specified, else returns null if there is no match
    public Artist getArtistFromServer(String artistID) {
        for (Artist e: _ArtistCollection) {
            if (e.getArtistID().equals(artistID)) {
                return e;
            }
        }
        return null;
    }
}
