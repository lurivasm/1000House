/**
 * 
 */
package exception;

/**
 * Exception Not Guest
 * It probes the user is registered
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class NotRegisteredUser extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NotRegisteredUser() {
	}
	
	public String toString() {
		return "ERROR 403\nYou are not registered\n";
	}
}
