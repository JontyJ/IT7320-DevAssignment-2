package tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbConnection.DBConnection;
import fine.Fine;
import parkingSystem.ParseRego;
import parkingSystem.Sensor;

public class TestFineClass {
	private Timestamp timestamp;
	private long freeParking = Time.valueOf( "01:00:00" ).getTime();
	long start;
	long finish;
	long actualParkTime;
	
	@Before
	public void setUp() throws Exception {
		timestamp = new Timestamp( System.currentTimeMillis() );
	}

	@After
	public void tearDown() throws Exception {
		timestamp = null;
	}
	
	@Test
	public void test12Fine() {
		double[] unexpectedFines = { 0.0, 15.0, 21.0, 30.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 1 hours 15 minutes
		finish = timestamp.getTime() + Time.valueOf( "01:15:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
		
	}
	
	@Test
	public void testSensorIsVacant() {
		boolean vacant = true;
		
		boolean sensor = new Sensor().fillPark(vacant); 
									//Fill the car park
		assertThat(sensor, is(false)); 
									//Check that the park is not empty
	}
	
	@Test
	public void testSensorIsNotVacant() {
		boolean vacant = false;
		
		boolean sensor = new Sensor().freePark(vacant); 
									//Free the car park
		assertThat(sensor, is(true)); 
									//Check that the car park is free
	}

	@Test
	public void testRandomRegos() {
		ParseRego testRegos = new ParseRego();
		
		assertNotNull(testRegos.getRego());
	}
	
	@Test
	public void test15Fine() {
		double[] unexpectedFines = { 0.0, 12.0, 21.0, 30.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 1 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "01:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
	}
	

	@Test
	public void test21Fine() {
		double[] unexpectedFines = { 0.0, 12.0, 15.0, 30.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 2 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "02:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
	}
	
	@Test
	public void test30Fine() {
		double[] unexpectedFines = { 0.0, 12.0, 15.0, 21.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 3 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "03:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
	}
	
	@Test
	public void test42Fine() {
		double[] unexpectedFines = { 0.0, 12.0, 15.0, 21.0, 30.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 5 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "05:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
	}
	
	@Test
	public void test57Fine() {
		double[] unexpectedFines = { 0.0, 12.0, 15.0, 21.0, 30.0, 42.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 7 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "07:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFine( actualParkTime ) );
		}
	}
	
	@Test
	public void test12Boundaries() {
		double unexpectedFine1 = 12.0;
		double unexpectedFine2 = 0.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 1 hours 29 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "00:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to no overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "01:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 0 minutes overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ), 1 );
		
		finish = timestamp.getTime() + Time.valueOf( "01:00:01" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 1 second overstay - mean system Y___Y
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
	
	@Test
	public void test15Boundaries() {
		double unexpectedFine1 = 15.0;
		double unexpectedFine2 = 12.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 1 hours 29 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "01:29:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates 29 minutes 59 seconds overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "01:30:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 30 minutes overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
	
	@Test
	public void test21Boundaries() {
		double unexpectedFine1 = 21.0;
		double unexpectedFine2 = 15.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 1 hours 29 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "01:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 59 minutes overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "02:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 1 hour overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
	
	@Test
	public void test30Boundaries() {
		double unexpectedFine1 = 30.0;
		double unexpectedFine2 = 21.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 2 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "02:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 1:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "03:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 2 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
	
	@Test
	public void test42Boundaries() {
		double unexpectedFine1 = 42.0;
		double unexpectedFine2 = 30.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 4 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "04:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 3:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "05:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 4 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
	
	@Test
	public void test57Boundaries() {
		double unexpectedFine1 = 57.0;
		double unexpectedFine2 = 42.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 6 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "06:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 5:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFine( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "07:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 6 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFine( actualParkTime ), 1 );
	}
}
