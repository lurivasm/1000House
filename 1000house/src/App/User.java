package App;
import java.util.*;
import Exception.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class User {
	private String name;
	private String surname;
	private String username;
	private String password;
	private UserStates state = UserStates.DISCONNECTED;
	private List<Profile> profile; 
	private Application app;
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	
	public User(String name, String surname, String username, String password, String profile, String CCNumber,Application app ) throws WrongProfile {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
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
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
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
		

		public Boolean bannUser() throws NotAdmin {
			if(app.getLog().getUsername().equals(app.getAdmUsername()) == true && app.getLog().getPassword().equals(app.getAdmPassword()) == true){
				state = UserStates.BANNED;
				app = null;
				return true;
			}
			else {
				throw new NotAdmin();
			}
		}
		
		public Boolean restoreUser(Application app) throws NotAdmin{
			if(app.getLog().getUsername().equals(app.getAdmUsername()) == true && app.getLog().getPassword().equals(app.getAdmPassword()) == true){
				state = UserStates.DISCONNECTED;
				this.app = app;
				return true;
			}
			else {
				throw new NotAdmin();
			}
		}
		
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
	
}

	