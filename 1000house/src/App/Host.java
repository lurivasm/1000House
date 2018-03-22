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

	
	
	
	
}
