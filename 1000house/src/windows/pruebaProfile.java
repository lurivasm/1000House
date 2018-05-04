package windows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.Application;
import app.Characteristics;
import app.User;
import app.UserStates;
import controllers.HouseController;
import controllers.MenuController;

public class pruebaProfile {

	public static void main(String[] args) throws Exception{
		User host;
		Application app;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		host.setState(UserStates.CONNECTED_HOST);
		app.setLog(host);
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		app.createHouse("Calle de la piruleta", c, "ZIII", 28964);
		app.createHouse("Calle Melancilia nº4", c, "WWWI", 28974);	
		app.createOffer(host.getHostProfile().getHouses().get(0), LocalDate.of(1998, 1, 1), 2, 100, 10);
		host.setState(UserStates.CONNECTED_GUEST);
		app.getavoffers().add(app.getwaitoffers().get(0));
		app.getavoffers().get(0).bookOffer();
		ProfileWindow h = new ProfileWindow(app);
	}
}
