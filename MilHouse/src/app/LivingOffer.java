/**
 *
 */
package app;
import java.io.Serializable;
import java.time.LocalDate;

import exception.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class LivingOffer extends Offer implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 5100019223446512297L;
	private int numMonths;

  /**
  *Constructor of LivingOffer
  * @return new LivingOffer
  * @throws HouseOfferException 
  */
	public LivingOffer(LocalDate iniDate, int price, int deposit, House house, Application app, int numMonths) throws HouseOfferException {
		super(iniDate, price, deposit,  house, app);

		for (Offer o : house.getOffers()) {
			if (o instanceof LivingOffer) {
				throw new HouseOfferException();
			}
		}
		this.numMonths = numMonths;

	}

 
  	/**
  	 * @return the numMonths
  	 */
  	public int getnumMonths() {
  		return numMonths;
  	}

  	/**
  	 * Sets the numMonths and the user state as waiting for changes
  	 * @param numMonths the numMonths to set
  	 */
  	public void setnumMonths(int numMonths) {
  		this.numMonths = numMonths;
  		state = OfferStates.WAITING;
  	}
}
