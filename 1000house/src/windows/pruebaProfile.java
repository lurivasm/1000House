package windows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.*;
import controllers.*;

public class pruebaProfile {

	public static void main(String[] args) throws Exception{
		User host, admin, u3, u4;
		Application app;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796492F", "OD", "1111111111111111" ,app);
		admin = new User( "Admin", "Istrator", "ibonacci", "11235813F", "A", "1234577" ,app);
		u3 = new User( "User", "3", "PATO", "12796082F", "O", "1230067890456782" ,app);
		u4 = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		host.setState(UserStates.CONNECTED_HOST);
		app.setLog(host);
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		app.createHouse("Calle de la piruleta", c, "Calle de la piruleta", 26321);
		app.createHouse("Calle Melancolia nº4", c, "Calle Melancolia nº4", 26321);	
		app.createOffer(host.getHostProfile().getHouses().get(0), LocalDate.of(1998, 1, 1), 2, 100, 10);
		
//		host.setState(UserStates.ADMIN);
//		
//		app.getwaitoffers().get(0).approveOffer();
//		app.getwaitoffers().get(0).setState(OfferStates.AVAILABLE);
//		host.setState(UserStates.CONNECTED_GUEST);
//		
//		app.getLog().getGuestProfile().getReserves().add(new Reserve(app.getLog(), app.getavoffers().get(0)));
//		app.getLog().getGuestProfile().getReserves().add(new Reserve(app.getLog(), app.getavoffers().get(0)));
		//	app.getavoffers().get(0).bookOffer();
		
//		admin.setState(UserStates.ADMIN);
//		app.setLog(admin);
		
		ProfileWindow h = new ProfileWindow(app);
		ProfileController cont = new ProfileController(h, app);
		AdminController ad = new AdminController(h, app);
		h.setProfileController(cont);
		h.setAdminController(ad);
		MenuController menu = new MenuController(h, app);
		h.setMenuController(menu);
	}
}
