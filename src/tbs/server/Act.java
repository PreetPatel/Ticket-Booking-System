package tbs.server;

public class Act {
    private String _Title;
    private String _ArtistID;
    private int _MinutesDuration;
    private String _ActID;

    public Act(String title, String artistID, int minutesDuration, String actID) {
        _Title = title;
        _ArtistID = artistID;
        _MinutesDuration = minutesDuration;
        _ActID = actID;
    }

    public String getTitle() {
        return _Title;
    }
    public String getActID() {
        return _ActID;
    }
    public String getArtistIDForAct() {
        return _ArtistID;
    }
    public int getDurationOfAct() {
        return _MinutesDuration;
    }

    public static boolean doesSameActByArtistExist(String title, String artistID) {
        for (Act e: TBSServerImpl.getActList()) {
            if (e.getArtistIDForAct().equals(artistID)) {
                if (e.getTitle().equals(title)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean doesActExist(String ID) {
        for (Act e: TBSServerImpl.getActList()) {
            if (e.getActID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static String addActToList(String title, String artistID, int minutesDuration) {

        int newActID;
        if (TBSServerImpl.getActList().isEmpty()) {
            newActID = 1;
        } else {
            newActID = (Integer.parseInt(TBSServerImpl.getActList().get(TBSServerImpl.getActList().size() - 1).getActID()) + 1);
        }

        Act newAct = new Act(title,artistID,minutesDuration,Integer.toString(newActID));
        TBSServerImpl.getActList().add(newAct);
        return newAct.getActID();

    }

}
