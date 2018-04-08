package tbs.server;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TBSServerImpl implements TBSServer {
    private static List<Theatre> Theatres = new ArrayList<>();
    private static List<Artist> Artists = new ArrayList<>();
    private static List<Act> Acts = new ArrayList<>();
    private static List<Performance> Performances = new ArrayList<>();
    private static List<Ticket> Tickets = new ArrayList<>();


    public static List<Artist> getArtistList() {
        return Artists;
    }
    public static List<Act> getActList() {
        return Acts;
    }
    public static List<Performance> getPerformanceList() {
        return Performances;
    }
    public static List<Theatre> getTheatreList() {
        return Theatres;
    }
    public static List<Ticket> getTicketList() {
        return Tickets;
    }

    @Override
    public List<String> getActIDsForArtist(String artistID) {
        List<String> result = new ArrayList<>();
        if (!new Artist(null,artistID).doesArtistExistByID()) {
            result.add("ERROR Artist does not exist");
            return result;
        } else {
            for(Act e: Acts) {
                if (e.getArtistIDForAct().equals(artistID)) {
                    result.add(e.getActID());
                }
            }
            return result;
        }
    }

    @Override
    public List<String> dump() {
        return null;

    }

    @Override
    public List<String> getArtistIDs() {
        List<String> results = new ArrayList<>();
        for (Artist e: Artists) {
            results.add(e.getID());
        }
        Collections.sort(results);
        return results;
    }

    @Override
    public List<String> getArtistNames() {
        List<String> results = new ArrayList<>();
        for (Artist e: Artists) {
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
        } else if (!new Act(actID).doesActExist()) {
            performanceIDs.add("ERROR Act ID does not exist");
        } else {
            for (Performance e:Performances) {
                if (e.getActID().equals(actID)) {
                    performanceIDs.add(e.getPerformanceID());
                }
            }
        }
        return performanceIDs;
    }

    @Override
    public List<String> getTheatreIDs() {
        List<String> result = new ArrayList<>();
        Collections.sort(Theatres);
        for (Theatre e: Theatres) {
            result.add(e.getID());
        }
        return result;
    }

    @Override
    public List<String> getTicketIDsForPerformance(String performanceID) {
        List<String> TicketIDsForPerformance = new ArrayList<>();
        if (!new Performance(performanceID).doesPerformanceExist()) {
            TicketIDsForPerformance.add("ERROR Performance ID does not exist");
        } else {
            for (Ticket e : Tickets) {
                if (e.getPerformanceID().equals(performanceID)) {
                    TicketIDsForPerformance.add(e.getTicketID());
                }
            }
        }
        return TicketIDsForPerformance;
    }

    @Override
    public List<String> salesReport(String actID) {
        ArrayList<String> salesReport = new ArrayList<>();
        Act checkAct = new Act(actID);
        if (checkAct.doesActExist()) {
            ArrayList<Performance> performancesForAct = Performance.getPerformancesForAct(checkAct.getActID());
            for (Performance e : performancesForAct) {
                int[] sales = e.getTotalSalesReport();
                salesReport.add(e.getPerformanceID() + "\t" + e.getStartTime() + "\t" + sales[1] + "\t" + "$" + sales[0]);
            }
        } else {
            salesReport.add("ERROR Act does not exist");
        }
        return salesReport;
    }

    @Override
    public List<String> seatsAvailable(String performanceID) {
        List<String> seatsAvailable = new ArrayList<>();
        Performance newPerformance = new Performance(performanceID);
        if (newPerformance.doesPerformanceExist()) {
            //int rows = Theatre.getTheatre(Performance.getPerformance(performanceID).getTheatreID()).getRows();
            int rows = newPerformance.getTheatre().getRows();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= rows; j++) {
                    Ticket checkTicket = new Ticket(performanceID,null,i,j,null);
                    if (checkTicket.isTicketAvailable()) {
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
        Act newAct = new Act(title,artistID,minutesDuration);
        if (!new Artist(null,artistID).doesArtistExistByID()) {
            return "ERROR Artist does not exist";
        } else if (minutesDuration <= 0) {
            return "ERROR Invalid format for act duration";
        } else if (title == null || title.trim().isEmpty()) {
            return "ERROR: Title is invalid";
        } else if (newAct.doesSameActByArtistExist()) {
            return "ERROR Act by this artist already exists";
        } else {
            return newAct.addActToList();
        }
    }

    @Override
    public String addArtist(String name) {
        Artist newArtist = new Artist(name);
        if (name == null || name.trim().isEmpty()) {
            return "ERROR Invalid Name";
        } else if (newArtist.doesArtistExist()) {
            return "ERROR Artist already exists";
        } else {
            return newArtist.addArtistToList();
        }

    }

    @Override
    public String initialise(String path) {
        File file = new File(path);

        try{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            while(scanner.hasNext()) {
                String[] tempInfo = scanner.next().split("\t");
                Theatres.add(new Theatre(tempInfo[1], Integer.parseInt(tempInfo[2]),Integer.parseInt(tempInfo[3])));

            }
            scanner.close();

        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return "ERROR Incorrect Format";
        }
        catch (FileNotFoundException e){
            return "ERROR File Not Found";
        }
        return "";
    }



    @Override
    public String issueTicket(String performanceID, int rowNumber, int seatNumber) {
        Ticket newTicket = new Ticket(performanceID,null,rowNumber,seatNumber, null);
        if (!newTicket.doesPerformanceExist()) {
            return "ERROR Performance does not exist";
        } else if (!newTicket.isSeatValid()) {
            return "ERROR Seat is not valid";
        } else if (!newTicket.isTicketAvailable()) {
            return "ERROR Seat is not available";
        } else {
            newTicket.calculatePrice();
           return newTicket.addTicketToList();
        }
    }

    @Override
    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {
       Performance newPerformance = new Performance(actID,theatreID,startTimeStr,premiumPriceStr,cheapSeatsStr,null);
       try {
           if (!newPerformance.doesActExist()) {
               return "ERROR Act with the specified Act ID does not exist.";
           } else if (!newPerformance.getTheatre().doesTheatreExist()) {
               return "ERROR Theatre does not exist";
           } else if (!newPerformance.checkDate()) {
               return "ERROR Date is in the wrong format. Format expected: yyyy-mm-ddThh:mm";
           } else if (!newPerformance.checkPrices()) {
               return "ERROR Prices are not in correct format.";
           } else {
               return newPerformance.addPerformanceToList();
           }
       } catch (NullPointerException e) {
           return "ERROR: A Field in the input is null";
       }
    }
}


