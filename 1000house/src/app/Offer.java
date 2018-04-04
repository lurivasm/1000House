/**
 *
 */
package app;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import exception.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public abstract class Offer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -943306651005895813L;
	protected Application app;
	protected LocalDate iniDate;
	protected int price;
	protected OfferStates state;
	protected double averageRate;
	protected List<Comment> comments;
	protected House house;
	protected String changes;
	protected LocalDate changesDate;
	

	/**
	 * Constructor of an offer
	 * @return New Offer
	 */
	public Offer(LocalDate iniDate, int price, House house, Application app) {
		this.iniDate = iniDate;
		this.price = price;
		this.house = house;
		this.averageRate = 0;
		this.state = OfferStates.WAITING;
		this.app = app;
		this.comments = new ArrayList<Comment>();
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
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();
		/*Case the offer is not available*/
		if (this.isAvailable() == false) return false;

		/*Correct case*/
		Reserve reserve = new Reserve(app.getLog(), this);
		if (app.getLog().getGuestProfile().addReserve(reserve) == false) return false;
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
	public Boolean buyOffer() throws NotRegisteredUser, NotGuest{
		/*Case Not Registered User*/
		if (app.getLog() == null) throw new NotRegisteredUser();
		/*Case the user is not a guest*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();
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
	* @return Boolean if the function has been successful or not
	*/
	public Boolean denyOffer() throws NotAdmin{
		/*If the user who tries to deny an offer is not an admin*/
		if(app.getLog().isAdmin() == false){
			throw new NotAdmin();
		}
		state = OfferStates.DENIED;
		app.getwaitoffers().remove(this);
		return true;
	}

	/**
	* The admin can approve offers changing the offer state to AVAILABLE
	* @throws NotAdmin if the user who tries to deny an offer is not an admin
	* @return Boolean if the function has been succesful or not
	*/
	public Boolean approveOffer() throws NotAdmin{
		/*If the user who tries to approve an offer is not an admin*/
		if(app.getLog().isAdmin() == false){
			throw new NotAdmin();
		}
		state = OfferStates.AVAILABLE;
		app.getwaitoffers().remove(this);
		app.getavoffers().add(this);
		return true;
	}

	/**
	* The admin can ask for changes in offers changing the offer state to CHANGES
	* The asked changes are saved in the changes field of the object, and the date of when the changes have been asked is saved too.
	* @throws NotAdmin if the user who tries to deny an offer is not an admin
	* @return Boolean if the function has been successful or not
	*/
	public Boolean askForChanges(String changes) throws NotAdmin{
		/*If the user who tries to ask for changes is not an admin*/
		if(app.getLog().isAdmin() == false){
			throw new NotAdmin();
		}
		state = OfferStates.CHANGES;
		this.changes = changes;
		changesDate = LocalDate.now();
	 	return true;
	}


	/**
	*	Calculates and sets the average rate of all the comments
	* @return Boolean
	*/
	public Boolean calculateRate(){
		int aux = 0, cont = 0;
		for (Comment rate : comments){
			if(rate instanceof Rate) {
				aux = aux + ((Rate)rate).getRate();
				cont++;
			}
		}
		if(cont == 0) {
			return true;
		}
		averageRate = aux/cont;
		return true;
	}


	/**
	 * @return the iniDate
	 */
	public LocalDate getIniDate() {
		return iniDate;
	}

	/**
	 * @param iniDate the iniDate to set
	 */
	public void setIniDate(LocalDate iniDate) {
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


	/**
	 * @return the app
	 */
	public Application getApp() {
		return app;
	}


	/**
	 * @param app the app to set
	 */
	public void setApp(Application app) {
		this.app = app;
	}


	/**
	 * @return the changes
	 */
	public String getChanges() {
		return changes;
	}


	/**
	 * @param changes the changes to set
	 */
	public void setChanges(String changes) {
		this.changes = changes;
	}


	/**
	 * @return the changesDate
	 */
	public LocalDate getChangesDate() {
		return changesDate;
	}


	/**
	 * @param changesDate the changesDate to set
	 */
	public void setChangesDate(LocalDate changesDate) {
		this.changesDate = changesDate;
	}

	
}
