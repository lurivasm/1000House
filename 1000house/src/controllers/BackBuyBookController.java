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
		if (but.getActionCommand().equals("Back")) {
			if(model.getLog().isGuest()) {
				view.setVisible(false);
				double page = Math.floor(view.getOffer() / 15) + 1;
				SearchWindow nv = new SearchWindow(view.getResults());
				nv.setNextPrevController(new NextPrevController(nv, model));
				nv.setMenuController(new MenuController(nv, model));
				nv.setGoController(new GoToOfferController(nv, model));
				while (nv.getPage() != page && page != 0)
					nv.nextPage();
			}
			else {
				view.setVisible(false);
				ProfileWindow nv = new ProfileWindow(model);
				nv.setAdminController(new AdminController(nv,model));
				nv.setProfileController(new ProfileController(nv,model));
				nv.setMenuController(new MenuController(nv,model));
			}
		} else if (but.getActionCommand().equals("Buy")) {
			if(view.getResults().get(view.getOffer()).isAvailable() == false) {
				JOptionPane.showMessageDialog(view, "This offer it's not available");
				return;
			}
			try {
				if (view.getResults().get(view.getOffer()).buyOffer() == false) {
					JOptionPane.showMessageDialog(view, "Error in your credit card! You have been banned and logged out");
					view.setVisible(false);
					LoginWindow nv = new LoginWindow();
					nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
					nv.setSearchController(new SearchController(nv,model));	
				} else {
					JOptionPane.showMessageDialog(view, "Offer Bought!");
					System.out.println(view.getResults().get(view.getOffer()).getState());
				}

			} catch (NotRegisteredUser | NotGuest | NotHost | OrderRejectedException e) {
				JOptionPane.showMessageDialog(view, e);
			}

		} else if (but.getActionCommand().equals("Book")) {
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
