package tbs.server;

public class Act {
    private String _Title;
    private String _ArtistID;
    private int _MinutesDuration;
    private String _ActID;
    private static int _ActIDTracker = 1;

    public Act(String title, String artistID, int minutesDuration, String actID) {
        _Title = title;
        _ArtistID = artistID;
        _MinutesDuration = minutesDuration;
        _ActID = actID;
        _ActIDTracker++;
    }

    public Act(String actID) {
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

    public boolean equals(Act o) {
        return (o.getActID().equals(_ActID));
    }

    public boolean doesSameActByArtistExist() {
        for (Act e: TBSServerImpl.getActList()) {
            if (e.getArtistIDForAct().equals(_ArtistID)) {
                if (e.getTitle().equals(_Title)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean doesActExist() {
        for (Act e: TBSServerImpl.getActList()) {
            if (e.getActID().equals(_ActID)) {
                return true;
            }
        }
        return false;
    }

    public String addActToList() {

        String newActID;
        newActID = "ACT" + _ActIDTracker;
        this._ActID = newActID;
        TBSServerImpl.getActList().add(this);
        return this.getActID();

    }

}
