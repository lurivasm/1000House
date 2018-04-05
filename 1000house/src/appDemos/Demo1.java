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
public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Creating the app Demo for the first time...");
		Application app = new Application("Demo");
		System.out.println("App created!");
		System.out.println("Setting the date of logging in in today...");
		ModifiableDate.setToday();
		System.out.println("Date set!\n");
		System.out.println("Trying to log in a user that is not in the app user list...");
		try{
			app.login("586706J", "Potato");
		}
		catch(NotRegisteredUser excep1){
			System.out.println(excep1);
		}
		System.out.println("Logging a host user in the app...");
		if(app.login("51999111X", "pezEspada") == true){
			System.out.println("Host logged!\n");
		}
		System.out.println("Creating a house...");
		List<Characteristics> c = new ArrayList<Characteristics>();
		c.add(Characteristics.TV);
		app.createHouse("DESCRIPTION1",c , "LOCATION1",45698 );
		System.out.println("House created!\n");
		System.out.println("Trying to create the same house...");
		try{
			app.createHouse("DESCRIPTION1",c , "LOCATION1",45698 );
		}
		catch(HouseException excep2){
			System.out.println(excep2);
		}
		
		System.out.println("Creating a LivingOffer...");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 9,1 ), 4, 500, 70) == true){
			System.out.println("Offer created!\n");
		}
		System.out.println("Creating a HolidaysOffer...");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 8,1 ), LocalDate.of(2018, 8, 23), 500, 70) == true){
			System.out.println("Offer created!");
		}
		System.out.println("The size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		
		System.out.println("Logging the host out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		System.out.println("The admin denies the LivingOffer and ask for changes in the price of the HolidaysOffer\n");
		for(Offer o : app.getwaitoffers()){
			if(o instanceof LivingOffer){
				o.denyOffer();	
			}
			else{
				o.askForChanges("Price");
			}
		}
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		
		System.out.println("Logging the host user in the app again...");
		if(app.login("51999111X", "pezEspada") == true){
			System.out.println("Host logged!\n");
		}
		System.out.println("One of the offers was denied so now the size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		System.out.println("The host removes the denied offer from his house offer list, and changes the price of the other offer");
		for(Offer o : app.getLog().getHostProfile().getHouses().get(0).getOffers()){
			if(o.getState().equals(OfferStates.DENIED)){
				app.getLog().getHostProfile().getHouses().get(0).getOffers().remove(o);
			}
			if(o.getState().equals(OfferStates.CHANGES)){
				o.setPrice(700);
			}
		}
		System.out.println("Now  the Host creates a new Living Offer");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 9,1 ), 8, 500, 70) == true){
			System.out.println("Offer created!\n");
		}
		System.out.println("The size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		
		System.out.println("Logging the host out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		System.out.println("The admin approves both the new and the changed offer\n");
		app.getwaitoffers().get(0).approveOffer();
		app.getwaitoffers().get(0).approveOffer();
		System.out.println("Now the size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		System.out.println("And the size of the app-available-offers-list is " + app.getavoffers().size() + "\n");
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
	}

}
