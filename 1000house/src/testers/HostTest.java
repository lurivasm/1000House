package testers;

import static org.junit.Assert.*;



import org.junit.*;
import exception.*;
import app.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class HostTest {
	private Host host;
	private Application app;
	private User u1;
	private User u2;
	

	/**
	 * Creates the Guest that will be used to test the method, 
	 * two users and app to test the changeCreditCard method (The users are set as connected)
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		host = new Host("39875096");
		app = new Application("Test");
		u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
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
			host.changeCCNumber("3436598", app);
		}
		catch(NotAdmin excep) {
			prob = 1;
		}
		app.setLog(u2);
		String cc = host.getccNumber();
		assertTrue(host.changeCCNumber("987654321", app));
		assertEquals(prob,1);
		assertNotSame(cc,"987654321");
	}

}


