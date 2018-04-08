package tbs.server;

import java.util.ArrayList;

public class Ticket extends Performance{
    private String _TicketID;
    private String _Price;
    private int _Row;
    private  int _Position;
    private String _PerformanceID;


    public Ticket(String performanceID, String price, int row, int position, String ticketID) {
        super(performanceID);
        _Price = price;
        _Row = row;
        _Position = position;
        _TicketID = ticketID;
        _PerformanceID = performanceID;
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

    public String calculatePrice() {
        if (_Row <= (this.getTheatre().getRows()/2)) {
            this._Price = this.getPremiumPrice();
            return this.getPremiumPrice();
        } else {
            this._Price = this.getCheapPrice();
            return this.getCheapPrice();
        }
    }

    public boolean isTicketAvailable() {
        for (Ticket e: TBSServerImpl.getTicketList()) {
            if ((e.getPosition() == _Position) && (e.getRow() == _Row) && (e.getPerformanceID().equals(_PerformanceID))) {
                return false;
            }
        }
        return true;
    }

    public String addTicketToList() {

        int newTicketID;
        if (TBSServerImpl.getTicketList().isEmpty()) {
            newTicketID = 1;
        } else {
            newTicketID = (Integer.parseInt(TBSServerImpl.getTicketList().get(TBSServerImpl.getTicketList().size() - 1).getTicketID()) + 1);
        }
        this._TicketID =  Integer.toString(newTicketID);
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
        return _Row <= maxRows && _Position <= maxRows;

    }
}
