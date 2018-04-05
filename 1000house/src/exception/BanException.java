/**
 * 
 */
package exception;

/**
 * @author danist
 *
 */
public class BanException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6214578594112052336L;

	public String toString() {
		return "Only the System an the admin can bann users\n";
	}
}
