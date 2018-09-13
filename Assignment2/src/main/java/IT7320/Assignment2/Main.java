package IT7320.Assignment2;

import java.io.FileNotFoundException;
import java.util.Random;

import DBConnection.DBConnection;
import ParkingSystem.ParseRego;
import ParkingSystem.Sensor;
import ParkingSystem.StopWatch;
import fine.Fine;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		boolean vacant = true; 		//Assuming park is free
		String rego = new ParseRego().getRego();

		//long timeParkedStart = new StopWatch().Start(); 	//Start stop watch
		boolean sensor = new Sensor().fillPark(vacant);		//Fill park
		
		long timeParked = new Sensor().timeParked();		//Get randomized long for the time parked( Ranging from 1 minute to 6 hours )  
		//long fine = (long) new Fine().increaseFine(timeParked); //Calculate the fine
		//int fineAmount = (int) fine;
		int timePark = (int) timeParked;
		//Sleeps for two seconds (Assuming that the car is parked for two seconds)
		//try {
	//		Thread.sleep(2000);
	//	} catch (InterruptedException ex) {
			
	//	}
		
		sensor = new Sensor().freePark(vacant);		//Free park
	//	int totalTimeParked = (int) new StopWatch().elapsedTime();		//Stop the stop watch and get the elapsed time
		
		// Insert everything into the database
		DBConnection test = new DBConnection();
		test.Insert(rego, timePark, sensor);
	}
}