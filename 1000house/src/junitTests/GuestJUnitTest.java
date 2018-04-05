package junitTests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.*;
import exception.*;
import app.*;


/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class GuestJUnitTest {
	Guest guest;
	Application app;
	User u1;
	User u2;
	Offer offer;
	Reserve reserve;
	
	

	/**
	 * Creates the Guest that will be used to test the method, two users and app to test the changeCreditCard method (The users are set as connected),
	 * an offer,and a reserve.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		guest = new Guest("39875096");
		app = new Application("Test");
		u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		List<Characteristics> c = new ArrayList<Characteristics>() ;
		House h = new House("L", "D", c, 654,u1);
		offer = new HolidaysOffer(LocalDate.of(2018, 6, 3), 50,10, h, app, LocalDate.of(2018, 20, 3));
		reserve = new Reserve(u1,offer);
	}

	

	/**
	 * Test method for {@link app.Guest#removeReserve(app.Offer)}.
	 * This test first tries to remove a reserve when it isn't in the list.Then it adds the reserve to the list ,
	 * and removes it,checking it returns true
	 */
	@Test
	public void testRemoveReserve() {
		assertFalse(guest.removeReserve(offer));
		guest.getReserves().add(reserve);
		assertTrue(guest.removeReserve(offer));
		
	}

	/**
	 * Test method for {@link app.Guest#removeOffer(app.Offer)}.
	 * This test first tries to remove an offer when it isn't in the list.Then it adds the offer to the list ,
	 * and removes it,checking the method returns true
	 */
	@Test
	public void testRemoveOffer() {
		assertFalse(guest.removeOffer(offer));
		guest.getOffers().add(offer);
		assertTrue(guest.removeOffer(offer));
	}

	/**
	 * Test method for {@link app.Guest#addReserve(app.Reserve)}.
	 * This test uses the method to add the reserve to the list, and then checks that the reserve is in the list
	 */
	@Test
	public void testAddReserve() {
		guest.addReserve(reserve);
		assertTrue(guest.getReserves().contains(reserve));
	}

	/**
	 * Test method for {@link app.Guest#addOffer(app.Offer)}.
	 * This test uses the method to add the offer to the list, and then checks that the offer is in the list
	 */
	@Test
	public void testAddOffer() {
		guest.addOffer(offer);
		assertTrue(guest.getOffers().contains(offer));
	}

	/**
	 * Test method for {@link app.Profile#changeCCNumber(java.lang.String, app.Application)}.
	 * This test first tries to change the guest credit card when the logged user ismn't the admin
	 * When the exception is thrown, the value of an int prob changes to 1(initializated to 0,doesnt' change if the exception is not thrown)
	 * Then, changes the logged user to  the admin,and saves the actual value of the credit card in an string cc.
	 * Finally, it changes the credit card of the guest,checking it returns true, and checks that prob is equal to 1 
	 * and the actual value of the credit card is different to cc
	 * @throws NotAdmin
	 */
	@Test
	public void testChangeCCNumber() throws NotAdmin{
		int prob = 0;
		app.setLog(u1);
		try {
			guest.changeCCNumber("3436598", app);
		}
		catch(NotAdmin excep) {
			prob = 1;
		}
		app.setLog(u2);
		String cc = guest.getccNumber();
		assertTrue(guest.changeCCNumber("987654321", app));
		assertEquals(prob,1);
		assertNotSame(cc,"987654321");
	}

}
