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
	
	/**
	 * The constructor adds the view and model fields
	 * 
	 * @param l_
	 *            window that will become the view
	 * @param model
	 *            application that will be the model
	 */
	public LoginLogoutProfileController(LoginWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(but.getActionCommand().equals("Login")) {//If the button pressed is the login, it logs the user in
			try {
				if(model.login(view.getNif(), view.getPassword()) == false) {//If it's banned,shows a message
					JOptionPane.showMessageDialog(view,"You're banned" );
					return;
				}
				if(model.getLog().getState().equals(UserStates.CONNECTED_HOST)) view.setUserLogin(1);
				else view.setUserLogin(0);
			}
			catch(NotRegisteredUser e) {
				JOptionPane.showMessageDialog(view,e );
			}	
		}
		else if(but.getActionCommand().equals("Logout")) {//Logs the user out
			model.logout();
			view.setUserLogout();
		}
		else {//Profile button, creates a profile window
			view.setVisible(false);
			ProfileWindow nv = new ProfileWindow(model);
			nv.setAdminController(new AdminController(nv,model));
			nv.setProfileController(new ProfileController(nv,model));
			nv.setMenuController(new MenuController(nv,model));
		}
	}
	
	
}
