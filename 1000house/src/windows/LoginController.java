package windows;
import app.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginController implements ActionListener {
	Application app;
	
	public LoginController() throws Exception{
		app = new Application("ventanas");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("EEY");
		
	}
	
	
}
