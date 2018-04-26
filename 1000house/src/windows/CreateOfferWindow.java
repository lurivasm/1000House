//package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;

/*import app.House;*/

public class CreateOfferWindow extends JFrame{
	private JPanel offerPanel = new JPanel();
	private JPanel housePanel = new JPanel();
	private JPanel allPanel = new JPanel();
	private JPanel profilePanel = new JPanel();
	private JLabel priceLabel, titleLabel
	private JButton accept, cancel;
	private JButton profilebutton = new JButton("Profile");
	private JButton menubutton = new JButton("Main menu");
	
	public 	CreateOfferWindow(/*List<House> houses*/) {
		super("Create an Offer!");
		
		titleLabel = new JLabel(" Create your House!!");
		titleLabel.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		titleLabel.setFont(new Font("Tahoma",30,30));
		
		/*Buttons*/
		accept = new JButton("Accept");
		cancel = new JButton("Cancel");
	
		/**/
	
		/**/
		BoxLayout profLayout = new BoxLayout(profilePanel,BoxLayout.Y_AXIS);
		profilePanel.setLayout(profLayout);	
		profilePanel.add(new JPanel());
		profilePanel.add(profilebutton);
		profilePanel.add(new JPanel());
		profilePanel.add(menubutton);
		for(int j = 0 ; j< 16 ; j++) {
			profilePanel.add(new JPanel());
		}
		profilePanel.setPreferredSize(new Dimension(180,300));		
		
		
		/*The container*/
		Container cont = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		cont.setLayout(layout);
		cont.add(profilePanel,BorderLayout.WEST);
		cont.add(offerPanel, BorderLayout.CENTER);
		cont.add(titleLabel,BorderLayout.NORTH);
		
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
