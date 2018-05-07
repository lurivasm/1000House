package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import app.Application;
import app.UserStates;
import windows.LoginWindow;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class MenuController implements ActionListener {
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
	public MenuController(JFrame l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {//Creates a new login window
		view.setVisible(false);
		LoginWindow nv = new LoginWindow();
		if(model.getLog() != null) {
			if(model.getLog().getState().equals(UserStates.CONNECTED_HOST)) nv.setUserLogin(1);
			else nv.setUserLogin(0);
		}
		nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
		nv.setSearchController(new SearchController(nv,model));		
		nv.setHostControllers(new HostController(nv,model));
	}
}
