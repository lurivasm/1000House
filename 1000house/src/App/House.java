package App;
import java.util.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class House {
	
	private String location;
	private String description;
	private List<Characteristics> characteristics = new ArrayList<Characteristics>();
	private User host;
	private List<Offer> offers = new ArrayList<Offer>(2);
	
	/**
	 * @param location
	 * @param description
	 * @param characteristics
	 * @param host
	 */
	public House(String location, String description, List<Characteristics> characteristics, User host) {
		this.location = location;
		this.description = description;
		this.characteristics = characteristics;
		this.host = host;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the characteristics
	 */
	public List<Characteristics> getCharacteristics() {
		return characteristics;
	}

	/**
	 * @param characteristics the characteristics to set
	 */
	public void setCharacteristics(List<Characteristics> characteristics) {
		this.characteristics = characteristics;
	}

	/**
	 * @return the host
	 */
	public User getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(User host) {
		this.host = host;
	}

	/**
	 * @return the offers
	 */
	public List<Offer> getOffers() {
		return offers;
	}


	
	
	
}
