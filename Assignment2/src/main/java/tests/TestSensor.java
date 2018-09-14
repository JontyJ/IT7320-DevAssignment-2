package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import parkingSystem.Sensor;

public class TestSensor {
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
}
