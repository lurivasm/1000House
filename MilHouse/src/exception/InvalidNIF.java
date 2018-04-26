/**
 *
 */
package exception;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class InvalidNIF extends Exception{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String NIF;

	/**
	 * @param nIF
	 */
	public InvalidNIF(String nIF) {
		NIF = nIF;
	}

	public String toString() {
		return "An user with NIF " + NIF + " already exists\n";
	}
}
