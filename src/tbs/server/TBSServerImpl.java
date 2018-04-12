package tbs.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TBSServerImpl implements TBSServer {

    private TheatreCollection theatreCollection = new TheatreCollection();
    private ArtistCollection artistCollection = new ArtistCollection();
    private ActCollection actCollection = new ActCollection();
    private PerformanceCollection performanceCollection = new PerformanceCollection();
    private TicketCollection ticketCollection = new TicketCollection();

    @Override
    public List<String> getActIDsForArtist(String artistID) {
        List<String> actIDforArtist = new ArrayList<>();
        //If there is no artist for the specified ID, the getArtistFromServer method returns null
        if (artistCollection.getArtistFromServer(artistID) == null) {
            actIDforArtist.add("ERROR Artist does not exist");
            return actIDforArtist;
        } else {
          return  actCollection.getActIdsForArtist(artistID);
        }
    }

    @Override
    public List<String> dump() {
        return new ArrayList<>();
    }

    @Override
    public List<String> getArtistIDs() {
        return artistCollection.getAllArtistIDs();
    }

    @Override
    public List<String> getArtistNames() {
        return artistCollection.getAllArtistNames();
    }

    @Override
    public List<String> getPeformanceIDsForAct(String actID) {
        ArrayList<String> performanceIDs = new ArrayList<>();
        if (actID == null || actID.trim().isEmpty()) {
            performanceIDs.add("ERROR Invalid Act ID");
        } else if (actCollection.getActFromServer(actID) == null) {
            performanceIDs.add("ERROR Act ID does not exist");
        } else {
            //Iterate through all performances and get all performanceIDs for performances with the input actID
            for (Performance e: performanceCollection.getPerformanceCollection()) {
                if (e.getActID().equals(actID)) {
                    performanceIDs.add(e.getPerformanceID());
                }
            }
        }
        Collections.sort(performanceIDs);
        return performanceIDs;
    }

    @Override
    public List<String> getTheatreIDs() {
        List<String> result = new ArrayList<>();
        //Iterate through the theatre collection and add Theatre IDs to the String List
        for (Theatre e: theatreCollection.getTheatreCollection()) {
            result.add(e.getID());
        }
        Collections.sort(result);
        return result;
    }

    @Override
    public List<String> getTicketIDsForPerformance(String performanceID) {
        List<String> TicketIDsForPerformance = new ArrayList<>();
        if (performanceCollection.getPerformanceFromServer(performanceID) == null) {
            TicketIDsForPerformance.add("ERROR Performance ID does not exist");
        } else {
            //Iterate through the ticket collection and get ticket IDs for all tickets that are created for the performance
            for (Ticket e : ticketCollection.getTicketCollection()) {
                if (e.getPerformance().getPerformanceID().equals(performanceID)) {
                    TicketIDsForPerformance.add(e.getTicketID());
                }
            }
        }
        Collections.sort(TicketIDsForPerformance);
        return TicketIDsForPerformance;
    }

    @Override
    public List<String> salesReport(String actID) {
        ArrayList<String> salesReport = new ArrayList<>();
        if (actCollection.getActFromServer(actID) == null) {
            salesReport.add("ERROR Act does not exist");
        } else {
            for (Performance e : performanceCollection.getPerformancesForAct(actID)) {
                //Iterate through all performances for the actID and get number of ticket sales and total revenue
                String ticketSales = ticketCollection.getTicketSalesForPerformance(e.getPerformanceID());
                //Append the Performance ID and start time for the performance to the sales Report string
                ticketSales = e.getPerformanceID() + "\t" + e.getStartTime() + "\t" + ticketSales;
                salesReport.add(ticketSales);
            }
        }
        return salesReport;
    }

    @Override
    public List<String> seatsAvailable(String performanceID) {
        List<String> seatsAvailable = new ArrayList<>();
        Performance checkPerformance = performanceCollection.getPerformanceFromServer(performanceID);
        if (checkPerformance != null) {
            //Iterate through all possible seats for a given performance
            int rows = checkPerformance.getTheatreRows();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= rows; j++) {
                    Ticket checkTicket = new Ticket(performanceCollection.getPerformanceFromServer(performanceID),i,j);
                    //Check if the seat for the temporary created ticket is available and add to the list of available
                    //seats if it is
                    if (ticketCollection.isAvailable(checkTicket)) {
                        seatsAvailable.add(i + "\t" + j);
                    }
                }
            }
        } else {
            seatsAvailable.add("ERROR Performance does not exist");
        }
        return seatsAvailable;
    }

    @Override
    public String addAct(String title, String artistID, int minutesDuration) {
        //Error checking for input parameters or if the artist exists in the system
        if (artistID == null) {
            return "ERROR Invalid ArtistID";
        } else if (artistCollection.getArtistFromServer(artistID) == null) {
            return "ERROR Artist does not exist";
        } else if (minutesDuration <= 0) {
            return "ERROR Invalid format for act duration";
        } else if (title == null || title.trim().isEmpty()) {
            return "ERROR: Title is invalid";
        } else if (actCollection.doesActExistInServer(artistID,title)) {
            return "ERROR Act by this artist already exists";
        } else {
            //Create an act object and add it to the act collection
            Act newAct = new Act(title, artistCollection.getArtistFromServer(artistID),minutesDuration);
            actCollection.add(newAct);
            return newAct.getActID();
        }
    }

    @Override
    public String addArtist(String name) {
        Artist newArtist = new Artist(name);
        if (name == null || name.trim().isEmpty()) {
            return "ERROR Invalid Name";
        } else {
            if(artistCollection.add(newArtist)) {
                return newArtist.getArtistID();
            } else {
                return "ERROR This Artist Already exists";
            }
        }
    }

    @Override
    public String initialise(String path) {
       return theatreCollection.addTheatreFromPath(path);
    }

    @Override
    public String issueTicket(String performanceID, int rowNumber, int seatNumber) {

            //Check if the performance exists in the server
            if (performanceCollection.getPerformanceFromServer(performanceID) == null) {
                return "ERROR Performance does not exist";
            }
            //Create a new ticket object and check its validity and if it is available for sale
            Ticket newTicket = new Ticket(performanceCollection.getPerformanceFromServer(performanceID), rowNumber, seatNumber);
            if (!newTicket.isSeatValid()) {
                return "ERROR Seat is not valid";
            } else if (!ticketCollection.isAvailable(newTicket)) {
                return "ERROR Seat is not available";
            } else {
                ticketCollection.add(newTicket);
                return newTicket.getTicketID();
            }
    }

    @Override
    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {

       try {
           //Check if the act and theatre exist for the performance
           if (actCollection.getActFromServer(actID) == null) {
               return "ERROR Act with the specified Act ID does not exist.";
           } else if (theatreCollection.getTheatreFromServer(theatreID) == null) {
               return "ERROR Theatre does not exist";
           }
           //Create a new performance object and validate all of its inputs such as time, price, etc..
           Theatre theatre = theatreCollection.getTheatreFromServer(theatreID);
           Performance newPerformance = new Performance(actID,theatre,startTimeStr,premiumPriceStr,cheapSeatsStr);
           if (!newPerformance.checkDate()) {
               return "ERROR Date is in the wrong format. Format expected(IS0 8601): yyyy-mm-ddThh:mm";
           } else if (!newPerformance.checkPrices()) {
               return "ERROR Prices are not in correct format.";
           } else if (performanceCollection.DoesPerformanceExist(newPerformance)){
               return "ERROR Performance already exists";
           } else {
               performanceCollection.add(newPerformance);
               return newPerformance.getPerformanceID();
           }
           //The catch statement ensures to report an error if any of the input parameters are null
       } catch (NullPointerException e) {
           return "ERROR An input field is null";
       }
    }
}


