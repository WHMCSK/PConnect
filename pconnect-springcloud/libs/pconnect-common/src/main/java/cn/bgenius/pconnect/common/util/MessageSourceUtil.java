package cn.bgenius.pconnect.common.util;

import java.util.Locale;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

public class MessageSourceUtil {
	private static final Logger LOG = LogManager.getLogger(MessageSourceUtil.class);

	private static MessageSource messageSource;

	private MessageSourceUtil() {
	}

	public static MessageSource getMessageSource() {
		return messageSource;
	}

	public static void setMessageSource(MessageSource messageSource) {
		MessageSourceUtil.messageSource = messageSource;
	}

	public static String getMessage(String code, Object[] args, Locale locale) {
		return getMessage(messageSource, code, args, locale);
	}

	public static String getMessage(MessageSource messageSource, String code, Object[] args, Locale locale) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("getMessage(MessageSource messageSource, String code, Object[] args, Locale locale)");
			LOG.debug("code=" + code + " args=" + args + " locale=" + locale);
		}

		String result = "";
		try {
			result = messageSource.getMessage(code, args, "", locale);// defaultMessage=""
		} catch (Exception e) {
			LOG.debug("Exception e code=" + code + " args=" + args + " locale=" + locale, e);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("result=" + result);
		}
		return result;
	}

	public static String getMessageUnknow(MessageSource messageSource, Locale locale) {
		return getMessage(messageSource, "common.unknow", null, locale);
	}
}
