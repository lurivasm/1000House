package testers;

import app.Application;
import app.Host;
import app.Profile;
import app.User;
import app.UserStates;
import exception.NotAdmin;

public class Prueba {

	public static void main(String[] args) throws Exception{
		Application app = new Application("Prueba");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		User u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		User u3 = new User( "User", "3", "PATO", "12796082F", "O", "12300678" ,app);
		User u4 = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		u3.setState(UserStates.CONNECTED_HOST);
		u4.setState(UserStates.CONNECTED_GUEST);
		
		app.setLog(u2);
		try {
			u3.banUser();
		}
		catch(NotAdmin excep) {
			System.out.println(excep);
		}
		
		System.out.println(");
	}

}
