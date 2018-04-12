package tbs.server;

public class Act {
    private String _Title;
    private Artist _Artist;
    private int _MinutesDuration;
    private String _ActID;
    private static int _ActIDTracker = 1;

    public Act(String title, Artist artist, int minutesDuration) {
        _Title = title;
        _Artist = artist;
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
    public Artist getArtistForAct() {
        return _Artist;
    }








}
