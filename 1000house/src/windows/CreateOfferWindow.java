package windows;

import app.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/*import app.House;*/

public class CreateOfferWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel offerPanel = new JPanel();
	private JPanel allPanel = new JPanel();
	private JPanel profilePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel typePanel = new JPanel();
	private JPanel timePanel = new JPanel();
	private JPanel endPanel = new JPanel();
	private JComboBox<String> housesBox;
	private JLabel priceLabel, titleLabel, depositLabel, houseLabel, iniLabel, endLabel, timeLabel, typeLabel;
	private JTextField priceTF, depositTF, iniTF, endTF, timeTF;
	private JButton accept, cancel;
	private JRadioButton holidaysOffer = new JRadioButton("Holidays Offer");
	private JRadioButton livingOffer = new JRadioButton("Living Offer");
	private JButton profilebutton = new JButton("Profile");
	private JButton menubutton = new JButton("Main menu");
	
	public 	CreateOfferWindow(ArrayList <House> houses) {
		super("Create an Offer!");
		
		titleLabel = new JLabel(" Create your Offer!!   ");
		titleLabel.setFont(new Font("Tahoma",30,30));
		
		/*Buttons*/
		accept = new JButton("Accept");
		cancel = new JButton("Cancel");
		buttonPanel.add(accept);
		buttonPanel.add(cancel);
		
		/*Labels*/
		priceLabel = new JLabel("           Price : ");
		depositLabel = new JLabel("       Deposit : ");
		houseLabel = new JLabel("Select your house :");
		iniLabel = new JLabel(" Initial Date : ");
		endLabel = new JLabel("     End Date : ");
		timeLabel = new JLabel("     Duration : ");
		typeLabel = new JLabel(" Select the type of offer :");
	
		/*TextFields*/
		priceTF = new JTextField(15);
		depositTF = new JTextField(15);
		iniTF = new JTextField(15);
		endTF = new JTextField(15);
		timeTF = new JTextField(15);
		
		/*House panel*/
		String aux;
		String houseNames[] = new String[houses.size()];
		int i = 0;
		for(House h : houses) {
			aux = h.getLocation() + " " + h.getZipcode();
			houseNames[i] = aux;
			i++;
		}
		housesBox = new JComboBox<String>(houseNames);
		
		/*Type of offer*/
		ButtonGroup type = new ButtonGroup();
		livingOffer.addActionListener(this);
		holidaysOffer.addActionListener(this);
		type.add(livingOffer);
		type.add(holidaysOffer);
		typePanel.setLayout(new GridLayout(12, 1));
		for(i = 0; i < 3; i++) {
			typePanel.add(new JPanel());
		}
		typePanel.add(typeLabel);
		typePanel.add(new JPanel());
		typePanel.add(livingOffer);
		typePanel.add(holidaysOffer);
		typePanel.add(new JPanel());
		typePanel.add(houseLabel);
		typePanel.add(housesBox);
		
		/*The panels for the characteristics*/
		JPanel pricePanel = new JPanel();
		pricePanel.add(priceLabel);
		pricePanel.add(priceTF);
		JPanel depositPanel = new JPanel();
		depositPanel.add(depositLabel);
		depositPanel.add(depositTF);
		JPanel iniPanel = new JPanel();
		iniPanel.add(iniLabel);
		iniPanel.add(iniTF);
		endPanel.add(endLabel);
		endPanel.add(endTF);
		timePanel.add(timeLabel);
		timePanel.add(timeTF);		
		
		/*The Offer Panel*/
		BoxLayout offerLayout = new BoxLayout(offerPanel,BoxLayout.Y_AXIS);
		offerPanel.setLayout(offerLayout);
		for(i = 0; i < 5; i++) {
			offerPanel.add(new JPanel());
		}
		offerPanel.add(pricePanel);
		offerPanel.add(new JPanel());
		offerPanel.add(depositPanel);
		offerPanel.add(new JPanel());
		offerPanel.add(iniPanel);
		offerPanel.add(new JPanel());
		offerPanel.add(timePanel);
		offerPanel.add(endPanel);
		offerPanel.setVisible(false);
		
		/*The AllPanel with all the characteristics*/
		allPanel.add(typePanel);
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		allPanel.add(offerPanel);
		JPanel centerPanel = new JPanel();
		BoxLayout centerLayout = new BoxLayout(centerPanel,BoxLayout.Y_AXIS);
		centerPanel.setLayout(centerLayout);
		centerPanel.add(new JPanel());
		centerPanel.add(titleLabel);
		centerPanel.add(allPanel);
		centerPanel.add(buttonPanel);
	//	Color color = new Color(180,255,190,220);
	//	centerPanel.setBackground(color);
		
		/*The Profile Panel*/
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
		//cont.setBackground(color);
		cont.setLayout(layout);
		cont.add(profilePanel,BorderLayout.WEST);
		cont.add(centerPanel, BorderLayout.CENTER);
		
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(livingOffer.isSelected()) {
			offerPanel.setVisible(true);
			timePanel.setVisible(true);
			endPanel.setVisible(false);
		}
		else if(holidaysOffer.isSelected()) {
			offerPanel.setVisible(true);
			endPanel.setVisible(true);
			timePanel.setVisible(false);
		}
		
	}
	
	
}
