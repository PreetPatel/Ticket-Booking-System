package tbs.server;

import java.util.HashSet;

public class ArtistCollection {
    private HashSet<Artist> _ArtistCollection;

    public ArtistCollection() {
        _ArtistCollection = new HashSet<>();
    }

    public HashSet<Artist> getArtistCollection() {
        return _ArtistCollection;
    }

    public boolean add (Artist e) {
        return _ArtistCollection.add(e);
    }

    public boolean doesNotExistInServer(String artistID) {
        for (Artist e: _ArtistCollection) {
            if (e.getArtistID().equals(artistID)) {
                return false;
            }
        }
        return true;
    }
}
