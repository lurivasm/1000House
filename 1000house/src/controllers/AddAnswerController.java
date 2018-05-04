package controllers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import exception.*;

import javax.swing.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class AddAnswerController implements ActionListener{
	@SuppressWarnings("unused")
	private Application model;//The model
	private SeeOfferWindow view;// The view where the controller is working on
	
	private JFrame w = new JFrame("Add Answer");// The window used to add answers
	private JPanel centerpanel = new JPanel();//Panel of the center of the window
	private JPanel textpanel ;//Panel for the text area 
	private SpringLayout centerlayout = new SpringLayout();//Layout for the center panel
	private JTextArea textfield = new JTextArea(8,30);//Text area for adding the comment
	private JButton accept = new JButton("Accept"); ; // Accept button
	
	/**
	 * The constructor adds the view and model fields
	 * @param l_ window that will become the view
	 * @param model application that will be the model
	 */
	public AddAnswerController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Container cp = w.getContentPane();//We get the container of the window
		//If the button pressed is the one to add an answer:
		if(arg0.getActionCommand().equals("Add Answer")) {
			accept.setVisible(false);
			//We set the tite on the north part of the container
			cp.setLayout(new BorderLayout());
			JLabel title = new JLabel("Add your answer");
			title.setHorizontalAlignment(JTextField.CENTER);
			cp.add(title, BorderLayout.NORTH);
			
			//We create the center panel, adding to it the text panel 
			centerpanel.setLayout(centerlayout);
			textpanel = new JPanel();
			textfield = new JTextArea(8,30);
			textfield.setLineWrap(true);
			textfield.setWrapStyleWord(true);
			JScrollPane pan = new JScrollPane(textfield);
			textpanel.add(pan);
			centerlayout.putConstraint(SpringLayout.WEST, textpanel, 80, SpringLayout.WEST, w);
			centerlayout.putConstraint(SpringLayout.NORTH, textpanel, 60, SpringLayout.SOUTH, w);
			centerpanel.add(textpanel);
			
			//We create and add the accept button
			accept = new JButton("Accept");
			centerlayout.putConstraint(SpringLayout.WEST, accept, 135, SpringLayout.WEST, textpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, accept, 20, SpringLayout.SOUTH, textpanel);
			centerpanel.add(accept);
			accept.setVisible(true);
			accept.addActionListener(this);//We set the accept action listener as this
					
			cp.add(centerpanel, BorderLayout.CENTER);
			
			w.pack(); // Important: subcomponents are located according to their layout using their
			// preferred sizes.
			w.setSize(500, 300);
			w.setLocationRelativeTo(null);
			w.setVisible(true);
		}
		//If the button pressed is the accept button
		else {
			String text = textfield.getText();//we get the text
			if(text.equals("")) {
				JOptionPane.showMessageDialog(w,"Wrong Format");
				return;
			}
			try {
				view.addAnswer(text);//We add it as an answer
			} catch (TextException e) {
				JOptionPane.showMessageDialog(w,e);
			} catch (NotGuest e) {
				JOptionPane.showMessageDialog(w,e);
			}
			
			textfield.setVisible(false);
			accept.setVisible(false);
			w.setVisible(false);//We close the window
		}

		
	}
}
