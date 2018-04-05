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
	private int debt;
	private static final long serialVersionUID = 1L;
	public List<House> houses= new ArrayList<House>();
	
	/**
	 * @param ccNumber
	 */
	
	public Host(String ccNumber) {
		super(ccNumber);
		this.debt = 0;
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
	public int getDeb() {
		return debt;
	}

	/**
	 * @param deb the deb to set
	 */
	public void setDeb(int deb) {
		this.debt = deb;
	}
		
}
