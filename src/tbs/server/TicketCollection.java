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

    //Loops through all tickets in the collection and returns a tickets list for a performanceID
    public ArrayList<Ticket> getTicketsForPerformance(String performanceID) {
        ArrayList<Ticket> ticketsForPerformance = new ArrayList<>();
        for (Ticket e: _TicketCollection) {
            if(e.getPerformance().getPerformanceID().equals(performanceID)) {
                ticketsForPerformance.add(e);
            }
        }
        return ticketsForPerformance;
    }

    //Loops through all tickets in the collection and increments the first element by the price of the ticket and the
    // second element is incremented as a counter for the number of tickets
    public String getTicketSalesForPerformance(String performanceID) {
        //Integer array used to store the values
        int[] ticketSales = {0,0};

        for (Ticket e: getTicketsForPerformance(performanceID)) {
            ticketSales[0] += Integer.parseInt(e.getTicketPrice().substring(1));
            ticketSales[1]++;
        }

        //Return correctly formatted string
        return ticketSales[1] + "\t" + "$" + ticketSales[0];
    }

    //Loops through all tickets to check if it includes the new ticket and returns false if it does which means that
    // there is a ticket already purchased for that seat. Else returns true.
    public boolean isAvailable(Ticket checkTicket) {
        for (Ticket e: _TicketCollection) {
            if (e.equals(checkTicket)) {
                return false;
            }
        }
        return true;
    }



}
