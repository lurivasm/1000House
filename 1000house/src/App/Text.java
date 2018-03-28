/**
 * 
 */
package App;
import java.util.*;
import java.io.*;

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
	private static final long serialVersionUID = 1L;
	private String text;
	private List<Comment> answers;
	
	/**
	 * 
	 */
	public Text(String text, User user, Offer offer) {
		super(user, offer);
		this.text = text;	
		this.answers = new ArrayList<Comment>();
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text the text to set
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
