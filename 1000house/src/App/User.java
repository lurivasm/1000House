package App;

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
	private Aplication app;
	
	/**
	 * @param name
	 * @param surname
	 * @param username
	 * @param password
	 */
	
	public User(String name, String surname, String username, String password,Aplication app) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.app = app;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	
		
	
}
