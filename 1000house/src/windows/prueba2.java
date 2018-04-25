package windows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.*;
import exception.*;

public class prueba2 {

	public static void main(String[] args) throws Exception{
		List<Offer> l = new ArrayList<Offer>();
		Application app = new Application("gd");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u1.setState(UserStates.CONNECTED_HOST);
		for(int i = 0;i < 10;i++) {
			House h = new House("Potato street Madrid" + i,"jhf", new ArrayList<Characteristics>(),47625 ,u1);
			LocalDate ini = LocalDate.of(2018, 4, 28);
			Offer offer1 =  new LivingOffer(ini, 1000, 200,h, app, 4);
			LocalDate end =  LocalDate.of(2018, 5, 10);
			Offer offer2 =  new HolidaysOffer(ini, 1000, 200,h, app, end);
			l.add(offer1);
			l.add(offer2);			
		}
		searchWindow w = new searchWindow(l);
		
	}

}
