package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.*;
import app.Application;
import javax.swing.*;


public class NextPrevController implements ActionListener {
	Application model;
	SearchWindow view;
	
	public NextPrevController(SearchWindow l_, Application model){
		view = l_;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton but = (JButton)e.getSource();
		if(but.getActionCommand().equals("Next ->")) view.nextPage();
		if(but.getActionCommand().equals("<- Prev")) view.prevPage();
	}
}
