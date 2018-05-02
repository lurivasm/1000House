package windows;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import app.*;
import java.util.List;
import java.util.ArrayList;
public class SearchWindow extends JFrame {
	/**
	 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
	 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
	 *
	 */
	private static final long serialVersionUID = -8202176993911021059L;
	private Container cp = this.getContentPane(); //We get the container of the JFrame
	private List<Offer> results; // List of the results of the search
	private List<JButton> goButtons = new ArrayList<JButton>(); // List of the go to ofefr buttons
	private JButton next = new JButton("Next ->"); // Button for the next page of offers
	private JButton prev = new JButton("<- Prev"); // Button for the prev page of offers
	private JButton menubutton = new JButton("Main menu"); // Main menu button
	private List<JPanel> panels = new ArrayList<JPanel>();// lList of the offers panels
	private JPanel resultsPanel = new JPanel();// Panel where the offer panels will be located
	private JPanel nextPanel = new JPanel();// Panel where the next button will be located
	private JPanel menuPanel = new JPanel();// Panel where the menu and prev button will be located
	private int page = 1; // Number of the page for the offer results
	
	
	public SearchWindow(List<Offer> r) {
		super("1000House");
		results = r;
		cp.setLayout(new BorderLayout());
		
		
		JLabel title; // We create the title of the window
		title = new JLabel("SEARCH RESULTS");
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",30,30));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
		
	
		//For each offer, we create a panel with the location, the zipcode, the price and the type of offer
		//We add these panels to a panel list and we create a button, adding it to a button list
		int cont = 0;
		for(Offer o : results) {	
			JPanel panel = new JPanel();
			JLabel label1 = new JLabel(o.getHouse().getLocation());
			JLabel label2 = new JLabel(Long.toString(o.getHouse().getZipcode()));
			JLabel label3 = new JLabel(Integer.toString(o.getPrice()) + " €");
			JLabel label4;
			if(o instanceof LivingOffer) {
				label4 = new JLabel("Living");
			}
			else {
				label4 = new JLabel("Holiday");
			}
			
			JButton go = new JButton("GO");
			go.setName(Integer.toString(cont));
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(go);
			panels.add(panel);
			goButtons.add(go);
			cont ++;
		}
		
		int i;
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS); //We create the layout of the results pane
		resultsPanel.setLayout(resultsLayout);
		//We fill the results panel with 15 of the offers panels we created. If there are less than 15 panels in the results
		// list, we add empty panels,and we set the next button as invisible
		if(results.size() >= 15) {
			for( i = 0 ; i < 15; i++) {
				resultsPanel.add(panels.get(i));
			}
		}
		else {
			for(i = 0 ; i < results.size(); i++) {
				resultsPanel.add(panels.get(i));
			}
			for(i = results.size() ; i <15; i++) {
				resultsPanel.add(new JPanel());
			}
			next.setVisible(false);
		}
		resultsPanel.setPreferredSize(new Dimension(400,400));
		cp.add(resultsPanel, BorderLayout.CENTER);
		
		// We add the next button to his panel, and add other empty panels to fit the button in his right place
		BoxLayout nextLayout = new BoxLayout(nextPanel,BoxLayout.Y_AXIS);
		nextPanel.setLayout(nextLayout);
		for(i = 0 ; i< 21 ; i++) {
			nextPanel.add(new JPanel());
		}
		nextPanel.add(next);
		cp.add(nextPanel,BorderLayout.EAST);
		nextPanel.setPreferredSize(new Dimension(180,300));	
		
		// We add the prev and menu button to their panel, and add other empty panels to fit the buttons in their right place
		BoxLayout profLayout =new BoxLayout(menuPanel,BoxLayout.Y_AXIS);	
		menuPanel.setLayout(profLayout);	
		menuPanel.add(new JPanel());
		menuPanel.add(menubutton);
		for(int j = 0 ; j< 16 ; j++) {
			menuPanel.add(new JPanel());
		}
		menuPanel.add(prev);
		prev.setVisible(false);
		menuPanel.setPreferredSize(new Dimension(180,300));	
		
		cp.add(menuPanel,BorderLayout.WEST);
		
		
		
		
		this.pack(); // Important: subcomponents are located according to their layout using their preferred sizes.		
		this.setSize(900, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	


	/**
	 * Sets the controller for the menu button
	 * @param c : the controller to be set
	 */
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);	
	}
	
	/**
	 * Sets the controller for the next and prev button
	 * @param c
	 */
	public void setNextPrevController(ActionListener c) {
		next.addActionListener(c);
		prev.addActionListener(c);
	}
	public void setGoController(ActionListener c) {
		for(JButton b : goButtons) b.addActionListener(c);
	}
	/**
	 * @return the actual offer page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @return the list of offers that resulted from the search
	 */
	public List<Offer> getResults(){
		return results;
	}
	/**
	 * This method creates and puts in the window the next results page, depending on the actual page
	 */
	public void nextPage() {
		int i;
		resultsPanel.setVisible(false);
		resultsPanel = new JPanel();
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		cp.add(resultsPanel, BorderLayout.CENTER);
		if(page  != Math.floor(results.size()/15)) {
			for(i = (page*15) ; i < (15 + page*15); i++) {
				resultsPanel.add(panels.get(i));
			}
			cp.add(resultsPanel, BorderLayout.CENTER);
			prev.setVisible(true);
			page++;
		}
		else {
			for( i = page*15 ; i < panels.size(); i++) {
				resultsPanel.add(panels.get(i));
			}
			for(i  = panels.size() ; i < (15 + page*15) ; i++) {
				resultsPanel.add(new JPanel());
			}
			cp.add(resultsPanel, BorderLayout.CENTER);
			next.setVisible(false);
			page++;
		}
	}
	
	/**
	 * This method creates and puts in the window the previous results page, depending on the actual page
	 */
	public void prevPage() {
		int i;
		page--;
		resultsPanel.setVisible(false);
		resultsPanel = new JPanel();
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		cp.add(resultsPanel, BorderLayout.CENTER);
		if(page == 1) {			
			for(i = 0 ; i < 15; i++) {
				resultsPanel.add(panels.get(i));
			}
			cp.add(resultsPanel, BorderLayout.CENTER);
			prev.setVisible(false);
		}
		else{
			for(i = ((page-1)*15) ; i < (15 + (page-1)*15); i++) {
				resultsPanel.add(panels.get(i));
			}
			cp.add(resultsPanel, BorderLayout.CENTER);
			next.setVisible(true);
		}
	}
}
