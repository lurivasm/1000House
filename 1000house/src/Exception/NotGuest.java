/**
 * 
 */
package Exception;

/**
 * Exception Not Guest
 * It probes the user is a guest
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class NotGuest extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String username;
	
	public NotGuest(String username) {
		this.username = username;
	}
	
	public String toString() {
		return "You are not the guest I'm looking for";
	}
}
