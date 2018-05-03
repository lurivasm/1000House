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
	private Container cp = this.getContentPane();
	private JPanel descrpanel = new JPanel();
	private JPanel infopanel = new JPanel();// 
	private JPanel menupanel = new JPanel();// Panel where the menu  button will be located
	private JPanel commentpanel = new JPanel();
	private JPanel centerpanel = new JPanel();
	private JPanel buttonpanel = new JPanel();
	private JLabel average ;
	private JButton back = new JButton("Back");
	private JButton buy = new JButton("Buy");
	private JButton book = new JButton("Book");
	private JButton next = new JButton("Next"); // Button for the next comment
	private JButton prev = new JButton("Prev"); // Button for the prev comment
	private JButton addanswer = new JButton("Add Answer");
	private JButton viewanswer = new JButton("View Answers");
	private JButton addcomment = new JButton("Comment");
	private JButton menubutton = new JButton("Main menu"); // Main menu button
	private SpringLayout centerlayout = new SpringLayout();
	private int comment = 0;
	private List<Offer >results;
	private int offer;
	public SeeOfferWindow(List<Offer> results, int offer) {
		super("1000House");
		this.results = results;
		this.offer = offer;
		Offer o = results.get(offer);
		cp.setLayout(new BorderLayout());
		
		JLabel title; // We create the title of the window
		title = new JLabel(o.getHouse().getLocation());
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",30,30));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
		
		BoxLayout backLayout =new BoxLayout(infopanel,BoxLayout.Y_AXIS);	
		infopanel.setLayout(backLayout);	
		infopanel.add(new JPanel());
		JLabel charlabel = new JLabel("Characteristics:");
		infopanel.add(charlabel);
		infopanel.add(new JPanel());
		for(Characteristics c : o.getHouse().getCharacteristics()) {
			JLabel charac = new JLabel("- " + c.toString());
			infopanel.add(charac);
			infopanel.add(new JPanel());
		}
		for(int j = 0 ; j< 10 ; j++) {
			infopanel.add(new JPanel());
		}
		
		JLabel priceLabel;
		if(o instanceof LivingOffer) priceLabel = new JLabel("Price per month :");
		else priceLabel = new JLabel("Total price:");
		infopanel.add(priceLabel);
		JLabel price = new JLabel(Integer.toString(o.getPrice()) + " €");
		price.setFont(new Font("Tahona",30,30));	
		infopanel.add(price);
		infopanel.add(new JPanel());
		infopanel.add(new JPanel());
		infopanel.add(buy);
		infopanel.add(new JPanel());
		infopanel.add(book);
		for(int j = 0 ; j< (12-o.getHouse().getCharacteristics().size()) ; j++) {
			infopanel.add(new JPanel());
		}		
		
		infopanel.setPreferredSize(new Dimension(180,200));
		cp.add(infopanel,BorderLayout.EAST);
		
		
		BoxLayout menuLayout = new BoxLayout(menupanel,BoxLayout.Y_AXIS);	
		menupanel.setLayout(menuLayout);	
		menupanel.add(new JPanel()); 
		menupanel.add(menubutton);
		menupanel.add(new JPanel());
		menupanel.add(back);
		for(int j = 0 ; j< 5 ; j++) {
			menupanel.add(new JPanel());
		}		
		
		JLabel ini = new JLabel("  Initial date : " + o.getIniDate().toString());
		menupanel.add(ini);
		menupanel.add(new JPanel());
		JLabel end;
		if(o instanceof LivingOffer) end = new JLabel("  Number of months: " + Integer.toString(((LivingOffer) o).getnumMonths()));
		else end = new JLabel("  End date : " + ((HolidaysOffer) o).getendDate().toString());
		menupanel.add(end);
		for(int j = 0 ; j< 3 ; j++) {
			menupanel.add(new JPanel());
		}	
		
		JLabel avlabel = new JLabel("  Average rate : ");
		results.get(offer).calculateRate();
		average = new JLabel("  " + Double.toString(o.getAverageRate()) + "/5");
		average.setFont(new Font("Tahona",30,30));
		menupanel.add(avlabel);
		menupanel.add(average);
		for(int j = 0 ; j< 11 ; j++) {
			menupanel.add(new JPanel());
		}	
		
		menupanel.setPreferredSize(new Dimension(180,300));			
		cp.add(menupanel,BorderLayout.WEST);
		
		
		
		centerpanel.setLayout(centerlayout);
		
		JPanel aux;
				
		commentpanel.setLayout(new GridLayout(7,1));
		if(o.getComments().isEmpty()) commentpanel.setVisible(false);
		
		int cont = 0;
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
			comment++;
		}
		for(int i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
		
		
		buttonpanel.add(prev);
		buttonpanel.add(addanswer);
		buttonpanel.add(viewanswer);
		buttonpanel.add(next);
		prev.setVisible(false);
		commentpanel.add(buttonpanel);
		commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		commentpanel.setPreferredSize(new Dimension(430,250));
		centerlayout.putConstraint(SpringLayout.SOUTH, commentpanel, -100, SpringLayout.SOUTH, centerpanel);
		centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 80, SpringLayout.WEST, centerpanel);		
		centerpanel.add(commentpanel);
		
		String[] split = splitSize(o.getHouse().getDescription(),50);
		descrpanel.setLayout(new GridLayout(12,0));
		descrpanel.add(new JPanel());
		descrpanel.add(new JPanel());
		for(String s : split) {
			JLabel description = new JLabel(s);
			description.setHorizontalAlignment(JTextField.CENTER);
			aux = new JPanel();
			aux.add(description);
			descrpanel.add(aux);
		}
		centerlayout.putConstraint(SpringLayout.SOUTH, descrpanel, -30, SpringLayout.NORTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST, descrpanel, 0, SpringLayout.WEST, commentpanel);
		centerpanel.add(descrpanel);
		
		addcomment.setPreferredSize(new Dimension(300,50));
		centerlayout.putConstraint(SpringLayout.NORTH, addcomment, 20, SpringLayout.SOUTH, commentpanel);
		centerlayout.putConstraint(SpringLayout.WEST,addcomment, 50, SpringLayout.WEST, commentpanel);
		centerpanel.add(addcomment);
		
		cp.add(centerpanel,BorderLayout.CENTER);
		
		
		
		
		this.pack(); // Important: subcomponents are located according to their layout using their preferred sizes.		
		this.setSize(900, 700);
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
		average.setText("  " + Double.toString(results.get(offer).getAverageRate()) + "/5");
	}
	public List<Offer> getResults(){
		return results;
	}
	public int getOffer() {
		return offer;
	}
	
}
