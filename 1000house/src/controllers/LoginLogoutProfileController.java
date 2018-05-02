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
	Application model;
	LoginWindow view;
	
	public LoginLogoutProfileController(LoginWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(but.getActionCommand().equals("Login")) {
			try {
				model.login(view.getNif(), view.getPassword());
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
