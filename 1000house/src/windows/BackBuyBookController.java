package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import controllers.GoToOfferController;
import controllers.MenuController;
import controllers.NextPrevController;

import javax.swing.*;

public class BackBuyBookController implements ActionListener{
	Application model;
	SeeOfferWindow view;
	
	public BackBuyBookController(SeeOfferWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		if(but.getActionCommand().equals("Back")) {
			view.setVisible(false);
			double page = Math.floor(view.getOffer()/15) + 1;
			SearchWindow nv = new SearchWindow(view.getResults());
			nv.setNextPrevController(new NextPrevController(nv,model)); 
			nv.setMenuController(new MenuController(nv,model));
			nv.setGoController(new GoToOfferController(nv,model));
			while(nv.getPage() != page && page != 0) nv.nextPage();
		}
		
	}
	
	

}
