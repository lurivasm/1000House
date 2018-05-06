package WindowsTests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.*;
import controllers.*;
import windows.ProfileWindow;

public class pruebaProfile {

	public static void main(String[] args) throws Exception{
		User host, admin;
		Application app;
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796492F", "OD", "111111111111111" ,app);
		admin = new User( "Admin", "Istrator", "ibonacci", "11235813F", "A", "1234577" ,app);

		host.setState(UserStates.CONNECTED_HOST);
		app.setLog(host);
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		app.createHouse("Calle de la piruleta", c, "Calle dela piruleta", 26021);
		app.createHouse("Calle Melancolia nº4", c, "Calle Mlancolia nº4", 26001);	
		app.createOffer(host.getHostProfile().getHouses().get(0), LocalDate.of(1998, 1, 1), 2, 100, 10);
		House h1 = new House("Calle de la piruleta", "Calle dela piruleta", c, 2621, host);
		
		app.getLog().getHostProfile().getHouses().get(0).getOffers().get(0).setState(OfferStates.CHANGES);
	//	Offer o1 = new LivingOffer(LocalDate.of(1998, 1, 1), 2, 100, h1, app, 2);
	//	Offer o2 = new LivingOffer(LocalDate.of(1998, 1, 1), 2, 100, app.getLog().getHostProfile().getHouses().get(1), app, 2);
		//		host.setState(UserStates.ADMIN);
//		
//		app.getwaitoffers().get(0).approveOffer();
//		app.getwaitoffers().get(0).setState(OfferStates.AVAILABLE);
//		host.setState(UserStates.CONNECTED_GUEST);
		
//		app.getLog().getGuestProfile().getReserves().add(new Reserve(app.getLog(), o1));
		//app.getLog().getGuestProfile().getReserves().add(new Reserve(app.getLog(), o2));
		
		
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
