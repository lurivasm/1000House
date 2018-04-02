/**
 *
 */
package app;
import java.io.Serializable;
import java.time.LocalDate;

import exception.*;


/**
 * Class Reserve
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class Reserve implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8905846146125294016L;
	private User guest;
	private Offer offer;
	private LocalDate date ;

	/**
	* Constructor of class Reserve
	* @param user who books an offer
	* @param offer to book
	* @return a new Reserve
	* @throws NotGuest in case the user is not a guest
	*/
	public Reserve(User guest, Offer offer) throws NotGuest{
		/*Case the user is not a guest*/
		if (guest.getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();

		this.guest = guest;
		this.offer = offer;
		date = LocalDate.now();
		
	}

	/**
	* Function that removes a reserve from the guest's reserves list
	* and adds the offer to the list of bought offers changing the
	* offer state to BOUGHT
	* @return Boolean in case he function has been successful or not
	* @throws NotGuest in case the user is not a guest
	*/
	public Boolean payOffer() throws NotGuest{
		/*Case the user is not a guest*/
		if (guest.getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();

		if (guest.getGuestProfile().addOffer(offer) == false) return false;
		if (guest.getGuestProfile().removeReserve(offer) == false) return false;
		offer.setState(OfferStates.BOUGHT);
		return true;
	}

	/**
	* Function that removes a reserve from the guest's reserves list
	* and changes the offer state to AVAILABLE canceling the reserve
	* @return Boolean in case he function has been successful or not
	* @throws NotGuest in case the user is not a guest
	*/
	public Boolean cancelReserve() throws NotGuest{
		/*Case the user is not a guest*/
		if (guest.getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();

		if(guest.getGuestProfile().removeReserve(offer) == false) return false;
		offer.setState(OfferStates.AVAILABLE);
		return true;
	}

	/**
	* @return guest the user to get
	*/
	public User getGuest() {
		return guest;
	}

	/**
	* @param guest the user to set
	*/
	public void setGuest(User guest) {
		this.guest = guest;
	}

	/**
	* @return offer to get
	*/
	public Offer getOffer() {
		return offer;
	}

	/**
	* @param offer to set
	*/
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}


	
	
	


}
