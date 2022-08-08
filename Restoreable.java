/***
 * Class to tell if an object is restoreable
 * @author Marco Clark
 * Date Created: 2/17/2022
 * Date last modified: 2/27/2022
 */
public interface Restoreable {
    /***
     * Abstract method to tell if an object is restoreable
     * @return boolean as to whether an object is restoreable
     */
    public abstract boolean isRestoreable();
}