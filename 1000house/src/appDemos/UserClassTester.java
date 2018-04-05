/**
 * 
 */
package appDemos;

import app.*;
import exception.*;


/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class UserClassTester {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		Application app = new Application("Test");
		System.out.println("Creating on user of each type(host,guest,admin and host-guest)\n");
		System.out.println("User 1 -> Host-Guest");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		System.out.println("User 2 -> Admin");
		User u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		System.out.println("User 3 -> Host");
		User u3 = new User( "User", "3", "PATO", "12796082F", "O", "12300678" ,app);
		System.out.println("User 4 -> Guest\n");
		User u4 = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		u3.setState(UserStates.CONNECTED_HOST);
		u4.setState(UserStates.CONNECTED_GUEST);
		System.out.println("Users created and setted as connected\n");
		
		System.out.println("Creating a user with a profile that doesn't exists\n");
		try {
			User user = new User("Wrong", "Profile", "SAD", "8573796K", "W", "344536", app);
		}
		catch(WrongProfile excep1) {
			System.out.println(excep1);
		}
		
		System.out.println("Getting the Host profile of two different users\n");
		System.out.println("User 4:");
		try {
			Profile p1 =  u4.getHostProfile();
		}
		catch(NotHost excep2) {
			System.out.println(excep2);
		}
		System.out.println("User 1:");
		Profile p1 = u1.getHostProfile();
		if(p1 instanceof Host) {
			System.out.println("This user has a Host profile\n");
		}
		
		System.out.println("Getting the Guest profile of two different users\n");
		System.out.println("User 3:");
		try {
			Profile p2 =  u3.getGuestProfile();
		}
		catch(NotGuest excep3) {
			System.out.println(excep3);
		}
		System.out.println("User 1:");
		Profile p2 = u1.getGuestProfile();
		if(p2 instanceof Guest) {
			System.out.println("This user has a Guest profile\n");
		}
		
		System.out.println("Trying to ban User 3 when the logged user is not the admin...");
		app.setLog(u1);
		try {
			u3.banUser();
		}
		catch(BanException excep4) {
			System.out.println(excep4);
		}
		System.out.println("Trying to ban User 3 when the logged user is the admin...");
		app.setLog(u2);
		u3.banUser();
		if(u3.getState().equals(UserStates.BANNED) && u3.getApp() == null) {
			System.out.println("User 3 has been banned");
		}
		
		System.out.println("Trying to restore User 3 when the logged user is not the admin...");
		app.setLog(u1);
		try {
			u3.restoreUser(app);
		}
		catch(NotAdmin excep5) {
			System.out.println(excep5);
		}
		System.out.println("Trying to restore User 3 when the logged user is  the admin...");
		app.setLog(u2);
		u3.restoreUser(app);
		if(u3.getState().equals(UserStates.DISCONNECTED) && u3.getApp() != null) {
			System.out.println("User 3 has been restored");
		}
		
		System.out.println("Trying to change from host to guest profile in an only host user(User 3)");
		try {
			u3.changeProfile("D");
		}
		catch(WrongProfile excep6){
			System.out.println(excep6);
		}
		
		System.out.println("Trying to change from guest to host profile in an only guest user(User 4)");
		try {
			u4.changeProfile("O");
		}
		catch(WrongProfile excep7){
			System.out.println(excep7);
		}
		

		System.out.println("Trying to change User 1 to a profile taht doesn't exist");
		try {
			u1.changeProfile("W");
		}
		catch(WrongProfile excep8){
			System.out.println(excep8);
		}
		
		System.out.println("Trying to change User 1  from Guest to Host");
		u1.changeProfile("O");
		if(u1.getState().equals(UserStates.CONNECTED_HOST)) {
			System.out.println("User1 now connected as Host\n");
		}
		
		System.out.println("Checking the type of each User...\n");
		if(u1.isHost() && u1.isGuest() && u1.isAdmin() == false) {
			System.out.println("User 1 is Host-Guest");
		}
		if(u2.isHost() == false && u2.isGuest() == false && u2.isAdmin()) {
			System.out.println("User 2 is Admin");
		}
		if(u3.isHost() && u3.isGuest() == false && u1.isAdmin() == false) {
			System.out.println("User 3 is Host");
		}
		if(u4.isHost() == false && u4.isGuest() && u4.isAdmin() == false) {
			System.out.println("User 4 is Guest\n");
		}
		
		System.out.println("END OF THE TEST");
	}

}
