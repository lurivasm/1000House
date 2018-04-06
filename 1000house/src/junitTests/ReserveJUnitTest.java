package junitTests;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.*;
import exception.*;
import app.*;


/**
 * Reserve Test
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class ReserveJUnitTest {
	User u1;
	User u2;
	User admin;
	Offer offer;
	LocalDate ini;
	LocalDate end;
	Application app;
	House house;
	List<Characteristics> charact = new ArrayList<Characteristics>();
	Reserve reserve;

	/**
	 * Creates an app, two users (u1 is the owner of the offer and u2 the guest), the offer
	 * and the house
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("test");
		u1 = new User("Dani", "Santo-Tomas", "tiraficher", "12345678A", "OD", "1234567946324968", app);
		u1.setState(UserStates.CONNECTED_HOST);
		u2 = new User("Javier", "Lopez Cano", "gatiti", "87654321B", "OD", "8765432164896421", app);
		u2.setState(UserStates.CONNECTED_HOST);
		app.setLog(u2);
		house = new House("LOCATION1", "DESCRIPTION1", charact, 28974, u1);
		ini = LocalDate.of(2018, 4, 28);
		offer =  new LivingOffer(ini, 1000, 200,house, app, 4);
	}

	/**
	 * It tests that a Reserve cannot be created when the logged user is Host
	 * @throws Exception
	 */
	@Test
	public void testReserveConstructor() throws Exception {
		int exGuest = 0;
		/*Case is connected as host*/
		try {
			reserve = new Reserve(u2, offer);
		}
		catch(NotGuest ex) {
			exGuest = 1;
		}
		assertEquals(exGuest, 1);
	}
	
	/**
	 * It tests that a user cannot pay its reserves unlees he is in guest profile
	 * @throws Exception
	 */
	@Test
	public void testPayOffer() throws Exception{
		int exGuest = 0;
		u2.changeProfile("D");
		reserve = new Reserve(u2, offer);
		u2.getGuestProfile().addReserve(reserve);
		u2.changeProfile("O");
		/*Case is not guest*/
		try {
			reserve.payOffer();
		}
		catch(NotGuest ex) {
			exGuest = 1;
		}
		/*Case is Guest*/
		u2.changeProfile("D");
		assertTrue(reserve.payOffer());
		assertEquals(exGuest, 1);
	}
	
	/**
	 * It tests that a user cannot cancel its reserves unlees he is in guest profile
	 * @throws Exception
	 */
	@Test
	public void testCancelOffer() throws Exception{
		int exGuest = 0;
		u2.changeProfile("D");
		reserve = new Reserve(u2, offer);
		u2.getGuestProfile().addReserve(reserve);
		u2.changeProfile("O");
		/*Case is not guest*/
		try {
			reserve.cancelReserve();
		}
		catch(NotGuest ex) {
			exGuest = 1;
		}
		/*Case is Guest*/
		u2.changeProfile("D");
		assertTrue(reserve.cancelReserve());
		assertEquals(exGuest, 1);
	}

}
