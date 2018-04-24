package windows;

public class prueba {

	public static void main(String[] args) throws Exception {
		LoginWindow j = new LoginWindow();
		LoginController c = new LoginController();
		j.setLoginController(c);
	}

}
