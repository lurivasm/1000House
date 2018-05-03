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
	private Application model;
	private JFrame view;
	
	public NextPrevController(JFrame l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(view instanceof SearchWindow) {
			SearchWindow window = (SearchWindow)view;
			if(but.getActionCommand().equals("Next ->")) window.nextPage();
			if(but.getActionCommand().equals("<- Prev")) window.prevPage();
		}
		else {
			SeeOfferWindow window = (SeeOfferWindow)view;
			if(but.getActionCommand().equals("Next")) window.nextPage();
			if(but.getActionCommand().equals("Prev")) window.prevPage();
		}
		
	}
}
