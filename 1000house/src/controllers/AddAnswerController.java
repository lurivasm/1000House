package controllers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import exception.*;

import javax.swing.*;

public class AddAnswerController implements ActionListener{
	@SuppressWarnings("unused")
	private Application model;
	private SeeOfferWindow view;
	
	private JFrame w = new JFrame("Add Answer");
	private JPanel centerpanel = new JPanel();
	private JPanel textpanel = new JPanel();
	private SpringLayout centerlayout = new SpringLayout();
	private JTextArea textfield = new JTextArea(8,30);
	
	
	public AddAnswerController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Container cp = w.getContentPane();
		if(arg0.getActionCommand().equals("Add Answer")) {
			cp.setLayout(new BorderLayout());
			JLabel title = new JLabel("Add your answer");
			title.setHorizontalAlignment(JTextField.CENTER);
			cp.add(title, BorderLayout.NORTH);
			
			centerpanel.setLayout(centerlayout);
			textfield.setLineWrap(true);
			textfield.setWrapStyleWord(true);
			JScrollPane pan = new JScrollPane(textfield);
			textpanel.add(pan);
			centerlayout.putConstraint(SpringLayout.WEST, textpanel, 80, SpringLayout.WEST, w);
			centerlayout.putConstraint(SpringLayout.NORTH, textpanel, 60, SpringLayout.SOUTH, w);
			centerpanel.add(textpanel);
			
			JButton accept = new JButton("Accept");
			centerlayout.putConstraint(SpringLayout.WEST, accept, 135, SpringLayout.WEST, textpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, accept, 20, SpringLayout.SOUTH, textpanel);
			centerpanel.add(accept);
			accept.addActionListener(this);
					
			cp.add(centerpanel, BorderLayout.CENTER);
			
			w.pack(); // Important: subcomponents are located according to their layout using their
			// preferred sizes.
			w.setSize(500, 300);
			w.setLocationRelativeTo(null);
			w.setVisible(true);
		}
		else {
			String text = textfield.getText();
			if(text.equals("")) {
				JOptionPane.showMessageDialog(w,"Wrong Format");
				return;
			}
			try {
				view.addAnswer(text);
			} catch (TextException e) {
				JOptionPane.showMessageDialog(w,e);
			} catch (NotGuest e) {
				JOptionPane.showMessageDialog(w,e);
			}
			
			textfield.setVisible(false);
			w.setVisible(false);
		}

		
	}
}
