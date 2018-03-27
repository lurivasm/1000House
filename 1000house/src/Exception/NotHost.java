/**
 * 
 */
package Exception;

/**
 * @author danist
 *
 */
public class NotHost extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toString() {
		return "You're not logged as Host. Please change your profile."; 
	}
}
