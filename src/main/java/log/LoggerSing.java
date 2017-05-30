package log;

import org.slf4j.Logger;

import servlet.DashboardServlet;

public class LoggerSing {

	private Logger logger;

	/**
	 * private constructor.
	 */
	public LoggerSing(Class c) {
		logger = org.slf4j.LoggerFactory.getLogger(c);
	}

	/**
	 * log info .
	 * @param message .
	 */
	public void logInfo(String message) {
		logger.info(message);
	}

	/**
	 * log error .
	 * @param message .
	 */
	public void logError(String message) {
		logger.error(message);
	}

	

}
