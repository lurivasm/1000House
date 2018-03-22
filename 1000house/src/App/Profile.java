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
	
	

	public Profile(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getccNumber() {
		return ccNumber;
	}

	public Boolean changeCCNumber(String cCNumber, String username, String password, Aplication app) {
		if(app.getAdmUsername().equals(username) == true && app.getAdmPassword().equals(password) == true) {
			ccNumber = cCNumber;
			return true;
		}
		return false;
	}
	
	

}
