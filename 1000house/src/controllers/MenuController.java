package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import app.Application;
import windows.LoginWindow;


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
		nv.setLoginLogoutController(new LoginLogoutController(nv,model));
		nv.setSearchController(new SearchController(nv,model));		
	}
}
