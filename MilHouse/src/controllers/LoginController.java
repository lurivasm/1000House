package controllers;
import app.*;
import exception.*;
import windows.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginController implements ActionListener {
	Application model;
	LoginWindow view;
	
	public LoginController(LoginWindow l_, Application model) throws Exception{
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			model.login(view.getNif(), view.getPassword());
			view.setUserLogin();
		}
		catch(NotRegisteredUser e) {
			JOptionPane.showMessageDialog(view,e );
		}		
	}
	
	
}
