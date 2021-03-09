package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean foodProvided;
    private boolean indoor;
    private boolean outdoor;
    private boolean freeParkingAvailable;
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

    public Venue(String venueName, String linkMoreInfo, int capacity, boolean foodProvided, boolean indoor, boolean outdoor, boolean freeParkingAvailable, String city, double distanceFromPublicTransportInKm) {
        this(venueName, linkMoreInfo);
        this.capacity = capacity;
        this.foodProvided= foodProvided;
        this.indoor = indoor;
        this.outdoor = outdoor;
        this.freeParkingAvailable = freeParkingAvailable;
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

    public boolean getFoodProvided() {
        return foodProvided;
    }

    public String isFoodProvidedYesOrNo() {
        return boolAsYesOrNo(foodProvided);
    }

    public void setFoodProvided(boolean foodProvided) {
        foodProvided = foodProvided;
    }

    public boolean getIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        indoor = indoor;
    }

    public String isIndoorYesOrNo() {
        return boolAsYesOrNo(indoor);
    }

    public boolean getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public String isOutdoorYesOrNo() {
        return boolAsYesOrNo(outdoor);
    }

    public boolean getFreeParkingAvailable() {
        return freeParkingAvailable;
    }

    public void setFreeParkingAvailable(boolean freeParkingAvailable) {
        freeParkingAvailable = freeParkingAvailable;
    }

    public String isFreeParkingAvailableYesOrNo() {
        return boolAsYesOrNo(freeParkingAvailable);
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

    public boolean isValid(){
        return city == null;
    }

    private String boolAsYesOrNo(boolean bool){
        return bool ? trueWord : falseWord;
    }

    public int getId() {
        return id;
    }
}