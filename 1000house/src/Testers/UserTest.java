package Testers;
import App.*;
import Exception.wrongProfile;


public class UserTest {

	public static void main(String[] args) throws wrongProfile {
		User u = new User("Manolo", "Perez", "MnOlO69", "123456", "Ow" );
		System.out.println(u.getName() + " "+ u.getSurname() + " viva" );
		
	}
	
	
}
