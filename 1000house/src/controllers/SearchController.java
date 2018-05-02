package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

import windows.*;
import app.*;
import exception.NotRegisteredUser;

import javax.swing.*;
import java.util.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class SearchController implements ActionListener{
	Application model;
	LoginWindow view;
	
	public SearchController(LoginWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	//This function search in the app when a button is used. Depending on the button, it search in a  different way
	//When there aren't any results,or if there is any problem , a window with a message appears
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg0){  
		JButton but = (JButton)arg0.getSource();
		List<Offer> l = null;
		if(but.getName().equals("type")) {
			l = model.searchType(view.getSelectedType());
			if(l.size() == 0) JOptionPane.showMessageDialog(view,"No results" );
			else {
				view.setVisible(false);
				SearchWindow nv = new SearchWindow(l);
				nv.setNextPrevController(new NextPrevController(nv,model)); 
				nv.setMenuController(new MenuController(nv,model));
				nv.setGoController(new GoToOfferController(nv,model));
			}
		}
		else if(but.getName().equals("code")) {
			try {
				l = model.searchCode(Long.parseLong(view.getCodeField()));
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(view,"Wrong format" );
				return;
			}			
			if(l.size() == 0) JOptionPane.showMessageDialog(view,"No results" );
			else {
				view.setVisible(false);
				SearchWindow nv = new SearchWindow(l);
				nv.setNextPrevController(new NextPrevController(nv,model)); 
				nv.setMenuController(new MenuController(nv,model));
				nv.setGoController(new GoToOfferController(nv,model));
			}
		}
		else if(but.getName().equals("date")) {
			String [] date1 = view.getDatesField()[0].split("-");
			String [] date2 = view.getDatesField()[1].split("-");
			LocalDate from ,to;
			try {
				from = LocalDate.of(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]), Integer.parseInt(date1[0]));
				to = LocalDate.of(Integer.parseInt(date2[2]), Integer.parseInt(date2[1]), Integer.parseInt(date2[0]));
			}
			catch(DateTimeException e) {
				JOptionPane.showMessageDialog(view,e );
				return;
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(view,"Wrong format" );
				return;
			}
			l = model.searchDate(from, to);
			if(l.size() == 0) JOptionPane.showMessageDialog(view,"No results" );
			else {
				view.setVisible(false);
				SearchWindow nv = new SearchWindow(l);
				nv.setNextPrevController(new NextPrevController(nv,model)); 
				nv.setMenuController(new MenuController(nv,model));
				nv.setGoController(new GoToOfferController(nv,model));
			}
		}
		else if(but.getName().equals("state")) {
			if(view.getSelectedState().equals("Bought")) {
				try {
					l = model.searchBought();
				} catch (NotRegisteredUser e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					l = model.searchBooked();
				} catch (NotRegisteredUser e) {
					e.printStackTrace();
				}
			}			
			if(l.size() == 0) JOptionPane.showMessageDialog(view,"No results" );
			else {
				view.setVisible(false);
				SearchWindow nv = new SearchWindow(l);
				nv.setNextPrevController(new NextPrevController(nv,model));
				nv.setMenuController(new MenuController(nv,model));
				nv.setGoController(new GoToOfferController(nv,model));
			}
		}
		else if(but.getName().equals("rate")) {
			double rate = Double.parseDouble(view.getRateField());
			if(rate > 5 || rate < 0) {
				JOptionPane.showMessageDialog(view,"Rate must be between 0 and 5" );
				return;
			}
			try {
				l = model.searchRate(rate);
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(view,"Wrong format" );
				return;
			}
			catch(NotRegisteredUser u){
				u.printStackTrace();
			}
			if(l.size() == 0) JOptionPane.showMessageDialog(view,"No results" );
			else {
				view.setVisible(false);
				SearchWindow nv = new SearchWindow(l);
				nv.setNextPrevController(new NextPrevController(nv,model));
				nv.setMenuController(new MenuController(nv,model));
				nv.setGoController(new GoToOfferController(nv,model));
			}
		}
	}
	
}
