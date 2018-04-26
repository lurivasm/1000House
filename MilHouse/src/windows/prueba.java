package windows;

import app.Application;
import controllers.*;

public class prueba {

	public static void main(String[] args) throws Exception {
		LoginWindow j = new LoginWindow();
		Application app = new Application("fDGAdsaf");
		LoginController c1 = new LoginController(j,app);
		LogoutController c2 = new LogoutController(j,app);
		j.setLoginController(c1);
		j.setLogoutController(c2);
	}

}
