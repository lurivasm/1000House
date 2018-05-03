package windows;

import app.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;


public class CreateOfferWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel offerPanel = new JPanel();
	private JPanel allPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
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
	private ButtonGroup type = new ButtonGroup();
	private JButton menubutton = new JButton("Main menu");
	
	public 	CreateOfferWindow(List<House> list) {
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
		priceTF = new JTextField(9);
		depositTF = new JTextField(9);
		iniTF = new JTextField("day-month-year");
		endTF = new JTextField("day-month-year");
		timeTF = new JTextField(9);
		
		/*House panel*/
		String aux;
		String houseNames[] = new String[list.size()];
		int i = 0;
		for(House h : list) {
			aux = h.getLocation() + "-" + h.getZipcode();
			houseNames[i] = aux;
			i++;
		}
		housesBox = new JComboBox<String>(houseNames);
		
		/*Type of offer*/
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
		
		/*Menu Panel*/
		BoxLayout menuLayout = new BoxLayout(menuPanel,BoxLayout.Y_AXIS);
		menuPanel.setLayout(menuLayout);	
		menuPanel.add(new JPanel());
		menuPanel.add(menubutton);
		for(int j = 0 ; j< 16 ; j++) {
			menuPanel.add(new JPanel());
		}
		menuPanel.setPreferredSize(new Dimension(180,300));		
		
		/*The container*/
		Container cont = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		//cont.setBackground(color);
		cont.setLayout(layout);
		cont.add(menuPanel,BorderLayout.WEST);
		cont.add(centerPanel, BorderLayout.CENTER);
		
		
		this.pack();
		this.setVisible(true);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(livingOffer.isSelected()) {
			iniTF.setText("day-month-year");
			priceTF.setText("");
			depositTF.setText("");
			timeTF.setText("");
			offerPanel.setVisible(true);
			timePanel.setVisible(true);
			endPanel.setVisible(false);
		}
		else if(holidaysOffer.isSelected()) {
			iniTF.setText("day-month-year");
			priceTF.setText("");
			depositTF.setText("");
			endTF.setText("day-month-year");
			offerPanel.setVisible(true);
			endPanel.setVisible(true);
			timePanel.setVisible(false);
		}
		
	}
	
	public String getPrice() {
		return priceTF.getText();
	}
	
	public String getDeposit() {
		return depositTF.getText();
	}
	
	public String getTime() {
		return timeTF.getText();
	}
	
	public String getIniDate() {
		return iniTF.getText();
	}
	
	public String getEndDate() {
		return endTF.getText();
	}
	
	public String getHouse() {
		return (String)housesBox.getSelectedItem();
	}
	
	public  String getTypeOffer(){
		if(livingOffer.isSelected()) {
			return "Living Offer";
		}
		else if(holidaysOffer.isSelected()) {
			return "Holidays Offer";
		}
		return "";
		}
	
	public void setCreateOfferController(ActionListener c) {
		accept.addActionListener(c);
	}
	
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);
		cancel.addActionListener(c);
	}
}