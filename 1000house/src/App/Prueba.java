package App;
import java.io.*;
import java.util.*;

import Exception.InvalidNIF;

public class Prueba {

	public static void main(String[] args) throws Exception,InvalidNIF{
		
	BufferedReader buffer = new BufferedReader(	new InputStreamReader(new FileInputStream("/home/danist/Documentos/UAM/PADSOF/Padsof/1000house/text/users.txt")));
	String linea;
	linea = buffer.readLine();
	List<User> users = new ArrayList<User>(); 
	while((linea = buffer.readLine()) != null) {
		String[] words = linea.split(";");
		String[] fullname = words[2].split(",");
		User u = new User(fullname[1], fullname[0], null,"1" , "OD", null, null);
		users.add(u);
	}
	for(User u : users) {
		try {
			if(u.getNIF().equals("1")) {
				throw new InvalidNIF(u.getName());
			}
		}
		catch(InvalidNIF excep){
			System.out.println(excep);
		}
	}
	buffer.close();

	}

}
