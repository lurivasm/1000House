package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import app.*;
import windows.*;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * Controller for the creating houses buttons in the house panel
 */
public class HouseController implements ActionListener{
	private Application model;
	private HouseWindow view;
	
	/**
	 * Constructor of the HouseController
	 * @param l_ the House Window
	 * @param model the model of the Window
	 */
	public HouseController(HouseWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@Override
	/**
	 * Sets the action for the accept button, creating a House
	 */
	public void actionPerformed(ActionEvent arg0) {
		try {
	        long zipcode = Long.parseLong(view.getZipcode());
	        String desc = view.getDescription();
	        String loc = view.getLocat();
	        
	        /*Case there are empty fields*/
	        if(desc.equals("") == true || loc.equals("") == true) {
	        	JOptionPane.showMessageDialog(view,"Fill all the fields");
	        	return;
	        }
	        /*Case the description is too much long*/
	        if(desc.length() > 450) {
	        	JOptionPane.showMessageDialog(view,"Description over 450 chars");
	        	return;
	        }
	        /*Create a house*/
	       	 model.createHouse(desc, view.getCharacteristics(), loc, zipcode);
	       
		   
		    /*Return to main menu*/
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
		
		/*Case the format of the ints and longs is incorrect*/
		catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view,"Wrong format");
			return;
		} 
		/*Case is not a host*/
		catch (Exception e) {
			JOptionPane.showMessageDialog(view,"You are not in Host Mode");
			return;
		}		
	}

}
