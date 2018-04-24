package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;


public class HouseWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel charPanel = new JPanel();
	private JPanel housePanel = new JPanel();
	private JPanel locPanel = new JPanel();
	private JPanel pricePanel = new JPanel();
	private JPanel descPanel = new JPanel();
	private JLabel descriptionLabel, priceLabel, charactLabel, locationLabel;
	private JTextField descTF, priceTF, locatTF;
	private JCheckBox poolBox, parkingBox, gardenBox, wifiBox, tvBox, airBox;
	private JButton accept, cancel;
	
	public HouseWindow() {
		super("Create a House!");
		
		SpringLayout houseLayout = new SpringLayout();
		
		/*Labels*/
		descriptionLabel = new JLabel("Description :");
		priceLabel = new JLabel("Price : ");
		charactLabel = new JLabel("Select the characteristics :");
		locationLabel = new JLabel("Location : ");
		
		/*Fields*/
		descTF = new JTextField();
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
		BoxLayout charLayout = new BoxLayout(charPanel, BoxLayout.X_AXIS);
		charPanel.setLayout(charLayout);
		charPanel.setPreferredSize(new Dimension(10, 90));
		
		charPanel.add(charactLabel);
		charPanel.add(airBox);
		charPanel.add(poolBox);
		charPanel.add(parkingBox);
		charPanel.add(gardenBox);
		charPanel.add(wifiBox);
		charPanel.add(tvBox);
		
		/*Location Panel*/
		locPanel.setLayout(new FlowLayout());
		locPanel.add(locationLabel);
		locPanel.add(locatTF);
		
		/*Description Panel*/
		descPanel.setLayout(new FlowLayout());
		descPanel.add(descriptionLabel);
		descPanel.add(descTF);
		
		/*Price Panel*/
		pricePanel.setLayout(new FlowLayout());
		pricePanel.add(priceLabel);
		pricePanel.add(priceTF);
		
		/*House Panel*/
		
		//houseLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
		housePanel.add(charPanel);
		housePanel.add(locPanel);
		housePanel.add(pricePanel);
		housePanel.add(descPanel);
		housePanel.add(accept);
		housePanel.add(cancel);
		
		Container cont = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		cont.setLayout(layout);
		cont.add(housePanel, BorderLayout.CENTER);
		
		
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
