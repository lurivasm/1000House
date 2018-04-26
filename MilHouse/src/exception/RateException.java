/**
 * 
 */
package exception;

/**
 * Exception Rate
 * It probes the rate is between 0 and 5
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class RateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int rate;
	
	public RateException(int rate) {
		this.rate = rate;
	}
	
	public String toString() {
		return "The rate " + rate + " is not between 0 and 5\n";
	}
}
