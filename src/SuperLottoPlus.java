/**
 * Model for operations performed
 * 
 * @author Philip Lin
 *
 */
import java.util.Random;

public class SuperLottoPlus{
	
	// Variables:
	final static Random generator = new Random();
	private static int[] ticketArray;
	
	/**
	 * Generates an array of 6 numbers that represent the lotto ticket
	 * First 5 numbers between 1-47, no duplicates
	 * Last number between 1-27 
	 */
	public static int[] generateNumbers(){
		
		ticketArray = new int[6];
		
		for( int i = 0; i < 5; i++ ){
			
			int numberToAdd = generator.nextInt(47) + 1;
			
			if( checkDuplicate(numberToAdd) == false )
				ticketArray[i] = numberToAdd;
			else
				--i;
		}
		
		ticketArray[5] = generator.nextInt(27) + 1;
		
		return ticketArray;
	}
	
	
	/**
	 * Checks if a number is a duplicate of a number already in the lottoNumbers array
	 * @param numberToCheck number to be checked against current array
	 * @return false if a duplicate is found, otherwise true
	 */
	public static boolean checkDuplicate(int numberToCheck){
		
		for( int number : ticketArray ){
			
			if( numberToCheck == number )
				return true;
		}
		
		return false;
	}
	
	/**
	 * Prints a string representation of the lottery ticket
	 * @param array array of lotto numbers
	 * @return string of lotto numbers
	 */
	public static String printTicket(int[] ticketArray){
		
		String ticketString = "";
		for( int i  = 0; i < 5; i++ ){
			
			ticketString += ticketArray[i] + " ";
		}
		
		return ticketString += ("(MEGA: " + ticketArray[5] + ")");
	}

}