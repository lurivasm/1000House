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

	/**
	 * Constructor of a text
	 * @param rate the rate
	 * @param user the user who writes the rate
	 * @param offer the offer who is being commented
	 * @return new rate
	 * @throws RateException when the rate is not between 0 and 5
	 */
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
	 * @throws RateException in case the rate is over 5 or less than 0
	 * @param the rate of the comment
	 */
	public void setRate(int rate) throws RateException{
		if (rate < 0 || rate > 5) {
			RateException ex = new RateException(rate);
			throw ex;
		}
		this.rate = rate;
	}


}
