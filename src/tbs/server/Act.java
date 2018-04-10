package tbs.server;

public class Act {
    private String _Title;
    private String _ArtistID;
    private int _MinutesDuration;
    private String _ActID;
    private static int _ActIDTracker = 1;

    public Act(String title, String artistID, int minutesDuration) {
        _Title = title;
        _ArtistID = artistID;
        _MinutesDuration = minutesDuration;
        _ActID = "ACT" + _ActIDTracker;
        _ActIDTracker++;
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






}
