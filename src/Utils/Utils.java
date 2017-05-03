package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

	public static String convertToDate(String date){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here	
		java.util.Date dateStr;


		try {
			dateStr = formatter.parse(date);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

			return dateDB.toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return "";

		}

	}
}
