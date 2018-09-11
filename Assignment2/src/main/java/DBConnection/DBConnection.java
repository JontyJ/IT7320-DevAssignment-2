package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	public void createFine() {
		
	}

	public static void updateFine(double amount) {
		// TODO Auto-generated method stub
		
	}
}