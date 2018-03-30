/**
 *
 */
package App;

/**
 * Abstract Class Comment
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public abstract class Comment implements Serializable{
	private User user;
	private Offer offer;

	/**
	* Constructor of Comment
	* @param user The user who writes the comment
	* @param offer The offer that recibes this comment
	* @return new Comment
	*/
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
