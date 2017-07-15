package main;
import java.util.Random;

public class RandomEvents {
	/**
	 * Static Random object for use in the static methods of this class
	 */
private static Random ran = new Random();


	
	
	/**
	 * Static method so it can be ran without creating an object of this class
	 * Returns boolean true (argument)percent of the time
	 * @param percent Takes an integer value of percentage chance the function will return true on
	 * @return returns a boolean. True if random in is greater than 99-percent
	 */
	public static boolean getRandomPercent(int percent){
		percent = 99 - percent;
		if(ran.nextInt(100)>=percent){
			return true;
			
		}
		else
			return false;
	}
	
	
	/**
	 * Static method so no object of this class needed
	 * returns an integer between 0 and max (not including max)
	 * outsources random generation to this class
	 * @param max - integer max of random call
	 * @return - random integer between 0 and max-1
	 */
	public static int getRandomInt(int max){
		return ran.nextInt(max);
		
	}
	
}
