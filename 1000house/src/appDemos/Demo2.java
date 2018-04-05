/**
 * 
 */
package appDemos;

import java.time.LocalDate;
import java.util.*;


import app.*;
import exception.*;
import modifiableDates.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */

public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Creating the app Demo. It was first created in the Demo1, so the constructor chrages the info from Demo1");
		Application app = new Application("Demo");
		System.out.println("App created!");
		System.out.println("Setting the date of logging in in today...");
		ModifiableDate.setToday();
		System.out.println("Date set!\n");
		System.out.println("Trying to search bought offers  when the user is not logged...");
		try{
			app.searchBought();
		}
		catch(NotRegisteredUser excep1){
			System.out.println(excep1);
		}
		System.out.println("Trying to search booked offers  when the user is not logged...");
		try{
			app.searchBooked();
		}
		catch(NotRegisteredUser excep2){
			System.out.println(excep2);
		}
		System.out.println("Trying to search by rate  when the user is not logged...");
		try{
			app.searchBooked();
		}
		catch(NotRegisteredUser excep3){
			System.out.println(excep3);
		}
		
		System.out.println("Logging the Guest user in...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		System.out.println("The guest tries to create a house...");
		List<Characteristics> c = new ArrayList<Characteristics>();
		app.createHouse("D",c , "L",54565);
		System.out.println("The guest tries to create an offer...");
		app.createOffer(null, LocalDate.of(2018, 9,1 ), 4, 500, 70);
		System.out.println("The gest search in the app by Type, searching for a Living Offer");
		List<Offer> result = new ArrayList<Offer>();
	}

}
