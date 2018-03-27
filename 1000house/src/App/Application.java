/**
 * 
 */
package App;
import java.util.*;
import Exception.*;
/**
 * @author eps
 *
 */
public class Application {

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
	public Application(String name, String admUsername, String admPassword) {
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
	
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param admUsername the admUsername to set
	 */
	public void setAdmUsername(String admUsername) {
		this.admUsername = admUsername;
	}


	/**
	 * @param admPassword the admPassword to set
	 */
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}


	/**
	 * @param log the log to set
	 */
	public void setLog(User log) {
		this.log = log;
	}


	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}


	/**
	 * @param offers the offers to set
	 */
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


	public Boolean createHouse(String description, List<Characteristics> characteristics, String location)  throws NotHost{
		if(log.getState().equals(UserStates.CONNECTED_HOST)) {
			House h = new House(location, description, characteristics,log);
			log.getHostProfile().getHouses().add(h);
			return true;
		}
		else {
			throw new NotHost();
		}
		
	}

	
	
}

