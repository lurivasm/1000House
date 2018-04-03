package testers;

import org.junit.Assert.*;

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
public class TextTest {
	User u1;
	User u2;
	Offer offer;
	LocalDate ini;
	LocalDate end;
	Text text;
	Text answer;
	Rate rate;
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
		u2.setState(UserStates.CONNECTED_GUEST);
		house = new House("LOCATION1", "DESCRIPTION1", charact, 28974, u1);
		ini = LocalDate.of(2018, 4, 28);
		end =  LocalDate.of(2018, 5, 10);
		offer =  new HolidaysOffer(ini, 1000, house, app, end);
		text = new Text("This offer is sublime", u2, offer);
	}

	
	@Test
	public void testAddAnswerRate() throws NotGuest, RateException{
		int exRate = 0;
		int exGuest = 0;
		try{
			text.addAnswer(6, u2, offer);
		}
		catch(RateException ex) {
			exRate = 1;
		}
		try {
			text.addAnswer(5, u1, offer);
		}
		catch(NotGuest excep) {
			exGuest = 1;
		}
		assertTrue(text.addAnswer(3, u2, offer));
		assertEquals(exRate, 1);
		assertEquals(exGuest, 1);
	}
	
	@Test
	public void testAddAnswerText() throws NotGuest, TextException{
		int exRate = 0;
		int exGuest = 0;
		String aka = ""
		try{
			text.addAnswer(aka, u2, offer);
		}
		catch(TextException ex) {
			exRate = 1;
		}
		try {
			text.addAnswer(5, u1, offer);
		}
		catch(NotGuest excep) {
			exGuest = 1;
		}
		assertTrue(text.addAnswer(3, u2, offer));
		assertEquals(exRate, 1);
		assertEquals(exGuest, 1);
	}

}
