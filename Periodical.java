/***
 * Class to model a title of type periodical
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
public class Periodical extends Title {
    private int month;
    private int issueNum;

    /***
     * Default constructor
     */
    public Periodical(){
        super();
        month = 0; 
        issueNum = 0;
    }

    /***
     * Constructor with 7 parameters
     * @param cN - call number
     * @param t - title
     * @param p - publisher
     * @param pY - year of publication
     * @param nC - number of copies
     * @param m - month of publication
     * @param iN - issue number
     * Sends cN, t, p, pY, and nC to the superclass constructor
     */
    public Periodical(String cN, String t, String p, int pY, int nC, int m, int iN){
        super(cN, t, p, pY, nC);
        month = m;
        issueNum = iN;
    }

    /***
     * Method to access publication month
     * @return publication month
     */
    public int getMonth(){
        return month;
    }

    /***
     * Method to access the issue number
     * @return issue number
     */
    public int getIssue(){
        return issueNum;
    }

    /***
     * Method to set the month of a periodical
     * @param m value assigned to month
     */
    public void setMonth(int m){
        month = m;
    }

    /***
     * Method to set the issue number of a periodical
     * @param i value assigned to issue number
     */
    public void setIssue(int i){
        issueNum = i;
    }

    /***
     * Method to output this as a string
     * @return string value of this
     */
    public String toString(){
        return super.toString() + "\nMonth: " + month + "\nIssue Number: " + issueNum;
    }
}