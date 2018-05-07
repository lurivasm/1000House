package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import javax.swing.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class NextPrevController implements ActionListener {
	@SuppressWarnings("unused")
	private Application model;
	private JFrame view;
	
	/**
	 * The constructor adds the view and model fields
	 * 
	 * @param l_
	 *            window that will become the view
	 * @param model
	 *            application that will be the model
	 */
	public NextPrevController(JFrame l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(view instanceof SearchWindow) { // If it's a search window, calls the method to go to the next or prev page of results
			SearchWindow window = (SearchWindow)view;
			if(but.getActionCommand().equals("Next ->")) window.nextPage();
			if(but.getActionCommand().equals("<- Prev")) window.prevPage();
		}
		else {//If it's a seeofferwindow calls the method to go to the next or prev comment
			SeeOfferWindow window = (SeeOfferWindow)view;
			if(but.getActionCommand().equals("Next")) window.nextPage();
			if(but.getActionCommand().equals("Prev")) window.prevPage();
		}
		
	}
}
