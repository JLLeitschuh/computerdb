package mappers;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;



public class DataMapper {

	/**
	 * convert data String to Timestamp
	 * @param date
	 * @return Timestamp from string
	 */
	

	public static LocalDate convertStringToDate(String strDate){

		try{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localeDate =  LocalDate.parse(strDate,formatter);
			
			return localeDate;

		}catch(Exception e ){
			e.printStackTrace();
			
		}
		
		return null;
	}

	public static Date asDate(LocalDate localDate) {
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	  }

}
