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
		
		System.out.println("The guest searches in the app by Type, searching for a Living Offer");
		List<Offer> result = new ArrayList<Offer>();
		result = app.searchType("Living");
		System.out.println("The search returned " + result.size() + " offer of type Living\n");
		
		System.out.println("The guest searches in the app by zipcode");
		result = app.searchCode(45698);
		System.out.println("The search returned " + result.size() + " offers with the zipcode 45698(The one of the first house created in Demo 1)\n");
		
		System.out.println("The guest searches in the app by date");
		result = app.searchDate(LocalDate.of(2018, 7, 1), LocalDate.of(2018,8 , 31));
		System.out.println("The search returned " + result.size() + " offer between those dates\n");
		
		System.out.println("The guest searches in the app the offers bought");
		result = app.searchBought();
		System.out.println("The search returned " + result.size() + " offers bought\n");
		
		System.out.println("The guest searches in the app the offers reserved");
		result = app.searchBooked();
		System.out.println("The search returned " + result.size() + " offers reserved\n");
		
		System.out.println("The guest searches in the app the offers with a rate of 0 or more");
		result = app.searchRate(0);
		System.out.println("The search returned " + result.size() + " offers with 0 or more rate\n");
		
		System.out.println("The guest tries to reserve an offer ");
		if(result.get(0).bookOffer() == true){
			System.out.println("Offer reserved!\n");
		}
		System.out.println("The size of the guest reserves list is " + app.getLog().getGuestProfile().getReserves().size() + "\n");
		
		System.out.println("Logging the guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("We set the log in date in one week before today");
		ModifiableDate.plusDays(7);
		System.out.println("Date set!\n");
		
		System.out.println("Logging the Guest user in again...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("It's been more than 5 days since the Guest made the reserve. The guest didn't pay the offer, so it was canceled");
		System.out.println("Now the size of the user reserves list is  " + app.getLog().getGuestProfile().getReserves().size() + "\n");
		
		System.out.println("The guest reserves the offer again ");
		result = app.searchRate(0);
		if(result.get(0).bookOffer() == true){
			System.out.println("Offer reserved!\n");
		}
		System.out.println("The size of the guest reserves list is " + app.getLog().getGuestProfile().getReserves().size() + "\n");
		
		System.out.println("Logging the guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("We set the log in date two days after today");
		ModifiableDate.setToday();
		ModifiableDate.plusDays(2);
		System.out.println("Date set!\n");
		
		System.out.println("Logging the Guest user in again...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		
		System.out.println("The guest pays the offer that he reserved");
		if(app.getLog().getGuestProfile().getReserves().get(0).payOffer() == true) {
			System.out.println("Offer paid!\n");
		}
		System.out.println("The size of the guest reserves list is " + app.getLog().getGuestProfile().getReserves().size() + "\n");
		System.out.println("The size of the guest offers list is " + app.getLog().getGuestProfile().getOffers().size() + "\n");
		
		System.out.println("Logging the guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging a Host-Guest user in...");
		if(app.login("X1130055", "secreta") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("We change the user to Host mode");
		if(app.getLog().changeProfile("O")) {
			System.out.println("Changed to host mode!");
		}
		
		System.out.println("The host creates a house and an offer on it");
		c.add(Characteristics.TV);
		app.createHouse("Somewhere",c , "Over the raimbow",12345 );
		app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2019, 1, 1), 8, 900, 200);
		System.out.println("House and offer created!\n");
		
		System.out.println("The size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
		

		System.out.println("The user tries to approve his own offer");
		try{
			app.getwaitoffers().get(0).approveOffer();
		}
		catch(NotAdmin excep) {
			System.out.println(excep);
		}
		
		System.out.println("Logging the Host-Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		
		System.out.println("The admin approves the host-guest offer");
		app.getwaitoffers().get(0).approveOffer();
		
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		
		System.out.println("Logging the Guest user in again...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The guest reserves and pays the offer created by the Host-Guest user");
		if(app.getavoffers().get(2).bookOffer() == true) {
			System.out.println("Offer reserved!\n");
		}
		if(app.getLog().getGuestProfile().getReserves().get(0).payOffer() == true ) {
			System.out.println("Offer paid!\n");
		}
		
		System.out.println("Logging the Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		
		System.out.println("The admin checks if any user was banned by the system, and if he finds one ,changes his creditCard and restores him");
		User u = app.getBannedUsers().get(0);
		System.out.println("The user found is banned and has a debt of " + u.getDebt());
		if(u.isGuest()) {
			u.getGuestProfile().changeCCNumber("6402894629053417", app);			
		}
		if(u.isHost()) {
			u.getHostProfile().changeCCNumber("6402894629053417", app);
		}
		u.restoreUser(app);
		
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		
		System.out.println("Logging another Host-Guest user in...");
		if(app.login("54444111D", "olvidame") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("We change the user to Host mode");
		if(app.getLog().changeProfile("O")) {
			System.out.println("Changed to host mode!");
		}
		
		System.out.println("The host creates a house and an offer on it");
		c.add(Characteristics.TV);
		app.createHouse("Bajo",c , "del mar",54321 );
		app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 3), 300, 20);
		System.out.println("House and offer created!\n");
		
		System.out.println("The size of the app-waiting-offers-list is " + app.getwaitoffers().size() + "\n");
					
		System.out.println("Logging the Host-Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		
		System.out.println("The admin approves the host-guest offer");
		app.getwaitoffers().get(0).approveOffer();
		
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		
		System.out.println("Logging the first Host-Guest user in again...");
		if(app.login("X1130055", "secreta") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("For testing the app, we change this User cccard back to a wrong one\n");
		app.getLog().getHostProfile().setCcNumber("284367905628765");
		app.getLog().getGuestProfile().setCcNumber("284367905628765");
		
		System.out.println("The user changes to guest mode and reserves the offer created by the other Host-Guest user");
		app.getLog().changeProfile("D");
		if(app.getavoffers().get(3).bookOffer() == true) {
			System.out.println("Offer reserved");
		}
		
		System.out.println("Logging the Host-Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Guest user in...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("This user tries to reserve an offer already reserved");
		if(app.getavoffers().get(3).bookOffer() == false) {
			System.out.println("This offer is already reserved\n");
		}
		
		System.out.println("Logging the Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the  Host-Guest user in again...");
		if(app.login("X1130055", "secreta") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The user changes to guest mode and tries to pay the offer reserved");
		app.getLog().changeProfile("D");
		if(app.getLog().getGuestProfile().getReserves().get(0).payOffer() == false) {
			System.out.println("The user credit card was wrong and he has been banned and logged out of the app\n");
		}
		
		System.out.println("Logging the Admin in...");
		if(app.login("11235813F", "Fibonacci")){
			System.out.println("Admin logged!\n");
		}
		
		System.out.println("The admin restores the user again");
		User u1 = app.getBannedUsers().get(0);
		if(u1.isGuest()) {
			u1.getGuestProfile().changeCCNumber("6402894629053417", app);			
		}
		if(u1.isHost()) {
			u1.getHostProfile().changeCCNumber("6402894629053417", app);
		}
		u1.restoreUser(app);
		
		System.out.println("Logging the Admin out...");
		if(app.logout()){
			System.out.println("Admin logged out!\n");
		}
		
		System.out.println("END OF THE TEST");
	}
	
}
