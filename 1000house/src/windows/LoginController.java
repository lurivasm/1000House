package windows;
import app.*;

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
		System.out.println("Tu contraseña es: " + view.getPassword());
		System.out.println("Tu nif es: " + view.getNif());
		
		
	}
	
	
}
