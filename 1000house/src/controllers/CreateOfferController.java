package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import java.time.*;

import app.*;
import windows.*;

public class CreateOfferController implements ActionListener{
	private Application model;
	private CreateOfferWindow view;
	
	/**
	 * Constructor of the CreateOfferController
	 * @param l_ the House WIndow
	 * @param model the model of the Window
	 */
	public CreateOfferController(CreateOfferWindow l_, Application model){
		this.view = l_;
		this.model = model;
	}
	
	@Override
	/**
	 * Sets the action for the accept button, creating an offer
	 */
	public void actionPerformed(ActionEvent arg0) {
		int deposit, price;
        String type = view.getTypeOffer();
        String d = view.getDeposit();
        String p = view.getPrice();
        String [] ini = view.getIniDate().split("-");
        String [] houseString = view.getHouse().split("-");
        long zipcode = Long.parseLong(houseString[1]);
        System.out.println(zipcode);
        House house = null;
        LocalDate iniDate, endDate;
        
        /*Case there are spaces*/
        if(p.equals("") == true || d.equals("") == true) {
        	JOptionPane.showMessageDialog(view,"Fill all the fields");
        	return;
        }
		try {
			deposit = Integer.parseInt(d);
			price = Integer.parseInt(p);
			iniDate = LocalDate.of(Integer.parseInt(ini[2]), Integer.parseInt(ini[1]), Integer.parseInt(ini[0]));
			for(House h : model.getLog().getHostProfile().getHouses()) {
	        	if(zipcode == h.getZipcode() && h.getLocation().equals(houseString[0])) {
	        		house = h;
	        		break;
	        	}
	        }
/*BORRRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARRR*/
			if(house == null) {
				JOptionPane.showMessageDialog(view,"THis house doesnt exist");
	        	return;
			}
			/*Case Living Offer*/
	        if (type.equals("Living Offer")) {
	        	String duration = view.getTime();
	        	if(duration.equals("") == true){
	        		JOptionPane.showMessageDialog(view,"Fill all the fields");
	            	return;
	        	}
				int numMonths = Integer.parseInt(duration);
	        	model.createOffer(house, iniDate, numMonths, price, deposit);
	        }
	        
		    /*Case Holidays Offer*/
	        else if(type.equals("Holidays Offer")) {
	        	String [] end = view.getEndDate().split("-");
	        	endDate = LocalDate.of(Integer.parseInt(end[2]), Integer.parseInt(end[1]), Integer.parseInt(end[0]));
	        	model.createOffer(house, iniDate, endDate, price, deposit);
	        }
	        
		    /*Return to main menu*/
		    view.setVisible(false);
			LoginWindow nv = new LoginWindow();
			if(model.getLog() != null) nv.setUserLogin();
			nv.setLoginLogoutProfileController(new LoginLogoutProfileController(nv,model));
			nv.setSearchController(new SearchController(nv,model));     
		}
		/*Case the date is incorrect*/
		catch(DateTimeException e) {
			JOptionPane.showMessageDialog(view,e);
			return;
		}
		/*Case the format for the longs is incorrect*/
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
