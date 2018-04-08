package tbs.server;

public class Theatre implements Comparable<Theatre>{

    private final String _ID;
    private int _Rows;
    private int _FloorArea;

    public Theatre(String ID, int Rows, int FloorArea) {
        _ID = ID;
        _Rows = Rows;
        _FloorArea = FloorArea;
    }

    public int compareTo(Theatre o) {
       return _ID.compareTo(o._ID);
    }

    public String getID() {
        return _ID;
    }
    public int getRows() {
        return _Rows;
    }
    public int getFloorArea() {
        return _FloorArea;
    }

    public boolean doesTheatreExist() {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(_ID)) {
                return true;
            }
        }
        return false;
    }
}


