package tbs.server;

import java.util.ArrayList;
import java.util.List;

public class TicketCollection {
    private  List<Ticket>  _TicketCollection;

    public TicketCollection() {
        _TicketCollection = new ArrayList<>();
    }

    public List<Ticket> getTicketCollection() {
        return _TicketCollection;
    }

    public boolean add(Ticket e) {
       return _TicketCollection.add(e);
    }

    public boolean isTicketAvailable(Ticket checkTicket) {
        for (Ticket e: _TicketCollection) {
            if ((e.getPosition() == checkTicket.getPosition()) && (e.getRow() == checkTicket.getRow()) && (e.getPerformance().getPerformanceID().equals(checkTicket.getPerformance().getPerformanceID()))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Ticket> getTicketsForPerformance(String performanceID) {
        ArrayList<Ticket> ticketsForPerformance = new ArrayList<>();
        for (Ticket e: _TicketCollection) {
            if(e.getPerformance().getPerformanceID().equals(performanceID)) {
                ticketsForPerformance.add(e);
            }
        }
        return ticketsForPerformance;
    }

    public int[] getTotalSalesReport(String performanceID) {
        int[] ticketSales = {0,0};

        for (Ticket e: getTicketsForPerformance(performanceID)) {
            ticketSales[0] += Integer.parseInt(e.getTicketPrice().substring(1));
            ticketSales[1]++;
        }
        return ticketSales;
    }


}
