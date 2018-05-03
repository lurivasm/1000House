package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import javax.swing.*;

public class GoToOfferController implements ActionListener{
	private Application model;
	private SearchWindow view;
	
	public GoToOfferController(SearchWindow l_, Application model){
		view = l_;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = (JButton)arg0.getSource();
		int offer = Integer.parseInt(but.getName());
		if(model.getLog() == null) {
			JOptionPane.showMessageDialog(view,"You are not registered" );
		}
		else {
			view.setVisible(false);
			SeeOfferWindow w = new SeeOfferWindow(view.getResults(),offer);
			w.setBackBuyBookController(new BackBuyBookController(w,model));
			w.setMenuController(new MenuController(w,model));
			w.setCommentController(new CommentController(w,model));
			w.setNextPrevController(new NextPrevController(w,model));
		}
		
	}

}
