package app;
import java.util.*;
import java.io.*;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class Guest extends Profile implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public List<Reserve> reserves = new ArrayList<Reserve>();
	public List<Offer> offers = new ArrayList<Offer>();
	/**
	 * @param ccNumber
	 */

	public Guest(String ccNumber) {
		super(ccNumber);
	}
	/**
	 * @return the reserves
	 */
	public List<Reserve> getReserves() {
		return reserves;
	}

	/**
	 * @return the offers
	 */
	public List<Offer> getOffers() {
		return offers;
	}

	/**
	 * Removes a reserve from the reserves list of the Guest
	 * @param offer
	 * @return true if everything is correct, false otherwise
	 */
	public Boolean removeReserve(Offer offer) {
		for(Reserve r : reserves) {
			if(r.getOffer().compareOffer(offer) == true) {
				reserves.remove(r);
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes an offer from the offers list of the Guest
	 * @param offer
	 * @return true if everything is correct, false otherwise
	 */
	public Boolean removeOffer(Offer offer) {
		for(Offer o : offers) {
			if(o.compareOffer(offer) == true) {
				offers.remove(offer);
				return true;
			}
		}
		return false;
	}

	public Boolean addReserve(Reserve reserve) {
		reserves.add(reserve);
		return true;
	}

	public Boolean addOffer(Offer offer) {
		offers.add(offer);
		return true;
	}

}
