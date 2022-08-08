/***
 * Class to model a title in a library
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
import java.util.Calendar;
public abstract class Title implements Restoreable, Comparable <Title>{
    private String callNum;
    private String title;
    private String pub;
    private int pubYear;
    private int numCopies;

    /***
     * Default constructor
     * Sets all String fields to "none" and integer fields to 0
     */
    protected Title (){
        callNum = "none";
        title = "none";
        pub = "none";
        pubYear = 0;
        numCopies = 0;
    }
    /***
     * Constructor with 5 parameters
     * @param cN - call number
     * @param t - title
     * @param p - publisher
     * @param pY - year of publication
     * @param nC - number of copies
     */
    protected Title(String cN, String t, String p, int pY, int nC){
        callNum = cN;
        title = t;
        pub = p;
        pubYear = pY;
        numCopies = nC;
    }

    /***
     * Method to access the call number
     * @return call number
     */
    public String getCallNum(){
        return callNum;
    }

    /***
     * Method to access the title
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /***
     * Method to access the publisher
     * @return publisher name
     */
    public String getPublisher(){
        return pub;
    }

    /***
     * Method to access the publication year
     * @return year of publication
     */
    public int getPubYear(){
        return pubYear;
    }

    /***
     * Method  to access the number of copies
     * @return number of copies
     */
    public int getNumCopies(){
        return numCopies;
    }

    /***
     * Method to set the call number of a title
     * @param c value assigned to call number
     */
    public void setCallNum(String c){
        callNum = c;
    }

    /***
     * Method to set the title of a title
     * @param t value assigned to title
     */
    public void setTitle(String t){
        title = t;
    }

    /***
     * Method to set the publisher of a title
     * @param p value assigned to publisher
     */
    public void setPublisher(String p){
        pub = p;
    }

    /***
     * Method to set the publication year
     * @param pY value assigned to publication year
     */
    public void setPubYear(int pY){
        pubYear = pY;
    }

    /***
     * Method to set the number of copies
     * @param nC value assigned to number of copies
     */
    public void setNumCopies(int nC){
        numCopies = nC;
    }

    /***
     * Method to compare two titles on the basis of publication year
     * @return value that denotes if parameter is less than, greater than, or equal to this
     * @param t title being compared to
     * return of -1 means this is less than t. return of 0 means this is equal to t. return of 1 means this is greater than t
     */
    public int compareTo(Title t){
        if (pubYear < t.getPubYear()){
            return -1;
        }
        else if (pubYear > t.getPubYear()){
            return 1;
        }
        else{
            return 0;
        }
    }

    /***
     * Method to determine if this is restoreable
     * @return boolean value if this is restoreable
     * this is restoreable if publication year is greater than or equal to 20
     */
    public boolean isRestoreable(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (year - pubYear >= 20){
            return true;
        }
        return false;
    }

    /***
     * Method to output this as a string
     * @return String value of title information
     */
    public String toString(){
        return "Call Number: " + callNum + "\nTitle: " + title + "\nPublisher: " + pub + "\nYear: " + pubYear + "\nCopies: " + numCopies;
    }
}