/**
 * 
 */
package app;
import java.util.*;

import exception.*;

import java.io.*;
import modifiableDates.*;;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class Application implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String admNIF = "11235813F";
	private String admPassword = "Fibonacci";
	private User log;
	private List<User> users= new ArrayList<User>();
	private List<Offer> avoffers = new ArrayList<Offer>();
	private List<Offer> waitoffers = new ArrayList<Offer>();
	
	
	
	/**
	 * @param name
	 */
	public Application(String name) {
		this.name = name;
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
	 * @return the list of available offers
	 */
	public List<Offer> getavoffers(){
		return avoffers;

	}

	
	
	/**
	 * This getter is only accessible for the admin
	 * @return the list of available offers
	 */
	public List<Offer> getwaitoffers() throws NotAdmin {
		if(log.isAdmin() == true) {
			return waitoffers;
		}
		else {
			throw new NotAdmin();
		}
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
	 * @param Avoffers the avoffers to set
	 */
	public void setavoffers(List<Offer> avoffers) {
		this.avoffers = avoffers;
	}

	
	/**
	 * @param waitoffers the waitoffers to set
	 */
	public void setWaitoffers(List<Offer> waitoffers) {
		this.waitoffers = waitoffers;
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
	 * Search in the app offer list the ones of the type given
	 * @param type of the offer
	 * @return the list of Avavoffers
	 */
	public List<Offer> searchType(String type){
		List<Offer> res = new ArrayList<Offer>();
		if(type.equals("Holidays")) {
			for(Offer o : avoffers) {
				if(o instanceof HolidaysOffer) {
					res.add(o);
				}
			}
		}
		else if(type.equals("Living")) {
			for(Offer o : avoffers) {
				if(o instanceof LivingOffer) {
					res.add(o);
				}
			}
		}
		return res;
	}
	
	
	
	
	/**
	 * Search in the app offer list the ones where the house has the code  given as zip code
	 * @param code of the house
	 * @return the list of avoffers 
	 */
	public List<Offer> searchCode(long code){
		List<Offer> res = new ArrayList<Offer>();
		for(Offer o : avoffers) {
			if(o.getHouse().getZipcode() == code) {
				res.add(o);
			}
		}
		return res;
	}
	
	/**
	 * Search in the app offer list the ones that have been bought
	 * @return a list of bought avoffers
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchBought() throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : avoffers) {
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
	 * @return a list of reserved avoffers
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchBooked() throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : avoffers) {
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
	 * @return a list of avoffers with an average rate equal or higher than the rate given
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchRate(double rate) throws NotRegisteredUser{
		if(log != null) {
			List<Offer> res = new ArrayList<Offer>();
			for(Offer o : avoffers) {
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
	
	
	
	/**
	 * Logs the user with the password and NIF given in the app
	 * If it's the first time that someone logs in, it creates the user list (readed from file) and the admin user.
	 * Otherwise, reads the saved app and restores the values it had the last time someone logged out.
	 * Finally, checks that the user's reserves are still on date to be bought, and then logs the user in
	 * @param NIF of the user
	 * @param password of the user
	 * @return true if the user logs in, false otherwise
	 * @throws InvalidNIF if there is an user in the file with the same NIF that another user
	 * @throws NotRegisteredUser if a user that it's not in the users list tries to log in
	 */
	public Boolean login(String NIF, String password) throws Exception {
		try{
			ObjectInputStream savedObject = new ObjectInputStream(new FileInputStream( "/home/danist/Documentos/UAM/PADSOF/Padsof/1000house/text/" + name + ".objectData" ));
			Application app = (Application)savedObject.readObject();
			savedObject.close();
			this.admNIF = app.getAdmNIF();
			this.admPassword = app.getAdmPassword();
			this.users = app.getUsers();
			this.avoffers = app.getavoffers();
		}
		catch(FileNotFoundException excep1) {
			System.out.println("CArgando");
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
			users.add(new User("Admin", "istrator",admPassword, admNIF, "A", null, this ));
			buffer.close();
		}
		
		try {
			for(User u : users) {
				if(u.getNIF().equals(NIF) == true && u.getPassword().equals(password) == true) {
					log = u;
					
					if(log.isHost() == true) {
						log.setState(UserStates.CONNECTED_HOST);
						return true;
					}
					else if(log.isGuest() == true) {
						log.setState(UserStates.CONNECTED_GUEST);
						for(Reserve r : log.getGuestProfile().getReserves()) {
							if(r.getDate().getYear() == ModifiableDate.getModifiableDate().getYear()) {
								if(r.getDate().getMonthValue() == ModifiableDate.getModifiableDate().getMonthValue()) {
									if((ModifiableDate.getModifiableDate().getDayOfMonth() - r.getDate().getDayOfMonth()) > 5 ) {
										log.getGuestProfile().getReserves().remove(r);
									}
								}
								else if(r.getDate().getMonthValue() < ModifiableDate.getModifiableDate().getMonthValue()) {
									log.getGuestProfile().getReserves().remove(r);
								}
							}
							else if(r.getDate().getYear() < ModifiableDate.getModifiableDate().getYear()) {
								log.getGuestProfile().getReserves().remove(r);
							}
						}
						return true;
					}
					else{
						log.setState(UserStates.ADMIN);
						return true;
					}
					
				}
				
			}
			throw new NotRegisteredUser();
			
		}
		catch(NotRegisteredUser excep){
			System.out.println(excep);
			return false;
		}
		
		
		
	}
	
	/**
	 * Logs the user out of the app, saving the state of the app in an .objectData
	 * @return true if everything is correct, false otherwise
	 * @throws Exception
	 */
	public Boolean logout() throws Exception {
		try {
			ObjectOutputStream outputObject = new ObjectOutputStream(
					new FileOutputStream("/home/danist/Documentos/UAM/PADSOF/Padsof/1000house/text/" + name + ".objectData"));
			
			log.setState(UserStates.DISCONNECTED);
			outputObject.writeObject(this);
			outputObject.close();
			return true;
		} catch (Exception excep) {
			System.out.println(excep);
			return false;
		}
	}
	
	
	
	
	
	/**
	 * Creates a Living offer, adding it to the waiting for review list of the app and to the house offers list
	 * @param house :the house of the offer
	 * @param iniDate : date when the offer begins
	 * @param numMonths : number of months of the offer
	 * @param price :price per month of the offer
	 * @return true if everything is correct, false otherwise
	 * @throws NotHost if an user who is not a host tries to create an offer
	 * @throws NotOwner if a host tries to create an offer on a house that doesn't belong to him
	 * @throws HouseOfferException if the house already has an offer of that type
	 */
	public Boolean createOffer(House house, String iniDate, int numMonths, int price) throws Exception {
		try {
			if(log.getState() != UserStates.CONNECTED_HOST) {
				throw new NotHost();
			}
			if(log.getHostProfile().getHouses().contains(house) == false) {
				throw new NotOwner();
			}
			LivingOffer o = new LivingOffer(iniDate, price, house, this, numMonths); 
			house.getOffers().add(o);
			waitoffers.add(o);
			return true;
		}
		catch(NotHost excep1) {
			System.out.println(excep1);
			return false;
		}
		catch (HouseOfferException excep2) {
			System.out.println(excep2);
			return false;
		}
		catch(NotOwner excep3) {
			System.out.println(excep3);
			return false;
		}
	}
	
	/**
	 * Creates a Holiday offer, adding it to the waiting for review list of the app and to the house offers list
	 * @param house :the house of the offer
	 * @param iniDate : date when the offer begins
	 * @param endDate : date when the offer ends
	 * @param price :price of the hole holiday
	 * @return true if everything is correct, false otherwise
	 * @throws NotHost if an user who is not a host tries to create an offer
	 * @throws NotOwner if a host tries to create an offer on a house that doesn't belong to him
	 * @throws HouseOfferException if the house already has an offer of that type
	 */
	public Boolean createOffer(House house, String iniDate, String endDate, int price) throws Exception {
		try {
			if(log.getState() != UserStates.CONNECTED_HOST) {
				throw new NotHost();
			}
			HolidaysOffer o = new HolidaysOffer(iniDate, price, house, this, endDate); 
			house.getOffers().add(o);
			waitoffers.add(o);
			return true;
		}
		catch(NotHost excep1) {
			System.out.println(excep1);
			return false;
		}
		catch (HouseOfferException excep2) {
			System.out.println(excep2);
			return false;
		}
	}
	
}

