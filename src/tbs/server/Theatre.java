package tbs.server;

public class Theatre implements Comparable<Theatre>{

    private String _ID;
    private int _Rows;
    private int _FloorArea;

    public Theatre(String ID, int Rows, int FloorArea) {
        _ID = ID;
        _Rows = Rows;
        _FloorArea = FloorArea;
    }

    public Theatre(String ID) {
        _ID = ID;
    }

    public int compareTo(Theatre o) {
        if (_ID.compareTo(o._ID) < 1) {
            return -1;
        } else if (_ID.compareTo(o._ID) > 1) {
            return 1;
        } else {
            return 0;
        }
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



    public Theatre getTheatre() {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(_ID)) {
                return e;
            }
        }
        return null;
    }
}


