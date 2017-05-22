package log;

import org.slf4j.Logger;

import servlet.DashBoardServlet;

public class LoggerSing {

	private Logger logger;
	private final static LoggerSing instance = new LoggerSing();

	private LoggerSing() {
		logger = org.slf4j.LoggerFactory.getLogger(DashBoardServlet.class);
	}

	public void logInfo(String message) {
		logger.info(message);
	}

	public void logError(String message) {
		logger.error(message);
	}
	
	public static LoggerSing getLog(){
		return instance;
	}

}
