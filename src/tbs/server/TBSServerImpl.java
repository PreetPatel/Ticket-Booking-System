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
        if (!Artist.doesArtistExistByID(artistID)) {
            result.add("ERROR Act does not exist for specified artist");
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
        return getTheatreIDs();

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
        return null;
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
        for (Ticket e: Tickets) {
            if(e.getPerformanceID().equals(performanceID)) {
                TicketIDsForPerformance.add(e.getTicketID());
            }
        }
        return TicketIDsForPerformance;
    }

    @Override
    public List<String> salesReport(String actID) {
        ArrayList<String> salesReport = new ArrayList<>();
        if (Act.doesActExist(actID)) {
            ArrayList<Performance> performancesForAct = Performance.getPerformancesForAct(actID);
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
        if (Performance.doesPerformanceExist(performanceID)) {
            int rows = Theatre.getTheatre(Performance.getPerformance(performanceID).getTheatreID()).getRows();
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= rows; j++) {
                    if (Ticket.isTicketAvailable(i,j,performanceID)) {
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
        if (!Artist.doesArtistExistByID(artistID)) {
            return "ERROR Artist does not exist";
        } else if (minutesDuration <= 0) {
            return "ERROR Invalid format for act duration";
        } else if (Act.doesSameActByArtistExist(title,artistID)) {
            return "ERROR Act by this artist already exists";
        } else {
            return Act.addActToList(title,artistID,minutesDuration);
        }
    }

    @Override
    public String addArtist(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "ERROR Invalid Name";
        } else if (Artist.doesArtistExist(name)) {
            return "ERROR Artist already exists";
        } else {
            return Artist.addArtistToList(name);
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
        if (!Performance.doesPerformanceExist(performanceID)) {
            return "ERROR Performance does not exist";
        } else if (!Performance.getPerformance(performanceID).isSeatValid(rowNumber,seatNumber)) {
            return "ERROR Seat is not valid";
        } else if (!Ticket.isTicketAvailable(rowNumber,seatNumber,performanceID)) {
            return "ERROR Seat is not available";
        } else {
            String price = Performance.getPerformance(performanceID).getPrice(rowNumber);
           return Ticket.addTicketToList(performanceID, price, rowNumber, seatNumber);
        }
    }

    @Override
    public String schedulePerformance(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {
        if (!Act.doesActExist(actID)) {
            return "ERROR Act with the specified Act ID does not exist.";
        } else if (!Theatre.doesTheatreExist(theatreID)) {
            return "ERROR Theatre does not exist";
        } else if (!Performance.checkDate(startTimeStr)){
            return "ERROR Date is in the wrong format. Format expected: yyyy-mm-ddThh:mm";
        } else if (!Performance.checkPrices(premiumPriceStr,cheapSeatsStr)) {
            return "ERROR Prices are not in correct format.";
        } else {
            return Performance.addPerformanceToList(actID,theatreID,startTimeStr,premiumPriceStr,cheapSeatsStr);
        }
    }
}


