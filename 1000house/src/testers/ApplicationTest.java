/**
 * 
 */
package testers;

import exception.*;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.*;


import app.*;

/**
 * @author danist
 *
 */
public class ApplicationTest {

	
	Application app;
	User u1;
	User u2;
	User u3;
	User u4;
	House h;
	HolidaysOffer o1;
	LivingOffer o2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("Test");
		u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u3 = new User( "User", "3", "pezEspada", "51999111X", "O", "12300678" ,app);
		u4 = new User( "User", "4", "NoSeSaBe", "55555111Z", "D", "1585678" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		u3.setState(UserStates.CONNECTED_HOST);
		u4.setState(UserStates.CONNECTED_GUEST);
		
		List<Characteristics> c = new ArrayList<Characteristics>();
		h = new House("LOCATION1", "DESCRIPTION1", c, 28974,u1);		
		u1.getHostProfile().getHouses().add(h);
		o1 = new HolidaysOffer(LocalDate.of(2018, 4, 5),50,u1.getHostProfile().getHouses().get(0),app,LocalDate.of(2018, 4, 20));
		o2 = new LivingOffer(LocalDate.of(2018, 4, 5),50,u1.getHostProfile().getHouses().get(0),app,3);
		h.getOffers().add(o1);
		h.getOffers().add(o2);
		app.getavoffers().add(o1);
		app.getavoffers().add(o2);
		
	}

	/**
	 * Test method for {@link app.Application#Application(java.lang.String)}.
	 */
	@Test
	public void testApplication() throws Exception{
		Application application = new Application("Test");
		assertNotSame(0,application.getUsers().size());
	}

	/**
	 * Test method for {@link app.Application#createHouse(java.lang.String, java.util.List, java.lang.String, long)}.
	 * This test tries to create a house when the logged user is guest, and checkd that the method returns false
	 * Then creates a house when the logged user is a host,an checks that the method returns true
	 * Finally, tries to create the same house again, and checks that the method returns false
	 */
	@Test
	public void testCreateHouse() throws Exception{
		List<Characteristics> c = new ArrayList<Characteristics>();
		app.setLog(u4);		
		assertFalse(app.createHouse("D", c, "L", 34677));		
		app.setLog(u3);
		assertTrue(app.createHouse("D", c, "L", 34677));
		assertNotSame(0,app.getLog().getHostProfile().getHouses().size());
		assertFalse(app.createHouse("D", c, "L", 34677));
	}

	/**
	 * Test method for {@link app.Application#searchType(java.lang.String)}.
	 * This test search in the app by type,and checks the size of the lists that the search returns.
	 * As there are only one offer of each type in the app(that was set in the setUpBefore),each list must have size = 1
	 */
	@Test
	public void testSearchType() throws Exception{
		List<Offer> l1 = app.searchType("Holidays");
		List<Offer> l2 = app.searchType("Living");
		assertEquals(l1.size(),1);
		assertEquals(l2.size(),1);
	}

	/**
	 * Test method for {@link app.Application#searchCode(long)}.
	 * This test search in the app by zipcode, and checks the size of the lists that the search returns.
	 * The two offers that are in the app are on the same house(that was set in the setUpBefore), so the size of the succesfull search list must be 2
	 * Also the size of the list of the search with a code  that doesn't belong to any house must be 0 
	 */
	@Test
	public void testSearchCode() {
		List<Offer> l1 = app.searchCode(28974);
		List<Offer> l2 = app.searchCode(12345);
		assertEquals(l1.size(),2);
		assertEquals(l2.size(),0);
	}

	/**
	 * Test method for {@link app.Application#searchDate(java.time.LocalDate, java.time.LocalDate)}.
	 * This test search in the app by a range of dates and checks the size of the lists that the search returns.
	 * In the first range of dates were the test seacrhs is only one of the two offers,and in the second range, the two offers must be returned by the search 
	 */
	@Test
	public void testSearchDate() {
		List<Offer> l1 = app.searchDate(LocalDate.of(2018, 4, 5),LocalDate.of(2018, 4, 20));
		List<Offer> l2 = app.searchDate(LocalDate.of(2018, 4, 5),LocalDate.of(2018, 7, 5));
		assertEquals(l1.size(),1);
		assertEquals(l2.size(),2);
	}

	/**
	 * Test method for {@link app.Application#searchBought()}.
	 * This test search the offers that have been bought
	 * First it tries to search in the app when the user searching is not registered, and checks that the method returns null
	 * Finally, it sets one offer as bought,search in the app, and checks the valiue of prob and the size of the list resulting from the search
	 */
	@Test
	public void testSearchBought() throws Exception {
		List<Offer> l1 ;		
		assertEquals(null,app.searchBought());		
		app.setLog(u1);
		app.getavoffers().get(0).setState(OfferStates.BOUGHT);
		app.getavoffers().get(1).setState(OfferStates.AVAILABLE);
		l1= app.searchBought();		
		assertEquals(l1.size(),1);
		
	}

