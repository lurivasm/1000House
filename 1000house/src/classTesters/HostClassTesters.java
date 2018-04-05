/**
 * 
 */
package classTesters;
import app.*;
import exception.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class HostClassTesters {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Creating the objects needed...");
		Host host = new Host("39875096");
		Application app = new Application("Test");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		User u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		System.out.println("Objects created\n");
		
		System.out.println("Trying to change the host Credit Card number when the logged user is not the admin...");
		app.setLog(u1);		
		try {
			host.changeCCNumber("3436598", app);
		}
		catch(NotAdmin excep) {
			System.out.println(excep);
		}
		
		System.out.println("Trying to change the host Credit Card number when the logged user is the admin...");
		app.setLog(u2);
		String cc = host.getccNumber();
		host.changeCCNumber("3436598", app);
		if(host.getccNumber().equals(cc) == false) {
			System.out.print("Credit Card changed!\n");
		}
		
		System.out.println("END OF THE TEST");
		
	}

}
