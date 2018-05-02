package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import app.Application;
import windows.LoginWindow;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class MenuController implements ActionListener {
	Application model;
	JFrame view;
	
	public MenuController(JFrame l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setVisible(false);
		LoginWindow nv = new LoginWindow();
		if(model.getLog() != null) nv.setUserLogin();
		nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
		nv.setSearchController(new SearchController(nv,model));		
	}
}
