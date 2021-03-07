package be.thomasmore.party.helpers;

public class ShowHideToggler {

    private final static String show ="show", hide = "hide";

    public static String oppositeFilter(String filter){
        if(filter==null || filter.equals(hide)){
            return show;
        }
        if(filter.equals(show)){
            return hide;
        }
        return show;
    }
}
