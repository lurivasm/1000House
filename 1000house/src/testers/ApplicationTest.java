/**
 * 
 */
package testers;

import exception.*;

import static org.junit.Assert.*;

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
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("Test");
		u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u3 = new User( "User", "3", "PATO", "12796082F", "O", "12300678" ,app);
		u4 = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		u3.setState(UserStates.CONNECTED_HOST);
		u4.setState(UserStates.CONNECTED_GUEST);
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
	 */
	@SuppressWarnings("unused")
	@Test
	public void testCreateHouse() throws NotHost{
		int prob = 0;
		List<Characteristics> c = new ArrayList<Characteristics>();
		app.setLog(u4);
		try {
			app.createHouse("D", c, "L", 34677);
		}
		catch(NotHost excep) {
			prob = 1;
		}
		app.setLog(u3);
		app.createHouse("D", c, "L", 34677);
		assertEquals(prob,1);
		assertNotSame(0,app.getLog().getHostProfile().getHouses().size());
	}

	/**
	 * Test method for {@link app.Application#searchType(java.lang.String)}.
	 */
	@Test
	public void testSearchType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#searchCode(long)}.
	 */
	@Test
	public void testSearchCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#searchDate(java.time.LocalDate, java.time.LocalDate)}.
	 */
	@Test
	public void testSearchDate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#searchBought()}.
	 */
	@Test
	public void testSearchBought() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#searchBooked()}.
	 */
	@Test
	public void testSearchBooked() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#searchRate(double)}.
	 */
	@Test
	public void testSearchRate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#logout()}.
	 */
	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#createOffer(app.House, java.time.LocalDate, int, int)}.
	 */
	@Test
	public void testCreateOfferHouseLocalDateIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.Application#createOffer(app.House, java.time.LocalDate, java.time.LocalDate, int)}.
	 */
	@Test
	public void testCreateOfferHouseLocalDateLocalDateInt() {
		fail("Not yet implemented");
	}

}
