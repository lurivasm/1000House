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
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	private JButton back = new JButton("Back");
	private JLabel label, label2;
	private JTextField field, field2;

	public WindowForm() {
		super("An example form");
		SpringLayout layout0 = new SpringLayout();
		thePanel.setLayout(layout0); 
		// The button panel(JPanel) by default uses FlowLayout, so we do nothing
		buttonPanel.add(ok); // Components rendered with flow layout are shown ...
		buttonPanel.add(cancel); // by default from left to write �
		buttonPanel.add(back); // using new rows if there is not enough horizontal space
		
		
		
		
		SpringLayout layout1 = new SpringLayout(); // Layout based on restrictions ...
		textPanel.setLayout(layout1); // Very flexible but low level.

		// Components to be located ...
		label = new JLabel("Name: ");
		field = new JTextField("<name>", 10);
		label2 = new JLabel("Age: ");
		field2 = new JTextField("<age>", 5);
		
		// The left side of label will be located 5 pixels away from the left side of
		// container
		layout1.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, textPanel);
		// The upper side of label will be located 5 pixels away from the upper part of
		// container
		layout1.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, textPanel);
		
		// The left side of field will be located 5 pixels away from the right side of
		// label
		layout1.putConstraint(SpringLayout.WEST, field, 5, SpringLayout.EAST, label);
		// The upper side of the field will be located 5 pixels away from the upper side
		// of container
		
		layout1.putConstraint(SpringLayout.NORTH, field, 5, SpringLayout.NORTH, textPanel);
		// The left side of label2 will be aligned with the left border of label
		layout1.putConstraint(SpringLayout.WEST, label2, 0, SpringLayout.WEST, label);
		// The upper part of label2 will be located 5 pixels away from the lower border
		// of label
		layout1.putConstraint(SpringLayout.NORTH, label2, 8, SpringLayout.SOUTH, label);
		// The left side of field2 will be aligned with the left side of field
		layout1.putConstraint(SpringLayout.WEST, field2, 0, SpringLayout.WEST, field);
		// El upper side of field2 will be located 5 pixels away from field.
		layout1.putConstraint(SpringLayout.NORTH, field2, 5, SpringLayout.SOUTH, field);
		textPanel.add(label);
		textPanel.add(field);
		textPanel.add(label2);
		textPanel.add(field2);
		textPanel.setPreferredSize(new Dimension(10, 50)); // important: preferred size for this panel
		
		
		
		
		layout0.putConstraint(SpringLayout.WEST, textPanel, 20, SpringLayout.WEST, thePanel);
		layout0.putConstraint(SpringLayout.NORTH, textPanel, 20, SpringLayout.NORTH, thePanel);
		layout0.putConstraint(SpringLayout.EAST, textPanel, 20, SpringLayout.EAST, thePanel);
		layout0.putConstraint(SpringLayout.WEST, buttonPanel, 20, SpringLayout.WEST, thePanel);
		layout0.putConstraint(SpringLayout.NORTH, buttonPanel, 20, SpringLayout.NORTH, thePanel);
		layout0.putConstraint(SpringLayout.NORTH, buttonPanel, 5 , SpringLayout.SOUTH, textPanel);		
		thePanel.add(textPanel);
		thePanel.add(buttonPanel);
		thePanel.setPreferredSize(new Dimension(290, 120)); //
		
		thePanel.setBorder( BorderFactory.createLineBorder(Color.black));
		
		Container cp = this.getContentPane(); // Get the Frame container
		SpringLayout layout = new SpringLayout(); 
		cp.setLayout(layout);
		layout.putConstraint(SpringLayout.WEST, thePanel , 360, SpringLayout.WEST, cp);
		layout.putConstraint(SpringLayout.NORTH, thePanel, 300, SpringLayout.NORTH, cp);
				
		cp.add(thePanel);
		
		cp.setBackground(Color.GREEN);
		this.pack(); // Important: subcomponents are located according to �
		// layout using their prefered sizes.
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
