package be.thomasmore.party.model;

public class Venue {
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean isFoodProvided;
    private boolean isIndoor;
    private boolean isOutdoor;
    private boolean isFreeParkingAvailable;
    private String city;
    private double distanceFromPublicTransportInKm;

    private final static String venueOnbekend = "onbekend";
    private final static String trueWord = "yes";
    private final static String falseWord = "no";

    public Venue() {
        this(venueOnbekend, "");
    }

    public Venue(String venueName, String linkMoreInfo) {
        this.venueName = venueName;
        this.linkMoreInfo = linkMoreInfo;
    }

    public Venue(String venueName, String linkMoreInfo, int capacity, boolean isFoodProvided, boolean isIndoor, boolean isOutdoor, boolean isFreeParkingAvailable, String city, double distanceFromPublicTransportInKm) {
        this(venueName, linkMoreInfo);
        this.capacity = capacity;
        this.isFoodProvided= isFoodProvided;
        this.isIndoor = isIndoor;
        this.isOutdoor = isOutdoor;
        this.isFreeParkingAvailable = isFreeParkingAvailable;
        this.city = city;
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFoodProvided() {
        return isFoodProvided;
    }

    public String isFoodProvidedYesOrNo() {
        return boolAsYesOrNo(isFoodProvided);
    }

    public void setFoodProvided(boolean foodProvided) {
        isFoodProvided = foodProvided;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    public String isIndoorYesOrNo() {
        return boolAsYesOrNo(isIndoor);
    }

    public boolean isOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(boolean outdoor) {
        isOutdoor = outdoor;
    }

    public String isOutdoorYesOrNo() {
        return boolAsYesOrNo(isOutdoor);
    }

    public boolean isFreeParkingAvailable() {
        return isFreeParkingAvailable;
    }

    public void setFreeParkingAvailable(boolean freeParkingAvailable) {
        isFreeParkingAvailable = freeParkingAvailable;
    }

    public String isFreeParkingAvailableYesOrNo() {
        return boolAsYesOrNo(isFreeParkingAvailable);
    }

    public String getCity() {
        return city == null ? venueOnbekend : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDistanceFromPublicTransportInKm() {
        return distanceFromPublicTransportInKm;
    }

    public void setDistanceFromPublicTransportInKm(double distanceFromPublicTransportInKm) {
        this.distanceFromPublicTransportInKm = distanceFromPublicTransportInKm;
    }

    private String boolAsYesOrNo(boolean bool){
        return bool ? trueWord : falseWord;
    }
}