	/**
	 * Test method for {@link app.Application#searchBooked()}.
	 * This test search the offers that have been reserved
	 * First it tries to search in the app when the user searching is not registered, and checks that the method returns null
	 * Finally, it sets one offer as reserved,search in the app, and checks the value of prob and the size of the list resulting from the search
	 */
	@Test
	public void testSearchBooked() throws NotRegisteredUser{
		List<Offer> l1 ;
		assertEquals(null,app.searchBooked());
		app.setLog(u1);
		app.getavoffers().get(0).setState(OfferStates.RESERVED);
		app.getavoffers().get(1).setState(OfferStates.AVAILABLE);
		l1= app.searchBooked();		
		assertEquals(l1.size(),1);
	}

	/**
	 * Test method for {@link app.Application#searchRate(double)}.
	 * This test search the offers that have the same or more than the rate given
	 * First it tries to search in the app when the user searching is not registered, and checks that the method returns null
	 * Finally, it sets one offer rate over the given and the other under the given,search in the app, and checks the value of prob and the size of the list resulting from the search
	 * 
	 */
	@Test
	public void testSearchRate() throws NotRegisteredUser {
		List<Offer> l1 ;
		assertEquals(null,app.searchRate(3));		
		app.setLog(u1);
		app.getavoffers().get(0).setAverageRate(5);
		app.getavoffers().get(1).setAverageRate(2);
		l1= app.searchRate(3);		
		assertEquals(l1.size(),1);
	}

	/**
	 * Test method for {@link app.Application#login(java.lang.String, java.lang.String)}.
	 * This test first tries to login a user that is not registered,checking that he is not logged.
	 * Also the method prints "ERROR 403 You are not registered"
	 * Finally,logs a registered user ,checking he is logged
	 */
	@Test
	public void testLogin() throws Exception {
		app.login("56899", "potato");
		assertEquals(null,app.getLog());
		app.login("51999111X", "pezEspada");		
		assertNotSame(null,app.getLog());
	}

	/**
	 * Test method for {@link app.Application#logout()}.
	 * This test logs an user in, logs it out, and checks that the method returned true
	 */
	@Test
	public void testLogout() throws Exception{
		app.login("51999111X", "pezEspada");
		assertTrue(app.logout());
	}

	/**
	 * Test method for {@link app.Application#createOffer(app.House, java.time.LocalDate, int, int)}.
	 * This test firstly tries to create an offer when the logged user is not a host,and checks that the method returns false
	 * Secondly tries to create an offer when the logged user is not the owner of the house where the offer is created, and checks that the method returns false
	 * Then tries to create a LivingOffer in a house that already has a LivingOffer, checking that the method returns false
	 * Finally, it creates a house and checks that the method returns true
	 */
	@Test
	public void test1CreateOffer() throws Exception{
		List<Characteristics> c = new ArrayList<Characteristics>();
		House house = new House("LOCATION", "DESCRIPTION", c, 28974,u3);
		u3.getHostProfile().getHouses().add(house);
		app.setLog(u2);
		assertFalse(app.createOffer(house, LocalDate.of(2018, 4, 5),50,3));
		u1.setState(UserStates.CONNECTED_HOST);
		app.setLog(u1);
		assertFalse(app.createOffer(house, LocalDate.of(2018, 4, 5),50,3));
		assertFalse(app.createOffer(h, LocalDate.of(2018, 4, 5),50,3));
		app.setLog(u3);
		assertTrue(app.createOffer(house, LocalDate.of(2018, 4, 5),50,3));
	}

	/**
	 * Test method for {@link app.Application#createOffer(app.House, java.time.LocalDate, java.time.LocalDate, int)}.
	 * This test firstly tries to create an offer when the logged user is not a host,and checks that the method returns false
	 * Secondly tries to create an offer when the logged user is not the owner of the house where the offer is created, and checks that the method returns false
	 * Then tries to create a HolidaysOffer in a house that already has a HolidaysOffer, checking that the method returns false
	 * Finally, it creates a house and checks that the method returns true
	 */
	@Test
	public void test2CreateOffer() throws Exception {
		List<Characteristics> c = new ArrayList<Characteristics>();
		House house = new House("LOCATION", "DESCRIPTION", c, 28974,u3);
		u3.getHostProfile().getHouses().add(house);
		app.setLog(u2);
		assertFalse(app.createOffer(house, LocalDate.of(2018, 4, 5),LocalDate.of(2018, 4, 20),50));
		u1.setState(UserStates.CONNECTED_HOST);
		app.setLog(u1);
		assertFalse(app.createOffer(house, LocalDate.of(2018, 4, 5),LocalDate.of(2018, 4, 20),50));
		assertFalse(app.createOffer(h, LocalDate.of(2018, 4, 5),LocalDate.of(2018, 4, 20),50));
		app.setLog(u3);
		assertTrue(app.createOffer(house, LocalDate.of(2018, 4, 5),LocalDate.of(2018, 4, 20),50));
	}

}
