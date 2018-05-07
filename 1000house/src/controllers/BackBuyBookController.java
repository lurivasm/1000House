package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import exception.NotGuest;
import exception.NotHost;
import exception.NotRegisteredUser;

import javax.swing.*;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 *Controller for the back , book and buy buttons
 */
public class BackBuyBookController implements ActionListener {
	private Application model;
	private SeeOfferWindow view;

	public BackBuyBookController(SeeOfferWindow l_, Application model) {
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton) arg0.getSource();
		if (but.getActionCommand().equals("Back")) {//If the button is the back button
			if(model.getLog().isGuest()) {//If the user is guest,it means he wants to go back to the search results
				view.setVisible(false);
				double page = Math.floor(view.getOffer() / 15) + 1;
				SearchWindow nv = new SearchWindow(view.getResults());
				nv.setNextPrevController(new NextPrevController(nv, model));
				nv.setMenuController(new MenuController(nv, model));
				nv.setGoController(new GoToOfferController(nv, model));
				while (nv.getPage() != page && page != 0)
					nv.nextPage();
			}
			else { // otherwise, the user is the admin, an wants to go back to the profile window
				view.setVisible(false);
				ProfileWindow nv = new ProfileWindow(model);
				nv.setAdminController(new AdminController(nv,model));
				nv.setProfileController(new ProfileController(nv,model));
				nv.setMenuController(new MenuController(nv,model));
			}
		} else if (but.getActionCommand().equals("Buy")) {//If the butto pressed is buy, we call the buy offer method
			if(view.getResults().get(view.getOffer()).isAvailable() == false) {
				JOptionPane.showMessageDialog(view, "This offer it's not available");
				return;
			}
			try {//If the user is banned,we go back to the login window
				if (view.getResults().get(view.getOffer()).buyOffer() == false) {
					JOptionPane.showMessageDialog(view, "Error in your credit card! You have been banned and logged out");
					view.setVisible(false);
					LoginWindow nv = new LoginWindow();
					nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
					nv.setSearchController(new SearchController(nv,model));	
					nv.setHostControllers(new HostController(nv,model));
				} else {
					JOptionPane.showMessageDialog(view, "Offer Bought!");
					System.out.println(view.getResults().get(view.getOffer()).getState());
				}

			} catch (NotRegisteredUser | NotGuest | NotHost | OrderRejectedException e) {
				JOptionPane.showMessageDialog(view, e);
			}

		} else if (but.getActionCommand().equals("Book")) {// If the button pressed is book , we call the book offer method
			if (view.getResults().get(view.getOffer()).isAvailable() == false) {
				JOptionPane.showMessageDialog(view, "This offer it's not available");
			} else {
				try {
					view.getResults().get(view.getOffer()).bookOffer();
					JOptionPane.showMessageDialog(view, "Offer Reserved!");
				} catch (NotRegisteredUser | NotGuest e) {
					JOptionPane.showMessageDialog(view, e);
				}

			}
		}

	}

}
