package app;
import java.io.*;

import java.util.*;

/**
 * @author Daniel Santo-Tomas daniel.santo-tomas@estudiante.uam.es
 * @author Lucia Rivas Molina lucia.rivas@estudiante.uam.es
 *
 */
public class Host extends Profile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<House> houses= new ArrayList<House>();
	
	/**
	 * @param ccNumber
	 */
	
	public Host(String ccNumber) {
		super(ccNumber);
	}

	/**
	 * @return the houses
	 */
	public List<House> getHouses() {
		return houses;
	}
	
	public void printHouses() {
		for(House h : houses) {
			System.out.println("Location" +  h.getLocation() + "\n" + "Description" + h.getDescription() + "\n");
		}
	}

	
	
	
	
}
