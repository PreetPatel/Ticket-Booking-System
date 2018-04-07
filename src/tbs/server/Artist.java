package tbs.server;

public class Artist extends TBSServerImpl {//implements Comparable<Artist> {

    String _Name;
    String _ID;

    public Artist(String name, String ID) {
        _Name = name;
        _ID = ID;
    }

    public String getName() {
        return _Name;
    }

    public String getID() {
        return _ID;
    }

    public static boolean doesArtistExist(String name) {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesArtistExistByID(String ID) {
        for (Artist e: TBSServerImpl.getArtistList()) {
            if (e.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }


    public static String addArtistToList(String name) {

            int newArtistID;
            if (TBSServerImpl.getArtistList().isEmpty()) {
                newArtistID = 1;
            } else {
                newArtistID = (Integer.parseInt(TBSServerImpl.getArtistList().get(TBSServerImpl.getArtistList().size() - 1).getID()) + 1);
            }
            Artist newArtist = new Artist(name,Integer.toString(newArtistID));
            TBSServerImpl.getArtistList().add(newArtist);
            return newArtist.getID();

    }

}
