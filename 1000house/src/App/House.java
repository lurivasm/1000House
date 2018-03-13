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
	
	/**
	 * @param location
	 * @param description
	 * @param characteristics
	 */
	public House(String location, String description, List<App.Characteristics> characteristics) {
		this.location = location;
		this.description = description;
		this.characteristics = characteristics;
	}

	/**
	 * @return Location of the house
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return Description odf the house
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return list of the House characteristics
	 */
	public List<Characteristics> getCharacteristics() {
		return characteristics;
	}

	/**
	 * @param characteristics
	 */
	public void setCharacteristics(List<Characteristics> characteristics) {
		this.characteristics = characteristics;
	}
	
	
	
}
