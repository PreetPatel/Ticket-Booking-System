package tbs.server;

public class Artist {

    String _Name;
    String _ID;

    public Artist(String name, String ID) {
        _Name = name;
        _ID = ID;
    }

    public Artist(String name) {
        _Name = name;
    }

    public String getName() {
        return _Name;
    }

    public String getID() {
        return _ID;
    }

    public boolean doesArtistExist() {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getName().equals(_Name)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesArtistExistByID() {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getID().equals(_ID)) {
                return true;
            }
        }
        return false;
    }


    public String addArtistToList() {

            int newArtistID;
            if (TBSServerImpl.getArtistList().isEmpty()) {
                newArtistID = 1;
            } else {
                newArtistID = (Integer.parseInt(TBSServerImpl.getArtistList().get(TBSServerImpl.getArtistList().size() - 1).getID()) + 1);
            }
            Artist newArtist = new Artist(_Name,Integer.toString(newArtistID));
            TBSServerImpl.getArtistList().add(newArtist);
            return newArtist.getID();

    }

}
