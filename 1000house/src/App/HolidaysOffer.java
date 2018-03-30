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
public class HolidaysOffer extends Offer implements Serializable{
  private String endDate;

  /**
  *Constructor of HolidaysOffer
  * @return new HolidaysOffer
  */
  public HolidaysOffer(String iniDate, int price, House house, Application app, String endDate){
    super(iniDate, price, house, app);
    this.endDate = endDate;
  }

  /**
	* When the offer is asked for changes, the host can change its characteristics
  * @param iniDate the starting date
  * @param price the price of the offer
  * @param house the offerted house
  * @param endDate the ending date
	*	@throws NotHost if the user is not a host
	* @throws NotOwner if the user who tries to modify the offer does not own it
	* @return Boolean
	*/
	public Boolean changeOffer(String iniDate, int price, House house, String endDate) throws NotHost, NotOwner{
		/*Case the user is not a host*/
		if (app.getLog().getState().equals(UserStates.CONNECTED_HOST) == false) throw new NotHost;
		/*Case the host is not the owner of the offer*/
		if (this.house.getHost().equals(app.getLog()) == false) throw new NotOwner;
		/*Case the offer has not the state CHANGES*/
		if (this.getState().equals(OfferStates.CHANGES) == false) return false;

		setIniDate(iniDate);
		setPrice(price);
		setHouse(house);
    setEndDate(endDate);
		return true;
	}


  	/**
  	 * @return the endDate
  	 */
  	public String getendDate() {
  		return endDate;
  	}

  	/**
  	 * @param endDate the endDate to set
  	 */
  	public void setEndDate(String iniDate) {
  		this.endDate = endDate;
  	}
}
