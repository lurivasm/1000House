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
public class CommentController implements ActionListener {

	private Application model;// The model
	private SeeOfferWindow view;// The view where the controller is working on

	private JFrame w = new JFrame("Comment");// The window used to add comments
	private JPanel centerpanel = new JPanel();// Panel placed in the center of the window
	private JPanel ratepanel;// Panel for the rate comment
	private JPanel textpanel;// Panel for the text comment
	private JLabel textlabel = new JLabel("Text: ");//Label for the text comment
	private JLabel ratelabel = new JLabel("Rate : ");//LAbel for the rate comment
	private SpringLayout centerlayout = new SpringLayout();//LAyout of theh center panel
	private JTextArea textfield = new JTextArea(8, 30);//Text area for the text comment
	private JComboBox<String> ratefield;//Combo box for the rate comment
	private JButton acceptrate = new JButton("Accept");//Button of accept for the rate comment
	private JButton accepttext = new JButton("Accept");//Button of accept for the text comment

	/**
	 * The constructor adds the view and model fields
	 * @param l_ window that will become the view
	 * @param model application that will be the model
	 */
	public CommentController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Container cp = w.getContentPane();//We get the container of the JFrame we created when someone presssed the button		
		
		//If the button pressed is comment:
		if (arg0.getActionCommand().equals("Comment")) {
			//We set the title to add your comment, in the north part of the window
			cp.setLayout(new BorderLayout());
			JLabel title = new JLabel("Add your comment");
			title.setHorizontalAlignment(JTextField.CENTER);
			cp.add(title, BorderLayout.NORTH);
			
			//We set the accept buttons as not visible
			accepttext.setVisible(false);
			acceptrate.setVisible(false);
			
			//We create the rate and text panels
			ratepanel = new JPanel();
			textpanel = new JPanel();
			
			//We create the two buttons to select the type of comment
			centerpanel.setLayout(centerlayout);
			ButtonGroup buttons = new ButtonGroup();
			JPanel butpan = new JPanel();
			JRadioButton rate = new JRadioButton("Rate");
			JRadioButton text = new JRadioButton("Text");
			rate.addActionListener(this);//We set this as the action listener of the buttons
			text.addActionListener(this);
			buttons.add(rate);
			buttons.add(text);
			butpan.add(rate);
			butpan.add(text);
			//We add the buttons in the north part of the center panel
			centerlayout.putConstraint(SpringLayout.WEST, butpan, 180, SpringLayout.WEST, centerpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, butpan, 20, SpringLayout.NORTH, centerpanel);
			centerpanel.add(butpan);
			
			//Now we create the rate panel
			ratepanel.add(ratelabel);
			String[] op = { "0", "1", "2", "3", "4", "5" };
			ratefield = new JComboBox<String>(op);
			ratefield.setPreferredSize(new Dimension(50, 20));
			ratepanel.add(ratefield);
			//We add it under the buttons and set it as invisible
			centerlayout.putConstraint(SpringLayout.WEST, ratepanel, 0, SpringLayout.WEST, butpan);
			centerlayout.putConstraint(SpringLayout.NORTH, ratepanel, 20, SpringLayout.SOUTH, butpan);
			centerpanel.add(ratepanel);
			ratepanel.setVisible(false);
			
			//Now we create the text panel 
			textpanel.add(textlabel);
			textfield.setLineWrap(true);
			textfield.setWrapStyleWord(true);
			JScrollPane pan = new JScrollPane(textfield);
			textpanel.add(pan);
			//We add it under the buttons too, setting it as not visible
			centerlayout.putConstraint(SpringLayout.WEST, textpanel, -120, SpringLayout.WEST, butpan);
			centerlayout.putConstraint(SpringLayout.NORTH, textpanel, 50, SpringLayout.SOUTH, butpan);
			centerpanel.add(textpanel);
			textpanel.setVisible(false);
			textlabel.setVisible(false);
			//We add the centerpanel in the center of the window
			cp.add(centerpanel, BorderLayout.CENTER);

			w.pack(); // Important: subcomponents are located according to their layout using their
						// preferred sizes.
			w.setSize(500, 300);
			w.setLocationRelativeTo(null);
			w.setVisible(true);
			
		} 
		//If the button pressed is the rate button(the one to select the rate type comment):
		else if (arg0.getActionCommand().equals("Rate")) {
			//We set the text panel and button as invisible
			textpanel.setVisible(false);
			accepttext.setVisible(false);
			//We set the rate panel,label, and field as visible
			ratepanel.setVisible(true);
			ratelabel.setVisible(true);
			ratefield.setVisible(true);
			//We create the accept button and set his name to comrate
			acceptrate = new JButton("Accept");
			acceptrate.setName("comrate");
			//We place the button under the rate panel
			centerlayout.putConstraint(SpringLayout.WEST, acceptrate, 20, SpringLayout.WEST, ratepanel);
			centerlayout.putConstraint(SpringLayout.NORTH, acceptrate, 20, SpringLayout.SOUTH, ratepanel);
			centerpanel.add(acceptrate);
			acceptrate.addActionListener(this);//We set the accept button action listener as this 
		} 
		//If the button pressed is the text button(the one to select the text type comment):
		else if (arg0.getActionCommand().equals("Text")) {
			//We set the rate panel,field  and button as invisible
			ratepanel.setVisible(false);
			acceptrate.setVisible(false);
			ratefield.setVisible(false);
			//We set the text label and panel as visible
			textpanel.setVisible(true);
			textlabel.setVisible(true);
			//We create the accept button and set his name to comtext
			accepttext = new JButton("Accept");
			accepttext.setName("comtext");
			//We place it under the text panel
			centerlayout.putConstraint(SpringLayout.WEST, accepttext, 170, SpringLayout.WEST, textpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, accepttext, 10, SpringLayout.SOUTH, textpanel);
			centerpanel.add(accepttext);
			accepttext.addActionListener(this);//We set the accept button action listener as this 

		} 
		//If the button pressed is one of the two accept buttons
		else if (arg0.getActionCommand().equals("Accept")) {
			//If the button pressed is the one for the text comment
			if(((JButton)arg0.getSource()).getName().equals("comtext")) {
				String text = textfield.getText();//We get the text
				try {
					view.commentText(text);//We comment the offer
					view.setVisible(false);//We set the view as not visible
					SeeOfferWindow nv = new SeeOfferWindow(view.getResults(),view.getOffer());//we create a new see offer window
					nv.setMenuController(new MenuController(nv,model));
					nv.setNextPrevController(new NextPrevController(nv,model));
					nv.setBackBuyBookController(new BackBuyBookController(nv,model));
					nv.setCommentController(new CommentController(nv,model));
					nv.setAddAnswerController(new AddAnswerController(nv,model));
					nv.setViewAnswersController(new ViewAnswersController(nv,model));
				} catch (TextException e) {//If the text exception is thrown
					JOptionPane.showMessageDialog(w,e);
				} catch (NotGuest e) {//If the not guest exception is thrown
					JOptionPane.showMessageDialog(w,e);
				}
				textfield.setVisible(false);
				w.setVisible(false);//We close the window
			}
			//If the button pressed is the one of the rate comment
			if(((JButton)arg0.getSource()).getName().equals("comrate")) {
				int rate = Integer.parseInt((String)ratefield.getSelectedItem());
				try {
					view.commentRate(rate);//We comment the offer
				} catch (RateException e) {//If the rate exception is thrown
					JOptionPane.showMessageDialog(w,e);
				} catch (NotGuest e) {//If the not guest exception is thrown
					JOptionPane.showMessageDialog(w,e);
				}
				ratefield.setVisible(false);
				ratelabel.setVisible(false);
				
				w.setVisible(false);//We close the window
			}

		}

	}

}
