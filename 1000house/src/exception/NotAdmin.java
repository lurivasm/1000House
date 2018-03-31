/**
 *
 */
package exception;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
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
