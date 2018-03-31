package testers;
import app.*;
import exception.*;


public class UserTest {

	public static void main(String[] args) throws WrongProfile,NotHost {
		Application app = new Application("Prueba");
		User u = new User("Manolo", "Perez", "MnOlO69", "123456", "D","1234990", app );
		System.out.println(u.getName() + " "+ u.getSurname() + " viva" );
		app.setLog(u);
		if(u.isHost()==true) {
			System.out.println("BIEEN");
		}
		else {
			System.out.println("BIEE33N");
		}
		
	}
	
	
}
