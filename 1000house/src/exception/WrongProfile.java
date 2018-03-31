package exception;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class WrongProfile extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String profile;

	/**
	 * @param profile
	 */
	public WrongProfile(String profile) {
		this.profile = profile;
	}

	public String toString(){
		return profile +" is not a tipe of profile";
	}



}
