package tbs.server;

import java.util.UUID;

public class Act {
    private final String _Title;
    private final Artist _Artist;
    private final int _MinutesDuration;
    private final String _ActID;
   // private static int _ActIDTracker = 1;

    public Act(String title, Artist artist, int minutesDuration) {
        _Title = title;
        _Artist = artist;
        _MinutesDuration = minutesDuration;
        _ActID = "ACT" + UUID.randomUUID();
        //_ActIDTracker++;
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
