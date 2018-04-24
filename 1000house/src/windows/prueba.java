package windows;

import app.Application;

public class prueba {

	public static void main(String[] args) throws Exception {
		LoginWindow j = new LoginWindow();
		Application app = new Application("fDGAdsaf");
		LoginController c = new LoginController(j,app);
		j.setLoginController(c);
	}

}
