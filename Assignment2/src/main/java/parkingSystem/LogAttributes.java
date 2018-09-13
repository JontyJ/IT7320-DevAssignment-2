package parkingSystem;

import java.io.InputStream;
import java.sql.Timestamp;

public class LogAttributes {
	private String parkingID;
	private String numberPlate;
	private InputStream arrival_image;
	private Timestamp arrival;
	private InputStream departure_image;
	private Timestamp departure;
	
	public LogAttributes(String inParkingID, String inNumberPlate, InputStream inArrival_image, Timestamp inArrival, InputStream inDeparture_image,
			Timestamp inDeparture) {
		this.parkingID = inParkingID;
		this.numberPlate = inNumberPlate;
		this.arrival_image = inArrival_image;
		this.arrival = inArrival;
		this.departure_image = inDeparture_image;
		this.departure = inDeparture;
	}

	public String getParkingID() {
		return parkingID;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public InputStream getArrival_image() {
		return arrival_image;
	}

	public Timestamp getArrival() {
		return arrival;
	}

	public InputStream getDeparture_image() {
		return departure_image;
	}

	public Timestamp getDeparture() {
		return departure;
	}
	
}
