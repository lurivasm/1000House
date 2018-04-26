package windows;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import app.*;
import java.util.List;
import java.util.ArrayList;
public class searchWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202176993911021059L;
	private Container cp = this.getContentPane(); //We get the container of the JFrame
	private List<Offer> results;
	private List<JButton> goButtons = new ArrayList<JButton>();
	private JButton logout = new JButton("Logout");
	private JButton next = new JButton("Next ->");
	private JButton profilebutton = new JButton("Profile");
	private JButton menubutton = new JButton("Main menu");
	private List<JPanel> panels = new ArrayList<JPanel>();
	private JPanel resultsPanel = new JPanel();
	private JPanel logoutPanel = new JPanel();
	private JPanel profilePanel = new JPanel();
	
	
	public searchWindow(List<Offer> r) {
		super("1000House");
		results = r;
		cp.setLayout(new BorderLayout());
		
		
		JLabel title; // We create the title of the window
		title = new JLabel("SEARCH RESULTS");
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",30,30));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
			
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
//			go.setHorizontalAlignment(JTextField.RIGHT);
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(go);
			panels.add(panel);
			goButtons.add(go);
		}
		

		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		for(int i = 0 ; i < 15; i++) {
			resultsPanel.add(panels.get(i));
		}
		cp.add(resultsPanel, BorderLayout.CENTER);
		
		GridLayout logoutLayout =new GridLayout(2,2);// we set the layout as a  2x2 grid layout
		logoutPanel.setLayout(logoutLayout);
		logoutLayout.setVgap(600);//We set the vertical distance between the different components of the panel
		logoutPanel.add(new JPanel());
		logoutPanel.add(logout);//We add the logout button on the top right part of the panel
		logoutPanel.add(new JPanel());//The other parts of the panel will be empty panels
		logoutPanel.add(next);	
		cp.add(logoutPanel,BorderLayout.EAST);
		logoutPanel.setVisible(true);
		
		
		BoxLayout profLayout =new BoxLayout(profilePanel,BoxLayout.Y_AXIS);	// we set the layout as a  2x2 grid layout
		profilePanel.setLayout(profLayout);	
		profilePanel.add(new JPanel());
		profilePanel.add(profilebutton);
		profilePanel.add(new JPanel());
		profilePanel.add(menubutton);
		for(int j = 0 ; j< 16 ; j++) {
			profilePanel.add(new JPanel());
		}
		profilePanel.setPreferredSize(new Dimension(180,300));		
		
		cp.add(profilePanel,BorderLayout.WEST);
		
		
		
		
		this.pack(); // Important: subcomponents are located according to theirlayout using their prefered sizes.		
		this.setSize(900, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

	public void setLogoutController(ActionListener c) {
		logout.addActionListener(c);	
	}
}
