package be.thomasmore.party.model;

public class Venue {
    private String venueName;
    private String linkMoreInfo;

    private final static String venueOnbekend = "onbekend";

    public Venue() {
        this(venueOnbekend, "");
    }

    public Venue(String venueName, String linkMoreInfo) {
        this.venueName = venueName;
        this.linkMoreInfo = linkMoreInfo;
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
}