/**
 * 
 */
package App;
import Exception.*;
import java.io.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public abstract class Profile implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5833794154819821835L;
	private String ccNumber;
	

	/**
	 * @param ccNumber : Credit Card number
	 */
	public Profile(String ccNumber) {
		this.ccNumber = ccNumber;
		
	}

	/**
	 * @return the Credit Card number
	 */
	public String getccNumber() {
		return ccNumber;
	}

	/**
	 * @param cCNumber
	 * @param app
	 * @return true if everything is correct, false otherwise
	 * @throws NotAdmin
	 */
	public Boolean changeCCNumber(String cCNumber, Application app) throws NotAdmin {
		if(app.getAdmNIF().equals(app.getLog().getNIF()) == true && app.getAdmPassword().equals(app.getLog().getPassword()) == true) {
			ccNumber = cCNumber;
			return true;
		}
		else {
			throw new NotAdmin();
		}
		
	}
	
}
