package tbs.server;

import java.util.UUID;

public class Artist {

    private final String _Name;
    private final String _ArtistID;
    //private static int _ArtistIDTracker = 1;

    public Artist(String name) {
        _Name = name;
        _ArtistID = "ARTIST" + UUID.randomUUID();
        //_ArtistIDTracker++;
    }

    public String getName() {
        return _Name;
    }

    public String getArtistID() {
        return _ArtistID;
    }

    //Overridden methods for checking equality in objects
    @Override
    public boolean equals(Object obj) {
        Artist a = (Artist)obj;
        return _ArtistID.equals(a._ArtistID) || _Name.toLowerCase().equals(a._Name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return _Name.toLowerCase().hashCode();
    }
}
