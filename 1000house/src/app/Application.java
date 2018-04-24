/**
 * 
 */
package app;
import java.util.*;

import exception.*;

import java.io.*;
import java.time.LocalDate;

import modifiableDates.*;
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
	private User log = null;
	private List<User> users= new ArrayList<User>();
	private List<Offer> avoffers = new ArrayList<Offer>();
	private List<Offer> waitoffers = new ArrayList<Offer>();
	
	
		
	/**
	 * Sets the name of the application and if it's the first time that someone creates that app, it creates the user list (readed from file) and the admin user.
	 * Otherwise, reads the saved app and restores the values it had the last time someone created it.
	 * @param name
	 */
	public Application(String name) throws Exception {
		this.name = name;
		try{
			ObjectInputStream savedObject = new ObjectInputStream(new FileInputStream( name + ".objectData" ));
			Application app = (Application)savedObject.readObject();
			savedObject.close();
			this.admNIF = app.getAdmNIF();
			this.admPassword = app.getAdmPassword();
			this.users = app.getUsers();
			this.avoffers = app.getavoffers();
			this.waitoffers = app.getwaitoffers();
			for(Offer o : avoffers) {
				o.setApp(this);
			}
			for(Offer o : waitoffers) {
				o.setApp(this);
			}
		}
		catch(FileNotFoundException excep1) {
			
			BufferedReader buffer = new BufferedReader(	new InputStreamReader(new FileInputStream("users.txt")));
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
	public List<Offer> getwaitoffers() {
		return waitoffers;	
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
	public Boolean createHouse(String description, List<Characteristics> characteristics, String location,long zipcode)  throws Exception{
		try{
			if(log.getState().equals(UserStates.CONNECTED_HOST)) {
				House h = new House(location, description, characteristics,zipcode,log);
				for(Offer o : avoffers){
					if(o.getHouse().compareHouse(h)){
						throw new HouseException();
					}
				}
				for(Offer o : waitoffers){
					if(o.getHouse().compareHouse(h)){
						throw new HouseException();
					}
				}
				for(House house : log.getHostProfile().getHouses()) {
					if(h.compareHouse(house)) {
						throw new HouseException();
					}
					
				}
				log.getHostProfile().getHouses().add(h);
				return true;
			}
			else {
				throw new NotHost();
			}
		}
		catch(NotHost excep1){
			System.out.println(excep1);
			return false;
		}
		catch(HouseException excep2){
			System.out.println(excep2);
			return false;
		}
		
		
	}
	
	
	
	
	/**
	 * Search in the app offer list the ones of the type given
	 * @param type of the offer
	 * @return the list of avoffers
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
	 * Search in the app the offers that are between from date and to date
	 * @param from
	 * @param to
	 * @return the list of offers
	 */
	public List<Offer> searchDate(LocalDate from, LocalDate to){
		from = from.minusDays(1);
		to = to.plusDays(1);
		List<Offer> res = new ArrayList<Offer>();
		for(Offer o: avoffers) {
			if(o instanceof LivingOffer) {
				LocalDate end = o.getIniDate().plusMonths(((LivingOffer) o).getnumMonths());
				if(o.getIniDate().isAfter(from) && end.isBefore(to)) {
					res.add(o);
				}
			}
			else if(o instanceof HolidaysOffer) {
				if(o.getIniDate().isAfter(from) && ((HolidaysOffer) o).getendDate().isBefore(to)) {
					res.add(o);
				}
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
		try{
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
		catch(NotRegisteredUser excep){
			System.out.println(excep);
			return null;
		}
		
	}
	
	/**
	 * Search in the app offer list the ones that have been reserved
	 * @return a list of reserved avoffers
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchBooked() throws NotRegisteredUser{
		try{
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
		catch(NotRegisteredUser excep){
			System.out.println(excep);
			return null;
		}
		
	}
	
	/**
	 * Search in the app offer list the ones that have the rate given or more
	 * @param rate
	 * @return a list of avoffers with an average rate equal or higher than the rate given
	 * @throws NotRegisteredUser if the user who tries to search is not registered
	 */
	public List<Offer> searchRate(double rate) throws NotRegisteredUser{
		try{
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
		catch(NotRegisteredUser excep){
			System.out.println(excep);
			return null;
		}
		
	}
	
	
	/**
	 * Checks that the offers asked for changes of the user given are on date to be changed.
	 * If it isn't on date,it removes it from the waiting-for-review app list and set it state to DENIED
	 * @param u : the user
	 * @param app : app were the user is trying to log
	 * @return true
	 * @throws NotHost
	 */
	private Boolean checkOffers(User u, Application app) throws NotHost{
		for(House h  : u.getHostProfile().getHouses()) {
			for(Offer o:h.getOffers()) {
				if(o.getState().equals(OfferStates.CHANGES)) {
					if(o.getChangesDate().getYear() == ModifiableDate.getModifiableDate().getYear()) {
						if(o.getChangesDate().getMonthValue() == ModifiableDate.getModifiableDate().getMonthValue()) {
							if((ModifiableDate.getModifiableDate().getDayOfMonth() - o.getChangesDate().getDayOfMonth()) > 5 ) {
								app.getwaitoffers().remove(o);
								o.setState(OfferStates.DENIED);
								return true;
							}
						}
						else if(o.getChangesDate().getMonthValue() < ModifiableDate.getModifiableDate().getMonthValue()) {
							app.getwaitoffers().remove(o);
							o.setState(OfferStates.DENIED);
							return true;
						}
					}
					else if(o.getChangesDate().getYear() < ModifiableDate.getModifiableDate().getYear()) {
						app.getwaitoffers().remove(o);
						o.setState(OfferStates.DENIED);
						return true;
					}
				}
			}
		}
		return true;
	}
	
	
	
	/**
	 * Checks that the user's reserves are still on date to be bought.
	 * If it isn't on date,it removes it from the user-reserves list 
	 * @param u
	 * @return
	 * @throws NotGuest
	 */
	private Boolean checkReserves(User u) throws NotGuest{
		for(Reserve r : u.getGuestProfile().getReserves()) {
			if(r.getDate().getYear() == ModifiableDate.getModifiableDate().getYear()) {
				if(r.getDate().getMonthValue() == ModifiableDate.getModifiableDate().getMonthValue()) {
					if((ModifiableDate.getModifiableDate().getDayOfMonth() - r.getDate().getDayOfMonth()) > 5 ) {
						r.cancelReserve();
						return true;
					}
				}
				else if(r.getDate().getMonthValue() < ModifiableDate.getModifiableDate().getMonthValue()) {
					r.cancelReserve();
					return true;
				}
			}
			else if(r.getDate().getYear() < ModifiableDate.getModifiableDate().getYear()) {
				r.cancelReserve();
				return true;
			}
		}
		return true;
	}
	/**
	 * Logs the user with the password and NIF given in the app.
	 * Also checks that the offers asked for changes and the reserves are still on date to be changed(offers9 or paid(reserves)
	 * Then logs the user in
	 * @param NIF of the user
	 * @param password of the user
	 * @return true if the user logs in, false otherwise
	 * @throws InvalidNIF if there is an user in the file with the same NIF that another user
	 * @throws NotRegisteredUser if a user that it's not in the users list tries to log in
	 */
	public Boolean login(String NIF, String password) throws Exception {
		try {
			for(User u : users) {
				if(u.getNIF().equals(NIF) == true && u.getPassword().equals(password) == true) {
					log = u;
					
					if(log.isHost() == true) {
						log.setState(UserStates.CONNECTED_HOST);
						checkOffers(log,this);
						return true;
					}
					else if(log.isGuest() == true) {
						log.setState(UserStates.CONNECTED_GUEST);
						checkReserves(log);
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
					new FileOutputStream(name + ".objectData"));
			
			if(log.getState().equals(UserStates.BANNED) == false) log.setState(UserStates.DISCONNECTED);
			outputObject.writeObject(this);
			outputObject.close();
			log = null;
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
	public Boolean createOffer(House house, LocalDate iniDate, int numMonths, int price, int deposit) throws Exception {
		try {
			if(log.getState() != UserStates.CONNECTED_HOST) {
				throw new NotHost();
			}
			if(log.getHostProfile().getHouses().contains(house) == false) {
				throw new NotOwner();
			}
			LivingOffer o = new LivingOffer(iniDate, price, deposit, house, this, numMonths); 
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
	public Boolean createOffer(House house, LocalDate iniDate, LocalDate endDate, int price, int deposit) throws Exception {
		try {
			if(log.getState() != UserStates.CONNECTED_HOST) {
				throw new NotHost();
			}
			if(log.getHostProfile().getHouses().contains(house) == false) {
				throw new NotOwner();
			}
			HolidaysOffer o = new HolidaysOffer(iniDate, price, deposit, house, this, endDate); 
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
	
	public List<User> getBannedUsers() throws NotAdmin{
		try {
			if(log.isAdmin() == false) {
				throw new NotAdmin();
			}
			List<User> res = new ArrayList<User>();
			for(User u : users) {
				if(u.getState().equals(UserStates.BANNED)) {
					res.add(u);
				}
			}
			return res;
		}
		catch(NotAdmin excep) {
			System.out.println(excep);
			return null;
		}
		
	}
	
}

