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
	private List<JPanel> panels = new ArrayList<JPanel>();
	private JPanel resultsPanel = new JPanel();
	
	
	public searchWindow(List<Offer> r) {
		super("1000House");
		results = r;
		cp.setLayout(new BorderLayout());
		cp.add(new JPanel(),BorderLayout.EAST);
		cp.add(new JPanel(),BorderLayout.WEST);
		
		JLabel title; // We create the title of the window
		title = new JLabel("SEARCH RESULTS");
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",30,30));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
		for(Offer o : results) {
			JPanel panel = new JPanel();
			JLabel label1 = new JLabel(o.getHouse().getLocation());
			JLabel label2 = new JLabel(Long.toString(o.getHouse().getZipcode()));
			JLabel label3 = new JLabel(Integer.toString(o.getPrice()));
			JButton go = new JButton("GO");
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			panel.add(go);
			panels.add(panel);
			goButtons.add(go);
		}
		
		GridLayout resultsLayout = new GridLayout(20,1);
		resultsPanel.setLayout(resultsLayout);
		resultsLayout.setVgap(20);
		for(int i = 0 ; i < 20 ; i++) {
			resultsPanel.add(panels.get(i));
		}
		
		this.pack(); // Important: subcomponents are located according to theirlayout using their prefered sizes.		
		this.setSize(900, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
