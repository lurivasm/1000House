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
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();

		this.guest = guest;
		this.offer = offer;
	}

	public Boolean buyOffer() throws NotRegisteredUser{
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();{

		if (app.getLog().getGuestProfile().addOffer(this) == false) return false;
		offer.setState(OfferStates.BOUGHT);
		return true;
	}

	public Boolean cancelReserve() {
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();{

		if(guest.getGuestProfile().removeReserve(offer) == false) return false;
		offer.setState(OfferStates.AVAILABLE);
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
