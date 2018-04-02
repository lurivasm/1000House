/**
 * 
 */
package testers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import app.*;
import exception.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class HouseTest {
	
	House h1;
	House h2;
	User host;
	Application app;
	List<Characteristics> c = new ArrayList<Characteristics>() ;

	/**
	 * Sets the houses up, using the app and an empty list of characteristics. The houses share the zip-code but 
	 * have different locations
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("Test");
		host = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		h1 = new House("LOCATION1", "DESCRIPTION1", c, 28974,host);
		h2 = new House("LOCATION2", "DESCRIPTION2", c, 28974,host);
	}

	/**
	 * Test method for {@link app.House#House(java.lang.String, java.lang.String, java.util.List, long, app.User)}.
	 * This test initializes a variable prob to 0. Then tries to create a housewith an user that is not host. 
	 * The exception NotHost is thrown,and the value of prob changes to 1.
	 *  Finally, it checks the value of prob.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testHouse() throws Exception {
		int prob = 0;
		User guest = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		try {
			House h = new House("LOCATION", "DESCRIPTION", c, 26718, guest);
		}
		catch(NotHost excep) {
			prob = 1;
		}
		assertEquals(prob,1);
	}

	/**
	 * Test method for {@link app.House#compareHouse(app.House)}.
	 * This test first compares two different houses, and checks that the method returns false.
	 * Finally, compares  a house with itself and checks that the method return true.
	 */
	@Test
	public void testCompareHouse() {
		assertFalse(h1.compareHouse(h2));
		assertTrue(h1.compareHouse(h1));
	}

}
