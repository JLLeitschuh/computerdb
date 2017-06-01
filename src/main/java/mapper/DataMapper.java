package mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataMapper {

	/**
	 * convert data String to Timestamp.
	 * @param strDate .
	 * @return Timestamp from string
	 */

	public static LocalDate convertStringToDate(String strDate) {

		if (strDate.equalsIgnoreCase("")) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localeDate = LocalDate.parse(strDate, formatter);

			return localeDate;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}


}
