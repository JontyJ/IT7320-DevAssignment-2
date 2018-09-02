package ParkingSystem;

public class StopWatch {
	
	public long Start() {
		long start = System.currentTimeMillis();
		return start;
	}


	public double elapsedTime() {
		long start = Start();
		long end = System.currentTimeMillis();
		return (end - start / 1000.0);
	}
}
