package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import app.*;
import windows.*;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * Controller for the users' buttons in the profile panel
 */
public class ProfileController implements ActionListener{
	private Application model;
	private ProfileWindow view;
	
	/**
	 * Constructor of the CreateOfferController
	 * @param l_ the House WIndow
	 * @param model the model of the Window
	 */
	public ProfileController(ProfileWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@SuppressWarnings("unused")
	@Override
	/**
	 * Sets the action for the change profile button, changing the profile in case the user
	 * is guest and host
	 */
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		
		/*Change Profile Button*/
		if(but.getActionCommand().equals("Change Profile")) {
			/*Change from guest to host*/
			if(model.getLog().getState().equals(UserStates.CONNECTED_GUEST) == true) {
				view.getTabbedPane().removeAll();
				view.getTabbedPane().add("Houses", view.getHouseScroll());
				view.getTabbedPane().add("Offers", view.getChangeOfferPanel());
				view.setStateLabel(UserStates.CONNECTED_HOST);
				model.getLog().setState(UserStates.CONNECTED_HOST);
				JOptionPane.showMessageDialog(view, "Connected as Host");
			}
			/*Change from host to guest*/
			else if(model.getLog().getState().equals(UserStates.CONNECTED_HOST) == true) {
				view.getTabbedPane().removeAll();
				view.getTabbedPane().add("Reserves", view.getReservePanel());
				view.getTabbedPane().add("History", view.getHistoryScroll());
				view.setStateLabel(UserStates.CONNECTED_GUEST);
				model.getLog().setState(UserStates.CONNECTED_GUEST);
				JOptionPane.showMessageDialog(view, "Connected as Guest");
			}
		}
		
		/*Change Offer button*/
		else if(but.getActionCommand().equals("Change Offer")) {
			Offer offer;
			int i = 0;
			String text = view.getChanges();
			if(text.equals("") == true) {
				JOptionPane.showMessageDialog(view, "Fill the field");
				return;
			}
			int num = view.getCOComboBox().getSelectedIndex();
			int index = view.getNCOComboBox().getSelectedIndex();
			int price;
			String [] date;
			LocalDate iniDate;
			LivingOffer lo;
			HolidaysOffer ho;
			for(House h : model.getLog().getHostProfile().getHouses()) {
				for(Offer o : h.getOffers()) {
					if(i == num) {
						if(o.getState().equals(OfferStates.CHANGES)) {
							offer = o;
							switch(index) {
								/*Case price is selected*/
								case 0:
									price = Integer.parseInt(text);
									o.setPrice(price);
									break;
		
								/*Case deposit is selected*/
								case 1:
									price = Integer.parseInt(text);
									o.setDeposit(price);
									break;
									
								/*Case initial date is selected*/
								case 2:
									date = text.split("-");
									try{
										iniDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
										o.setIniDate(iniDate);
									}catch(Exception e) {
										JOptionPane.showMessageDialog(view, "Wrong Format");
										return;
									}
									break;
									
								/*Case end date is selected*/	
								case 3:
									if(o instanceof LivingOffer) {
										JOptionPane.showMessageDialog(view, "This offer is for Holidays");
										return;
									}
									ho = (HolidaysOffer)o;
									date = text.split("-");
									try{
										iniDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
										ho.setEndDate(iniDate);
									}catch(Exception e) {
										JOptionPane.showMessageDialog(view, "Wrong Format");
										return;
									}
									break;
									
								/*Case Duration is selected to change*/	
								case 4:
									if(o instanceof HolidaysOffer) {
										JOptionPane.showMessageDialog(view, "This offer is for Living");
										return;
									}
									lo = (LivingOffer)o;
									price = Integer.parseInt(text);
									lo.setnumMonths(price);
									break;									
							}
							/*Sets the state to waiting so that the admin can accept it*/
							o.setState(OfferStates.WAITING);
							JOptionPane.showMessageDialog(view, "Offer Changed");
							view.setVisible(false);
							ProfileWindow p = new ProfileWindow(model);
							ProfileController cont = new ProfileController(p, model);
							p.setProfileController(cont);
							MenuController menu = new MenuController(p, model);
							p.setMenuController(menu);
							AdminController ad = new AdminController(p, model);
							p.setAdminController(ad);
							return;
							
						}
						/*Case the offer does not have CHANGES state*/
						else {
							JOptionPane.showMessageDialog(view, "This offer does not need changes");
							return;
						}
					}
					i++;
					
				}
			}
			
		}
		
		/*If you don't have any reserve you cannot click the buttons*/
		else if(model.getLog().getGuestProfile().getReserves().isEmpty() == true) {
			JOptionPane.showMessageDialog(view, "You don't have any reserve");
		}
		
		/*Pay Button*/
		else if(but.getActionCommand().equals(" Pay "))	{
			String item = (String)view.getReserveComboBox().getSelectedItem();
			String items[] = item.split("-");
			long zipcode = Long.parseLong(items[1]);
			
			/*Look for the reserve to pay*/
			for(Reserve r : model.getLog().getGuestProfile().getReserves()) {
				if(r.getOffer().getHouse().getLocation().equals(items[0])
						&& zipcode == r.getOffer().getHouse().getZipcode()){
					try {
						r.payOffer();
						view.setVisible(false);
						ProfileWindow h = new ProfileWindow(model);
						ProfileController cont = new ProfileController(h, model);
						h.setProfileController(cont);
						MenuController menu = new MenuController(h, model);
						h.setMenuController(menu);
						AdminController ad = new AdminController(h, model);
						h.setAdminController(ad);
						JOptionPane.showMessageDialog(view, "Reserve Payed");
						
					/*If while you were paying you are banned you are logged out*/
					} catch(java.lang.NullPointerException e) {
						JOptionPane.showMessageDialog(view, "You have been Banned");
						view.setVisible(false);
						LoginWindow nv = new LoginWindow();
						if(model.getLog() != null) {
							if(model.getLog().getState().equals(UserStates.CONNECTED_HOST)) nv.setUserLogin(1);
							else nv.setUserLogin(0);
						}
						nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
						nv.setSearchController(new SearchController(nv,model));	
						nv.setHostControllers(new HostController(nv,model));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(view, e);
					};
				}
			}
		}
		
		/*Cancel Button*/
		else if(but.getActionCommand().equals("Cancel"))	{
			String item = (String)view.getReserveComboBox().getSelectedItem();
			String items[] = item.split("-");
			long zipcode = Long.parseLong(items[1]);
			
			/*Look for the reserve to cancel*/
			for(Reserve r : model.getLog().getGuestProfile().getReserves()) {
				if(r.getOffer().getHouse().getLocation().equals(items[0])
						&& zipcode == r.getOffer().getHouse().getZipcode()){
					try {
						r.cancelReserve();
						JOptionPane.showMessageDialog(view, "Reserve Cancelled");
						view.setVisible(false);
						ProfileWindow h = new ProfileWindow(model);
						ProfileController cont = new ProfileController(h, model);
						h.setProfileController(cont);
						MenuController menu = new MenuController(h, model);
						h.setMenuController(menu);
						AdminController ad = new AdminController(h, model);
						h.setAdminController(ad);
								
					} catch (Exception e) {
						JOptionPane.showMessageDialog(view, e);
					};
				}
			}
		}
		
		
	}
}