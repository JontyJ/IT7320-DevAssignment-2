package ParkingSystem;

public class Sensor {
	
	//Fill park if the park is empty
	public boolean fillPark( boolean vacant ) {
		if ( vacant = true ) {
			vacant = false;
			return vacant;
		}
		else 
			System.out.println("Park is already in use");
			return vacant;
	}
	
	//Free park if the park is occupied
	public boolean freePark( boolean vacant ) {
		if ( vacant = false ) {
			vacant = true;
			return vacant;
		}
		else
			System.out.println("Park is already free");
			return vacant;		
	}

}
