/**
 *
 */
package app;
import java.io.*;
import java.time.LocalDate;

import exception.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class HolidaysOffer extends Offer implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = -4113687983271560385L;
	private LocalDate endDate;

  	/**
	 * Constructor of HolidaysOffer
	 * 
	 * @return new HolidaysOffer
	 */
	public HolidaysOffer(LocalDate iniDate, int price, int deposit, House house, Application app,LocalDate endDate) throws Exception {
		super(iniDate, price,deposit, house, app);

		for (Offer o : house.getOffers()) {
			if (o instanceof HolidaysOffer) {
				throw new HouseOfferException();
			}
		}
		this.endDate = endDate;

	}


  	/**
  	 * Sets the endDate and the user state as waiting for changes
  	 * @return the endDate
  	 */
  	public LocalDate getendDate() {
  		return endDate;
  	}

  	/**
  	 * @param endDate the endDate to set
  	 */
  	public void setEndDate(LocalDate endDate) {
  		this.endDate = endDate;
  		state = OfferStates.WAITING;
  	}
}
