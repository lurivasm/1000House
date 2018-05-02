package windows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.*;
import controllers.*;

public class prueba2 {

	public static void main(String[] args) throws Exception{
		List<Offer> l = new ArrayList<Offer>();
		Application app = new Application("gd");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u1.setState(UserStates.CONNECTED_HOST);
		House ha = new House("12345678901234567890123455789012345678901234567890" ,"jhf", new ArrayList<Characteristics>(),9875 ,u1);
		LocalDate init = LocalDate.of(2018, 4, 28);
		Offer offer18 =  new LivingOffer(init, 1000, 200,ha, app, 4);
		l.add(offer18);
		for(int i = 0;i < 20;i++) {
			House h = new House("Potato street Madrid" + i,"jhf", new ArrayList<Characteristics>(),47625 ,u1);
			LocalDate ini = LocalDate.of(2018, 4, 28);
			Offer offer1 =  new LivingOffer(ini, 1000, 200,h, app, 4);
			LocalDate end =  LocalDate.of(2018, 5, 10);
			Offer offer2 =  new HolidaysOffer(ini, 100, 200,h, app, end);
			l.add(offer1);
			l.add(offer2);			
		}
		SearchWindow w = new SearchWindow(l);
		w.setNextPrevController(new NextPrevController(w,app));
		w.setMenuController(new MenuController(w,app));
		w.setGoController(new GoToOfferController(w,app));
		
	}

}
