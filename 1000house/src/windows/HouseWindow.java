package windows;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import app.Characteristics;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * Creating a house Window
 */
public class HouseWindow extends JFrame{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * JPanels for each characteristics
	 */
	private JPanel charPanel = new JPanel();
	private JPanel housePanel = new JPanel();
	private JPanel locPanel = new JPanel();
	private JPanel zipcodePanel = new JPanel();
	private JPanel descPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	/**
	 * Labels, textfields and buttons
	 */
	private JLabel descriptionLabel, priceLabel, charactLabel, locationLabel;
	private JTextField priceTF, locatTF;
	private JTextArea descTF;
	private JCheckBox poolBox, parkingBox, gardenBox, wifiBox, tvBox, airBox;
	private JButton accept, cancel;
	private JButton menubutton = new JButton("Main menu");
	
	/**
	 * Constructor of the House Window
	 */
	public HouseWindow() {
		super("1000House");
		int i;
	//	Color color = new Color(180,255,190,220);
		
		/*Labels*/
		descriptionLabel = new JLabel("Description :           ");
		priceLabel = new JLabel("    Zipcode :  ");
		charactLabel = new JLabel("Select the characteristics :");
		charactLabel.setFont(new Font("Tahoma", 18, 18));
		locationLabel = new JLabel("   Location : ");
		JLabel titleLabel = new JLabel(" Create a House!");
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
		for(i = 0; i < 5; i++) charPanel.add(new JPanel());
		charPanel.add(charactLabel);
		charPanel.add(new JPanel());
		charPanel.add(airBox);
		charPanel.add(poolBox);
		charPanel.add(parkingBox);
		charPanel.add(gardenBox);
		charPanel.add(wifiBox);
		charPanel.add(tvBox);
		for(i = 0; i < 7; i++) charPanel.add(new JPanel());
		
		/*Location Panel*/
		locPanel.setLayout(new FlowLayout());
		locPanel.add(locationLabel);
		locPanel.add(locatTF);
		
		/*Description Panel*/
		descPanel.add(descScrollPane);
		
		/*Zipcode Panel*/
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
		allPanel.add(zipcodePanel);
		allPanel.add(new JPanel());
		allPanel.add(descriptionLabel);
		allPanel.add(descPanel);

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
		cont.setLayout(layout);
		cont.add(menuPanel,BorderLayout.WEST);
		cont.add(housePanel, BorderLayout.CENTER);
		cont.add(titleLabel,BorderLayout.NORTH);
		cont.setPreferredSize(new Dimension(800, 400));
		
		this.pack();
		this.setVisible(true);
		this.setSize(900, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Getter of the description
	 * @return the description of the House
	 */
	public String getDescription() {
		return descTF.getText();
	}
	
	/**
	 * Getter of the location
	 * @return the location of the house
	 */
	public String getLocat() {
		return priceTF.getText();
	}
	
	/**
	 * Getter of the zipcode
	 * @return the zipcode of the house
	 */
	public String getZipcode() {
		return priceTF.getText();
	}
	
	/**
	 * Getter of the characteristics
	 * @return the characteristics of the house
	 */
	public List<Characteristics> getCharacteristics(){
		List<Characteristics> chars = new ArrayList<Characteristics>();
		if (poolBox.isSelected()) chars.add(Characteristics.Pool);
		if (parkingBox.isSelected()) chars.add(Characteristics.Parking);
		if (gardenBox.isSelected()) chars.add(Characteristics.Garden);
		if (wifiBox.isSelected()) chars.add(Characteristics.WiFI);
		if (tvBox.isSelected()) chars.add(Characteristics.TV);
		if (airBox.isSelected()) chars.add(Characteristics.AirConditioner);
		return chars;		
	}
	
	/**
	 * Set the action for the accept button
	 * @param c the actionListener for the accept button
	 */
	public void setHouseController(ActionListener c) {
		accept.addActionListener(c);	
	}
	
	/**
	 * Set the action for the menu and the cancel button
	 * @param c the actionListener for the buttons
	 */
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);
		cancel.addActionListener(c);
	}
}