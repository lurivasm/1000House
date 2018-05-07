package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import app.*;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import exception.NotAdmin;
import windows.*;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * Controller for the admin's buttons in the profile panel
 */
public class AdminController implements ActionListener{
	private Application model;
	private ProfileWindow view;
	
	/**
	 * Constructor of the CreateOfferController
	 * @param l_ the Profile Window
	 * @param model the model of the Window
	 */
	public AdminController(ProfileWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@Override
	/**
	 * Sets the action for the change credit card numbers and accept, cancel, see and 
	 * deny offers buttons
	 */
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		
		/*Change CCNUmber Button*/
		if(but.getActionCommand().equals("Apply")) {
			String old = view.getOldCCNumber();
			String neww = view.getNewCCNUmber();
			
			/*If there are no banned users you cannot change it*/
			if(model.getBannedUsers().isEmpty() == true) {
				JOptionPane.showMessageDialog(view,"There Are No Banned Users");
				return;
			}
			/*If there are spaces in the jtextfields*/
			if(old.equals("") == true || neww.equals("") == true) {
				JOptionPane.showMessageDialog(view,"Fill all the fields");
				return;
			}
			/*Invalid new Credit Card*/
			else if(TeleChargeAndPaySystem.isValidCardNumber(neww) == false) {
				JOptionPane.showMessageDialog(view,"New CCard invalid");
				return;
			}
			int found = 0;
			for(User u : model.getBannedUsers()) {
				if(u.isGuest() == true) {
					if(u.getGuestProfile().getccNumber().equals(old)) {
						found = 1;
						u.getGuestProfile().setCcNumber(neww);
						try {
							u.restoreUser(model);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(view,e);
						}
						break;
					}
				}
				else if(u.isHost() == true) {
					if(u.getHostProfile().getccNumber().equals(old)) {
						found = 1;
						u.getHostProfile().setCcNumber(neww);
						try {
							u.restoreUser(model);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(view,e);
						}
						break;
					}
				}
			}
			if(found == 0) JOptionPane.showMessageDialog(view,"User not found");
			else JOptionPane.showMessageDialog(view,"CCard changed");
			view.setVisible(false);
			ProfileWindow h = new ProfileWindow(model);
			ProfileController cont = new ProfileController(h, model);
			h.setProfileController(cont);
			MenuController menu = new MenuController(h, model);
			h.setMenuController(menu);
			AdminController ad = new AdminController(h, model);
			h.setAdminController(ad);
		}
		
		/*If there are no waiting Offers*/
		else if(model.getwaitoffers().isEmpty() == true) {
			JOptionPane.showMessageDialog(view,"There Are No Waiting Offers");
			return;
		}
		
		/*Ask for changes button*/
		else if(but.getActionCommand().equals("Ask for Changes")) {
			int index = view.getWOComboBox().getSelectedIndex();
			try {
				if(model.getwaitoffers().get(index).getState().equals(OfferStates.WAITING) == false) {
					JOptionPane.showMessageDialog(view,"This offer is already asked for changes");
					return;
				}
				model.getwaitoffers().get(index).askForChanges("Change something");
			} catch (NotAdmin e) {
				JOptionPane.showMessageDialog(view,e);
				return;
			}
			JOptionPane.showMessageDialog(view,"Asked for changes");
			view.setVisible(false);
			ProfileWindow h = new ProfileWindow(model);
			ProfileController cont = new ProfileController(h, model);
			h.setProfileController(cont);
			MenuController menu = new MenuController(h, model);
			h.setMenuController(menu);
			AdminController ad = new AdminController(h, model);
			h.setAdminController(ad);
			return;
		}
		
		/*See Offer button*/
		else if(but.getActionCommand().equals("See")) {
			view.setVisible(false);
			SeeOfferWindow nv = new SeeOfferWindow(model.getwaitoffers(),view.getWaitOffer()-1);
			nv.setBackBuyBookController(new BackBuyBookController(nv,model));
			nv.setMenuController(new MenuController(nv,model));
			nv.setAdminView();
		}
		
		/*Accept offer button*/
		else if(but.getActionCommand().equals("Accept")) {
			int index = view.getWOComboBox().getSelectedIndex();
			try {
				model.getwaitoffers().get(index).approveOffer();
			} catch (NotAdmin e) {
				JOptionPane.showMessageDialog(view,e);
				return;
			}
			JOptionPane.showMessageDialog(view,"Offer accepted");
			view.setVisible(false);
			ProfileWindow h = new ProfileWindow(model);
			ProfileController cont = new ProfileController(h, model);
			h.setProfileController(cont);
			MenuController menu = new MenuController(h, model);
			h.setMenuController(menu);
			AdminController ad = new AdminController(h, model);
			h.setAdminController(ad);
			return;
		}
		
		/*Deny Offer button*/
		else if(but.getActionCommand().equals("Deny")) {
			int index = view.getWOComboBox().getSelectedIndex();
			try {
				model.getwaitoffers().get(index).denyOffer();
			} catch (NotAdmin e) {
				JOptionPane.showMessageDialog(view,e);
				return;
			}
			JOptionPane.showMessageDialog(view,"Offer denied");
			view.setVisible(false);
			ProfileWindow h = new ProfileWindow(model);
			ProfileController cont = new ProfileController(h, model);
			h.setProfileController(cont);
			MenuController menu = new MenuController(h, model);
			h.setMenuController(menu);
			AdminController ad = new AdminController(h, model);
			h.setAdminController(ad);
			return;
		}
	}
			
}
