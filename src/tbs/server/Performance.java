package tbs.server;
import java.util.ArrayList;

public class Performance{
    private String _ActID;
    private String _TheatreID;
    private String _StartTime;
    private String _PremiumPrice;
    private String _CheapPrice;
    private String _PerformanceID;

    public Performance(String actID, String theatreID, String startTime, String premiumPrice, String cheapPrice, String performanceID) {
        _ActID = actID;
        _TheatreID = theatreID;
        _StartTime = startTime;
        _PremiumPrice = premiumPrice;
        _CheapPrice = cheapPrice;
        _PerformanceID = performanceID;
    }

    public String getPerformanceID() {
        return _PerformanceID;
    }
    public String getStartTime() {
        return _StartTime;
    }

    public String getTheatreID() {
        return _TheatreID;
    }

    public String getActID() {
        return _ActID;
    }

    public static boolean checkDate(String startTime) {
        return startTime.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})");
    }

    public static boolean checkPrices(String premiumPrice, String cheapPrice) {
        return (premiumPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))") && cheapPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))"));
    }

    public static String addPerformanceToList(String actID, String theatreID, String startTimeStr, String premiumPriceStr, String cheapSeatsStr) {

        int newPerformanceID;
        if (TBSServerImpl.getPerformanceList().isEmpty()) {
            newPerformanceID = 1;
        } else {
            newPerformanceID = (Integer.parseInt(TBSServerImpl.getPerformanceList().get(TBSServerImpl.getPerformanceList().size() - 1).getPerformanceID()) + 1);
        }
        Performance newPerformance = new Performance(actID,theatreID,startTimeStr,premiumPriceStr,cheapSeatsStr,Integer.toString(newPerformanceID));
        TBSServerImpl.getPerformanceList().add(newPerformance);
        return newPerformance.getPerformanceID();

    }

    public static boolean doesPerformanceExist(String ID) {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e.getPerformanceID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public static Performance getPerformance(String performanceID) {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e.getPerformanceID().equals(performanceID)) {
                return e;
            }
        }
        return null;
    }

    public  boolean isSeatValid(int row, int position) {
        int maxRows = Theatre.getTheatre(_TheatreID).getRows();
        return row <= maxRows && position <= maxRows;

    }

    public String getPrice(int row) {
        if (row <= (Theatre.getTheatre(_TheatreID).getRows()/2)) {
            return _PremiumPrice;
        } else {
            return _CheapPrice;
        }
    }

    public static ArrayList<Performance> getPerformancesForAct(String actID) {
        ArrayList<Performance> performancesForAct = new ArrayList<>();
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e.getActID().equals(actID)) {
                performancesForAct.add(e);
            }
        }
        return performancesForAct;
    }

    public int[] getTotalSalesReport() {
        int[] ticketSales = {0,0};
        for (Ticket e: Ticket.getTicketsForPerformance(_PerformanceID)) {
            ticketSales[0] += Integer.parseInt(e.getTicketPrice().substring(1));
            ticketSales[1]++;
        }
        return ticketSales;
    }
}
