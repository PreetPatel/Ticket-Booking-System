package tbs.server;
import java.util.ArrayList;

public class Performance extends Act{
    private String _ActID;
    private String _TheatreID;
    private String _StartTime;
    private String _PremiumPrice;
    private String _CheapPrice;
    private String _PerformanceID;
    private static int _PerformanceIDTracker = 1;

    public Performance(String actID, String theatreID, String startTime, String premiumPrice, String cheapPrice, String performanceID) {
        super(actID);
        _ActID = actID;
        _TheatreID = theatreID;
        _StartTime = startTime;
        _PremiumPrice = premiumPrice;
        _CheapPrice = cheapPrice;
        _PerformanceID = performanceID;
    }

    public  Performance(String performanceID) {
        super(null);
        _PerformanceID = performanceID;
        _TheatreID = this.getTheatreIDforPerformance();
    }

    public String getPerformanceID() {
        return _PerformanceID;
    }
    public String getStartTime() {
        return _StartTime;
    }

    public String getActID() {
        return _ActID;
    }

    public boolean checkDate() {
        return _StartTime.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})");
    }

    public boolean checkPrices() throws NullPointerException {
        return (_PremiumPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))") && _CheapPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))"));
    }

    public String getCheapPrice() {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e._PerformanceID.equals(_PerformanceID)) {
                return e._CheapPrice;
            }
        }
        return null;
    }

    public String getPremiumPrice() {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e._PerformanceID.equals(_PerformanceID)) {
                return e._PremiumPrice;
            }
        }
        return null;
    }

    public String addPerformanceToList() {

        String newPerformanceID = "PERFORMANCE" + _PerformanceIDTracker;
        _PerformanceIDTracker++;
        this._PerformanceID = newPerformanceID;
        TBSServerImpl.getPerformanceList().add(this);
        return this.getPerformanceID();
    }

    public boolean doesPerformanceExist() {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e.getPerformanceID().equals(_PerformanceID)) {
                return true;
            }
        }
        return false;
    }

    public Theatre getTheatre() {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(_TheatreID)) {
                return e;
            }
        }
        return null;
    }

    public String getTheatreIDforPerformance() {
        for (Performance e: TBSServerImpl.getPerformanceList()) {
            if (e._PerformanceID.equals(_PerformanceID)) {
                return e._TheatreID;
            }
        }
        return null;
    }

    public boolean doesTheatreForPerformanceExist() {
        for (Theatre e: TBSServerImpl.getTheatreList()) {
            if (e.getID().equals(_TheatreID)) {
                return true;
            }
        }
        return false;
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
