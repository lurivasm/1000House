/**
 * 
 */
package App;
import java.util.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public abstract class Offer {
	
	private String iniDate;
	private int price;
	private OfferStates state;
	private double averageRate;
	private List<Comment> comments = new ArrayList<Comment>(); 
	private House house;
	
	/**
	 * @return New Offer
	 */
	public Offer(String iniDate, int price, House house) {
		this.iniDate = iniDate;
		this.price = price;
		this.house = house;
		this.averageRate = 0;
		this.state = OfferStates.WAITING;
	}
	
	
	public Boolean isReserved() {
		if (OfferStates.RESERVED.compareTo(state) == 0) return true;
		return false;
	}
	
	public Boolean isBought() {
		if (OfferStates.BOUGHT.compareTo(state) == 0) return true;
		return false;
	} 
	
	public Boolean bookOffer() {
		if (this.isReserved() == true || this.isBought() == true) return false;
		this.setState(OfferStates.RESERVED);
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
	
	public Boolean compareOffer(Offer offer) {
		return true;
	}

}
