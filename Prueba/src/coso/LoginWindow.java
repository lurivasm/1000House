package coso;

import java.awt.*;

import javax.swing.*;

class LoginWindow extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 9102096458417906733L;
	private JPanel buttonPanel = new JPanel();
	private JPanel textPanel = new JPanel();// Hereda de JPanel
	private JPanel searchPanel = new JPanel();
	private JPanel thePanel = new JPanel();
	private JButton login = new JButton("Login");
	private JLabel nifLabel, passwordLabel,title;
	private JTextField field, passwordF;

	public LoginWindow() {
		super("An example form");
		SpringLayout panelLayout = new SpringLayout();
		thePanel.setLayout(panelLayout); 
		// The button panel(JPanel) by default uses FlowLayout, so we do nothing
		buttonPanel.add(login); // Components rendered with flow layout are shown ...
		
		
		
		
		
		SpringLayout textLayout = new SpringLayout(); // Layout based on restrictions ...
		textPanel.setLayout(textLayout); // Very flexible but low level.

		// Components to be located ...
		nifLabel = new JLabel("NIF: ");
		field = new JTextField(10);
		passwordLabel = new JLabel("Password: ");
		passwordF = new JPasswordField(10);
		
		// The left side of label will be located 5 pixels away from the left side of
		// container
		textLayout.putConstraint(SpringLayout.WEST, passwordLabel, 30, SpringLayout.WEST, textPanel);
		// The upper side of label will be located 5 pixels away from the upper part of
		// container
		textLayout.putConstraint(SpringLayout.SOUTH, passwordLabel, -8, SpringLayout.SOUTH, textPanel);
		
		// The left side of field will be located 5 pixels away from the right side of
		// label
		textLayout.putConstraint(SpringLayout.WEST, passwordF, 5, SpringLayout.EAST, passwordLabel);
		// The upper side of the field will be located 5 pixels away from the upper side
		// of container
		
		textLayout.putConstraint(SpringLayout.SOUTH, passwordF, -5, SpringLayout.SOUTH, textPanel);
		// The upper part of label2 will be located 5 pixels away from the lower border
		// of label
		textLayout.putConstraint(SpringLayout.SOUTH, nifLabel, -8, SpringLayout.NORTH, passwordLabel);
		// The RIGHT side of field will be aligned with the RIGHT side of field2
		textLayout.putConstraint(SpringLayout.EAST, nifLabel, 0, SpringLayout.EAST, passwordLabel);
		// El upper side of field2 will be located 5 pixels away from field.
		textLayout.putConstraint(SpringLayout.SOUTH, field, -5, SpringLayout.NORTH, passwordF);
		textLayout.putConstraint(SpringLayout.WEST, field, 0, SpringLayout.WEST, passwordF);
	
		textPanel.add(nifLabel);
		textPanel.add(field);
		textPanel.add(passwordLabel);
		textPanel.add(passwordF);
		textPanel.setPreferredSize(new Dimension(1, 90)); // important: preferred size for this panel
		
		
		
		
		panelLayout.putConstraint(SpringLayout.WEST, textPanel, 20, SpringLayout.WEST, thePanel);
		panelLayout.putConstraint(SpringLayout.NORTH, textPanel, 20, SpringLayout.NORTH, thePanel);
		panelLayout.putConstraint(SpringLayout.EAST, textPanel, 20, SpringLayout.EAST, thePanel);
		panelLayout.putConstraint(SpringLayout.WEST, buttonPanel, 110, SpringLayout.WEST, thePanel);
		panelLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 4 , SpringLayout.SOUTH, textPanel);	
		
		thePanel.add(textPanel);
		thePanel.add(buttonPanel);
		thePanel.setPreferredSize(new Dimension(290, 120)); //
		
				
		Container cp = this.getContentPane(); // Get the Frame container
		cp.setLayout(new BorderLayout());		
		cp.add(thePanel,BorderLayout.WEST);
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
		JPanel date1Panel = new JPanel();
		JPanel date2Panel = new JPanel();
		JPanel datesPanel = new JPanel();
		datesPanel.setLayout(new BorderLayout());
		JPanel searchDatePanel = new JPanel();
		JLabel date1Label = new JLabel("Date beggining : ");
		JLabel date2Label = new JLabel("Date ending : ");
		date1Panel.add(date1Label);
		date1Panel.add(date1Field);
		date2Panel.add(date2Label);
		date2Panel.add(date2Field);
		datesPanel.add(date1Panel, BorderLayout.EAST);
		datesPanel.add(date2Panel,BorderLayout.SOUTH);
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
		searchLayout.putConstraint(SpringLayout.WEST, searchDatePanel, 0, SpringLayout.WEST, searchCodePanel);
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
}
