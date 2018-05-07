package windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class LoginWindow extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 9102096458417906733L;
	private Container cp = this.getContentPane(); //We get the container of the JFrame
	private JPanel buttonPanel = new JPanel(); // Panel for the login button
	private JPanel textPanel = new JPanel();// Panel for the Nif and Pasword fields of the login
	private JPanel searchPanel = new JPanel();// Panel where all the searches will be located
	private JPanel loginPanel = new JPanel();// Panel where the buttonpanel and the textpanel will be located
	private JPanel profilePanel = new JPanel();	//Panel with the profile butt
	private JPanel logoutPanel = new JPanel();//Panel with lthe logout button
	private JPanel searchStatePanel = new JPanel(); // Panel of the bought or booked search
	private JPanel searchRatePanel = new JPanel(); // Panel for the rate search
	private JButton login = new JButton("Login");//Login button
	private JButton profilebutton = new JButton("Profile");//Profile button
	private JButton createHousebutton = new JButton("Create House"); // Create house button, only visible for the host
	private JButton createOfferbutton = new JButton("Create Offer");// Create house button, only visible for the host
	private JButton logout = new JButton("Logout");//Logout button
	private JButton searchButton1 = new JButton("SEARCH");//Search button for the types search
	private JButton searchButton2 = new JButton("SEARCH");//Search button for the code search
	private JButton searchButton3 = new JButton("SEARCH");// Search button for the dates search
	private JButton searchButton4 = new JButton("SEARCH");// Search button for the states search
	private JButton searchButton5 = new JButton("SEARCH");// Search button for the rate search
	private JTextField nifField;//Text field of the Nif
	private JPasswordField passwordF;//Field of the password
	private JTextField date1Field = new JTextField("day-month-year");//We create the two Fields for the dates
	private JTextField date2Field = new JTextField("day-month-year");
	private JTextField codeField; // field for the code search
	private JTextField rateField; //Field for the rate search
	private JComboBox<String> typesBox ;//ComboBox for the types search
	private JComboBox<String> statesBox ;//ComboBox for the state search
	private SpringLayout searchLayout;

	public LoginWindow() {
		super("1000House");
		
		cp.setLayout(new BorderLayout());//We set the layout of the container as border layout
		
		JLabel title; // We create the title of the app
		title = new JLabel("1000HOUSE");
		title.setHorizontalAlignment(JTextField.CENTER);//We put it in the center
		title.setFont(new Font("Tahoma",40,40));
		cp.add(title,BorderLayout.NORTH);//We add it on the north part of the window
		
		//Now we start with the Login part
		
		buttonPanel.add(login);// We add the button login to the button panel		 		
		
		SpringLayout textLayout = new SpringLayout(); // We create the layout of the textPanel
		textPanel.setLayout(textLayout); 

		JLabel nifLabel, passwordLabel;  //Labels used for the textPanel
		//We create the Labels and the fields
		nifLabel = new JLabel("NIF: ");
		nifField = new JTextField(10);
		passwordLabel = new JLabel("Password: ");
		passwordF = new JPasswordField(10);
		
		//We put the labels and fields in their place using the SpringLayout
		textLayout.putConstraint(SpringLayout.WEST, passwordLabel, 30, SpringLayout.WEST, textPanel);
		textLayout.putConstraint(SpringLayout.SOUTH, passwordLabel, -8, SpringLayout.SOUTH, textPanel);
		textLayout.putConstraint(SpringLayout.WEST, passwordF, 5, SpringLayout.EAST, passwordLabel);
		textLayout.putConstraint(SpringLayout.SOUTH, passwordF, -5, SpringLayout.SOUTH, textPanel);
		textLayout.putConstraint(SpringLayout.SOUTH, nifLabel, -8, SpringLayout.NORTH, passwordLabel);
		textLayout.putConstraint(SpringLayout.EAST, nifLabel, 0, SpringLayout.EAST, passwordLabel);
		textLayout.putConstraint(SpringLayout.SOUTH, nifField, -5, SpringLayout.NORTH, passwordF);
		textLayout.putConstraint(SpringLayout.WEST, nifField, 0, SpringLayout.WEST, passwordF);
		//Finally ,we add those components to the text panel
		textPanel.add(nifLabel);
		textPanel.add(nifField);
		textPanel.add(passwordLabel);
		textPanel.add(passwordF);
		textPanel.setPreferredSize(new Dimension(1, 90)); 		
		
		SpringLayout panelLayout = new SpringLayout();//We set the loginPanel layout
		loginPanel.setLayout(panelLayout); 
		//We put the textPanel and the button panel in the login panel
		panelLayout.putConstraint(SpringLayout.WEST, textPanel, 20, SpringLayout.WEST, loginPanel);
		panelLayout.putConstraint(SpringLayout.NORTH, textPanel, 20, SpringLayout.NORTH, loginPanel);
		panelLayout.putConstraint(SpringLayout.EAST, textPanel, 20, SpringLayout.EAST, loginPanel);
		panelLayout.putConstraint(SpringLayout.WEST, buttonPanel, 110, SpringLayout.WEST, loginPanel);
		panelLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 4 , SpringLayout.SOUTH, textPanel);	
		//Finally, we add all to the panel
		loginPanel.add(textPanel);
		loginPanel.add(buttonPanel);
		loginPanel.setPreferredSize(new Dimension(290, 120)); //We set the preferredSize
		cp.add(loginPanel,BorderLayout.WEST);//We add the LoginPanel in the west part of the window
		
		//Now we start with the profile and logout part. Those two buttons wont be added to the container until the user is logged
		
		GridLayout profLayout =new GridLayout(20,2);	// we set the layout as a  20x2 grid layout
		profilePanel.setLayout(profLayout);	
		profLayout.setVgap(10);//We set the vertical distance between the different components of the panel
		profilePanel.add(profilebutton);//We add the profile button in the top left part
		profilePanel.add(new JPanel());
		profilePanel.add(createHousebutton);//we add the craete house button
		profilePanel.add(new JPanel());
		profilePanel.add(createOfferbutton);//we add the create offer button
		profilePanel.add(new JPanel());
		for(int i = 0; i < 20; i++)profilePanel.add(new JPanel());//the rest will be empty panels		
		// We set the craete house and create offer buttons as not visible
		createHousebutton.setVisible(false);
		createOfferbutton.setVisible(false);
		
		GridLayout logoutLayout =new GridLayout(2,2);// we set the layout as a  2x2 grid layout
		logoutPanel.setLayout(logoutLayout);
		logoutLayout.setVgap(600);//We set the vertical distance between the different components of the panel
		logoutPanel.add(new JPanel());
		logoutPanel.add(logout);//We add the logout button on the top right part of the panel
		logoutPanel.add(new JPanel());//The other parts of the panel will be empty panels
		logoutPanel.add(new JPanel());	
		
		
		//Now we start with the search part.Each search will have a panel, that will be located in a different part of a bigger panel
		
		searchLayout = new SpringLayout();//We create the layout of the searchPanel
		searchPanel.setLayout(searchLayout);
		
		//First,the types search
		String[] types = {"Living","Holiday"};
		typesBox = new JComboBox<String>(types);//We create a combobox with the two options
		typesBox.setPreferredSize(new Dimension(120,23 ));
		JPanel searchTypePanel = new JPanel();//We create the panel.It has Flow LAyout(default)
		JLabel typeLabel = new JLabel("Type : ");//We create the label
		//We add the components in the order we want them to appear
		searchTypePanel.add(typeLabel);
		searchTypePanel.add(typesBox);
		searchButton1.setName("type"); // w set the name of the button to type
		searchTypePanel.add(searchButton1);
		//We put the type panel in his place in the seacrh panel, and add it
		searchLayout.putConstraint(SpringLayout.WEST, searchTypePanel, 80, SpringLayout.WEST, searchPanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchTypePanel, 150, SpringLayout.NORTH, searchPanel);
		searchPanel.add(searchTypePanel);
		
		
		
		//Secondly, the code search
		codeField = new JTextField();//we create the text field
		codeField.setPreferredSize(new Dimension(120,23 ));
		JPanel searchCodePanel = new JPanel();//We create the panel.It has Flow LAyout(default)
		JLabel codeLabel = new JLabel("Code : ");// we create the label
		//We add the components in the order we want them to appear
		searchCodePanel.add(codeLabel);
		searchCodePanel.add(codeField);
		searchButton2.setName("code"); // w set the name of the button to code
		searchCodePanel.add(searchButton2);
		//We put the code panel in his place in the seacrh panel, and add it
		searchLayout.putConstraint(SpringLayout.WEST, searchCodePanel, -2, SpringLayout.WEST, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchCodePanel, 30, SpringLayout.SOUTH, searchTypePanel);
		searchPanel.add(searchCodePanel);
		
		//Then, the dates seacrh panel
		date1Field.setPreferredSize(new Dimension(120,23 ));
		date2Field.setPreferredSize(new Dimension(120,23 ));
		JPanel datesPanel = new JPanel();// We create the panel and set his layout as a 2x2 grid layout
		datesPanel.setLayout(new GridLayout(2,2));
		JLabel date1Label = new JLabel("Date beggining :");//we create the labels and align them to the right
		JLabel date2Label = new JLabel("Date ending :");
		date1Label.setHorizontalAlignment(JTextField.RIGHT);
		date2Label.setHorizontalAlignment(JTextField.RIGHT);
		//We add the components in the order we want them to appear
		datesPanel.add(date1Label);
		datesPanel.add(date1Field);
		datesPanel.add(date2Label);
		datesPanel.add(date2Field);
		JPanel searchDatePanel = new JPanel();// We create the bigger panel, that has flow layout(default)
		//We add in order the dates panel and the button
		searchDatePanel.add(datesPanel);
		searchButton3.setName("date"); // w set the name of the button to date
		searchDatePanel.add(searchButton3);
		//We put the search dates panel in his place in the search panel, and add it
		searchLayout.putConstraint(SpringLayout.WEST, searchDatePanel, -15, SpringLayout.WEST, searchCodePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchDatePanel, 30, SpringLayout.SOUTH, searchCodePanel);
		searchPanel.add(searchDatePanel);
		
		//Now the search state panel. It would be invisible until the user is logged in
		String[] states = {"Bought","Booked"};
		statesBox = new JComboBox<String>(states);//We create a combobox with the two options
		statesBox.setPreferredSize(new Dimension(120,23 ));
		searchStatePanel = new JPanel();//We create the panel.It has Flow LAyout(default)
		JLabel stateLabel = new JLabel("State : ");//We create the label
		//We add the components in the order we want them to appear
		searchStatePanel.add(stateLabel);
		searchStatePanel.add(statesBox);
		searchButton4.setName("state"); // w set the name of the button to state
		searchStatePanel.add(searchButton4);
		//We put the type panel in his place in the seacrh panel, and add it
		searchLayout.putConstraint(SpringLayout.WEST, searchStatePanel, 15, SpringLayout.WEST, searchDatePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchStatePanel, 30, SpringLayout.SOUTH, searchDatePanel);
		searchPanel.add(searchStatePanel);
		searchStatePanel.setVisible(false);
		
		//Finally, we create the search rate panel. It would be invisible until the user is logged in
		rateField = new JTextField();//we create the text field
		rateField.setPreferredSize(new Dimension(120,23 ));
		JLabel rateLabel = new JLabel("Rate : ");// we create the label
		//We add the components in the order we want them to appear
		searchRatePanel.add(rateLabel);
		searchRatePanel.add(rateField);
		searchButton5.setName("rate"); // w set the name of the button to rate
		searchRatePanel.add(searchButton5);
