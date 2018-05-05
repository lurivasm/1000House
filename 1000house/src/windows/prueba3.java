package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import app.Application;
import app.Characteristics;
import app.HolidaysOffer;
import app.House;
import app.LivingOffer;
import app.Offer;
import app.OfferStates;
import app.User;
import app.UserStates;
import controllers.AddAnswerController;
import controllers.BackBuyBookController;
import controllers.CommentController;
import controllers.MenuController;
import controllers.NextPrevController;
import controllers.ViewAnswersController;

public class prueba3 {
	public static void main(String[] args) throws Exception{
		List<Offer> l = new ArrayList<Offer>();
		Application app = new Application("gd");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "1234567890123456" ,app);
		u1.setState(UserStates.CONNECTED_HOST);
		List<Characteristics> list = new ArrayList<Characteristics>();
		list.add(Characteristics.AirConditioner);
		House ha = new House("12345678901234567890123455789012345678901234567890" ,"jhf", list,9875 ,u1);
		LocalDate init = LocalDate.of(2018, 4, 28);
		list.add(Characteristics.Garden);
		list.add(Characteristics.Parking);
		list.add(Characteristics.Pool);
		list.add(Characteristics.TV);
		list.add(Characteristics.WiFI);
		Offer offer18 =  new LivingOffer(init, 1000, 200,ha, app, 4);
		l.add(offer18);
		for(int i = 0;i < 20;i++) {
			House h = new House("Potato street Madrid" + i,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", list,47625 ,u1);
			LocalDate ini = LocalDate.of(2018, 4, 28);
			Offer offer1 =  new LivingOffer(ini, 1000, 200,h, app, 4);
			LocalDate end =  LocalDate.of(2018, 5, 10);
			Offer offer2 =  new HolidaysOffer(ini, 100, 200,h, app, end);
			l.add(offer1);
			l.add(offer2);			
		}
		u1.setState(UserStates.CONNECTED_GUEST);
		app.setLog(u1);
		l.get(1).commentOffer(3);	
		l.get(1).commentOffer("123456789012345678901234534567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		u1.setName("Manolo");
		l.get(1).commentOffer("Pos esta to guapa la caasa esa ,10 de 10, me encanta LOKO");
		l.get(1).commentOffer("PUTA BASURA DE MIERDA, VOY A QUEMAR TU PUTA CASA ASQUEROSO CABRON, TENIA CUCARACHAS EN TODOS LADOS Y HE COGIDO SIDA DE SENTARME EN TU COCHAMBROSO SOFA. Pero la piscina estaba bien :)");
		l.get(1).setState(OfferStates.AVAILABLE);
		SeeOfferWindow w = new SeeOfferWindow(l,1);
		w.setBackBuyBookController(new BackBuyBookController(w,app));
		w.setMenuController(new MenuController(w,app));
		w.setNextPrevController(new NextPrevController(w,app));
		w.setCommentController(new CommentController(w,app));
		w.setAddAnswerController(new AddAnswerController(w,app));
		w.setViewAnswersController(new ViewAnswersController(w,app));
//		JFrame w = new JFrame();
//		Container cp = w.getContentPane();
//		cp.setLayout(new BorderLayout());
//		JTextArea a = new JTextArea(5,8);
//		cp.add(a,BorderLayout.CENTER);
//		w.pack(); // Important: subcomponents are located according to their layout using their preferred sizes.		
//		w.setSize(900, 700);
//		w.setVisible(true);
//		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}


}
