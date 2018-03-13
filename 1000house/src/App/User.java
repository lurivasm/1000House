package App;

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
	private UserStates State = UserStates.Disconnected;
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	
	public User(String name, String surname, String username, String password) {
		super();
		Name = name;
		Surname = surname;
		Username = username;
		Password = password;
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
