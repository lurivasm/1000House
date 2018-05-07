package controllers;
import windows.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.Application;


/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 *
 */
public class HostController implements ActionListener {
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
	public HostController(LoginWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Create House")) {//If the button pressed is the create house, creates anew house window
			view.setVisible(false);
			HouseWindow nv = new HouseWindow();
			nv.setHouseController(new HouseController(nv,model));
			nv.setMenuController(new MenuController(nv,model));
		}
		else { // Otherwise, it creates a create offer window. If the user doesn't have a house created, the app doesn't let him create an offer
			if(model.getLog().getHostProfile().getHouses().isEmpty()) JOptionPane.showMessageDialog(view,"You dont' have any house created");
			else {
				view.setVisible(false);
				CreateOfferWindow nv = new CreateOfferWindow(model.getLog().getHostProfile().getHouses());
				nv.setCreateOfferController(new CreateOfferController(nv,model));
				nv.setMenuController(new MenuController(nv,model));
			}
		}
		
	}
}
