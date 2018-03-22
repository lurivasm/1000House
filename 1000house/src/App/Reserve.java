/**
 * 
 */
package App;
import java.util.*;
import Exception.*;

/**
 * Class Reserve
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class Reserve {
	private User guest;
	private Offer offer;
	
	public Reserve(User guest, Offer offer) throws NotGuest, NotRegisteredUser{
		if(guest.getState().compareTo(UserStates.CONNECTED_GUEST) != 0) {
			NotGuest ex = new NotGuest(guest.getUsername());
			throw ex;
		}
		
		this.guest = guest;
		this.offer = offer;
	}
	
	public Boolean buyOffer() {
		offer.setState(OfferStates.BOUGHT);
		return true;
	}
	
	public Boolean cancelReserve() {
		offer.setState(OfferStates.AVAILABLE);
		if(guest.removeReserve(offer) == false) return false;
		return true;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	
}
