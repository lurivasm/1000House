package coso;

import java.awt.*;

import javax.swing.*;

class WindowForm extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 9102096458417906733L;
	private JPanel buttonPanel = new JPanel();
	private JPanel textPanel = new JPanel();// Hereda de JPanel
	private JPanel thePanel = new JPanel();
	private JButton login = new JButton("Login");
	private JLabel nifLabel, passwordLabel,title;
	private JTextField field, passwordF;

	public WindowForm() {
		super("An example form");
		SpringLayout layout0 = new SpringLayout();
		thePanel.setLayout(layout0); 
		// The button panel(JPanel) by default uses FlowLayout, so we do nothing
		buttonPanel.add(login); // Components rendered with flow layout are shown ...
		
		
		
		
		
		SpringLayout layout1 = new SpringLayout(); // Layout based on restrictions ...
		textPanel.setLayout(layout1); // Very flexible but low level.

		// Components to be located ...
		nifLabel = new JLabel("NIF: ");
		field = new JTextField(10);
		passwordLabel = new JLabel("Password: ");
		passwordF = new JPasswordField(10);
		
		// The left side of label will be located 5 pixels away from the left side of
		// container
		layout1.putConstraint(SpringLayout.WEST, passwordLabel, 30, SpringLayout.WEST, textPanel);
		// The upper side of label will be located 5 pixels away from the upper part of
		// container
		layout1.putConstraint(SpringLayout.SOUTH, passwordLabel, -8, SpringLayout.SOUTH, textPanel);
		
		// The left side of field will be located 5 pixels away from the right side of
		// label
		layout1.putConstraint(SpringLayout.WEST, passwordF, 5, SpringLayout.EAST, passwordLabel);
		// The upper side of the field will be located 5 pixels away from the upper side
		// of container
		
		layout1.putConstraint(SpringLayout.SOUTH, passwordF, -5, SpringLayout.SOUTH, textPanel);
		// The upper part of label2 will be located 5 pixels away from the lower border
		// of label
		layout1.putConstraint(SpringLayout.SOUTH, nifLabel, -8, SpringLayout.NORTH, passwordLabel);
		// The RIGHT side of field will be aligned with the RIGHT side of field2
		layout1.putConstraint(SpringLayout.EAST, nifLabel, 0, SpringLayout.EAST, passwordLabel);
		// El upper side of field2 will be located 5 pixels away from field.
		layout1.putConstraint(SpringLayout.SOUTH, field, -5, SpringLayout.NORTH, passwordF);
		layout1.putConstraint(SpringLayout.WEST, field, 0, SpringLayout.WEST, passwordF);
	
		textPanel.add(nifLabel);
		textPanel.add(field);
		textPanel.add(passwordLabel);
		textPanel.add(passwordF);
		textPanel.setPreferredSize(new Dimension(1, 90)); // important: preferred size for this panel
		
		
		
		
		layout0.putConstraint(SpringLayout.WEST, textPanel, 20, SpringLayout.WEST, thePanel);
		layout0.putConstraint(SpringLayout.NORTH, textPanel, 20, SpringLayout.NORTH, thePanel);
		layout0.putConstraint(SpringLayout.EAST, textPanel, 20, SpringLayout.EAST, thePanel);
		layout0.putConstraint(SpringLayout.WEST, buttonPanel, 110, SpringLayout.WEST, thePanel);
		layout0.putConstraint(SpringLayout.NORTH, buttonPanel, 4 , SpringLayout.SOUTH, textPanel);	
		
		thePanel.add(textPanel);
		thePanel.add(buttonPanel);
		thePanel.setPreferredSize(new Dimension(290, 120)); //
		thePanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
		Container cp = this.getContentPane(); // Get the Frame container
//		SpringLayout layout = new SpringLayout(); 
//		cp.setLayout(layout);
//		layout.putConstraint(SpringLayout.WEST, thePanel , 360, SpringLayout.WEST, cp);
//		layout.putConstraint(SpringLayout.NORTH, thePanel, 300, SpringLayout.NORTH, cp);
		cp.setLayout(new BorderLayout());		
		cp.add(thePanel,BorderLayout.CENTER);
		title = new JLabel("                              1000HOUSE");
		title.setFont(new Font("Tahoma",40,40));
		cp.add(title,BorderLayout.NORTH);
		
		this.pack(); // Important: subcomponents are located according to �
		// layout using their prefered sizes.
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
