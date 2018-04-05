/**
 * 
 */
package junitTests;
import exception.*;

import static org.junit.Assert.*;

import org.junit.*;
import app.*;
/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class UserJUnitTest {
	
	private User u1;
	private User u2;
	private User u3;
	private User u4;
	private Application app;
	
	/**
	 * It creates an app and the 4 type of users : host-guest,admin,host and guest, setting their states to connected
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		app = new Application("Test");
		u1 = new User( "Lucia", "Rivas Molina", "Gnomo69", "12796482F", "OD", "12345678" ,app);
		u2 = new User( "Admin", "Istrator", "11223", "12796567M", "A", "1234577" ,app);
		u3 = new User( "User", "3", "PATO", "12796082F", "O", "1230067890456782" ,app);
		u4 = new User( "User", "4", "Bsnjs", "34596482F", "D", "1585678" ,app);
		u1.setState(UserStates.CONNECTED_GUEST);
		u2.setState(UserStates.ADMIN);
		u3.setState(UserStates.CONNECTED_HOST);
		u4.setState(UserStates.CONNECTED_GUEST);
	}
	
	@SuppressWarnings("unused")
	
	/**
	 * Test method for {@link app.User#User(java.lang.String, java.lang.String, java.lang.String,java.lang.String, java.lang.String, java.lang.String,  app.Application)}.
	 * This test tries to create a user with a profile(W) that doesn't exist.
	 * The exception is thrown and the value of an int prob changes to 1
	 * Finally, it checks that the value of prob is 1(It means that the exception was thrown)
	 * @throws WrongProfile
	 */
	@Test
	public void testUser() throws WrongProfile{
		User user;
		int prob = 0;
		try {
			user = new User("Wrong", "Profile", "SAD", "8573796K", "W", "344536", app);
		}
		catch(WrongProfile excep) {
			prob = 1;
		}
		assertEquals(prob,1);
	}
	
	@SuppressWarnings("unused")
	/**
	 * Test method for {@link app.User#getHostProfile()}.
	 * This test tries to get the host profile from a guest user. When the exception is thrown, the variable prob changes his value from 0 to 1
	 * Finally, it gets the Host profile from a host-guest user, and checks that prob is equal to 1 and p is a host.
	 */
	@Test
	public void testGetHostProfile() throws NotHost{
		int prob = 0;
		try {
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
	 * This test bans a user
	 * It checks that the user state is banned and the user can't acces to the app(user.app is null)
	 */
	@Test
	public void testBanUser() {
		u1.banUser();
		assertEquals(u1.getState(), UserStates.BANNED);
		assertEquals(u1.getApp(), null);
	}

	/**
	 * Test method for {@link app.User#restoreUser(app.Application)}.
	 * This test first bans a user ,and then tries to restore it when the user logged is not the admin
	 * When the exception is thrown, prob changes his value to 1.
	 * Finally, it restores the banned user
	 * It checks that the value of prob is 1 ,the user state is disconnected and the user can access to the app(user.app isn't null)
	 */
	@Test
	public void testRestoreUser() throws Exception {
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
		u3.setDebt(90);
		u3.restoreUser(app);
		assertEquals(prob, 1);
		assertEquals(u3.getState(), UserStates.DISCONNECTED);
		assertNotSame(90,u3.getDebt());
		assertNotSame(null, u1.getApp());
	}

	/**
	 * Test method for {@link app.User#changeProfile(java.lang.String)}.
	 * This test first tries to change to the Admin profile,then tries to change to the Guest profile when the user is only host, and to host when is only guest.
	 * Those three things are wrong, so the exceptions are thrown and the value of prob1,prob2 and prob3 changes to 1
	 * Finally, it changes from Guest to Host profile in a guest-host user,and save the profile in state1,and then changes back from Host to Guest,saving the profile in state2
	 * It checks the value of prob1,prob2, prob3, state1 and state2
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
	 * This test checks that isHost returns false in u2(Admin) and u4(Guest), and return true when the
	 *  user has a Host profile(u1 is host-guest and u3 is host) 
	 */
	@Test
	public void testIsHost() {
		assertFalse(u2.isHost());
		assertFalse(u4.isHost());
		assertTrue(u1.isHost());
		assertTrue(u3.isHost());
	}

	/**
	 * Test method for {@link app.User#isGuest()}.
	 * This test checks that isGuest returns false in u2(Admin) and u3(Host), and return true when the 
	 * user has a Guest profile(u1 is host-guest and u4 is Guest) 
	 */
	@Test
	public void testIsGuest() {
		assertFalse(u2.isGuest());
		assertFalse(u3.isGuest());
		assertTrue(u1.isGuest());
		assertTrue(u4.isGuest());
	}

	/**
	 * Test method for {@link app.User#isAdmin()}.
	 * This test checks that isAdmin returns false when the user is not the admin(u1 is host-guest, u3 is host and u4 is guest),
	 * and it returns when the user is the admin(u2)
	 */
	@Test
	public void testIsAdmin() {
		assertFalse(u1.isAdmin());
		assertFalse(u3.isAdmin());
		assertFalse(u4.isAdmin());
		assertTrue(u2.isAdmin());
	}

}
