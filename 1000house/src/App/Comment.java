/**
 * 
 */
package App;
import java.io.*;
/**
 * Abstract Class Comment
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public abstract class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Offer offer;
	
	
	public Comment(User user, Offer offer) {
		this.user = user;
		this.offer = offer;
	}
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}
	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	

}
