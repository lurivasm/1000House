package Exception;

/**
 * @author eps
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
