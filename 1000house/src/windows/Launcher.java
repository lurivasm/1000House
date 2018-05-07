package windows;

import app.Application;
import controllers.*;
import modifiableDates.*;
/**
 * Launcher of the app
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 *
 */
public class Launcher {

	public static void main(String[] args) throws Exception {
		LoginWindow nv = new LoginWindow();
		ModifiableDate.setToday();		
		Application app = new Application("Final");		
		
		nv.setLoginLogoutProfileController( new LoginLogoutProfileController(nv,app));
		nv.setSearchController(new SearchController(nv,app));
		nv.setHostControllers(new HostController(nv,app));
	}

}
