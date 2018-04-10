package tbs.server;

public class Ticket {
    private String _TicketID;
    private Performance _Performance;
    private String _Price;
    private int _Row;
    private  int _Position;
    private static int _TickerIDTracker = 1;

    public int getRow() {
        return _Row;
    }

    public  int getPosition() {
        return _Position;
    }

    public String getTicketPrice() {
        return _Price;
    }
    public String getTicketID() {
        return _TicketID;
    }
    public Performance getPerformance() {
        return _Performance;
    }

    public Ticket(Performance performance, int row, int position) throws NullPointerException {
        _Performance = performance;
        _Row = row;
        _Position = position;
        _TicketID = "TICKET" + _TickerIDTracker;
        _TickerIDTracker++;
        if (_Row <= (_Performance.getTheatre().getRows() / 2)) {
            _Price = _Performance.getPremiumPrice();
        } else {
            _Price = _Performance.getCheapPrice();
        }
    }

    public  boolean isSeatValid() {
        int maxRows = _Performance.getTheatre().getRows();
        return _Row <= maxRows && _Position <= maxRows && _Row > 0 && _Position > 0;
    }
}
