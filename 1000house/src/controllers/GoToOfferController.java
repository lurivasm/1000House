package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import javax.swing.*;

/**
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 *
 */
public class GoToOfferController implements ActionListener{
	private Application model;
	private SearchWindow view;
	
	/**
	 * The constructor adds the view and model fields
	 * 
	 * @param l_
	 *            window that will become the view
	 * @param model
	 *            application that will be the model
	 */
	public GoToOfferController(SearchWindow l_, Application model){
		view = l_;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {//This method creates a new see offer window with the offer selected
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
			w.setViewAnswersController(new ViewAnswersController(w,model));
			w.setAddAnswerController(new AddAnswerController(w,model));
		}
		
	}

}
