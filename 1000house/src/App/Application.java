/**
 * 
 */
package App;
import java.util.*;
import Exception.*;
import java.io.*;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class Application implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String admNIF;
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
		this.admNIF = admUsername;
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
	public String getAdmNIF() {
		return admNIF;
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
	public void setAdmNIF(String admNIF) {
		this.admNIF = admNIF;
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


	/**
	 * Creates a house and ads it to the host's house list
	 * @param description
	 * @param characteristics
	 * @param location
	 * @param zipcode
	 * @return true if the house is created correctly, false otherwise
	 * @throws NotHost if the user who tries to create a house is not a host
	 */
	public Boolean createHouse(String description, List<Characteristics> characteristics, String location,long zipcode)  throws NotHost{
		if(log.getState().equals(UserStates.CONNECTED_HOST)) {
			House h = new House(location, description, characteristics,zipcode,log);
			log.getHostProfile().getHouses().add(h);
			return true;
		}
		else {
			throw new NotHost();
		}
		
	}
	
	/**
	 * Search in the app offer list the ones that have been bought
	 * @return a list of bought offers
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchBought() throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : offers) {
				if(o.getState().equals(OfferStates.BOUGHT) == true) {
					res.add(o);
				}
			}
			return res;
		}
		else {
			throw new NotRegisteredUser();
		}
	}
	
	/**
	 * Search in the app offer list the ones that have been reserved
	 * @return a list of reserved offers
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchBooked() throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : offers) {
				if(o.getState().equals(OfferStates.RESERVED) == true) {
					res.add(o);
				}
			}
			return res;
		}
		else {
			throw new NotRegisteredUser();
		}
	}
	
	/**
	 * Search in the app offer list the ones that have the rate given or more
	 * @param rate
	 * @return a list of offers with an average rate equal or higher than the rate given
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchRate(double rate) throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : offers) {
				if(o.getAverageRate() >= rate) {
					res.add(o);
				}
			}
			return res;
		}
		else {
			throw new NotRegisteredUser();
		}
	}
	
	
	
	public Boolean login(String username, String password) throws Exception {
		try{
			ObjectInputStream entradaObjetos = 	new ObjectInputStream(new FileInputStream( "app.objectData" ));
		}
		catch(FileNotFoundException excep1) {
			BufferedReader buffer = new BufferedReader(	new InputStreamReader(new FileInputStream("/home/danist/Documentos/UAM/PADSOF/Padsof/1000house/text/users.txt")));
			String line;
			line = buffer.readLine();
			while((line = buffer.readLine()) != null) {
				String[] words = line.split(";");
				String[] fullname = words[2].split(",");
				try{
					for(User u : users ) {
						if(u.getNIF().equals(words[2])) {
							throw new InvalidNIF(words[2]);
						}
					}
					users.add(new User(fullname[1], fullname[0], words[3], words[1], words[0], words[4], this));
				}
				catch(InvalidNIF excep2) {
					System.out.println(excep2);
				}
			}
			/*We also create the Admin Profile*/
			users.add(new User("Admin", "istrator",admPassword, admNIF ))
		}
	}
	
	
	
}

