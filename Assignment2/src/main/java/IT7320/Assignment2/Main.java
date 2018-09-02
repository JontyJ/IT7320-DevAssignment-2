package IT7320.Assignment2;

import java.io.FileNotFoundException;

import DBConnection.DBConnection;
import ParkingSystem.Sensor;
import ParkingSystem.StopWatch;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		boolean vacant = true; 		//Assuming park is free
		String rego = "HYEDFR";		//Assuming license plate number
		
		long timeParkedStart = new StopWatch().Start(); 	//Start stop watch
		boolean sensor = new Sensor().fillPark(vacant);		//Fill park
		
		//Sleeps for two seconds (Assuming that the car is parked for two seconds)
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			
		}
		sensor = new Sensor().freePark(vacant);		//Free park
		int totalTimeParked = (int) new StopWatch().elapsedTime();		//Stop the stop watch and get the elapsed time
		
		//Insert everything into the database
		DBConnection test = new DBConnection();
		test.Insert(rego, totalTimeParked, sensor);
	}
}