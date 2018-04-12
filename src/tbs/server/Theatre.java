package tbs.server;

public class Theatre {

    private final String _TheatreID;
    private final int _Rows;
    private final int _FloorArea;

    public String getID() {
        return _TheatreID;
    }
    public int getRows() {
        return _Rows;
    }

    public Theatre(String ID, int Rows, int FloorArea) {
        _TheatreID = ID;
        _Rows = Rows;
        _FloorArea = FloorArea;
    }
    //Overridden methods for checking equality in objects
    @Override
    public boolean equals(Object obj) {
        Theatre theatre = (Theatre)obj;
        return _TheatreID.equals(theatre._TheatreID);
    }

    @Override
    public int hashCode() {
        return _TheatreID.toLowerCase().hashCode();
    }
}


