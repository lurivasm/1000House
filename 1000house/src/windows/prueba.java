package windows;


import app.Application;
import controllers.*;

public class prueba {

	public static void main(String[] args) throws Exception {
		LoginWindow j = new LoginWindow();
		Application app = new Application("Demo");
		LoginLogoutController c1 = new LoginLogoutController(j,app);
		
		j.setLoginLogoutController(c1);
		j.setSearchController(new SearchController(j,app));
	}

}
