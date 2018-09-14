package IT7320.Assignment2;

import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.util.Random;

import DBConnection.DBConnection;
import ParkingSystem.ParseRego;
import ParkingSystem.Sensor;
import ParkingSystem.StopWatch;
import fine.Fine;
=======
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import dbConnection.DBConnection;
import parkingSystem.ParseRego;
import parkingSystem.Sensor;
>>>>>>> da4399456f5800707b10e4ae469a3519d56cf85a

public class Main {
	/**
	 * Program demo using a randomised parking time which may fall under overstay. The actual implementation assumes an embedded timer running continuously
	 * taking photographic evidence at every fine increment stage and updating the database accordingly
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws FileNotFoundException, ParseException, SQLException {
		String park_id = "1001";
		boolean vacant = true; 		//Assuming park is free
		String rego = new ParseRego().getRego();
		int freeParkingInMinutes = 60;
		Timestamp start;
		Timestamp finish;
		Calendar calStart; 
		long overstay;
		
		boolean sensor = new Sensor().fillPark(vacant);		//Fill park
		
		start = new Timestamp( new Date().getTime() );		//Creates timestamp from system
		System.out.println( "Arrival timestamp: " + start );
		
		calStart = Calendar.getInstance();					//Creation of an instance of the calendar
		calStart.setTimeInMillis(start.getTime());
		calStart.add(Calendar.MINUTE, freeParkingInMinutes);//Incrementing the calendar by the free parking time in minutes

<<<<<<< HEAD
	public static void main(String[] args) throws FileNotFoundException {
		
		boolean vacant = true; 		//Assuming park is free
		String rego = new ParseRego().getRego();

		//long timeParkedStart = new StopWatch().Start(); 	//Start stop watch
		boolean sensor = new Sensor().fillPark(vacant);		//Fill park
		
		long timeParked = new Sensor().timeParked();		//Get randomized long for the time parked( Ranging from 1 minute to 6 hours )  
		long fine = (long) new Fine().increaseFine(timeParked); //Calculate the fine
		int fineAmount = (int) fine;
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
=======
		DBConnection test = new DBConnection();
		test.Insert(park_id, rego, start, sensor);
		
		try {
			System.out.println( "Sleeping for 2 seconds" );
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		finish = new Timestamp( new Date().getTime() + new Sensor().timeParked() );		//Get randomized long for the time parked( Ranging from 1 minute to 6 hours )  
		System.out.println( "Departure timestamp: " + finish );

		sensor = new Sensor().freePark( vacant );
		
		DBConnection.updateParking( park_id, finish, sensor );
		
		sensor = new Sensor().freePark( sensor );				//Freeing park as the car leaves
		
		Timestamp temp = new Timestamp( calStart.getTime().getTime() );
		
		overstay = finish.getTime() - temp.getTime();		//Calculating overstay
		int seconds = (int)overstay / 1000;
		
	    int hours = seconds / 3600;							//Converting to hour minutes and seconds
	    int minutes = ( seconds % 3600 ) / 60;
	    seconds = ( seconds % 3600 ) % 60;
		
	    if( overstay > 0 ) {
			System.out.println("Overstay: " + hours + "h " + minutes + "m " + seconds + "s. Creating fine entry.");
			DBConnection.createFine( park_id, rego, start, finish, overstay );
		}
		
		DBConnection.archive();
>>>>>>> da4399456f5800707b10e4ae469a3519d56cf85a
	}
}