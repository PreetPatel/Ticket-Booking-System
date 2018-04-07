package tbs.server;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class TBSServerImpl implements TBSServer {
    private static List<Theatre> Theatres = new ArrayList<>();
    private static List<Artist> Artists = new ArrayList<>();
    private static List<Act> Acts = new ArrayList<>();
    private static List<Performance> Performances = new ArrayList<>();

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
        return null;
    }

    @Override
    public List<String> salesReport(String actID) {
        return null;
    }

    @Override
    public List<String> seatsAvailable(String performanceID) {
        return null;

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
        return null;
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


