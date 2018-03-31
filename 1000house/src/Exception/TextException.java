/**
 *
 */
package Exception;

/**
 * Text Rate
 * It probes the text is not over 150 characters
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class TextException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	
	
	public String toString() {
		return "The text is over 150 characters. Please, write less";
	}
}
