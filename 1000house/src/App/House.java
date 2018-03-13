package App;
import java.util.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
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
	 * @return Location of the house
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
	 * @return Description odf the house
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
	 * @return list of the House characteristics
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
