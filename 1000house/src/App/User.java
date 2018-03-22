package App;
import java.util.*;
import Exception.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class User {
	private String Name;
	private String Surname;
	private String Username;
	private String Password;
	private UserStates State = UserStates.DISCONNECTED;
	private List<Profile> profile; 
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	
	public User(String name, String surname, String username, String password, String profile, String CCNumber) throws wrongProfile {
		Name = name;
		Surname = surname;
		Username = username;
		Password = password;
		if(profile.equals("D") == true) {
			this.profile  = new ArrayList<Profile>(1);
			Profile p =  new Guest(CCNumber);
			this.profile.add(p) ;
		}
		else if (profile.equals("O") == true) {
			this.profile = new ArrayList<Profile>(1);
			Profile p =  new Host(CCNumber);
			this.profile.add(p) ;
		}
		else if (profile.equals("OD")) {
			this.profile = new ArrayList<Profile>(2);
			Profile p1 =  new Host(CCNumber);
			Profile p2 =  new Guest(CCNumber);
			this.profile.add(p1) ;
			this.profile.add(p2) ;
		}
		else {
			throw new wrongProfile(profile);
		}
		
	}

	/**
	 * @return the state
	 */
	public UserStates getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(UserStates state) {
		State = state;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return Surname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	
	
		
	
}
