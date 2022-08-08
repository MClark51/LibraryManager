/***
 * Class to model a title of type book
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
public class Book extends Title {
    
    private String author;
    private Long isbn;

    /***
     * Default constructor
     */
    public Book(){
        super();
        author = "none";
        isbn = Long.valueOf(0);
    }

    /***
     * Constructor with 7 parameters
     * @param cN - call number
     * @param t - title
     * @param p - publisher
     * @param pY - year of publication
     * @param nC - number of copies
     * @param a - author
     * @param is - isbn
     * Sends cN, t, p, pY, and nC to the superclass constructor
     */
    public Book(String cN, String t, String p, int pY, int nC, String a, Long is){
        super(cN,t,p,pY,nC);
        author = a;
        isbn = is;
    }

    /**
     * Method to get the author of a book
     * @return author of book
     */
    public String getAuthor(){
        return author;
    }

    /***
     * Method to get the ISBN of a book
     * @return ISBN of book
     */
    public Long getISBN(){
        return isbn;
    }

    /***
     * Method to set the author of a book
     * @param a value assigned to author
     */
    public void setAuthor(String a){
        author = a;
    }

    /***
     * Method to set the ISBN of a book
     * @param i value assigned to ISBN
     */
    public void setISBN(Long i){
        isbn = i;
    }

    /***
     * Method to return this as a String
     * @return string value of this
     */
    public String toString(){
        return super.toString() + "\nAuthor: " + author + "\nISBN: " + isbn;
    }
}
