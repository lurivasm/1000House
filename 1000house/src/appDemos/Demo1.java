/**
 * 
 */
package appDemos;
import java.time.LocalDate;
import java.util.*;


import app.*;
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
		app.login("586706J", "Potato");
		System.out.println("Logging a host user in the app...");
		if(app.login("51999111X", "pezEspada") == true){
			System.out.println("Host logged!\n");
		}
		System.out.println("Creating two houses...");
		List<Characteristics> c = new ArrayList<Characteristics>();
		c.add(Characteristics.TV);
		app.createHouse("DESCRIPTION1",c , "LOCATION1",45698 );
		app.createHouse("DESCRIPTION2",c , "LOCATION2",96347 );
		System.out.println("House created!\n");
		System.out.println("Trying to create the same house...");
		app.createHouse("DESCRIPTION1",c , "LOCATION1",45698 );
		System.out.println("Creating a LivingOffer...");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 9,1 ), 4, 500, 70) == true){
			System.out.println("Offer created!\n");
		}
		System.out.println("Creating a HolidaysOffer...");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 8,1 ), LocalDate.of(2018, 8, 23), 500, 70) == true){
			System.out.println("Offer created!");
		}
		System.out.println("The size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		System.out.println("Trying to create another LivingOffer in the same house...");
		app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 6,1 ), 4, 500, 70);
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
		System.out.println("Now  the Host creates two new Living Offers");
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2018, 9,1 ), 8, 500, 70) == true){
			System.out.println("Offer created!\n");
		}
		if(app.createOffer(app.getLog().getHostProfile().getHouses().get(1), LocalDate.of(2018, 10,1 ), 5, 300, 40) == true){
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
		System.out.println("The admin approves one of  the new offers and the changed one\n");
		app.getwaitoffers().get(0).approveOffer();
		app.getwaitoffers().get(0).approveOffer();
		System.out.println("Then asks for changes in the other new house\n");
		app.getwaitoffers().get(0).askForChanges("Deposit");
		System.out.println("Now the size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		System.out.println("And the size of the app-available-offers-list is " + app.getavoffers().size() + "\n");
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		System.out.println("We set the log in date in one week before today");
		ModifiableDate.plusDays(7);
		System.out.println("Date set!\n");
		System.out.println("Logging the host user in the app again...");
		if(app.login("51999111X", "pezEspada") == true){
			System.out.println("Host logged!\n");
		}
		System.out.println("It's been more than 5 days since the Admin asked the Host offer for changes, so it has been denied");
		System.out.println("Now the size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		System.out.println("Now the Host deletes the offer from his house offer list ");
		app.getLog().getHostProfile().getHouses().get(1).getOffers().remove(0);
		System.out.println("Logging the host out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		System.out.println("END OF THE TEST");
	}

}
