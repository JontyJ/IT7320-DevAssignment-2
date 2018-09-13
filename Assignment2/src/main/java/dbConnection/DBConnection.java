package dbConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import fine.Fine;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/parking_space";
	private static final String USER = "root";
	private static final String PASS = "";
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private List parkingSpaceInfo = new ArrayList();
	private Iterator itr = null;
	
	public void displayDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement stmt = con.prepareStatement("Select * from space_1");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("Inside DB");
				parkingSpaceInfo.add(rs.getInt("parking_space_id"));
				parkingSpaceInfo.add(rs.getString("registration_number"));
				parkingSpaceInfo.add(rs.getInt("time_parked"));
				parkingSpaceInfo.add(rs.getBlob("picture_arrival"));
				parkingSpaceInfo.add(rs.getBlob("picture_left"));
				parkingSpaceInfo.add(rs.getInt("occupied"));			
			}
			rs.close();
			stmt.close();
			con.close();
			
			
			for (itr=parkingSpaceInfo.iterator(); itr.hasNext(); )
			{
				System.out.println(itr.next());	
				
			}	
					
		}catch(Exception e) { 
			System.out.println(e); 
		}  
	}

	public void createFine( String number_plate, File photo_evidence ) throws FileNotFoundException {
		try {
			con = DriverManager.getConnection( URL, USER, PASS );
			stmt = con.prepareStatement( "insert into fines ( parking_id, number_plate, 30m_photo_evidence, fine_amount ) values ( ?, ?, ?, ? )" );
			
			stmt.setInt( 1, 1001 );
			stmt.setString( 2, number_plate );
			
			FileInputStream p_evidence = new FileInputStream( photo_evidence );
			stmt.setBinaryStream(3, (InputStream)p_evidence, (int)( photo_evidence.length() ) );
			stmt.setDouble( 4, Fine.retrieveFine() );
			
			stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	public static void updateFine(double amount) {
		
	}
	
	public void Insert(String parking_space, String rego, Timestamp timestamp, boolean sensor) throws FileNotFoundException {

		String success = "yay";
		
		//Getting local images because we assume the camera at the car park will take pictures
		File pictureArrival = new File("C:\\IT7320_Images\\new-zealand-01-plate.jpg");
		FileInputStream isArrival = new FileInputStream(pictureArrival);
		//File pictureLeft = new File("C:\\Users\\Jonty\\Desktop\\Crash_Bandicoot.png");
		//FileInputStream isLeft = new FileInputStream(pictureLeft);
		
		//Connect to the db and insert all the data
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			//PreparedStatement stmt = con.prepareStatement("Insert into space_1(registration_number, picture_arrival, arrival_time, picture_left, occupied)" + "values (?,?,?,?,?)");
			PreparedStatement stmt = con.prepareStatement("Insert into space_1(parking_space_id, registration_number, picture_arrival, arrival_time, occupied)" + "values (?,?,?,?,?)");
			
			stmt.setString(1, parking_space);
			stmt.setString(2, rego);
			stmt.setBinaryStream(3, (InputStream) isArrival, (int)(pictureArrival.length()));
			stmt.setTimestamp(4, timestamp);
			//stmt.setBinaryStream(4, (InputStream) isLeft, (int)(pictureLeft.length()));
			stmt.setBoolean(5, sensor);
			
			stmt.executeUpdate();
			
			//Display yay if data is successfully inserted
			System.out.println(success);
			
		}catch(Exception e){ System.out.println(e);}
	
	}

	public static void archive() {
		// TODO Auto-generated method stub
		
	}
}