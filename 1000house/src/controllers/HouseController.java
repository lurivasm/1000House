package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import app.*;
import exception.*;
import windows.*;


public class HouseController implements ActionListener{
	private Application model;
	private HouseWindow view;
	
	public HouseController(HouseWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
	        long zipcode = Long.parseLong(view.getZipcode());
	        String desc = view.getDescription();
	        String loc = view.getLocat();
	        if(desc.equals("") == true || loc.equals("") == true) {
	        	JOptionPane.showMessageDialog(view,"Fill all the fields");
	        	return;
	        }
	        if(desc.length() > 450) {
	        	JOptionPane.showMessageDialog(view,"Description over 450 chars");
	        	return;
	        }
	        if(desc.length() > 450) {
	        	JOptionPane.showMessageDialog(view,"Description over 450 chars" + "Total introduced: " + desc.length());
	        	return;
	        }
		    model.createHouse(desc, view.getCharacteristics(), loc, zipcode);
		    /*Return to main menu*/
		    view.setVisible(false);
			LoginWindow nv = new LoginWindow();
			if(model.getLog() != null) nv.setUserLogin();
			nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
			nv.setSearchController(new SearchController(nv,model));     
		}
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view,"Wrong format");
			return;
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(view,"You are not in Host Mode");
			return;
		}		
	}

}
