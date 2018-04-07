package tbs.server;

import java.util.ArrayList;

public class Theatre implements Comparable<Theatre>{

    private String _ID;
    private int _Rows;
    private int _FloorArea;
    //private ArrayList<Seat> _Seats = new ArrayList<>();

    public Theatre(String ID, int Rows, int FloorArea) {
        _ID = ID;
        _Rows = Rows;
        _FloorArea = FloorArea;
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
    /*public ArrayList<Seat> getSeatsList() {
        return _Seats;
    }*/

    public static boolean doesTheatreExist(String ID) {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static Theatre getTheatre(String ID) {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(ID)) {
                return e;
            }
        }
        return null;
    }
}


