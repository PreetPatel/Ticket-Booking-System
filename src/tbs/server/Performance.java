package tbs.server;

public class Performance extends PerformanceCollection{
    private String _ActID;
    private Theatre _Theatre;
    private String _StartTime;
    private String _PremiumPrice;
    private String _CheapPrice;
    private String _PerformanceID;
    private static int _PerformanceIDTracker = 1;

    public String getPerformanceID() {
        return _PerformanceID;
    }

    public String getStartTime() {
        return _StartTime;
    }

    public String getActID() {
        return _ActID;
    }

    public String getTheatreID() {
        return _Theatre.getID();
    }

    public int getTheatreRows() {
        return _Theatre.getRows();
    }
    public String getCheapPrice() {
        return _CheapPrice;
    }

    public String getPremiumPrice() {
        return  _PremiumPrice;
    }

    public Performance(String actID, Theatre theatre, String startTime, String premiumPrice, String cheapPrice) {
        _ActID = actID;
        _Theatre = theatre;
        _StartTime = startTime;
        _PremiumPrice = premiumPrice;
        _CheapPrice = cheapPrice;
        _PerformanceID = "PERFORMANCE" + _PerformanceIDTracker;
        _PerformanceIDTracker++;
    }
    //Checks the input date to match the regex pattern to represent the ISO 8601 standard
    public boolean checkDate() throws NullPointerException{
        return _StartTime.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})");
    }
    // Checks prices to match the regex pattern that represents the currency format $200 or $2,000 or $20,000
    public boolean checkPrices() throws NullPointerException {
        return (_PremiumPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))") && _CheapPrice.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))"));
    }








}
