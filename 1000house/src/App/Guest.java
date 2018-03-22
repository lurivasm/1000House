package App;
import java.util.*;

public class Guest extends Profile{
	
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
	
	public Boolean removeReserve(Offer o) {
		for(Reserve r : reserves) {
			if(r.getOffer().compareOffer(o) == true) {
				reserves.remove(r);
				return true;
			}
		}
		return false;
	}
	
	
	

}
