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
        if (_Row <= (_Performance.getTheatreRows() / 2)) {
            _Price = _Performance.getPremiumPrice();
        } else {
            _Price = _Performance.getCheapPrice();
        }
    }
    //Checks to see if the row and position specified for the ticket actually exist by comparing them to the maximum
    // row size for the theatre
    public  boolean isSeatValid() {
        int maxRows = _Performance.getTheatreRows();
        return _Row <= maxRows && _Position <= maxRows && _Row > 0 && _Position > 0;
    }

    //Overridden methods for checking equality in objects
    @Override
    public boolean equals(Object obj) {
        Ticket a = (Ticket) obj;
        return (_Position == a.getPosition()) && (_Row == a.getRow()) && (_Performance.getPerformanceID().equals(a.getPerformance().getPerformanceID()));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
