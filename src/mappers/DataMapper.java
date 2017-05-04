package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

	/**
	 * convert data String to Timestamp
	 * @param date
	 * @return Timestamp from string
	 */
	public static Timestamp convertToDate(String date){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd"); // your template here	
		java.util.Date data;
		
		
		try {
			data = formatter.parse(date);
			
			if(data.before(formatter.parse("1970-01-01"))){
				return null;
			}
			SimpleDateFormat output = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String dataStr = output.format(data);
					
			return Timestamp.valueOf(dataStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;

		}

	}
	
}
