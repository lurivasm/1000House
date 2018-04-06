/**
 * 
 */
package appDemos;


import java.time.LocalDate;
import java.util.*;


import app.*;
import modifiableDates.*;
import exception.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */

public class Demo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Creating the app Demo. It was first created in the Demo1,and modified in Demo2 so the constructor charges the info from Demo1");
		Application app = new Application("Demo");
		System.out.println("App created!");
		
		System.out.println("Setting the date of logging in in today...");
		ModifiableDate.setToday();
		System.out.println("Date set!\n");
				
		System.out.println("Logging the Guest user in ...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The size of the guest offers list is " + app.getLog().getGuestProfile().getOffers().size() + "\n");
		System.out.println("The guest tries to buy an offer that has already been bought");
		List<Offer> result = app.searchBought();
		if(result.get(0).buyOffer() == false) {
			System.out.println("Offer already bought!\n");
		}
		
		System.out.println("The guest tries to buy an offer that is available");		
		if(app.getavoffers().get(1).buyOffer() == true) {
			System.out.println("Offer bought!\n");
		}
		
		
		System.out.println("The size of the guest offers list is " + app.getLog().getGuestProfile().getOffers().size() + "\n");
		
		System.out.println("Logging the guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Host-Guest user in...");
		if(app.login("X1130055", "secreta") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("For testing the app, we change this User cccard back to a wrong one\n");
		app.getLog().getHostProfile().setCcNumber("790362798457254");
		app.getLog().getGuestProfile().setCcNumber("790362798457254");
		
		System.out.println("We change the user to Host mode");
		if(app.getLog().changeProfile("O")) {
			System.out.println("Changed to host mode!");
		}
		
		System.out.println("The host creates a house and an offer on it");
		List<Characteristics> c = new ArrayList<Characteristics>();
		c.add(Characteristics.TV);
		app.createHouse("Sun Bay",c , "2",90461 );
		app.createOffer(app.getLog().getHostProfile().getHouses().get(1), LocalDate.of(2018, 6, 1), 3, 100, 30);
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
		
		System.out.println("Logging the Guest user in again...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The size of the guest offers list is " + app.getLog().getGuestProfile().getOffers().size() + "\n");
		
		System.out.println("The guest buys the offer created by the Host-Guest");
		if(app.getavoffers().get(4).buyOffer() == true) {
			System.out.println("Offer bought!\n");
		}
		
		System.out.println("The size of the guest offers list is " + app.getLog().getGuestProfile().getOffers().size() + "\n");
		
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
		System.out.println("Now the debt is " +  u.getDebt() + "\n");
		
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
		
		System.out.println("The host creates an offer");
		c.add(Characteristics.TV);
		app.createOffer(app.getLog().getHostProfile().getHouses().get(0), LocalDate.of(2019, 3, 1),9, 600, 100);
		System.out.println("Offer created!\n");
		
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
		
		System.out.println("The user changes to guest mode and buys the offer created by the other Host-Guest user");
		app.getLog().changeProfile("D");
		if(app.getavoffers().get(5).buyOffer() == false) {
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
		
		System.out.println("Logging the other Host-Guest user in...");
		if(app.login("54444111D", "olvidame") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The user tries to comment  an offer in host mode");
		try{
			app.getavoffers().get(0).commentOffer("Is good");
		}
		catch(NotGuest excep) {
			System.out.println(excep);
		}
		
		System.out.println("The user changes to guest mode and tries to comment a long long text");
		app.getLog().changeProfile("D");
		String aka = "Pipopipopipopipiiiiiiii pipopipopiiiiiiiiii pipopipiiiii CONTIGO PIPO "
				+ "Pipopipopipopipiiiiiiii pipopipopiiiiiiiiii pipopipiiiii CONTIGO PIPO "
				+ "Pipopipopipopipiiiiiiii pipopipopiiiiiiiiii pipopipiiiii CONTIGO PIPO "
				+ "Pipopipopipopipiiiiiiii pipopipopiiiiiiiiii pipopipiiiii CONTIGO PIPO";
		app.getavoffers().get(0).commentOffer(aka);
		
		System.out.println("The user comments with a normal comment");
		if(app.getavoffers().get(0).commentOffer("I love this offer") == true) {
			System.out.println("Offer commented!\n");
		}
		System.out.println("The size of the offer comments list now is " + app.getavoffers().get(0).getComments().size());
		
		System.out.println("Logging the Host-Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("Logging the Guest user in ...");
		if(app.login("55555111Z", "NoSeSaBe") == true){
			System.out.println("Guest logged!\n");
		}
		
		System.out.println("The host tries to answer the comment with a rate of 100");
		try{
			((Text)app.getavoffers().get(0).getComments().get(0)).addAnswer(100);
		}
		catch(RateException excep1) {
			System.out.println(excep1);
		}
		
		System.out.println("The host answer the comment with a rate of 3");
		if(((Text)app.getavoffers().get(0).getComments().get(0)).addAnswer(3) == true) {
			System.out.println("Comment answered!\n");
		}
		System.out.println("The size of the comment answer list now is " +((Text)app.getavoffers().get(0).getComments().get(0)).getAnswers().size());
		
		
		System.out.println("Logging the Guest out...");
		if(app.logout()){
			System.out.println("User logged out!\n");
		}
		
		System.out.println("END OF THE TEST");
	}
}

