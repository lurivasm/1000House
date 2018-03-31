/**
 *
 */
package App;
import java.io.Serializable;


import Exception.*;

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
	public LivingOffer(String iniDate, int price, House house, Application app, int numMonths) throws HouseOfferException {
		super(iniDate, price, house, app);

		for (Offer o : house.getOffers()) {
			if (o instanceof LivingOffer) {
				throw new HouseOfferException();
			}
		}
		this.numMonths = numMonths;

	}

  /**
  * When the offer is asked for changes, the host can change its characteristics
  * @param iniDate the starting date
  * @param price the price of the offer
  * @param house the offerted house
  * @param numMonths the number of days the guest can be there
  *	@throws NotHost if the user is not a host
  * @throws NotOwner if the user who tries to modify the offer does not own it
  * @return Boolean
  */
  public Boolean changeOffer(String iniDate, int price, House house, int numMonths) throws NotHost, NotOwner{
    /*Case the user is not a host*/
    if (app.getLog().getState().equals(UserStates.CONNECTED_HOST) == false) throw new NotHost();
    /*Case the host is not the owner of the offer*/
    if (this.house.getHost().equals(app.getLog()) == false) throw new NotOwner();
    /*Case the offer has not the state CHANGES*/
    if (this.getState().equals(OfferStates.CHANGES) == false) return false;

    this.iniDate = iniDate;
	this.price = price;
	this.house = house;
    this.numMonths = numMonths;
    return true;
  }

  	/**
  	 * @return the numMonths
  	 */
  	public int getnumMonths() {
  		return numMonths;
  	}

  	/**
  	 * @param numMonths the numMonths to set
  	 */
  	public void setnumMonths(int numMonths) {
  		this.numMonths = numMonths;
  	}
}
