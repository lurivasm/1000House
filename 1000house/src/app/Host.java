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

	/**
	 * @return the deb
	 */
	
}
