package tests;

import static org.junit.Assert.*;

import java.sql.Time;
import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fine.Fine;

public class TestFineClass {
	private Timestamp timestamp;
	private long freeParking = Time.valueOf( "02:00:00" ).getTime();
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
	public void test15Fine() {
		double[] unexpectedFines = { 12.0, 21.0, 30.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 2 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "02:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFineIncrease( actualParkTime ) );
		}
	}
	
	@Test
	public void test21Fine() {
		double[] unexpectedFines = { 12.0, 15.0, 30.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 3 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "03:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFineIncrease( actualParkTime ) );
		}
	}
	
	@Test
	public void test30Fine() {
		double[] unexpectedFines = { 12.0, 15.0, 21.0, 42.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 4 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "04:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFineIncrease( actualParkTime ) );
		}
	}
	
	@Test
	public void test42Fine() {
		double[] unexpectedFines = { 12.0, 15.0, 21.0, 30.0, 57.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 6 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "06:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFineIncrease( actualParkTime ) );
		}
	}
	
	@Test
	public void test57Fine() {
		double[] unexpectedFines = { 12.0, 15.0, 21.0, 30.0, 42.0 };
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 8 hours 49 minutes
		finish = timestamp.getTime() + Time.valueOf( "08:49:00" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking;
		
		for( int i = 0; i < unexpectedFines.length; i++ ) {
			assertNotEquals( unexpectedFines[ i ], Fine.retrieveFineIncrease( actualParkTime ) );
		}
	}
	
	@Test
	public void test15Boundaries() {
		double unexpectedFine1 = 15.0;
		double unexpectedFine2 = 12.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 2 hours 29 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "02:29:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates 29 minutes 59 seconds overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFineIncrease( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "02:30:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 30 minutes overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFineIncrease( actualParkTime ), 1 );
	}
	
	@Test
	public void test21Boundaries() {
		double unexpectedFine1 = 21.0;
		double unexpectedFine2 = 15.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 2 hours 29 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "02:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 59 minutes overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFineIncrease( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "03:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 1 hour overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFineIncrease( actualParkTime ), 1 );
	}
	
	@Test
	public void test30Boundaries() {
		double unexpectedFine1 = 30.0;
		double unexpectedFine2 = 21.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 3 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "03:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 1:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFineIncrease( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "04:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 2 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFineIncrease( actualParkTime ), 1 );
	}
	
	@Test
	public void test42Boundaries() {
		double unexpectedFine1 = 42.0;
		double unexpectedFine2 = 30.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 5 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "05:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 3:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFineIncrease( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "06:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 4 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFineIncrease( actualParkTime ), 1 );
	}
	
	@Test
	public void test57Boundaries() {
		double unexpectedFine1 = 57.0;
		double unexpectedFine2 = 42.0;
		                            //Convert timestamp to long
		start = timestamp.getTime();
									//Adding 7 hours 59 minutes 59 seconds
		finish = timestamp.getTime() + Time.valueOf( "07:59:59" ).getTime();
									//Calculate actual parking time
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 5:59:59 overstay
		assertNotEquals( unexpectedFine1, Fine.retrieveFineIncrease( actualParkTime ) );
		
		finish = timestamp.getTime() + Time.valueOf( "08:00:00" ).getTime();
		actualParkTime = ( finish - start ) - freeParking; //Evaluates to 6 hours overstay
		assertNotEquals( unexpectedFine2, Fine.retrieveFineIncrease( actualParkTime ), 1 );
	}
}
