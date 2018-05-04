package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import app.Text;


import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ViewAnswersController implements ActionListener {
	@SuppressWarnings("unused")
	private Application model;// The model
	private SeeOfferWindow view;// The view where the controller is working on

	private JFrame w = new JFrame("Add Answer");// The window used to view answers
	private JPanel centerpanel = new JPanel();// Panel of the center of the window
	private JPanel commentpanel = new JPanel();// Panel for the answers
	private JPanel buttonpanel = new JPanel();// Panel for the next and prev buttons
	private SpringLayout centerlayout = new SpringLayout();// Layout for the center panel
	private JButton next = new JButton("Next") ;//Next button
	private JButton prev = new JButton("Prev") ; //prev button
	private int answer;// Number of the answer that's been showed
	

	/**
	 * The constructor adds the view and model fields
	 * 
	 * @param l_
	 *            window that will become the view
	 * @param model
	 *            application that will be the model
	 */
	public ViewAnswersController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Container cp = w.getContentPane();//We get the container of the window
		Text c =(Text) view.getResults().get(view.getOffer()).getComments().get(view.getComment());//we get he comment we're working on
		//If the button pressed is the view answers button :
		if(arg0.getActionCommand().equals("View Answers")) {
			answer = 0;
			commentpanel.setVisible(false);
			//We set the title on the north part of the container
			cp.setLayout(new BorderLayout());
			JLabel title = new JLabel("Answers");
			title.setHorizontalAlignment(JTextField.CENTER);
			cp.add(title, BorderLayout.NORTH);
			
			//We get the first answer and put it on the window
			Text a = (Text)c.getAnswers().get(0);
			commentpanel = new JPanel();
			centerpanel.setLayout(centerlayout);
			JPanel aux;
			commentpanel.setLayout(new GridLayout(7, 1));
			int cont = 0; 
			JLabel user = new JLabel(a.getUser().getName() + " :");
			user.setHorizontalAlignment(JTextField.LEFT);
			commentpanel.add(user);
			String[] lines = view.splitSize(a.getText(), 50);
			for (String s : lines) {
				aux = new JPanel();
				JLabel com = new JLabel(s);
				aux.add(com);
				commentpanel.add(aux);
				cont++;
			}
			for(int i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
			
			//we add the next and prev buttons
			if(next.getActionListeners().length == 0) {//If it's the first time that the comments are showed, we add the action listeners
				next.addActionListener(this);
				prev.addActionListener(this);
			}
			
			buttonpanel.add(prev);
			buttonpanel.add(next);
			
			prev.setVisible(false);//Its the first answer, there is not previous answers
			if(c.getAnswers().size() == 1) next.setVisible(false);//If it's the only answer, we set tthe next button as not visible
			else next.setVisible(true);
			commentpanel.add(buttonpanel);
			commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));//We adda border to the comment panel
			commentpanel.setPreferredSize(new Dimension(430,250));
			//Finally we add the comment panel in the south part of the center panel,using the spring layout
			centerlayout.putConstraint(SpringLayout.NORTH, commentpanel, 20, SpringLayout.NORTH, centerpanel);
			centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 35, SpringLayout.WEST, centerpanel);		
			centerpanel.add(commentpanel);
			commentpanel.setVisible(true);
			cp.add(centerpanel, BorderLayout.CENTER);
			
			w.pack(); // Important: subcomponents are located according to their layout using their
			// preferred sizes.
			w.setSize(500, 300);
			w.setLocationRelativeTo(null);
			w.setVisible(true);
		}
		//If the button pressed is the next button, we show the next answer
		else if(arg0.getActionCommand().equals("Next")) {
			answer ++;
			commentpanel.setVisible(false);
			Text a = (Text)c.getAnswers().get(answer);
			commentpanel = new JPanel();
			centerpanel.setLayout(centerlayout);
			JPanel aux;
			commentpanel.setLayout(new GridLayout(7, 1));
			int cont = 0; 
			JLabel user = new JLabel(a.getUser().getName() + " :");
			user.setHorizontalAlignment(JTextField.LEFT);
			commentpanel.add(user);
			String[] lines = view.splitSize(a.getText(), 50);
			for (String s : lines) {
				aux = new JPanel();
				JLabel com = new JLabel(s);
				aux.add(com);
				commentpanel.add(aux);
				cont++;
			}
			for(int i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
			
			prev.setVisible(true);
			if(c.getAnswers().size()-1 == answer) next.setVisible(false);//If it's the last answer, we set tthe next button as not visible
			else next.setVisible(true);
			commentpanel.add(buttonpanel);
			commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));//We adda border to the comment panel
			commentpanel.setPreferredSize(new Dimension(430,250));
			//Finally we add the comment panel in the south part of the center panel,using the spring layout
			centerlayout.putConstraint(SpringLayout.NORTH, commentpanel, 20, SpringLayout.NORTH, centerpanel);
			centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 35, SpringLayout.WEST, centerpanel);		
			centerpanel.add(commentpanel);
			commentpanel.setVisible(true);
			cp.add(centerpanel, BorderLayout.CENTER);
		}
		//If the button pressed is the prev button, we show the previous answer
		else if(arg0.getActionCommand().equals("Prev")) {
			answer --;
			commentpanel.setVisible(false);
			Text a = (Text)c.getAnswers().get(answer);
			commentpanel = new JPanel();
			centerpanel.setLayout(centerlayout);
			JPanel aux;
			commentpanel.setLayout(new GridLayout(7, 1));
			int cont = 0; 
			JLabel user = new JLabel(a.getUser().getName() + " :");
			user.setHorizontalAlignment(JTextField.LEFT);
			commentpanel.add(user);
			String[] lines = view.splitSize(a.getText(), 50);
			for (String s : lines) {
				aux = new JPanel();
				JLabel com = new JLabel(s);
				aux.add(com);
				commentpanel.add(aux);
				cont++;
			}
			for(int i = 0; i < (5-cont) ; i++) commentpanel.add(new JPanel());
			
			next.setVisible(true);
			if(0 == answer) prev.setVisible(false);//If it's the firstanswer, we set tthe prev button as not visible
			else prev.setVisible(true);
			commentpanel.add(buttonpanel);
			commentpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));//We adda border to the comment panel
			commentpanel.setPreferredSize(new Dimension(430,250));
			//Finally we add the comment panel in the south part of the center panel,using the spring layout
			centerlayout.putConstraint(SpringLayout.NORTH, commentpanel, 20, SpringLayout.NORTH, centerpanel);
			centerlayout.putConstraint(SpringLayout.WEST, commentpanel, 35, SpringLayout.WEST, centerpanel);		
			centerpanel.add(commentpanel);
			commentpanel.setVisible(true);
			cp.add(centerpanel, BorderLayout.CENTER);
		}
		

	}
}
