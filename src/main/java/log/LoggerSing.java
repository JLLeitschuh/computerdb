package log;

import org.slf4j.Logger;

import servlet.DashBoardServlet;

public class LoggerSing {

	private Logger logger;
	private static final LoggerSing INSTANCE = new LoggerSing();

	/**
	 * private constructor.
	 */
	private LoggerSing() {
		logger = org.slf4j.LoggerFactory.getLogger(DashBoardServlet.class);
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

	public static LoggerSing getLog() {
		return INSTANCE;
	}

}
