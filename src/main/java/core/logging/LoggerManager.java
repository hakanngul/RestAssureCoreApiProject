package core.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {
    private static final Logger log = LogManager.getLogger(LogManager.class);

    public void log(String message) {
        log.info(message);
    }

    public void logDebug(String message) {
        log.debug(message);
    }

    public void logError(String message, Throwable t) {
        log.error(message, t);
    }
}
