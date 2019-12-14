import java.util.Scanner;

public class TimeConversion {
	
	static String secToString(float timeInSec){
		
		int numMinutes = (int) timeInSec / 60;
		int numSeconds = (int) timeInSec % 60;
		int numMSeconds = (int) ((timeInSec - numMinutes*60 - numSeconds)*100);
		
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
	
	static float stringToSec(String time){
		
		float timeInSec = 0;
		
		
		if(time.length() > 6){
			timeInSec += Float.valueOf(time.substring(0,2)) * 60; //minutes
			timeInSec += Float.valueOf(time.substring(3,5)); //seconds
			timeInSec += Float.valueOf(time.substring(6,8)) / 100;
		}
		else if (time.length() == 5){
			timeInSec += Float.valueOf(time.substring(0,2));
			timeInSec += Float.valueOf(time.substring(3,5)) / 100;
		}
		else {
			timeInSec += Float.valueOf(time.substring(0,2));
			timeInSec += Float.valueOf(time.substring(3,4)) / 100;
		}
		
		return timeInSec;
		
	}
	
	
}
