package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import exception.*;

import javax.swing.*;

public class CommentController implements ActionListener {
	@SuppressWarnings("unused")
	private Application model;
	private SeeOfferWindow view;

	private JFrame w = new JFrame("Comment");
	private JPanel centerpanel = new JPanel();
	private JPanel ratepanel;
	private JPanel textpanel;
	private JLabel textlabel = new JLabel("Text: ");
	private JLabel ratelabel = new JLabel("Rate : ");
	private SpringLayout centerlayout = new SpringLayout();
	private JTextArea textfield = new JTextArea(8, 30);
	private JComboBox<String> ratefield;
	private JButton acceptrate = new JButton("Accept");;
	private JButton accepttext = new JButton("Accept");

	public CommentController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Container cp = w.getContentPane();
		

		if (arg0.getActionCommand().equals("Comment")) {
			cp.setLayout(new BorderLayout());
			JLabel title = new JLabel("Add your comment");
			title.setHorizontalAlignment(JTextField.CENTER);
			cp.add(title, BorderLayout.NORTH);
			
			accepttext.setVisible(false);
			acceptrate.setVisible(false);

			ratepanel = new JPanel();
			textpanel = new JPanel();

			centerpanel.setLayout(centerlayout);
			ButtonGroup buttons = new ButtonGroup();
			JPanel butpan = new JPanel();
			JRadioButton rate = new JRadioButton("Rate");
			JRadioButton text = new JRadioButton("Text");
			rate.addActionListener(this);
			text.addActionListener(this);
			buttons.add(rate);
			buttons.add(text);
			butpan.add(rate);
			butpan.add(text);
			centerlayout.putConstraint(SpringLayout.WEST, butpan, 180, SpringLayout.WEST, centerpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, butpan, 20, SpringLayout.NORTH, centerpanel);
			centerpanel.add(butpan);

			ratepanel.add(ratelabel);
			String[] op = { "0", "1", "2", "3", "4", "5" };
			ratefield = new JComboBox<String>(op);
			ratefield.setPreferredSize(new Dimension(50, 20));
			ratepanel.add(ratefield);
			centerlayout.putConstraint(SpringLayout.WEST, ratepanel, 0, SpringLayout.WEST, butpan);
			centerlayout.putConstraint(SpringLayout.NORTH, ratepanel, 20, SpringLayout.SOUTH, butpan);
			centerpanel.add(ratepanel);
			ratepanel.setVisible(false);

			textpanel.add(textlabel);
			textfield.setLineWrap(true);
			textfield.setWrapStyleWord(true);
			JScrollPane pan = new JScrollPane(textfield);
			textpanel.add(pan);
			centerlayout.putConstraint(SpringLayout.WEST, textpanel, -120, SpringLayout.WEST, butpan);
			centerlayout.putConstraint(SpringLayout.NORTH, textpanel, 50, SpringLayout.SOUTH, butpan);
			centerpanel.add(textpanel);
			textpanel.setVisible(false);
			textlabel.setVisible(false);

			cp.add(centerpanel, BorderLayout.CENTER);

			w.pack(); // Important: subcomponents are located according to their layout using their
						// preferred sizes.
			w.setSize(500, 300);
			w.setLocationRelativeTo(null);
			w.setVisible(true);
			
		} else if (arg0.getActionCommand().equals("Rate")) {
			textpanel.setVisible(false);
			accepttext.setVisible(false);
			ratepanel.setVisible(true);
			ratelabel.setVisible(true);
			ratefield.setVisible(true);
			acceptrate = new JButton("Accept");
			acceptrate.setName("comrate");
			centerlayout.putConstraint(SpringLayout.WEST, acceptrate, 20, SpringLayout.WEST, ratepanel);
			centerlayout.putConstraint(SpringLayout.NORTH, acceptrate, 20, SpringLayout.SOUTH, ratepanel);
			centerpanel.add(acceptrate);
			acceptrate.addActionListener(this);
		} else if (arg0.getActionCommand().equals("Text")) {
			ratepanel.setVisible(false);
			acceptrate.setVisible(false);
			ratefield.setVisible(false);
			textpanel.setVisible(true);
			textlabel.setVisible(true);
			accepttext = new JButton("Accept");
			accepttext.setName("comtext");
			centerlayout.putConstraint(SpringLayout.WEST, accepttext, 170, SpringLayout.WEST, textpanel);
			centerlayout.putConstraint(SpringLayout.NORTH, accepttext, 10, SpringLayout.SOUTH, textpanel);
			centerpanel.add(accepttext);
			accepttext.addActionListener(this);

		} else if (arg0.getActionCommand().equals("Accept")) {
			if(((JButton)arg0.getSource()).getName().equals("comtext")) {
				String text = textfield.getText();
				try {
					view.commentText(text);
					view.setVisible(false);
					SeeOfferWindow nv = new SeeOfferWindow(view.getResults(),view.getOffer());
					nv.setMenuController(new MenuController(nv,model));
					nv.setNextPrevController(new NextPrevController(nv,model));
					nv.setBackBuyBookController(new BackBuyBookController(nv,model));
					nv.setCommentController(new CommentController(nv,model));
					nv.setAddAnswerController(new AddAnswerController(nv,model));
				} catch (TextException e) {
					JOptionPane.showMessageDialog(w,e);
				} catch (NotGuest e) {
					JOptionPane.showMessageDialog(w,e);
				}
				textfield.setVisible(false);
				w.setVisible(false);
			}
			if(((JButton)arg0.getSource()).getName().equals("comrate")) {
				int rate = Integer.parseInt((String)ratefield.getSelectedItem());
				try {
					view.commentRate(rate);
				} catch (RateException e) {
					JOptionPane.showMessageDialog(w,e);
				} catch (NotGuest e) {
					JOptionPane.showMessageDialog(w,e);
				}
				ratefield.setVisible(false);
				ratelabel.setVisible(false);
				
				w.setVisible(false);
			}

		}

	}

}
