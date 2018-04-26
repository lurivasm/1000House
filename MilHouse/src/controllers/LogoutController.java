package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Application;
import windows.LoginWindow;

public class LogoutController implements ActionListener{
	Application model;
	LoginWindow view;
	
	public LogoutController(LoginWindow l_, Application model) throws Exception{
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.logout();
		view.setUserLogout();
	}
	
	
}
