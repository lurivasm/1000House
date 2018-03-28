package App;

import java.io.*;
import java.util.*;

import Exception.InvalidNIF;

public class Prueba {

	public static void main(String[] args) throws Exception, InvalidNIF {
		Application app = new Application("Prueba");
		if(app.login("51999111X", "pezEspada") == true) {
		System.out.println("DONE");	
		}
		app.logout();
		
	}	
}
