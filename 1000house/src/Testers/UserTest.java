/**
 * 
 */
package Testers;

import static org.junit.Assert.*;
import App.*;
import Exception.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author danist
 *
 */
public class UserTest {

	private static Application app;
	private static User u1;
	private static User u2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = new Application("TestApp");
		u1 = new User("Lucia", "Rivas Molina", "Gnomo72", "123456F","OD","1234567", app );
		u2 =  new User("Admin", "Istrator","Fibonacci",  "11235813F","A","1237890", app );
	}

	/**
	 * Test method for {@link App.User#getHostProfile()}.
	 */
	@Test
	public void testGetHostProfile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link App.User#getGuestProfile()}.
	 */
	@Test
	public void testGetGuestProfile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link App.User#banUser()}.
	 */
	@Test
	public void testBanUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link App.User#restoreUser(App.Application)}.
	 */
	@Test
	public void testRestoreUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link App.User#changeProfile(java.lang.String)}.
	 */
	@Test
	public void testChangeProfile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link App.User#isHost()}.
	 */
	@Test
	public void testIsHost() {
		fail("Not yet implemented");
	}

}
