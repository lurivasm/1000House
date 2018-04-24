package windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

class LoginWindow extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 9102096458417906733L;
	private JPanel buttonPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel profilePanel = new JPanel();		
	private JPanel logoutPanel = new JPanel();
	private JButton login = new JButton("Login");
	private JButton profilebutton = new JButton("Profile");
	private JButton logout = new JButton("Logout");
	private JTextField nifField;
	private JPasswordField passwordF;

	public LoginWindow() {
		super("An example form");
		
		JLabel nifLabel, passwordLabel,title;
		
		SpringLayout panelLayout = new SpringLayout();
		loginPanel.setLayout(panelLayout); 
		buttonPanel.add(login); 
		
		
		
		
		
		SpringLayout textLayout = new SpringLayout(); // Layout based on restrictions ...
		textPanel.setLayout(textLayout); // Very flexible but low level.

		// Components to be located ...
		nifLabel = new JLabel("NIF: ");
		nifField = new JTextField(10);
		passwordLabel = new JLabel("Password: ");
		passwordF = new JPasswordField(10);
		
		
		textLayout.putConstraint(SpringLayout.WEST, passwordLabel, 30, SpringLayout.WEST, textPanel);
		textLayout.putConstraint(SpringLayout.SOUTH, passwordLabel, -8, SpringLayout.SOUTH, textPanel);
		textLayout.putConstraint(SpringLayout.WEST, passwordF, 5, SpringLayout.EAST, passwordLabel);
		textLayout.putConstraint(SpringLayout.SOUTH, passwordF, -5, SpringLayout.SOUTH, textPanel);
		textLayout.putConstraint(SpringLayout.SOUTH, nifLabel, -8, SpringLayout.NORTH, passwordLabel);
		textLayout.putConstraint(SpringLayout.EAST, nifLabel, 0, SpringLayout.EAST, passwordLabel);
		textLayout.putConstraint(SpringLayout.SOUTH, nifField, -5, SpringLayout.NORTH, passwordF);
		textLayout.putConstraint(SpringLayout.WEST, nifField, 0, SpringLayout.WEST, passwordF);
	
		textPanel.add(nifLabel);
		textPanel.add(nifField);
		textPanel.add(passwordLabel);
		textPanel.add(passwordF);
		textPanel.setPreferredSize(new Dimension(1, 90)); // important: preferred size for this panel
		
		
		
		
		panelLayout.putConstraint(SpringLayout.WEST, textPanel, 20, SpringLayout.WEST, loginPanel);
		panelLayout.putConstraint(SpringLayout.NORTH, textPanel, 20, SpringLayout.NORTH, loginPanel);
		panelLayout.putConstraint(SpringLayout.EAST, textPanel, 20, SpringLayout.EAST, loginPanel);
		panelLayout.putConstraint(SpringLayout.WEST, buttonPanel, 110, SpringLayout.WEST, loginPanel);
		panelLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 4 , SpringLayout.SOUTH, textPanel);	
		
		loginPanel.add(textPanel);
		loginPanel.add(buttonPanel);
		loginPanel.setPreferredSize(new Dimension(290, 120)); //		
		loginPanel.setVisible(false);
		
			
		
		GridLayout profLayout =new GridLayout(2,2);		
		profilePanel.setLayout(profLayout);	
		profLayout.setVgap(600);
		profLayout.setHgap(50);
		profilePanel.add(profilebutton);
		profilePanel.add(new JPanel());
		profilePanel.add(new JPanel());
		profilePanel.add(new JPanel());
		
		
		GridLayout logoutLayout =new GridLayout(2,2);
		logoutPanel.setLayout(logoutLayout);
		logoutLayout.setVgap(600);
		logoutPanel.add(new JPanel());
		logoutPanel.add(logout);
		logoutPanel.add(new JPanel());
		logoutPanel.add(new JPanel());
		
		Container cp = this.getContentPane(); 
		cp.setLayout(new BorderLayout());		
		cp.add(loginPanel,BorderLayout.WEST);
		cp.add(profilePanel,BorderLayout.WEST);
		cp.add(logoutPanel,BorderLayout.EAST);
		title = new JLabel("1000HOUSE");
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setFont(new Font("Tahoma",40,40));
		cp.add(title,BorderLayout.NORTH);
		
		String[] types = {"Living","Holiday"};
		JComboBox<String> typesBox = new JComboBox<String>(types);
		typesBox.setPreferredSize(new Dimension(120,23 ));
		JPanel searchTypePanel = new JPanel();
		JLabel typeLabel = new JLabel("Type : ");
		JButton searchButton1 = new JButton("SEARCH");
		searchTypePanel.add(typeLabel);
		searchTypePanel.add(typesBox);
		searchTypePanel.add(searchButton1);
		
		JTextField codeField = new JTextField();
		codeField.setPreferredSize(new Dimension(120,23 ));
		JPanel searchCodePanel = new JPanel();
		JLabel codeLabel = new JLabel("Code : ");
		JButton searchButton2 = new JButton("SEARCH");
		searchCodePanel.add(codeLabel);
		searchCodePanel.add(codeField);
		searchCodePanel.add(searchButton1);
		searchTypePanel.add(searchButton2);
		
		
		JTextField date1Field = new JTextField("day-month-year");
		JTextField date2Field = new JTextField("day-month-year");
		date1Field.setPreferredSize(new Dimension(120,23 ));
		date2Field.setPreferredSize(new Dimension(120,23 ));
		JPanel datesPanel = new JPanel();
		datesPanel.setLayout(new GridLayout(2,2));
		JPanel searchDatePanel = new JPanel();
		JLabel date1Label = new JLabel("Date beggining :");
		JLabel date2Label = new JLabel("Date ending :");
		date1Label.setHorizontalAlignment(JTextField.RIGHT);
		date2Label.setHorizontalAlignment(JTextField.RIGHT);
		datesPanel.add(date1Label);
		datesPanel.add(date1Field);
		datesPanel.add(date2Label);
		datesPanel.add(date2Field);
		JButton searchButton3 = new JButton("SEARCH");
		searchDatePanel.add(datesPanel);
		searchDatePanel.add(searchButton3);
		
		
		
		JLabel search = new JLabel("SEARCH IN THE APP!");
		search.setFont(new Font("Tahoma",20,20));
		SpringLayout searchLayout = new SpringLayout();
		searchPanel.setLayout(searchLayout);
		searchLayout.putConstraint(SpringLayout.WEST, searchTypePanel, 80, SpringLayout.WEST, searchPanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchTypePanel, 200, SpringLayout.NORTH, searchPanel);
		searchLayout.putConstraint(SpringLayout.WEST, searchCodePanel, -2, SpringLayout.WEST, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchCodePanel, 30, SpringLayout.SOUTH, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.WEST, search, 50, SpringLayout.WEST, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.SOUTH, search, -15, SpringLayout.NORTH, searchTypePanel);
		searchLayout.putConstraint(SpringLayout.WEST, searchDatePanel, -15, SpringLayout.WEST, searchCodePanel);
		searchLayout.putConstraint(SpringLayout.NORTH, searchDatePanel, 30, SpringLayout.SOUTH, searchCodePanel);
		searchPanel.add(searchTypePanel);
		searchPanel.add(searchCodePanel);
		searchPanel.add(search);
		searchPanel.add(searchDatePanel);
		searchPanel.setPreferredSize(new Dimension(300, 300));
		
		cp.add(searchPanel,BorderLayout.CENTER);

		
		
		this.pack(); // Important: subcomponents are located according to �
		// layout using their prefered sizes.
		this.setSize(900, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public String getNif() {
		return nifField.getText();
	}
	
	public String getPassword() {
		String p = "";
		for(char c : passwordF.getPassword()) {
			p = p + c;
		}
		return p;
	}
	
	
	public void setLoginController(ActionListener c) {
		login.addActionListener(c);	
	}
}
