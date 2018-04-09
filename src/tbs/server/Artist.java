package tbs.server;

public class Artist {

    private String _Name;
    private String _ArtistID;
    private static int _ArtistIDTracker = 1;

    public Artist(String name, String ID) {
        _Name = name;
        _ArtistID = ID;
        _ArtistIDTracker++;
    }

    public String getName() {
        return _Name;
    }

    public String getArtistID() {
        return _ArtistID;
    }

    public boolean doesArtistExist() {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getName().toLowerCase().equals(_Name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean doesNotExistByID() {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getArtistID().equals(_ArtistID)) {
                return false;
            }
        }
        return true;
    }


    public String addArtistToList() {
            String newArtistID = "ARTIST" + _ArtistIDTracker;
            this._ArtistID = newArtistID;
            TBSServerImpl.getArtistList().add(this);
            return _ArtistID;

    }

}
