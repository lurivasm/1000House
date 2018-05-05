package controllers;
import app.*;
import exception.*;
import windows.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class LoginLogoutProfileController implements ActionListener {
	private Application model;
	private LoginWindow view;
	
	public LoginLogoutProfileController(LoginWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(but.getActionCommand().equals("Login")) {
			try {
				if(model.login(view.getNif(), view.getPassword()) == false) {
					JOptionPane.showMessageDialog(view,"You're banned" );
					return;
				}
				view.setUserLogin();
			}
			catch(NotRegisteredUser e) {
				JOptionPane.showMessageDialog(view,e );
			}	
		}
		if(but.getActionCommand().equals("Logout")) {
			model.logout();
			view.setUserLogout();
		}
			
	}
	
	
}