//		We put the code panel in his place in the seacrh panel, and add it
		searchLayout.putConstraint(SpringLayout.WEST, searchRatePanel, 7, SpringLayout.WEST, searchStatePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchRatePanel, 30, SpringLayout.SOUTH, searchStatePanel);
		searchPanel.add(searchRatePanel);
		searchRatePanel.setVisible(false);
		
		
		//We also create a title and put it on the top of the search panels
		JLabel search = new JLabel("SEARCH IN THE APP!");
		search.setFont(new Font("Tahoma",20,20));		
		searchLayout.putConstraint(SpringLayout.WEST, search, 50, SpringLayout.WEST, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.SOUTH, search, -15, SpringLayout.NORTH, searchTypePanel);		
		searchPanel.add(search);
		
		cp.add(searchPanel,BorderLayout.CENTER);//We add the whole search panel in the center of the window
		
		
		
		this.pack(); // Important: subcomponents are located according to theirlayout using their prefered sizes.		
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * @return the nif on the nif text field
	 */
	public String getNif() {
		return nifField.getText();
	}
	
	/**
	 * @return the password on the password field
	 */
	public String getPassword() {
		String p = "";
		for(char c : passwordF.getPassword()) {
			p = p + c;
		}
		return p;
	}
	
	/**
	 * @return the selected option in the types combo box
	 */
	public String getSelectedType() {
		return (String)typesBox.getSelectedItem();
	}
	
	/**
	 * @return the code in the code text field
	 */
	public String getCodeField() {
		return codeField.getText();
	}
	
	/**
	 * @return an array with the two dates in the dates text fields
	 */
	public String[] getDatesField(){
		String[] s = {date1Field.getText(),date2Field.getText()};
		return s;
	}
	
	/**
	 * @return the selected option in the state combo box
	 */
	public String getSelectedState() {
		return (String)statesBox.getSelectedItem();
	}
	
	/**
	 * @return the rate in the rate text field
	 */
	public String getRateField() {
		return rateField.getText();
	}
	
	/**
	 * Changes the window to logged mode, making the profile  and logout button visible,
	 *  the rate and state search visible, and the login panel invisible
	 *  If the user logged is a host, it also makes the create offer and create house buttons as visible
	 */
	public void setUserLogin(int host) {
		loginPanel.setVisible(false);
		profilePanel.setVisible(true);
		if(host == 1) {
			createHousebutton.setVisible(true);
			createOfferbutton.setVisible(true);
		}
		logoutPanel.setVisible(true);
		cp.add(profilePanel,BorderLayout.WEST);
		cp.add(logoutPanel, BorderLayout.EAST);
		searchStatePanel.setVisible(true);
		searchRatePanel.setVisible(true);
		
	}
	
	/**
	 * Changes the window to not logged mode, making the profile and logout button invisible,
	 *  the rate  and state search invisible, and the login panel visible
	 */
	public void  setUserLogout() {
		profilePanel.setVisible(false);
		createHousebutton.setVisible(false);
		createOfferbutton.setVisible(false);
		logoutPanel.setVisible(false);
		loginPanel.setVisible(true);
		nifField.setText("");
		passwordF.setText("");
		cp.add(loginPanel,BorderLayout.WEST);
		cp.add(new JPanel(), BorderLayout.EAST);
		searchStatePanel.setVisible(false);
		searchRatePanel.setVisible(false);
	}
	
	/**
	 * Sets the controller for the login,logout and profile button
	 * @param c : the controller to be set
	 */
	public void setLoginLogoutProfileController(ActionListener c) {
		login.addActionListener(c);	
		logout.addActionListener(c);
		profilebutton.addActionListener(c);
	}
	
	/**
	 * Sets the controller for the different search buttons
	 * @param c : the controller to be set
	 */
	public void setSearchController(ActionListener c) {
		searchButton1.addActionListener(c);	
		searchButton2.addActionListener(c);	
		searchButton3.addActionListener(c);	
		searchButton4.addActionListener(c);	
		searchButton5.addActionListener(c);	
	}
	
	/**
	 * Sets the controllers for the create offer and create house buttons
	 * @param c : the controller to be set
	 */
	public void setHostControllers(ActionListener c) {
		createOfferbutton.addActionListener(c);
		createHousebutton.addActionListener(c);
	}
	
}
