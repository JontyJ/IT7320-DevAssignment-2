package ParkingSystem;

import java.util.Random;

public class ParseRego {
		
	public String getRego() {
		String rego[] = {"HYEDFR","Y65FDC", "PROGUY", "R3244C", "Y6TTG6", "POI56U", 
						 "LKR324", "G56T55", "MYT5FV", "HY6YY6"};	
		Random rand = new Random();
		int randNum = rand.nextInt(rego.length);
		
		return (rego[randNum]);
	}
}
