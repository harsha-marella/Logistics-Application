package exception;

public class NoNeighboursException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoNeighboursException(String s){
        System.out.println("No Neighbours Names for facility: "+s );
    }
}
