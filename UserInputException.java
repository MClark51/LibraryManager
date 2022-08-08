/***
 * Class for when the User Input is not acceptable
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
public class UserInputException extends Exception{
    
    /***
     * Default constructor
     */
    public UserInputException(){
        super("User Input is invalid");
    }

    /***
     * Constructor with one parameter
     * @param msg message given when exception is thrown
     */
    public UserInputException(String msg){
        super(msg);
    }
}