package windows;

import java.util.ArrayList;
import java.util.List;

import app.*;
import controllers.*;

public class prueba {

	public static void main(String[] args) throws Exception {
		House h1;
		House h2;
		User host;
		Application app;
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		h1 = new House("LOCATION1", "DESCRIPTION1", c, 28964,host);
		h2 = new House("LOCATION2", "DESCRIPTION2", c, 28974,host);
		ArrayList <House> houses = new ArrayList <House>();
		houses.add(h1);
		houses.add(h2);
		
		CreateOfferWindow j = new CreateOfferWindow(houses);
	}

}
