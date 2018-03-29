/**
 *
 */
package App;
import java.util.*;

import Exception.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public abstract class Offer {
	private Application app;
	private String iniDate;
	private int price;
	private OfferStates state;
	private double averageRate;
	private List<Comment> comments;
	private House house;

	/**
	 * Constructor of an offer
	 * @return New Offer
	 */
	public Offer(String iniDate, int price, House house, Aplication app) {
		this.iniDate = iniDate;
		this.price = price;
		this.house = house;
		this.averageRate = 0;
		this.state = OfferStates.WAITING;
		this.app = app;
		this.comments =  = new ArrayList<Comment>();
	}


	/**
	* Returns if the offer is reserved or not
	* @return Boolean
	*/
	public Boolean isReserved() {
		if (OfferStates.RESERVED.compareTo(state) == 0) return true;
		return false;
	}

	/**
	* Returns if the offer is bought or not
	* @return Boolean
	*/
	public Boolean isBought() {
		if (OfferStates.BOUGHT.compareTo(state) == 0) return true;
		return false;
	}

	/**
	* Returns if the offer is available or not
	* @return Boolean
	*/
	public Boolean isAvailable() {
		if (OfferStates.AVAILABLE.compareTo(state) == 0) return true;
		return false;
	}

	/**
	* Returns true if the offer has any problem: is denied, waiting or asked for changes
	* @return Boolean
	*/
	public Boolean hasProblem() {
		if (OfferStates.WAITING.compareTo(state) == 0) return true;
		else if (OfferStates.CHANGES.compareTo(state) == 0) return true;
		else if (OfferStates.DENIED.compareTo(state) == 0) return true;
		return false;
	}


	/**
	* To reserve an offer it state changes to RESERVED
	* It creates a new reserve and adds it to the guest's list
	*	@throws NotGuest in case the user is not a guest
	* @throws NotRegisteredUser in case the user is not Registered
	* @return Boolean if the reserve has been successful or not
	*/
	public Boolean bookOffer() throws NotGuest, NotRegisteredUser {
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest;
		/*Case the offer is not available*/
		if (this.isAvailable() == false) return false;

		/*Correct case*/
		Reserve reserve = new Reserve(app.getLog(), this);
		if (app.getLog().getGuestProfile().addReserve(this) == false) return false;
		this.setState(OfferStates.RESERVED);
		return true;
	}


	/**
	* The connected guest pay an offer without booking it
	* The function sets the offer state into BOUGHT and adds the offer to
	* the guest's list of reserves
	*	@throws NotGuest in case the user is not a guest
	* @throws NotRegisteredUser in case the user is not Registered
	* @return Boolean if the purchase has been successful or not
	*/
	public Boolean payOffer() throws NotRegisteredUser, NotGuest{
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest;
		/*Case the offer is not available*/
		if (this.isAvailable() == false) return false;

		/*Correct case*/
		this.setState(OfferStates.BOUGHT);
		if (app.getLog().getGuestProfile().addOffer(this) == false) return false;
		return true;
	}


	/**
	*	Two offers are equals if they have the same house, price, state and iniDate
	* @return Boolean : true if the offers are equals and false if not
	*/
	public Boolean compareOffer(Offer offer) {
		if(this.house.compareHouse(offer.house) == true){
			if(this.iniDate.equals(offer.iniDate) == true){
				if(this.price == offer.price){
					if(this.state.equals(offer.state) == true){
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	* The admin can deny offers which do not check the requirements changing the offer state to DENIED
	* @throws NotAdmin if the user who tries to deny an offer is not an admin
	* @return Boolean if the function has been succesful or not
	*/
	public Boolean denyOffer() throws NotAdmin{
		/*If the user who tries to deny an offer is not an admin*/
		if(app.getLog().getNIF().equals(app.getAdmNIF()) == false || app.getLog().getPassword().equals(app.getAdmPassword()) == false){
			throw new NotAdmin;
		}
		if(this.setState(OfferStates.DENIED) == false) return false;
		return true;
	}

	/**
	* The admin can approve offers changing the offer state to AVAILABLE
	* @throws NotAdmin if the user who tries to deny an offer is not an admin
	* @return Boolean if the function has been succesful or not
	*/
	public Boolean approveOffer() throws NotAdmin{
		/*If the user who tries to approve an offer is not an admin*/
		if(app.getLog().getNIF().equals(app.getAdmNIF()) == false || app.getLog().getPassword().equals(app.getAdmPassword()) == false){
			throw new NotAdmin;
		}
		if(this.setState(OfferStates.AVAILABLE)) return true;
		return false;
	}

	/**
	* The admin can ask for changes in offers changing the offer state to CHANGES
	* @throws NotAdmin if the user who tries to deny an offer is not an admin
	* @return Boolean if the function has been succesful or not
	*/
	public Boolean askForChanges() throws NotAdmin{
		/*If the user who tries to ask for changes is not an admin*/
		if(app.getLog().getNIF().equals(app.getAdmNIF()) == false || app.getLog().getPassword().equals(app.getAdmPassword()) == false){
			throw new NotAdmin;
		}
		this.setState(OfferStates.CHANGES);
	 	return true;
	}


	/**
	*	Calculates and sets the average rate of all the comments
	* @return Boolean
	*/
	public Boolean calculateRate(){
		int aux = 0, cont = 0;
		for (Rate rate : comments){
			aux = aux + rate.getRate();
			cont++;
		}
		setAverageRate(aux/cont);
		return true;
	}


	/**
	 * @return the iniDate
	 */
	public String getIniDate() {
		return iniDate;
	}

	/**
	 * @param iniDate the iniDate to set
	 */
	public void setIniDate(String iniDate) {
		this.iniDate = iniDate;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the state
	 */
	public OfferStates getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(OfferStates state) {
		this.state = state;
	}

	/**
	 * @return the averageRate
	 */
	public double getAverageRate() {
		return averageRate;
	}

	/**
	 * @param averageRate the averageRate to set
	 */
	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the house of this offer
	 */
	public House getHouse() {
		return house;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouse(House house) {
		this.house = house;
	}


}
