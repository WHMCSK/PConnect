package cn.bgenius.pconnect.common.util;

import org.apache.log4j.Logger;

public class LogUtil {
	private static final Logger LOG = Logger.getLogger(LogUtil.class);

	private LogUtil() {
	}

	public static boolean isDebugEnabled() {
		return LOG.isDebugEnabled();
	}

	public static void debug(String msg) {
		if (msg != null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(msg);
			}
		}
	}

	public static void debug(Object object) {
		if (object != null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(object);
			}
		}
	}

	public static void error(String msg) {
		if (msg != null) {
			LOG.error(msg);
		}
	}

	public static void error(String msg, Throwable throwable) {
		if (msg != null) {
			LOG.error(msg, throwable);
		}
	}
}