/**
 * 
 */
package App;

/**
 * @author eps
 *
 */
public abstract class Profile {
	
	private String ccNumber;
	private Aplication app;
	
	
	

	public Profile(String ccNumber, Aplication app) {
		this.ccNumber = ccNumber;
		this.app = app;
	}

	public String getccNumber() {
		return ccNumber;
	}

	public Boolean changeCCNumber(String cCNumber, String username, String password) {
		if(app.getAdmUsername().equals(username) == true && app.getAdmPassword().equals(password) == true) {
			ccNumber = cCNumber;
			return true;
		}
		return false;
		
	}
	
	

}
