package tbs.server;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TBSServerImpl implements TBSServer {

    private TheatreCollection Theatres = new TheatreCollection();
    private ArtistCollection Artists = new ArtistCollection();
    private ActCollection Acts = new ActCollection();
    private PerformanceCollection Performances = new PerformanceCollection();
    private TicketCollection Tickets = new TicketCollection();

    @Override
    public List<String> getActIDsForArtist(String artistID) {
        List<String> actIDforArtist = new ArrayList<>();
        if (Artists.doesNotExistInServer(artistID)) {
            actIDforArtist.add("ERROR Artist does not exist");
        } else {
            for(Act e: Acts.getActCollection()) {
                if (e.getArtistIDForAct().equals(artistID)) {
                    actIDforArtist.add(e.getActID());
                }
            }
        }
        return actIDforArtist;
    }

    @Override
    public List<String> dump() {
        List<String> dump = new ArrayList<>();
       return dump;
    }

    @Override
    public List<String> getArtistIDs() {
        List<String> results = new ArrayList<>();
        for (Artist e: Artists.getArtistCollection()) {
            results.add(e.getArtistID());
        }
        Collections.sort(results);
        return results;
    }

    @Override
    public List<String> getArtistNames() {
        List<String> results = new ArrayList<>();
        for (Artist e: Artists.getArtistCollection()) {
            results.add(e.getName());
        }
        Collections.sort(results);
        return results;
    }

    @Override
    public List<String> getPeformanceIDsForAct(String actID) {
        ArrayList<String> performanceIDs = new ArrayList<>();
        if (actID == null || actID.trim().isEmpty()) {
            performanceIDs.add("ERROR Invalid Act ID");
        } else if (Acts.getActFromServer(actID) == null) {
            performanceIDs.add("ERROR Act ID does not exist");
        } else {
            for (Performance e: Performances.getPerformanceCollection()) {
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
        for (Theatre e: Theatres.getTheatreCollection()) {
            result.add(e.getID());
        }
        Collections.sort(result);
        return result;
    }

    @Override
    public List<String> getTicketIDsForPerformance(String performanceID) {
        List<String> TicketIDsForPerformance = new ArrayList<>();
        if (Performances.getPerformanceFromServer(performanceID) == null) {
            TicketIDsForPerformance.add("ERROR Performance ID does not exist");
        } else {
            for (Ticket e : Tickets.getTicketCollection()) {
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
        if (Acts.getActFromServer(actID) == null) {
            salesReport.add("ERROR Act does not exist");
        } else {
            for (Performance e : Performances.getPerformancesForAct(actID)) {
                int[] sales = Tickets.getTotalSalesReport(e.getPerformanceID());
                salesReport.add(e.getPerformanceID() + "\t" + e.getStartTime() + "\t" + sales[1] + "\t" + "$" + sales[0]);
            }
        }
        return salesReport;
    }

    @Override
    public List<String> seatsAvailable(String performanceID) {
        List<String> seatsAvailable = new ArrayList<>();
        Performance checkPerformance = Performances.getPerformanceFromServer(performanceID);
        if (checkPerformance != null) {
            int rows = checkPerformance.getTheatre().getRows();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= rows; j++) {
                    Ticket checkTicket = new Ticket(Performances.getPerformanceFromServer(performanceID),i,j);
                    if (Tickets.isTicketAvailable(checkTicket)) {
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

        if (artistID == null) {
            return "ERROR Invalid ArtistID";
        } else if (Artists.doesNotExistInServer(artistID)) {
            return "ERROR Artist does not exist";
        } else if (minutesDuration <= 0) {
            return "ERROR Invalid format for act duration";
        } else if (title == null || title.trim().isEmpty()) {
            return "ERROR: Title is invalid";
        } else if (Acts.doesSameActByArtistExist(artistID,title)) {
            return "ERROR Act by this artist already exists";
        } else {
            Act newAct = new Act(title,artistID,minutesDuration);
            Acts.add(newAct);
            return newAct.getActID();
        }
    }

    @Override
    public String addArtist(String name) {
        Artist newArtist = new Artist(name);
        if (name == null || name.trim().isEmpty()) {
            return "ERROR Invalid Name";
        } else {
            if(Artists.add(newArtist)) {
                return newArtist.getArtistID();
            } else {
                return "ERROR This Artist Already exists";
            }
        }

    }

    @Override
    public String initialise(String path) {
        File file = new File(path);
       return Theatres.addTheatreFromFile(file);
    }

    @Override
    public String issueTicket(String performanceID, int rowNumber, int seatNumber) {

            if (Performances.getPerformanceFromServer(performanceID) == null) {
                return "ERROR Performance does not exist";
            }
            Ticket newTicket = new Ticket(Performances.getPerformanceFromServer(performanceID), rowNumber, seatNumber);
            if (!newTicket.isSeatValid()) {
                return "ERROR Seat is not valid";
            } else if (!Tickets.isTicketAvailable(newTicket)) {
                return "ERROR Seat is not available";
            } else {
                Tickets.add(newTicket);
                return newTicket.getTicketID();
            }
    }

    @Override
    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {

       try {
           if (Acts.getActFromServer(actID) == null) {
               return "ERROR Act with the specified Act ID does not exist.";
           } else if (Theatres.getTheatreFromServer(theatreID) == null) {
               return "ERROR Theatre does not exist";
           }
           Theatre theatre = Theatres.getTheatreFromServer(theatreID);
           Performance newPerformance = new Performance(actID,theatre,startTimeStr,premiumPriceStr,cheapSeatsStr);
           if (!newPerformance.checkDate()) {
               return "ERROR Date is in the wrong format. Format expected(IS0 8601): yyyy-mm-ddThh:mm";
           } else if (!newPerformance.checkPrices()) {
               return "ERROR Prices are not in correct format.";
           } else {
               Performances.add(newPerformance);
               return newPerformance.getPerformanceID();
           }
       } catch (NullPointerException e) {
           return "ERROR An input field is null";
       }
    }
}


