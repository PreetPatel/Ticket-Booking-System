package tbs.server;

import java.util.ArrayList;

public class Ticket extends Performance {
    private String _TicketID;
    private String _Price;
    private int _Row;
    private  int _Position;
    private static int _TickerIDTracker = 1;


    public Ticket(String performanceID, int row, int position, String ticketID) throws NullPointerException {
        super(performanceID);
        _Row = row;
        _Position = position;
        _TicketID = ticketID;
        if (_Row <= (getTheatre().getRows() / 2)) {
            this._Price = getPremiumPrice();
        } else {
            this._Price = getCheapPrice();
        }

    }

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

    public boolean isTicketAvailable() {
        for (Ticket e: TBSServerImpl.getTicketList()) {
            if ((e.getPosition() == _Position) && (e.getRow() == _Row) && (e.getPerformanceID().equals(this.getPerformanceID()))) {
                return false;
            }
        }
        return true;
    }

    public String addTicketToList() {
        String newTicketID = "TICKET" + _TickerIDTracker;
        _TickerIDTracker++;
        this._TicketID = newTicketID;
        TBSServerImpl.getTicketList().add(this);
        return this.getTicketID();

    }

    public static ArrayList<Ticket> getTicketsForPerformance(String performanceID) {
        ArrayList<Ticket> ticketsForPerformance = new ArrayList<>();
        for (Ticket e: TBSServerImpl.getTicketList()) {
            if(e.getPerformanceID().equals(performanceID)) {
                ticketsForPerformance.add(e);
            }
        }
        return ticketsForPerformance;
    }

    public  boolean isSeatValid() {
        int maxRows = this.getTheatre().getRows();
        return _Row <= maxRows && _Position <= maxRows && _Row > 0 && _Position > 0;

    }
}
