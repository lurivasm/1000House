/**
 *
 */
package app;
import java.io.Serializable;
import java.util.*;

import exception.*;

/**
 * Class Text
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class Text extends Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3467003339082362870L;
	private String text;
	private List<Comment> answers;

	/**
	 * Constructor of a text
	 * @param text the text to write
	 * @param user the user who writes the text
	 * @param offer the offer who is being commented
	 * @return new Text
	 * @throws TextException when the text is over 150 characters
	 */
	public Text(String text, User user, Offer offer) throws TextException{
		super(user, offer);
		if (text.length() > 250) throw new TextException();
		this.text = text;
		this.answers = new ArrayList<Comment>();
	}


	/**
	* Adds a new text to the array of answers
	* @param text the text to write
	* @param user the user who writes the text
	* @param offer the offer who is being commented
	* @return Boolean if the function has been successful
	*/
	public Boolean addAnswer(String text, User user, Offer offer) throws TextException, NotGuest{
		if(offer.app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();
		Text t = new Text(text, user, offer);
		if (answers.add(t) == false) return false;
		return true;
	}

	/**
	* Adds a new rate to the array of answers
	* @param rate the rate to write
	* @param user the user who writes the rate
	* @param offer the offer who is being commented
	* @return Boolean if the function has been successful
	*/
	public Boolean addAnswer(int rate, User user, Offer offer) throws RateException, NotGuest{
		if(offer.app.getLog().getState().equals(UserStates.CONNECTED_GUEST) == false) throw new NotGuest();
		Rate r = new Rate(rate, user, offer);
		if (answers.add(r) == false) return false;
		return true;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to write
	 * @throws TextException when the text is over 150 characters
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the answers
	 */
	public List<Comment> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void addAnswer(Comment answer) {
		this.answers.add(answer);
	}

}
