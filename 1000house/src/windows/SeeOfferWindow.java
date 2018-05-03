package windows;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import app.*;
import exception.*;

import java.util.List;
import java.util.ArrayList;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class SeeOfferWindow  extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2992997500811884016L;
	private Container cp = this.getContentPane(); // container of the JFRame
	private JPanel descrpanel = new JPanel(); // Panel for the house description
	private JPanel infopanel = new JPanel();// Panel for the characteristics, th eprice and the buy and book buttons
	private JPanel menupanel = new JPanel();// Panel for the back button, menu button, ini date, end date or num months, and average rate
	private JPanel commentpanel = new JPanel();// Panel for the comments
	private JPanel centerpanel = new JPanel();// PAnel where the comments and descriptions panels will be located , an also the comment button
	private JPanel buttonpanel = new JPanel();// Panel for the add answer, view answer, prev and next buttons
	private JLabel average ; //LAbel for the average rate
	private JButton back = new JButton("Back");// Back to the offer list button
	private JButton buy = new JButton("Buy");// Buy button
	private JButton book = new JButton("Book");//Book button
	private JButton next = new JButton("Next"); // Button for the next comment
	private JButton prev = new JButton("Prev"); // Button for the prev comment
	private JButton addanswer = new JButton("Add Answer");// Add answer button
	private JButton viewanswer = new JButton("View Answers");///View answers of a comment button
	private JButton addcomment = new JButton("Comment");// Comment an offer button
	private JButton menubutton = new JButton("Main menu"); // Main menu button
	private SpringLayout centerlayout = new SpringLayout();// LAyout for the center panel
	private int comment = 0;// Position in the offer comment list of the comment that is been showed
	private List<Offer >results;// List of results from the search mada before showing the offer
	private int offer;// Number of the offer showed in the window in the results list
	public SeeOfferWindow(List<Offer> results, int offer) {
		super("1000House");
		this.results = results;
		this.offer = offer;
		Offer o = results.get(offer);//We get the offer
		cp.setLayout(new BorderLayout());//We set the layout of the window to bordrr layout
		
		JLabel title; // We create the title of the window
		title = new JLabel(o.getHouse().getLocation());
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",30,30));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
		//Firts we create the east panel with the characteristics, the price and the buy and book buttons
		
		BoxLayout infoLayout =new BoxLayout(infopanel,BoxLayout.Y_AXIS);// We use the box layout
		infopanel.setLayout(infoLayout);	
		infopanel.add(new JPanel());
		JLabel charlabel = new JLabel("Characteristics:"); // We place the characteristics label
		infopanel.add(charlabel);
		infopanel.add(new JPanel());
		//We place the different characteristics under the label
		for(Characteristics c : o.getHouse().getCharacteristics()) {
			JLabel charac = new JLabel("- " + c.toString());
			infopanel.add(charac);
			infopanel.add(new JPanel());
		}
		//We fill the rest of the space with empty panels
		for(int j = 0 ; j< 10  ; j++) {
			infopanel.add(new JPanel());
		}
		//Now we add the price
		JLabel priceLabel;
		if(o instanceof LivingOffer) priceLabel = new JLabel("Price per month :");
		else priceLabel = new JLabel("Total price:");
		infopanel.add(priceLabel);
		JLabel price = new JLabel(Integer.toString(o.getPrice()) + " €");
		price.setFont(new Font("Tahona",30,30));	//We set the price under the priceLabel and make it bigger
		infopanel.add(price);
		//We put some empty panels and add the buy and book buttons
		infopanel.add(new JPanel());
		infopanel.add(new JPanel());
		infopanel.add(buy);
		infopanel.add(new JPanel());
		infopanel.add(book);
		//We fill the rest with empty panels
		for(int j = 0 ; j< (12-o.getHouse().getCharacteristics().size()) ; j++) {
			infopanel.add(new JPanel());
		}				
		//Finally , we add the panel the east part of the window
		infopanel.setPreferredSize(new Dimension(180,200));
		cp.add(infopanel,BorderLayout.EAST);
		
		//Secondly, we create the west panel, with the menu button, the ini date, the end date or the num of months
		//and the average rate
		
		BoxLayout menuLayout = new BoxLayout(menupanel,BoxLayout.Y_AXIS);	//We use again a bocx layout
		menupanel.setLayout(menuLayout);	
		menupanel.add(new JPanel()); 
		menupanel.add(menubutton);//We place the menu button and some empty panels
		menupanel.add(new JPanel());
		menupanel.add(back);
		for(int j = 0 ; j< 5 ; j++) {
			menupanel.add(new JPanel());
		}		
		//We place now the ini date of  the offer
		JLabel ini = new JLabel("  Initial date : " + o.getIniDate().toString());
		menupanel.add(ini);
		menupanel.add(new JPanel());
		//Then,depending on the type of offer, we add the label with the end date or the number of months
		JLabel end; 
		if(o instanceof LivingOffer) end = new JLabel("  Number of months: " + Integer.toString(((LivingOffer) o).getnumMonths()));
		else end = new JLabel("  End date : " + ((HolidaysOffer) o).getendDate().toString());
		menupanel.add(end);
		//We add more empty panels
		for(int j = 0 ; j< 3 ; j++) {
			menupanel.add(new JPanel());
		}	
		//We add the average rate
		JLabel avlabel = new JLabel("  Average rate : ");
		results.get(offer).calculateRate();
		average = new JLabel("  " + Double.toString(o.getAverageRate()) + "/5");
		average.setFont(new Font("Tahona",30,30));
		menupanel.add(avlabel);
		menupanel.add(average);
		for(int j = 0 ; j< 11 ; j++) {
			menupanel.add(new JPanel());
		}	
		//Finally we place the panel in the west part of the window
		menupanel.setPreferredSize(new Dimension(180,300));			
		cp.add(menupanel,BorderLayout.WEST);		
		
		//Finally we create the center panel, with the description of the house and the comment panel
		
		centerpanel.setLayout(centerlayout);		
		JPanel aux;				
		commentpanel.setLayout(new GridLayout(7,1));// For the comment panel we use a grid Layout
		int cont = 0; // We use this cont for counting how many lines the comment has, and fill the rest wit empty panels
		//We go trough the comments list until we find a text comment, putting it on the commen panel,dividing it
		//in lines of 50 characters
		for(Comment c : o.getComments()) {
			if(c instanceof Text) {
				JLabel user = new JLabel(c.getUser().getName() + " :");
				user.setHorizontalAlignment(JTextField.LEFT);
				commentpanel.add(user);
				String[] lines = splitSize(((Text) c).getText(),50);
				for(String s : lines) {
					aux = new JPanel();				
					JLabel com = new JLabel(s);
					aux.add(com);
					commentpanel.add(aux);
					cont++;
				}
				if(((Text) c).getAnswers().isEmpty()) viewanswer.setVisible(false);
				else viewanswer.setVisible(true);
				break;
			}	
			comment++; // We update the comment number if the comment found is not a text comment
		}
		if(cont == 0) commentpanel.setVisible(false);	//If there aren't any text comment, we set this panel as not visible	
		for(int i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());//We fill the rest of lines with empty panels
		//Then we add the prev,add answer,view answer and next buttons to a a panel,adding this one to the comment panel		
		buttonpanel.add(prev);
		buttonpanel.add(addanswer);
		buttonpanel.add(viewanswer);
		buttonpanel.add(next);
		prev.setVisible(false);//Its the first comment, there is not previous comments
		if(o.getComments().size() == comment+1) next.setVisible(false);
		commentpanel.add(buttonpanel);
		commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));//We adda border to the comment panel
		commentpanel.setPreferredSize(new Dimension(430,250));
		//Finally we add the comment panel in the south part of the center panel,using the spring layout
		centerlayout.putConstraint(SpringLayout.SOUTH, commentpanel, -100, SpringLayout.SOUTH, centerpanel);
		centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 80, SpringLayout.WEST, centerpanel);		
		centerpanel.add(commentpanel);
		 //Now we create the description panel
		String[] split = splitSize(o.getHouse().getDescription(),50);//we divide the description in lines of 50 characters
		descrpanel.setLayout(new GridLayout(12,0));
		descrpanel.add(new JPanel());
		descrpanel.add(new JPanel());
		//We ad the description lines, and fil the rest of space with empty panels
		for(String s : split) {
			JLabel description = new JLabel(s);
			description.setHorizontalAlignment(JTextField.CENTER);
			aux = new JPanel();
			aux.add(description);
			descrpanel.add(aux);
		}
		//We place the description panel in the north part of the center panel
		centerlayout.putConstraint(SpringLayout.SOUTH, descrpanel, -30, SpringLayout.NORTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST, descrpanel, 0, SpringLayout.WEST, commentpanel);
		centerpanel.add(descrpanel);		
		//Finally we put the comment button under the comment panel
		addcomment.setPreferredSize(new Dimension(300,50));
		centerlayout.putConstraint(SpringLayout.NORTH, addcomment, 20, SpringLayout.SOUTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST,addcomment, 50, SpringLayout.WEST, commentpanel);
		centerpanel.add(addcomment);
		//We place the center panel in the center
		cp.add(centerpanel,BorderLayout.CENTER);
		
		
		
		
		this.pack(); // Important: subcomponents are located according to their layout using their preferred sizes.		
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
     * Split text into n number of characters.
     *
     * @param text the text to be split.
     * @param size the split size.
     * @return an array of the split text.
     */
    private static String[] splitSize(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
    
    /**
	 * Sets the controller for the menu button
	 * @param c : the controller to be set
	 */
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);	
	}
	public void setBackBuyBookController(ActionListener c) {
		back.addActionListener(c);
		buy.addActionListener(c);
		book.addActionListener(c);
	}
	
	public void setNextPrevController(ActionListener c) {
		next.addActionListener(c);
		prev.addActionListener(c);
	}
	
	public void setCommentController(ActionListener c) {
		addcomment.addActionListener(c);
	}
	public void setAddAnswerController(ActionListener c) {
		addanswer.addActionListener(c);
	}
	
	public void nextPage() {
		commentpanel.setVisible(false);
		Offer o = results.get(offer);
		int i,cont = 0;
		JPanel aux;
		comment++;
		commentpanel = new JPanel();
		commentpanel.setLayout(new GridLayout(7,1));		
		for(i = comment; i < o.getComments().size(); i++,comment++) {
			Comment c = o.getComments().get(i);
			if(c instanceof Text) {
				JLabel user = new JLabel(c.getUser().getName() + " :");
				user.setHorizontalAlignment(JTextField.LEFT);
				commentpanel.add(user);
				String[] lines = splitSize(((Text) c).getText(),50);
				for(String s : lines) {
					aux = new JPanel();				
					JLabel com = new JLabel(s);
					aux.add(com);
					commentpanel.add(aux);
					cont++;
				}
				if(((Text) c).getAnswers().isEmpty()) viewanswer.setVisible(false);
				else viewanswer.setVisible(true);
				break;
			}		
		}
		for(i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
		
		
		prev.setVisible(true);
		if(comment == o.getComments().size()-1) next.setVisible(false);
		
		commentpanel.add(buttonpanel);
		commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		commentpanel.setPreferredSize(new Dimension(430,250));
		centerlayout.putConstraint(SpringLayout.SOUTH, commentpanel, -100, SpringLayout.SOUTH, centerpanel);
		centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 80, SpringLayout.WEST, centerpanel);		
		centerpanel.add(commentpanel);
		centerlayout.putConstraint(SpringLayout.SOUTH, descrpanel, -30, SpringLayout.NORTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST, descrpanel, 0, SpringLayout.WEST, commentpanel);
		addcomment.setPreferredSize(new Dimension(300,50));
		centerlayout.putConstraint(SpringLayout.NORTH, addcomment, 20, SpringLayout.SOUTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST,addcomment, 50, SpringLayout.WEST, commentpanel);
		System.out.print(comment);
	}
	
	public void prevPage() {
		commentpanel.setVisible(false);
		Offer o = results.get(offer);
		int i,cont = 0;
		JPanel aux;
		comment--;
		commentpanel = new JPanel();
		commentpanel.setLayout(new GridLayout(7,1));		
		for(i = comment; i >= 0; i--,comment--) {
			Comment c = o.getComments().get(i);
			if(c instanceof Text) {
				JLabel user = new JLabel(c.getUser().getName() + " :");
				user.setHorizontalAlignment(JTextField.LEFT);
				commentpanel.add(user);
				String[] lines = splitSize(((Text) c).getText(),50);
				for(String s : lines) {
					aux = new JPanel();				
					JLabel com = new JLabel(s);
					aux.add(com);
					commentpanel.add(aux);
					cont++;
				}
				if(((Text) c).getAnswers().isEmpty()) viewanswer.setVisible(false);
				else viewanswer.setVisible(true);
				break;
			}		
		}
		for(i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
				
		next.setVisible(true);
		if(comment == 1) prev.setVisible(false);
		
		commentpanel.add(buttonpanel);
		commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		commentpanel.setPreferredSize(new Dimension(430,250));
		centerlayout.putConstraint(SpringLayout.SOUTH, commentpanel, -100, SpringLayout.SOUTH, centerpanel);
		centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 80, SpringLayout.WEST, centerpanel);		
		centerpanel.add(commentpanel);
		centerlayout.putConstraint(SpringLayout.SOUTH, descrpanel, -30, SpringLayout.NORTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST, descrpanel, 0, SpringLayout.WEST, commentpanel);
		addcomment.setPreferredSize(new Dimension(300,50));
		centerlayout.putConstraint(SpringLayout.NORTH, addcomment, 20, SpringLayout.SOUTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST,addcomment, 50, SpringLayout.WEST, commentpanel);
	}
	
	public void commentText(String text) throws TextException, NotGuest {
		results.get(offer).commentOffer(text);
		
	}
	public void commentRate(int rate) throws RateException, NotGuest {
		
		results.get(offer).commentOffer(rate);
		results.get(offer).calculateRate();
		average.setText("  " + String.format("%.2f",results.get(offer).getAverageRate()) + "/5");
		comment++;
	}
	
	public void addAnswer(String text) throws TextException, NotGuest {
		((Text)results.get(offer).getComments().get(comment)).addAnswer(text);
		viewanswer.setVisible(true);
	}
	public List<Offer> getResults(){
		return results;
	}
	public int getOffer() {
		return offer;
	}
	
}
