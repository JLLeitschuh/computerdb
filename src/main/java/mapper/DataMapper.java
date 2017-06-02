package mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import log.LoggerSing;

public class DataMapper {

	/**
	 * convert data String to Timestamp.
	 * @param strDate .
	 * @return Timestamp from string
	 */

	public static LocalDate convertStringToDate(String strDate) {

		LoggerSing.logger.info("DATE INFO " + strDate);
		if (strDate == null || strDate.equalsIgnoreCase("")) {
			LoggerSing.logger.info("DATE KO ");
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localeDate = LocalDate.parse(strDate, formatter);
			LoggerSing.logger.info("DATE OK");
			return localeDate;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
