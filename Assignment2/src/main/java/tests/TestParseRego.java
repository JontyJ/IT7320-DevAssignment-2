package tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import parkingSystem.ParseRego;

public class TestParseRego {

	@Test
	public void testRandomRegos() {
		ParseRego testRegos = new ParseRego();
		
		assertNotNull(testRegos.getRego());
	}
}
