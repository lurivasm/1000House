package App;

import java.util.*;

/**
 * @author eps
 *
 */
public class Host extends Profile {
	
	public List<House> houses= new ArrayList<House>();
	
	/**
	 * @param ccNumber
	 */
	
	public Host(String ccNumber) {
		super(ccNumber);
	}

	/**
	 * @return the houses
	 */
	public List<House> getHouses() {
		return houses;
	}
	
	public void printHouses() {
		for(House h : houses) {
			System.out.println("Location" +  h.getLocation() + "\n" + "Description" + h.getDescription() + "\n");
		}
	}

	
	
	
	
}
