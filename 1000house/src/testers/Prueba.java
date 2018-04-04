package testers;

import app.Application;
import app.Host;
import app.Profile;
import app.User;
import app.UserStates;
import exception.NotAdmin;

public class Prueba {

	public static void main(String[] args) throws Exception{
		Application app = null;

		
		System.out.println(app.getUsers().size());
	}

}
