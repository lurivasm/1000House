package windows;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import app.Characteristics;


public class HouseWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel charPanel = new JPanel();
	private JPanel housePanel = new JPanel();
	private JPanel locPanel = new JPanel();
	private JPanel zipcodePanel = new JPanel();
	private JPanel descPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JLabel descriptionLabel, priceLabel, charactLabel, locationLabel;
	private JTextField priceTF, locatTF;
	private JTextArea descTF;
	private JCheckBox poolBox, parkingBox, gardenBox, wifiBox, tvBox, airBox;
	private JButton accept, cancel;
	private JButton menubutton = new JButton("Main menu");
	
	public HouseWindow() {
		super("Create a House!");
	//	Color color = new Color(180,255,190,220);
		/*Labels*/
		descriptionLabel = new JLabel("Description :           ");
		priceLabel = new JLabel("    Zipcode :  ");
		charactLabel = new JLabel("Select the characteristics :");
		charactLabel.setFont(new Font("Tahoma", 18, 18));
		locationLabel = new JLabel("   Location : ");
		JLabel titleLabel = new JLabel(" Create your House!!");
		titleLabel.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		titleLabel.setVerticalAlignment(JTextField.CENTER);
		titleLabel.setFont(new Font("Tahoma",30,30));
				
		/*Fields*/
		descTF = new JTextArea(10, 25);
		descTF.setLineWrap(true);
		descTF.setWrapStyleWord(true);
		JScrollPane descScrollPane = new JScrollPane(descTF);
		priceTF = new JTextField(15);
		locatTF = new JTextField(15);
		
	
		/*Characteristics Box*/
		poolBox = new JCheckBox("Swimming-Pool");
		parkingBox = new JCheckBox("Parking");
		gardenBox = new JCheckBox("Garden");
		wifiBox = new JCheckBox("Wifi");
		tvBox = new JCheckBox("TV");
		airBox = new JCheckBox("Air Conditioner");
		
		/*Buttons*/
		accept = new JButton("Accept");
		cancel = new JButton("Cancel");
		
		/*Characteristics Panel*/
		BoxLayout charLayout = new BoxLayout(charPanel, BoxLayout.Y_AXIS);
		charPanel.setLayout(charLayout);
		charPanel.setPreferredSize(new Dimension(100, 100));
	//	charPanel.setBackground(color);
		
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(charactLabel);
		charPanel.add(new JPanel());
		charPanel.add(airBox);
		charPanel.add(poolBox);
		charPanel.add(parkingBox);
		charPanel.add(gardenBox);
		charPanel.add(wifiBox);
		charPanel.add(tvBox);
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		charPanel.add(new JPanel());
		
		/*Location Panel*/
		locPanel.setLayout(new FlowLayout());
		locPanel.add(locationLabel);
		locPanel.add(locatTF);
	//	locPanel.setBackground(color);
		
		/*Description Panel*/
		descPanel.add(descScrollPane);
	//	descPanel.setBackground(color);
		
		/*Price Panel*/
		zipcodePanel.setLayout(new FlowLayout());
		zipcodePanel.add(priceLabel);
		zipcodePanel.add(priceTF);
	//	zipcodePanel.setBackground(color);
		
		JPanel allPanel = new JPanel();
		BoxLayout allLayout = new BoxLayout(allPanel, BoxLayout.Y_AXIS);
		allPanel.setLayout(allLayout);
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		allPanel.add(locPanel);
		allPanel.add(zipcodePanel);
		allPanel.add(descriptionLabel);
		allPanel.add(descPanel);
	//	allPanel.setBackground(color);
		
		/*Button Panel*/
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(accept);
		buttonPanel.add(cancel);
	//	buttonPanel.setBackground(color);
		
		allPanel.add(buttonPanel);
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		
		/*House Panel*/
		BoxLayout houseLayout = new BoxLayout(housePanel, BoxLayout.X_AXIS);
		housePanel.setLayout(houseLayout);
		housePanel.setPreferredSize(new Dimension(200, 200));
		housePanel.setBackground(Color.black);
		housePanel.add(allPanel);
		housePanel.add(charPanel);
	//	housePanel.setBackground(color);
		
		/*Menu Panel*/
		BoxLayout menuLayout = new BoxLayout(menuPanel,BoxLayout.Y_AXIS);
		menuPanel.setLayout(menuLayout);	
		menuPanel.add(new JPanel());
		menuPanel.add(menubutton);
		for(int j = 0 ; j< 16 ; j++) {
			menuPanel.add(new JPanel());
		}
		menuPanel.setPreferredSize(new Dimension(180,300));		
	//	menuPanel.setBackground(color);
		
		
		/*The container*/
		Container cont = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		cont.setLayout(layout);
		cont.add(menuPanel,BorderLayout.WEST);
		cont.add(housePanel, BorderLayout.CENTER);
		cont.add(titleLabel,BorderLayout.NORTH);
	//	cont.setBackground(color);
		cont.setPreferredSize(new Dimension(800, 400));
	//	this.getContentPane().setBackground(color);
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public String getDescription() {
		return descTF.getText();
	}
	
	public String getLocat() {
		return priceTF.getText();
	}
	
	public String getZipcode() {
		return priceTF.getText();
	}
	
	public List<Characteristics> getCharacteristics(){
		List<Characteristics> chars = new ArrayList<Characteristics>();
		//poolBox, parkingBox, gardenBox, wifiBox, tvBox, airBox
		if (poolBox.isSelected()) chars.add(Characteristics.Pool);
		if (parkingBox.isSelected()) chars.add(Characteristics.Parking);
		if (gardenBox.isSelected()) chars.add(Characteristics.Garden);
		if (wifiBox.isSelected()) chars.add(Characteristics.WiFI);
		if (tvBox.isSelected()) chars.add(Characteristics.TV);
		if (airBox.isSelected()) chars.add(Characteristics.AirConditioner);
		return chars;		
	}
	
	public void setHouseController(ActionListener c) {
		accept.addActionListener(c);	
	}
	
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);
		cancel.addActionListener(c);
	}
}