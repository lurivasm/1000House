package WindowsTests;


import app.Application;
import controllers.*;
import windows.LoginWindow;

public class prueba {

	public static void main(String[] args) throws Exception {
		LoginWindow j = new LoginWindow();
		Application app = new Application("Demo");
		LoginLogoutProfileController c1 = new LoginLogoutProfileController(j,app);
		
		j.setLoginLogoutProfileController(c1);
		j.setSearchController(new SearchController(j,app));
	}

}
