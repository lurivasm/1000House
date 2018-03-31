package Exception;

public class HouseOfferException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4331803479990137957L;
	
	
	public String toString() {
		return "Already an offer of the asked type in this house";
	}
}
