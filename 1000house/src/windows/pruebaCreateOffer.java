package windows;

import java.util.ArrayList;
import java.util.List;

import app.*;
import controllers.*;

public class pruebaCreateOffer {

	public static void main(String[] args) throws Exception {
		House h1;
		House h2;
		User host;
		Application app;
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		host.setState(UserStates.CONNECTED_HOST);
		app.setLog(host);
		
		app.createHouse("Calle de la piruleta", c, "ZIII", 28964);
		app.createHouse("Calle Melancilia nº4", c, "WWWI", 28974);		
		
		CreateOfferWindow j = new CreateOfferWindow(app.getLog().getHostProfile().getHouses());
		CreateOfferController cont = new CreateOfferController(j, app);
		j.setCreateOfferController(cont);
		MenuController menu = new MenuController(j, app);
		j.setMenuController(menu);
	
	}

}
