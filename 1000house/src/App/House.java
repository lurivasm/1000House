package App;
import java.util.*;

/**
 * @author Daniel Santo-Tomas 
 * @author Lucia Rivas Molina
 *
 */
public class House {
	
	private String Location;
	private String Description;
	private List<Characteristics> Characteristics = new ArrayList<Characteristics>();
	
	/**
	 * @param location
	 * @param description
	 * @param characteristics
	 */
	public House(String location, String description, List<App.Characteristics> characteristics) {
		Location = location;
		Description = description;
		Characteristics = characteristics;
	}

	/**
	 * @return
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		Location = location;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return
	 */
	public List<Characteristics> getCharacteristics() {
		return Characteristics;
	}

	/**
	 * @param characteristics
	 */
	public void setCharacteristics(List<Characteristics> characteristics) {
		Characteristics = characteristics;
	}
	
	
	
}
