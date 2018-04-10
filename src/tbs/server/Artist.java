package tbs.server;

public class Artist {

    private String _Name;
    private String _ArtistID;
    private static int _ArtistIDTracker = 1;

    public Artist(String name) {
        _Name = name;
        _ArtistID = "ARTIST" + _ArtistIDTracker;
        _ArtistIDTracker++;
    }

    public String getName() {
        return _Name;
    }

    public String getArtistID() {
        return _ArtistID;
    }

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
