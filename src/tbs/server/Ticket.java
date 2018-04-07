package tbs.server;

import java.util.ArrayList;

public class Ticket {
    private String _TicketID;
    private String _PerformanceID;
    private String _Price;
    private int _Row;
    private  int _Position;


    public Ticket(String performanceID, String price, int row, int position, String ticketID) {
        _PerformanceID = performanceID;
        _Price = price;
        _Row = row;
        _Position = position;
        _TicketID = ticketID;
    }

    public int getRow() {
        return _Row;
    }

    public  int getPosition() {
        return _Position;
    }

    public String getPerformanceID() {
        return _PerformanceID;
    }

    public String getTicketPrice() {
        return _Price;
    }
    public String getTicketID() {
        return _TicketID;
    }

    public static boolean isTicketAvailable(int row, int position, String performanceID) {
        for (Ticket e: TBSServerImpl.getTicketList()) {
            if ((e.getPosition() == position) && (e.getRow() == row) && (e.getPerformanceID().equals(performanceID))) {
                return false;
            }
        }
        return true;
    }

    public static String addTicketToList(String performanceID, String price, int rowNumber,int seatNumber) {

        int newTicketID;
        if (TBSServerImpl.getTicketList().isEmpty()) {
            newTicketID = 1;
        } else {
            newTicketID = (Integer.parseInt(TBSServerImpl.getTicketList().get(TBSServerImpl.getTicketList().size() - 1).getTicketID()) + 1);
        }
        Ticket newTicket = new Ticket(performanceID, price, rowNumber, seatNumber, Integer.toString(newTicketID));
        TBSServerImpl.getTicketList().add(newTicket);
        return newTicket.getTicketID();

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
}
