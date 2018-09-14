package fine;

import java.sql.Time;


public class Fine {
	private static final double LESS_THAN_30M = 12.00;
	private static final double LESS_THAN_60M = 15.00;
	private static final double LESS_THAN_120M = 21.00;
	private static final double LESS_THAN_240M = 30.00;
	private static final double LESS_THAN_360M = 42.00;
	private static final double OVER_360M = 57.00;
	
	/**
	 * public static double retrieveFine()
	 * Purpose: Retrieves the initial fine value for DBConnection's createFine method
	 * Input: NULL
	 * Outputs: double
	 * @return
	 */
	public static double retrieveFine() {
		return LESS_THAN_30M;
	}
	
	/**
	 * public static double increaseFine( long time )
	 * Purpose: Receives a calculated over-stay time represented as a long. 30 minutes = 1800000, 60 minutes = 3600000
	 *          120 minutes = 7200000, 240 minutes = 14400000 and 360 minutes = 21600000. Based on the fines retrieved from
	 *          https://www.huttcity.govt.nz/Services/Roads-and-parking/Fees-and-charges/ retrieved on August 2018,the method 
	 *          returns the according value as a double.
	 * Input: time as long
	 * Outputs: fine as double
	 */
	public static double retrieveFine( long time ) {
		if ( time > 0 && time < 1800000 )
			return LESS_THAN_30M;
		else if( time >= 1800000 && time < 3600000  )
			return LESS_THAN_60M;
		else if ( time >= 3600000 && time < 7200000 )
			return LESS_THAN_120M;
		else if ( time >= 7200000 && time < 14400000 )
			return LESS_THAN_240M;
		else if ( time >= 14400000 && time < 21600000 )
			return LESS_THAN_360M;
		else if ( time >= 21600000 )
			return OVER_360M;
		else
			return 0;
	}
	
	/**
	 * public static void main(String[] args)
	 * Purpose: Tests basic functionality of Fine class
	 * @param args
	 */
	public static void main(String[] args) {
		Time start = Time.valueOf("00:00:00");
		Time end = Time.valueOf("00:30:00");
		long thirty = end.getTime() - start.getTime();
		
		end = Time.valueOf("01:00:00");
		long sixty = end.getTime() - start.getTime();
		
		end = Time.valueOf("02:00:00");
		long oneTwenty = end.getTime() - start.getTime();
		
		end = Time.valueOf("04:00:00");
		long twoForty = end.getTime() - start.getTime();
		
		end = Time.valueOf("06:00:00");
		long threeSixty = end.getTime() - start.getTime();
		
		System.out.println( "Long representation of half hour: " + thirty );
		System.out.println( "Long representation of one hour: " + sixty );
		System.out.println( "Long representation of two hours: " + oneTwenty );
		System.out.println( "Long representation of four hours: " + twoForty );
		System.out.println( "Long representation of six hours: " + threeSixty );
		
		System.out.println( "\nTesting return of increaseFine method based on long\nSending 1800000: " + 
                            Fine.retrieveFine(thirty) + "\nSending 3600000: " + Fine.retrieveFine(sixty) +
                            "\nSending 7200000: " + Fine.retrieveFine(oneTwenty) + "\nSending 14400000: " +
                            Fine.retrieveFine(twoForty) + "\nSending 21600000: " + Fine.retrieveFine(threeSixty) );
	}
}