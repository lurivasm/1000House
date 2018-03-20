/**
 * 
 */
package App;
import java.util.*;

/**
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class Text extends Comment{
	private String text;
	private List<Comment> answers = new ArrayList<Comment>();
	/**
	 * 
	 */
	public Text() {
		
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
	public void addAnswer() {
		this.answers = answers;
	}
	
}
