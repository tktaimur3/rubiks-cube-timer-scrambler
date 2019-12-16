import java.util.Scanner;

/**
 * @author Christopher Arjune
 *
 * Purpose: A class to seamlessly convert between the two main types of data in the program: String and float
 */
public class TimeConversion {
	
	/**
	 * Method to get a time in seconds to be converted into a string that is formatted
	 * 
	 * @param timeInSec the time in seconds
	 * @return the formatted string time
	 */
	static String secToString(float timeInSec) {
		
		//Splits up individual times
		int numMinutes = (int) timeInSec / 60;
		int numSeconds = (int) timeInSec % 60;
		int numMSeconds = (int) (timeInSec*100)%100;
		String time = "";
		
		
		if(numMinutes > 0){
			for(int i = 0; i < 2 - String.valueOf(numMinutes).length(); i++){
				time += "0";
			}
			
			time += numMinutes + ":";
		}

		for(int i = 0; i < 2 - String.valueOf(numSeconds).length(); i++){
			time += "0";
		}
		
		time += numSeconds + ".";
		
		for(int i = 0; i < 2 - String.valueOf(numMSeconds).length(); i++){
			time += "0";
		}
		
		time += numMSeconds;
		
		return time;
	}
	
	/**
	 * Takes a formatted string and returns a float with the time in seconds
	 * 
	 * @param time The formatted time string
	 * @return the time in seconds
	 */
	static float stringToSec(String time) {
		
		double timeInSec = 0;
		
		
		if(time.length() > 6){
			timeInSec += Double.valueOf(time.substring(0,2)) * 60; //minutes
			timeInSec += Double.valueOf(time.substring(3,5)); //seconds
			timeInSec += Double.valueOf(time.substring(6,8)) / 100;
		}
		else if (time.length() == 5){
			timeInSec += Double.valueOf(time.substring(0,2));
			timeInSec += Double.valueOf(time.substring(3,5)) / 100;
		}
		else {
			timeInSec += Double.valueOf(time.substring(0,2));
			timeInSec += Double.valueOf(time.substring(3,4)) / 100;
		}
				
		return (float) timeInSec;
		
	}
	
	
}
