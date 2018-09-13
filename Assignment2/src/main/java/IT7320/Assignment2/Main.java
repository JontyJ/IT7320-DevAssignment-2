package IT7320.Assignment2;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import dbConnection.DBConnection;
import fine.Fine;
import parkingSystem.ParseRego;
import parkingSystem.Sensor;
import parkingSystem.StopWatch;

public class Main {
	/**
	 * Program demo using a randomised parking time which may fall under overstay. The actual implementation assumes an embedded timer running continuously
	 * taking photographic evidence at every fine increment stage and updating the database accordingly
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws FileNotFoundException, ParseException {
		String park_id = "1001";
		boolean vacant = true; 		//Assuming park is free
		String rego = new ParseRego().getRego();
		int freeParkingInMinutes = 120;
		Timestamp start;
		Timestamp finish;
		Calendar calStart; 
		long overstay;
		
		boolean sensor = new Sensor().fillPark(vacant);		//Fill park
		
		start = new Timestamp( new Date().getTime() );		//Creates timestamp from system
		System.out.println(start);
		
		calStart = Calendar.getInstance();					//Creation of an instance of the calendar
		calStart.setTimeInMillis(start.getTime());
		calStart.add(Calendar.MINUTE, freeParkingInMinutes);//Incrementing the calendar by the free parking time in minutes

		DBConnection test = new DBConnection();
		test.Insert(park_id, rego, start, sensor);
		
		try {
			System.out.println("Sleeping for 2 seconds");
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		finish = new Timestamp( new Date().getTime() + new Sensor().timeParked() );		//Get randomized long for the time parked( Ranging from 1 minute to 6 hours )  
		System.out.println(finish);

		sensor = new Sensor().freePark(sensor);				//Freeing park as the car leaves
		
		start = new Timestamp( calStart.getTime().getTime() );
		
		overstay = finish.getTime() - start.getTime();		//Calculating overstay
		int seconds = (int)overstay / 1000;
		
	    int hours = seconds / 3600;							//Converting to hour minutes and seconds
	    int minutes = ( seconds % 3600 ) / 60;
	    seconds = ( seconds % 3600 ) % 60;
		
	    if( overstay > 0 ) {
			System.out.println("Overstay: " + hours + "h " + minutes + "m " + seconds + "s. Creating fine entry.");
//			//DBConnection.createFine();
		}
//		
		if( overstay >= 1800000 ) {
			System.out.println( "Overstay time is greater then 30 minutes, updating fine db entry." );
//			//DBConnection.updateFine( Fine.retrieveFineIncrease( actualTimeParked - freeParking ) );
		}
		
		DBConnection.archive();
	}
}