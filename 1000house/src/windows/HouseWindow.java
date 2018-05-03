package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;


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
	private JLabel descriptionLabel, priceLabel, charactLabel, locationLabel;
	private JTextField descTF, priceTF, locatTF;
	private JCheckBox poolBox, parkingBox, gardenBox, wifiBox, tvBox, airBox;
	private JButton accept, cancel;
	private JButton profilebutton = new JButton("Profile");
	private JButton menubutton = new JButton("Main menu");
	
	public HouseWindow() {
		super("Create a House!");
		
		/*Labels*/
		descriptionLabel = new JLabel("Description :");
		priceLabel = new JLabel("      Zipcode :  ");
		charactLabel = new JLabel("Select the characteristics :");
		charactLabel.setFont(new Font("Tahoma", 18, 18));
		locationLabel = new JLabel("   Location : ");
		JLabel titleLabel = new JLabel(" Create your House!!");
		titleLabel.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		titleLabel.setFont(new Font("Tahoma",30,30));
				
		/*Fields*/
		descTF = new JTextField();
		descTF.setPreferredSize(new Dimension(170, 50));
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
		
		/*Description Panel*/
		descPanel.setLayout(new FlowLayout());
		descPanel.add(descriptionLabel);
		descPanel.add(descTF);
		
		/*Price Panel*/
		zipcodePanel.setLayout(new FlowLayout());
		zipcodePanel.add(priceLabel);
		zipcodePanel.add(priceTF);
		JPanel allPanel = new JPanel();
		BoxLayout allLayout = new BoxLayout(allPanel, BoxLayout.Y_AXIS);
		allPanel.setLayout(allLayout);
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		allPanel.add(new JPanel());
		allPanel.add(locPanel);
		allPanel.add(descPanel);
		allPanel.add(zipcodePanel);
		
		/*Button Panel*/
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(accept);
		buttonPanel.add(cancel);
		
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
		
		
		/**/
		JPanel profilePanel = new JPanel();
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
		cont.add(housePanel, BorderLayout.CENTER);
		cont.add(titleLabel,BorderLayout.NORTH);
		
		
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}