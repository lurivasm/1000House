package windows;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import app.*;
import java.util.List;
import java.util.ArrayList;
public class SearchWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202176993911021059L;
	private Container cp = this.getContentPane(); //We get the container of the JFrame
	private List<Offer> results;
	private List<JButton> goButtons = new ArrayList<JButton>();
	private JButton next = new JButton("Next ->");
	private JButton prev = new JButton("<- Prev");
	private JButton menubutton = new JButton("Main menu");
	private List<JPanel> panels = new ArrayList<JPanel>();
	private JPanel resultsPanel = new JPanel();
	private JPanel nextPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private int page = 1;
	
	
	public SearchWindow(List<Offer> r) {
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
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(label4);
			panel.add(go);
			panels.add(panel);
			goButtons.add(go);
		}
		
		int i;
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
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
		
		BoxLayout nextLayout =new BoxLayout(nextPanel,BoxLayout.Y_AXIS);
		nextPanel.setLayout(nextLayout);
		for(i = 0 ; i< 21 ; i++) {
			nextPanel.add(new JPanel());
		}
		nextPanel.add(next);
		cp.add(nextPanel,BorderLayout.EAST);
		nextPanel.setPreferredSize(new Dimension(180,300));	
		
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
	
	


	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);	
	}
	public void setNextPrevController(ActionListener c) {
		next.addActionListener(c);
		prev.addActionListener(c);
	}
	
	public void nextPage() {
		int i;
		resultsPanel.setVisible(false);
		resultsPanel = new JPanel();
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		cp.add(resultsPanel, BorderLayout.CENTER);
		if(page == 1) {
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
	
	public void prevPage() {
		int i;
		page--;
		resultsPanel.setVisible(false);
		resultsPanel = new JPanel();
		BoxLayout resultsLayout = new BoxLayout(resultsPanel,BoxLayout.Y_AXIS);
		resultsPanel.setLayout(resultsLayout);
		cp.add(resultsPanel, BorderLayout.CENTER);
		System.out.println(page);
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
