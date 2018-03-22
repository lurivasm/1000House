/**
 * 
 */
package App;
import Exception.RateException;

/**
 * Class Rate
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */

public class Rate extends Comment {
	private int rate;
	
	public Rate(int rate, User user, Offer offer) throws RateException{
		super(user, offer);
		if (rate < 0 || rate > 5) {
			RateException ex = new RateException(rate);
			throw ex;
		}
		this.rate = rate;
	}
	
	/**
	 * 
	 * @return the rate of the comment
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * 
	 * @param the rate of the comment
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
