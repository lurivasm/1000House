package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import app.*;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import exception.NotAdmin;
import windows.*;

public class adminController implements ActionListener{
	private Application model;
	private ProfileWindow view;
	
	/**
	 * Constructor of the CreateOfferController
	 * @param l_ the House WIndow
	 * @param model the model of the Window
	 */
	public adminController(ProfileWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@Override
	/**
	 * Sets the action for the change profile button, changing the profile in case the user
	 * is guest and host
	 */
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		
		/*Change CCNUmber Button*/
		if(but.getActionCommand().equals("Apply")) {
			String old = view.getOlcCCNumber();
			String neww = view.getNewCCNUmber();
			
			/*If there are no banned users*/
			if(model.getBanUsers().isEmpty() == true) {
				JOptionPane.showMessageDialog(view,"There Are No Banned Users");
				return;
			}
			/*Spaces in the jtextfields*/
			if(old.equals("") == true || neww.equals("") == true) {
				JOptionPane.showMessageDialog(view,"Fill all the fields");
				return;
			}
			/*Invalid new Credit Card*/
			else if(TeleChargeAndPaySystem.isValidCardNumber(neww) == false) {
				JOptionPane.showMessageDialog(view,"New CCard invalid");
				return;
			}
			for(User u : model.getBanUsers()) {
				if(u.isGuest() == true) {
					if(u.getGuestProfile().getccNumber().equals(old)) {
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
			JOptionPane.showMessageDialog(view,"CCard changed");
			view.setVisible(false);
			ProfileWindow h = new ProfileWindow(model);
			ProfileController cont = new ProfileController(h, model);
			h.setProfileController(cont);
			MenuController menu = new MenuController(h, model);
			h.setMenuController(menu);
			adminController ad = new adminController(h, model);
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
				model.getwaitoffers().get(index).askForChanges("Change something");
			} catch (NotAdmin e) {
				JOptionPane.showMessageDialog(view,e);
				return;
			}
			JOptionPane.showMessageDialog(view,"Asked for changes");
			return;
		}
		
		/*TIENES QUE IMPLEMETAR ESTO DANITI*/
		/*LUEGO BORRA ESTOS COMENTARIOS XDXDXD HASTA EL *****/
		/*See Offer button*/
		else if(but.getActionCommand().equals("See")) {
			
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
			adminController ad = new adminController(h, model);
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
			adminController ad = new adminController(h, model);
			h.setAdminController(ad);
			return;
		}
	}
			
}
