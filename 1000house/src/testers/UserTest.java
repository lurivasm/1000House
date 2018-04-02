/**
 * 
 */
package testers;
import exception.*;

import static org.junit.Assert.*;

import org.junit.*;
import app.*;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class UserTest {
	
	private User u1;
	private User u2;
	private User u3;
	private User u4;
	private Application app;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		app = new Application("Prueba");
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
	 * Test method for {@link app.User#getHostProfile()}.
	 * This test tries to get the host profile from a guest user. When the exception is thrown, the variable prob changes his value from 0 to 1
	 * Finally, it gets the Host profile from a host-guest user, and checks that prob is equal to 1 and p is a host.
	 */
	@Test
	public void testGetHostProfile() throws NotHost{
		int prob = 0;
		try {
			@SuppressWarnings("unused")
			Profile p =  u4.getHostProfile();
		}
		catch(NotHost excep) {
			prob = 1;
		}
		Profile p = u1.getHostProfile();
		assertEquals(prob,1);
		assertTrue(p instanceof Host);
	}

	/**
	 * Test method for {@link app.User#getGuestProfile()}.
	 * This test tries to get the guest profile from a host user. When the exception is thrown, the variable prob changes his value from 0 to 1
	 * Finally, it gets the Guest profile from a host-guest user, and checks that prob is equal to 1 and p is a guest.
	 */
	@Test
	public void testGetGuestProfile() throws NotGuest{
		int prob = 0;
		try {
			@SuppressWarnings("unused")
			Profile p =  u3.getGuestProfile();
		}
		catch(NotGuest excep) {
			prob = 1;
		}
		Profile p = u1.getGuestProfile();
		assertEquals(prob,1);
		assertTrue(p instanceof Guest);
	}
	

	/**
	 * Test method for {@link app.User#banUser()}.
	 * This test first tries to ban a user when the logged user is not the admin.
	 * When the exception is thrown, prob changes his value to 1.
	 * Finally, it bans a user ,after changing the logged user to the admin.
	 * It checks that the value of prob is 1 ,the user state is banned and the user can't acces to the app(user.app is null)
	 */
	@Test
	public void testBanUser() throws NotAdmin{
		int prob = 0;
		app.setLog(u1);
		try {
			u3.banUser();
		}
		catch(NotAdmin excep) {
			prob = 1;
		}
		app.setLog(u2);
		u1.banUser();
		assertEquals(prob, 1);
		assertEquals(u1.getState(), UserStates.BANNED);
		assertEquals(u1.getApp(), null);
	}

	/**
	 * Test method for {@link app.User#restoreUser(app.Application)}.
	 * This test first bans a user ,and then tries to restore it when the user logged is not the admin
	 * When the exception is thrown, prob changes his value to 1.
	 * Finally, it restores the banned user
	 * It checks that the value of prob is 1 ,the user state is disconnected and the user can acces to the app(user.app isn't null)
	 */
	@Test
	public void testRestoreUser() throws NotAdmin {
		int prob = 0;
		app.setLog(u2);
		u3.banUser();
		app.setLog(u1);
		try {
			u3.restoreUser(app);
		}
		catch(NotAdmin excep) {
			prob = 1;
		}
		app.setLog(u2);
		u3.restoreUser(app);
		assertEquals(prob, 1);
		assertEquals(u3.getState(), UserStates.DISCONNECTED);
		assertNotSame(null, u1.getApp());
	}

	/**
	 * Test method for {@link app.User#changeProfile(java.lang.String)}.
	 */
	@Test
	public void testChangeProfile()throws WrongProfile {
		int prob1 = 0,prob2 = 0,prob3 = 0;
		try {
			u3.changeProfile("A");
		}
		catch(WrongProfile excep) {
			prob1 = 1;
		}
		
		try {
			u3.changeProfile("G");
		}
		catch(WrongProfile excep) {
			prob2 = 1;
		}
		
		try {
			u3.changeProfile("H");
		}
		catch(WrongProfile excep) {
			prob3 = 1;
		}
		
		u1.changeProfile("O");
		UserStates state1 = u1.getState();
		u1.changeProfile("D");
		UserStates state2 = u1.getState();
		assertEquals(prob1, 1);
		assertEquals(prob2, 1);
		assertEquals(prob3, 1);
		assertEquals(state1,UserStates.CONNECTED_HOST);
		assertEquals(state2, UserStates.CONNECTED_GUEST);
	}

	/**
	 * Test method for {@link app.User#isHost()}.
	 */
	@Test
	public void testIsHost() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.User#isGuest()}.
	 */
	@Test
	public void testIsGuest() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link app.User#isAdmin()}.
	 */
	@Test
	public void testIsAdmin() {
		fail("Not yet implemented");
	}

}
