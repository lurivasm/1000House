package App;
import java.util.*;
import Exception.*;
import java.io.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class User implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4303830052594172747L;
	private String name;
	private String surname;
	private String password;
	private String NIF;
	private UserStates state = UserStates.DISCONNECTED;
	private List<Profile> profile; 
	private Application app;
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	
	/**
	 * Constructor of user
	 * @param name of the user
	 * @param surname of the user
	 * @param username of the user
	 * @param password of the user
	 * @param NIF of the user
	 * @param profile : depending of the type of user. O if it's a host , D if it's a guest, and OD if it's both .The administrator will have the profile list empty
	 * @param CCNumber of the user
	 * @param app related to the user
	 * @throws WrongProfile if the type of profile to create is wrong
	 */
	public User(String name, String surname, String password, String NIF, String profile, String CCNumber,Application app ) throws WrongProfile {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.NIF = NIF;
		if(profile.equals("D") == true) {
			this.profile  = new ArrayList<Profile>(1);
			Guest p =  new Guest(CCNumber);
			this.profile.add(p) ;
		}
		else if (profile.equals("O") == true) {
			this.profile = new ArrayList<Profile>(1);
			Host p =  new Host(CCNumber);
			this.profile.add(p) ;
		}
		else if (profile.equals("OD")) {
			this.profile = new ArrayList<Profile>(2);
			Host p1 =  new Host(CCNumber);
			Guest p2 =  new Guest(CCNumber);
			this.profile.add(p1) ;
			this.profile.add(p2) ;
		}
		else if(profile.equals("A")) {
			this.profile = null;
		}
			
		else {
			throw new WrongProfile(profile);
		}
	}
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the surname
		 */
		public String getSurname() {
			return surname;
		}

		/**
		 * @param surname the surname to set
		 */
		public void setSurname(String surname) {
			this.surname = surname;
		}

		
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		

		/**
		 * @return the nIF
		 */
		public String getNIF() {
			return NIF;
		}

		/**
		 * @param nIF the nIF to set
		 */
		public void setNIF(String nIF) {
			NIF = nIF;
		}

		/**
		 * @return the state
		 */
		public UserStates getState() {
			return state;
		}

		/**
		 * @param state the state to set
		 */
		public void setState(UserStates state) {
			this.state = state;
		}

		/**
		 * @return the app
		 */
		public Application getApp() {
			return app;
		}

		/**
		 * @param app the app to set
		 */
		public void setApp(Application app) {
			this.app = app;
		}

		/**
		 * @return the host profile
		 */
		public Host getHostProfile() throws NotHost{
			if(profile.size() == 2 || state.equals(UserStates.CONNECTED_HOST)) {
				return (Host)profile.get(0);
			}
			else {
				throw new NotHost();
			}
		}
		
		/**
		 * @return the guest profile
		 */
		public Guest getGuestProfile() throws NotGuest{
			if(profile.size() == 2) {
				return (Guest)profile.get(1);
			}
			else if(state.equals(UserStates.CONNECTED_GUEST)) {
				return (Guest)profile.get(0);
			}
			else {
				throw new NotGuest();
			}
		}
		

		/**
		 * Bans a user, changing his state to banned an removing his access to the app
		 * @return true if the user is banned
		 * @throws NotAdminm if someone who isn't the admin tries to ban an user
		 */
		public Boolean banUser() throws NotAdmin {
			if(app.getLog().getNIF().equals(app.getAdmNIF()) == true && app.getLog().getPassword().equals(app.getAdmPassword()) == true){
				state = UserStates.BANNED;
				app = null;
				return true;
			}
			else {
				throw new NotAdmin();
			}
		}
		
		/**
		 * Restore an user that had been banned, changing his state to disconnected and restoring his acces to the appp
		 * @param app
		 * @return true if the user is restored correctly
		 * @throws NotAdmin  if someone who isn't the admin tries to restore an user
		 */
		public Boolean restoreUser(Application app) throws NotAdmin{
			if(app.getLog().getNIF().equals(app.getAdmNIF()) == true && app.getLog().getPassword().equals(app.getAdmPassword()) == true){
				state = UserStates.DISCONNECTED;
				this.app = app;
				return true;
			}
			else {
				throw new NotAdmin();
			}
		}
		
		/**
		 * Changes from the Host profile to the guest and from the guest to the host
		 * @param prof
		 * @return true if everything is correct
		 * @throws WrongProfile if the user tries to change to a profile that doesn't exist
		 */
		public Boolean changeProfile(String prof) throws WrongProfile{
			if(prof.equals("D") == true && profile.size() == 2) {
				state = UserStates.CONNECTED_GUEST;
				return true;
			}
			else if (prof.equals("O") == true && profile.size() == 2) {
				state = UserStates.CONNECTED_HOST;
				return true;
			}
			else {
				throw new WrongProfile(prof);
			}
		}
		
		
		public Boolean isHost() {
			if(profile.size() == 2 || profile.get(0) instanceof Host) {
				return true;
			}
			return false;
		}
}

	