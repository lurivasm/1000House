/**
 * 
 */
package App;
import java.util.*;

/**
 * @author eps
 *
 */
public class Aplication {

	private String name;
	private String admUsername;
	private String admPassword;
	private User log;
	private List<User> users= new ArrayList<User>();
	private List<Offer> offers = new ArrayList<Offer>();
	
	
	/**
	 * @param name
	 * @param admUsername
	 * @param admPassword
	 */
	public Aplication(String name, String admUsername, String admPassword) {
		this.name = name;
		this.admUsername = admUsername;
		this.admPassword = admPassword;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
		
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}


	/**
	 * @return
	 */
	public String getAdmUsername() {
		return admUsername;
	}


	
	/**
	 * @return
	 */
	public String getAdmPassword() {
		return admPassword;
	}

	
	/**
	 * @return
	 */
	public User getLog() {
		return log;
	}


	
	
}
	
	/*public List<Offer> searchType(OfferType type){
		List<Offer> res = new ArrayList<Offer>();
		for(Offer o : offers) {
			if (o instanceof HolidaysOffer) {
				
			}
		}
		
		
		return res;
	}
	
}*/
