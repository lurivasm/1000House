package junitTests;



import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.*;
import exception.*;
import app.*;

/**
 * TextTest
 * @author Lucia Rivas Molina <lucia.rivasmolina@estudiante.uam.es>
 * @author Daniel Santo-Tomas <daniel.santo-tomas@estudiante.uam.es>
 *
 */
public class LivingOfferJUnitTest {
	User u1;
	User u2;
	User admin;
	Offer offer;
	Offer comp;
	LocalDate ini;
	Application app;
	House house;
	List<Characteristics> charact = new ArrayList<Characteristics>() ;
	
	/**
	 * Creates two users and the offer that we need to create a new text comment
	 * in order to probe the addAnswer function for both types, rate and text
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("test");
		u1 = new User("Dani", "Santo-Tomas", "tiraficher", "12345678A", "OD", "12345678", app);
		u1.setState(UserStates.CONNECTED_HOST);
		u2 = new User("Javier", "Lopez Cano", "gatiti", "87654321B", "OD", "87654321", app);
		u2.setState(UserStates.CONNECTED_HOST);
		admin = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		admin.setState(UserStates.ADMIN);
		app.setLog(null);
		house = new House("LOCATION1", "DESCRIPTION1", charact, 28974, u1);
		ini = LocalDate.of(2018, 4, 28);
		offer =  new LivingOffer(ini, 1000, 200,house, app, 4);
		comp = new LivingOffer(ini, 100, 200,house, app, 4);
	}

	
	@Test
	public void testBookOffer() throws Exception{
		int exNotReg = 0;
		int exNotGuest = 0;
		/*NotRegistered logged*/
		try{
			offer.bookOffer();
		}
		catch(NotRegisteredUser ex) {
			exNotReg = 1;
		}
		/*Host logged*/
		app.setLog(u2);
		try {
			offer.bookOffer();
		}
		catch(NotGuest excep) {
			exNotGuest = 1;
		}
		/*Guest logged*/
		u2.setState(UserStates.CONNECTED_GUEST);
		/*Case is not available*/
		assertFalse(offer.bookOffer());
		
		offer.setState(OfferStates.AVAILABLE);
		assertTrue(offer.bookOffer());
		assertEquals(offer.getState(), OfferStates.RESERVED);
		assertEquals(exNotReg, 1);
		assertEquals(exNotGuest, 1);
	}
	
	@Test
	public void testisAvailable(){
		/*Case is not available*/
		assertFalse(offer.isAvailable());
		/*Case is available*/
		offer.setState(OfferStates.AVAILABLE);
		assertTrue(offer.isAvailable());
	}
	
	@Test
	public void testisReserved(){
		/*Case is not*/
		assertFalse(offer.isReserved());
		/*Case is*/
		offer.setState(OfferStates.RESERVED);
		assertTrue(offer.isReserved());
	}
	
	@Test
	public void testisBought(){
		/*Case is not*/
		assertFalse(offer.isBought());
		/*Case is*/
		offer.setState(OfferStates.BOUGHT);
		assertTrue(offer.isBought());
	}
	
	@Test
	public void testBuyOffer() throws Exception{
		int exNotReg = 0;
		int exNotGuest = 0;
		/*NotRegistered logged*/
		try{
			offer.buyOffer();
		}
		catch(NotRegisteredUser ex) {
			exNotReg = 1;
		}
		/*Host logged*/
		app.setLog(u2);
		try {
			offer.buyOffer();
		}
		catch(NotGuest excep) {
			exNotGuest = 1;
		}
		/*Guest logged*/
		u2.setState(UserStates.CONNECTED_GUEST);
		/*Case is not available*/
		assertFalse(offer.buyOffer());
		
		offer.setState(OfferStates.AVAILABLE);
		assertTrue(offer.buyOffer());
		assertEquals(offer.getState(), OfferStates.BOUGHT);
		assertEquals(exNotReg, 1);
		assertEquals(exNotGuest, 1);
	}
	
	@Test 
	public void testCompareOffer() {
		assertFalse(comp.compareOffer(offer));
		comp.setPrice(1000);
		assertTrue(comp.compareOffer(offer));		
	}
	
	@Test 
	public void testDenyOffer() throws Exception{
		int exAdmin = 0;
		try {
			offer.denyOffer();
		}
		catch(NotAdmin ex) {
			exAdmin = 1;
		}
		app.setLog(admin);
		assertTrue(offer.denyOffer());
		assertEquals(exAdmin, 1);
	}
	
	@Test 
	public void testApproveOffer() throws Exception{
		int exAdmin = 0;
		try {
			offer.approveOffer();
		}
		catch(NotAdmin ex) {
			exAdmin = 1;
		}
		app.setLog(admin);
		assertTrue(offer.approveOffer());
		assertEquals(exAdmin, 1);
	}
	
	@Test 
	public void testAskForChanges() throws Exception{
		int exAdmin = 0;
		String changes = "Change the house";
		try {
			offer.askForChanges(changes);
		}
		catch(NotAdmin ex) {
			exAdmin = 1;
		}
		app.setLog(admin);
		assertTrue(offer.askForChanges(changes));
		assertEquals(exAdmin, 1);
	}
	
	@Test
	public void testCalculateRate() throws Exception{
		app.setLog(u2);
		u2.setState(UserStates.CONNECTED_GUEST);
		offer.commentOffer(3);
		offer.commentOffer(3);
		assertTrue(offer.calculateRate());
		assertEquals(offer.getAverageRate(), 3);
	}
	
	@Test
	public void testCommentOfferRate() throws Exception{
		int exGuest = 0;
		try {
			offer.commentOffer(4);
		}
		catch(NotGuest ex) {
			exGuest = 1;
		}
		app.setLog(u2);
		u2.setState(UserStates.CONNECTED_GUEST);
		assertTrue(offer.commentOffer(4));
		assertEquals(exGuest, 1);
	}
	
	@Test
	public void testCommentOfferText() throws Exception{
		int exGuest = 0;
		try {
			offer.commentOffer("Good!");
		}
		catch(NotGuest ex) {
			exGuest = 1;
		}
		app.setLog(u2);
		u2.setState(UserStates.CONNECTED_GUEST);
		assertTrue(offer.commentOffer("Good!"));
		assertEquals(exGuest, 1);
	}
	
}
