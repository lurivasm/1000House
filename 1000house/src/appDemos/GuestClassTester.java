/**
 * 
 */
package appDemos;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.*;
import exception.*;


/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class GuestClassTester {

	public static void main(String[] args) throws Exception{
		
		System.out.println("Creating the objects needed...");
		Guest guest = new Guest("39875096");
		Application app = new Application("Test");
		User u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		User u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		House h = new House("L", "D", c, 654,u1);
		Offer offer = new HolidaysOffer(LocalDate.of(2018, 6, 3), 50, h, app, LocalDate.of(2018, 12, 3));
		Reserve reserve = new Reserve(u1,offer);		
		System.out.println("Objects created\n");
		
		System.out.println("Trying to remove a reserve when there is not a reserve with the offer asked.. ");
		if(guest.removeReserve(offer) == false) {
			System.out.println("There isn't a reserve with that offer!\n");
		}
		System.out.println("Adding a reserve and trying to remove it... ");
		if(guest.addReserve(reserve) == true) {
			System.out.println("Reserve added!");
		}
		if(guest.removeReserve(offer) == true) {
			System.out.println("Reserve removed!\n");
		}
		
		System.out.println("Trying to remove an offer when it isn't in the offer list");
		if(guest.removeOffer(offer) == false) {
			System.out.println("That offer is not in the list!\n");
		}
		System.out.println("Adding an offer and trying to remove it... ");
		if(guest.addOffer(offer) == true) {
			System.out.println("Offer added!");
		}
		if(guest.removeOffer(offer) == true) {
			System.out.println("Offer removed!\n");
		}
		
		System.out.println("Trying to change the Guest Credit Card number when the logged user is not the admin...");
		app.setLog(u1);
		try {
			guest.changeCCNumber("3436598", app);
		}
		catch(NotAdmin excep) {
			System.out.println(excep);
		}
		System.out.println("Trying to change the Guest Credit Card number when the logged user is the admin...");
		app.setLog(u2);
		String cc = guest.getccNumber();
		guest.changeCCNumber("3436598", app);
		if(guest.getccNumber().equals(cc) == false) {
			System.out.print("Credit Card changed!\n");
		}
		System.out.println("END OF THE TEST");
		
	}

}
