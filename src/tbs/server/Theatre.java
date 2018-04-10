package tbs.server;

public class Theatre {

    private final String _ID;
    private int _Rows;
    private int _FloorArea;

    public String getID() {
        return _ID;
    }
    public int getRows() {
        return _Rows;
    }

    public Theatre(String ID, int Rows, int FloorArea) {
        _ID = ID;
        _Rows = Rows;
        _FloorArea = FloorArea;
    }

}


