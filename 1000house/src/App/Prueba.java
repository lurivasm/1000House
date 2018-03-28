package App;

import java.io.*;
import java.util.*;

import Exception.InvalidNIF;

public class Prueba {

	public static void main(String[] args) throws Exception, InvalidNIF {
		Application app = new Application("Prueba");
		app.login("1234", "1234");
		app.logout();	
	}	
}
