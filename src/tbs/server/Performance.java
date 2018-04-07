package tbs.server;

public class Performance {
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

    public static boolean checkDate(String startTime) {
        return startTime.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})");
    }

    public static boolean checkPrices(String premiumPrice, String cheapPrice) {
        try {
            if (!(premiumPrice.charAt(0) == '$') || !(cheapPrice.charAt(0) == '$')) {
                int tempPremium = Integer.parseInt(premiumPrice.substring(1));
                int tempCheap = Integer.parseInt(cheapPrice.substring(1));
                return tempCheap < 0 && tempPremium < 0;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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



}
