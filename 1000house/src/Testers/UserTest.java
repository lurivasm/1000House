package Testers;
import App.*;
import Exception.*;


public class UserTest {

	public static void main(String[] args) throws WrongProfile,NotHost {
		Application app = new Application("Prueba","Paco","1345");
		User u = new User("Manolo", "Perez", "MnOlO69", "123456", "OD","1234990", app );
		System.out.println(u.getName() + " "+ u.getSurname() + " viva" );
		app.setLog(u);
		u.changeProfile("O");
		app.createHouse("Que bonico", null, "En su sitio");
		u.getHostProfile().printHouses();
		
		
	}
	
	
}
