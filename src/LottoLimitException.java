/**
 * LottoLimitException.java
 * Exception class for lotto ticket quantities less than 1
 * 
 * @author Philip Lin
 *
 */
@SuppressWarnings("serial")
public class LottoLimitException extends Exception{
	
	String message;
	
	/**
	 * Constructor default
	 */
	public LottoLimitException(){
		
		super("Sorry, there is a 1 lotto ticket minimum!");
		message = super.getMessage();
	}
	
	/**
	 * Constructor
	 * @param message message for exception
	 */
	public LottoLimitException(String message){
		
		super(message);
		this.message = message;
	}
	
	/**
	 * Returns message for exception
	 */
	public String getMessage(){
		
		return message;
	}
	
}