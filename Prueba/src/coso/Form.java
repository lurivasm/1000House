package coso;

import java.awt.Dimension;

import javax.swing.*;

class Form extends JPanel {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6461248229231127502L;
	private JLabel label, label2;
	 private JTextField field, field2;
	 public Form () {
	 SpringLayout layout = new SpringLayout(); // Layout based on restrictions ...
	 this.setLayout(layout); // Very flexible but low level.

	 // Components to be located ...
	 label = new JLabel("Name: ");
	 field = new JTextField("<name>", 15);
	 label2 = new JLabel("Age: ");
	 field2 = new JTextField("<age>", 5);
	 // The left side of label will be located 5 pixels away from the left side of container
	 layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
	 // The upper side of label will be located 5 pixels away from the upper part of container
	 layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, this);
	 // The left side of field will be located 5 pixels away from the right side of label
	 layout.putConstraint(SpringLayout.WEST, field, 5, SpringLayout.EAST, label);
	 // The upper side of the field will be located 5 pixels away from the upper side of container
	 layout.putConstraint(SpringLayout.NORTH, field, 5, SpringLayout.NORTH, this);
	 // The left side of label2 will be aligned with the left border of label
	 layout.putConstraint(SpringLayout.WEST, label2, 0, SpringLayout.WEST, label);
	 // The upper part of label2 will be located 5 pixels away from the lower border of label
	 layout.putConstraint(SpringLayout.NORTH, label2, 8, SpringLayout.SOUTH, label);
	 // The left side of field2 will be aligned with the left side of field
	 layout.putConstraint(SpringLayout.WEST, field2, 0, SpringLayout.WEST, field);
	 // El upper side of field2 will be located 5 pixels away from field.
	 layout.putConstraint(SpringLayout.NORTH, field2, 5, SpringLayout.SOUTH, field);
	 this.add(label); this.add(field); this.add(label2); this.add(field2);
	 this.setPreferredSize(new Dimension(250,50)); // important: preferred size for this panel
	 this.setVisible(true); }
	}
