/**
 * 
 */
package Exception;

/**
 * @author danist
 *
 */
public class NotAdmin extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toString() {
		return "You're not the Admin"; 
	}
	
}
