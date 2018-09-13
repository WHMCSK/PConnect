package cn.bgenius.pconnect.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * http://dyccsxg.iteye.com/blog/1908607<br>
 * 时间跳变1582-10-04 的下一天是 1582-10-15<br>
 * <br>
 * Calendar calendar = Calendar.getInstance(); calendar.set(Calendar.YEAR,
 * 1582); calendar.set(Calendar.MONTH, 9); calendar.set(Calendar.DAY_OF_MONTH,
 * 4);
 * 
 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 * System.out.println(format.format(calendar.getTime())); // 1582-10-04
 * calendar.add(Calendar.DAY_OF_MONTH, 1); // 增加一天
 * System.out.println(format.format(calendar.getTime())); // 1582-10-15 #
 * 参见：http
 * ://dlc.sun.com.edgesuite.net/jdk/jdk-api-localizations/jdk-api-zh-cn/builds
 * /latest/html/zh_CN/api/java/util/GregorianCalendar.html
 * 
 * 在JVM启动的时候，加入参数-Duser.timezone=GMT+08<br>
 * GMT, UTC 和 CST 时间的区别<br>
 * GMT 代表格林尼治标准时间；<br>
 * UTC 是比 GMT 更加精确的世界时间标准，在不需要精确到秒的情况下，通常也将GMT和UTC视作等同；<br>
 * CST 可以同时表示中国、美国、澳大利亚、古巴四个国家的标准时间；<br>
 * 参见：<br>
 * http://www.cnblogs.com/sanshi/archive/2009/08/28/1555717.html<br>
 * http://baike.baidu.com/view/42936.htm<br>
 * 
 * @author huangym
 * 
 */
public class DateUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	public static final int PRE_DAY_MILLISECOND = 24 * 60 * 60 * 1000;

	public static final long TIME_INTERVAL_MILLISECOND = 24L * 60 * 60 * 1000 * 92;

	private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
	private static final TimeZone BEIJING_TIME_ZONE = TimeZone.getTimeZone("Asia/Shanghai");
	private static final int UTC_TIME_ZONE_INT = 307;
	private static final int BEIJING_TIME_ZONE_INT = 521;

	public static final String TIME_PATTERN_DEFAULT = "yyyyMMddHHmmssSSS";
	public static final String TIME_PATTERN_DISPLAY_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String TIME_PATTERN_DISPLAY = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_PATTERN_DAY = "yyyy-MM-dd";
	public static final String TIME_PATTERN_SEARCH_DAY = "yyyy MM dd";
	public static final String TIME_PATTERN_DAY_SLASH = "yyyy/MM/dd";

	public static final int[] DAY_OF_MONTH_MAX = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static final long UTC_DATE_LONG_DEFAULT = 19700101L;
	public static final long UTC_DATETIME_LONG_DEFAULT = 19700101000000000L;

	public static final long VALID_MILLISECOND_SHORT = 2L * 60 * 1000;// 2分钟的有效期

	private DateUtil() {
	}

	public static TimeZone getTimeZone(Short timeZoneId) {
		return timeZoneId == null ? null : getTimeZone(timeZoneId.intValue());
	}

	public static TimeZone getTimeZone(int timeZoneIdInt) {
		return TimeZone.getDefault();
	}

	public static TimeZone getTimeZone(String timeZoneId) {
		return getTimeZone(timeZoneId);
	}

	public static TimeZone getUtcTimeZone() {
		return UTC_TIME_ZONE;
	}

	public static TimeZone getBeijinTimeZone() {
		return BEIJING_TIME_ZONE;
	}

	public static int getUtcTimeZoneInt() {
		return UTC_TIME_ZONE_INT;
	}

	public static int getBeijinTimeZoneInt() {
		return BEIJING_TIME_ZONE_INT;
	}

	public static Date toUtcDate(String s, String pattern) {
		return toDate(s, pattern, UTC_TIME_ZONE);
	}

	public static Date toUtcDate(String s) {
		return toDate(s, TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
	}

	/**
	 * 
	 * @param s
	 *            UTC时间，格式:yyyyMMddHHmmssSSS
	 * @return
	 */
	public static Date toUtcDate(long s) {
		return toDate(String.valueOf(s), TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
	}

	/**
	 * 
	 * @param s
	 *            日期字符串
	 * @param pattern
	 *            yyyy-MM-dd<br>
	 *            HH:mm:ss<br>
	 *            yyyy-MM-dd HH:mm:ss<br>
	 *            MM-dd<br>
	 *            HH:mm<br>
	 *            MM-dd HH:mm:ss<br>
	 *            yyyy-MM-dd HH:mm:ss.S<br>
	 *            yyyyMMdd<br>
	 *            yyyyMMddHHmmssS<br>
	 *            yyyyMMddHHmmssSSS<br>
	 *            MM-dd HH:mm<br>
	 *            yyyy-MM-dd HH:mm:ss <br>
	 *            yyyy-MM-dd HH:mm:ss.SSS<br>
	 * @param timeZone
	 *            日期字符串对应的时区
	 * @return 本地日期对象
	 */
	public static Date toDate(String s, String pattern, TimeZone timeZone) {
		if (StrUtil.isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss.SSS";
		}
		// if (LOG.isDebugEnabled()) {
		// LOG.debug("toDate(String s, String pattern, TimeZone timeZone)");
		// LOG.debug("s=" + s);
		// LOG.debug("pattern=" + pattern);
		// LOG.debug("timeZone=" + timeZone);
		// }
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.setTimeZone(timeZone);
			Date result = dateFormat.parse(s);
			return result;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static Date toDate(String s, TimeZone timeZone) {
		return toDate(s, "yyyy-MM-dd HH:mm:ss.SSS", timeZone);
	}

	public static Date toDate(String s, String pattern) {
		return toDate(s, pattern, TimeZone.getDefault());
	}

	public static Date toDate(String s) {
		if (StrUtil.isBlank(s))
			return null;
		return toDate(s, "yyyyMMddHHmmssSSS", TimeZone.getDefault());
	}

	public static Date toDate(long s) {
		if (s == 0)
			return null;
		return toDate(s, "yyyyMMddHHmmssSSS", TimeZone.getDefault());
	}

	/**
	 * 
	 * @param s
	 *            日期字符串, yyyyMMdd|yyyyMMddHHmmssSSS,具体格式根据pattern来决定
	 * @param pattern
	 * @param timeZone
	 *            日期字符串对应的时区
	 * @return
	 */
	public static Date toDate(long s, String pattern, TimeZone timeZone) {
		return toDate(String.valueOf(s), pattern, timeZone);
	}

	/**
	 * 
	 * @param date
	 *            本地日期对象
	 * @param pattern
	 *            yyyy-MM-dd<br>
	 *            HH:mm:ss<br>
	 *            yyyy-MM-dd HH:mm:ss<br>
	 *            MM-dd<br>
	 *            HH:mm<br>
	 *            MM-dd HH:mm:ss<br>
	 *            yyyy-MM-dd HH:mm:ss.S<br>
	 *            yyyyMMdd<br>
	 *            yyyyMMddHHmmssS<br>
	 *            MM-dd HH:mm<br>
	 *            yyyy-MM-dd HH:mm:ss.SSS<br>
	 *            yyyy-MM-dd HH:mm:ss<br>
	 *            yyyy年MM月dd日 HH:mm:ss<br>
	 *            yyyy年MM月dd日 HH:mm<br>
	 * @param timeZone
	 *            格式化后的时间的时区
	 * @return
	 */
	public static String formatDate(Date date, String pattern, TimeZone timeZone) {
		if (StrUtil.isBlank(pattern)) {
			throw new NullPointerException("StringUtil.isBlank(pattern) pattern=" + pattern);
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			if (timeZone != null) {
				dateFormat.setTimeZone(timeZone);
			}
			return dateFormat.format(date);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "";
	}

	public static String formatDate(String dateStr, String dateStrPattern, TimeZone timeZone, String pattern) {
		if (StrUtil.isBlank(pattern)) {
			throw new NullPointerException("StringUtil.isBlank(pattern) pattern=" + pattern);
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateStrPattern);
			if (timeZone != null) {
				dateFormat.setTimeZone(timeZone);
			}
			Date date = dateFormat.parse(dateStr);
			return formatDate(date, pattern, timeZone);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "";
	}

	public static String formatDate(String dateStr, String dateStrPattern, int timeZoneIdInt, String pattern) {
		if (StrUtil.isBlank(pattern)) {
			throw new NullPointerException("StringUtil.isBlank(pattern) pattern=" + pattern);
		}
		try {
			TimeZone timeZone = getTimeZone(timeZoneIdInt);
			return formatDate(dateStr, dateStrPattern, timeZone, pattern);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "";
	}

	/**
	 * 
	 * @param date
	 *            本地日期对象
	 * @param timeZone
	 *            格式化后的时间的时区
	 * @return
	 */
	public static String formatDate(Date date, TimeZone timeZone) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS", timeZone);
	}

	public static String formatDate(Date date, String pattern) {
		return formatDate(date, pattern, TimeZone.getDefault());
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS", TimeZone.getDefault());
	}

	/**
	 * 
	 * @param date
	 *            本地日期对象
	 * @param timeZoneIdInt
	 *            格式化后的时间的时区
	 * @return
	 */
	public static String formatDate(Date date, int timeZoneIdInt) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS", getTimeZone(timeZoneIdInt));
	}

	/**
	 * 
	 * @return yyyyMMddHHmmssSSS
	 */
	public static long getLongDate() {
		return formatDateToLong(new Date());
	}
	
	public static long getLongDate(Date date) {
		return formatDateToLong(date);
	}
	
	public static long formatDateToLong(Date date) {
		return formatDateToHbaseLong(date, BEIJING_TIME_ZONE);
	}

	public static long formatDateToHbaseLong(Date date, TimeZone timeZone) {
		String result = formatDate(date, TIME_PATTERN_DEFAULT, timeZone);
		return MathUtil.toLong(result);
	}

	public static long formatDateToHbaseUtc(Date date) {
		String result = formatDate(date, TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
		return MathUtil.toLong(result);
	}

	public static long formatDateForLongUtc(String date, String pattern) {
		return formatDateToHbaseUtc(toDate(date, pattern));
	}

	public static long formatDateToHbaseUtc() {
		String result = formatDate(new Date(), TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
		return MathUtil.toLong(result);
	}

	/**
	 * 
	 * @param utcLong
	 *            utcLong格式yyyyMMddHHmmssSSS
	 * @param timeZoneIdInt
	 *            格式化后的时间的时区
	 * @return
	 */
	public static String formatDateHbaseUtcToDisplay(long utcLong, int timeZoneIdInt) {
		return formatDateHbaseUtcToDisplay(utcLong, TIME_PATTERN_DEFAULT, timeZoneIdInt, TIME_PATTERN_DISPLAY_DEFAULT);
	}

	/**
	 * 
	 * @param utcLong
	 *            utcLong格式yyyyMMddHHmmssSSS
	 * @param timeZone
	 *            用户设定的时区
	 * @return
	 */
	public static String formatDateHbaseUtcToDisplay(long utcLong, TimeZone timeZone) {
		return formatDateHbaseUtcToDisplay(utcLong, TIME_PATTERN_DEFAULT, timeZone, TIME_PATTERN_DISPLAY_DEFAULT);
	}

	/**
	 * 
	 * @param dateLong
	 *            utcLong格式yyyyMMddHHmmssSSS
	 * @param timeZone
	 *            用户设定的时区
	 * @return
	 */
	public static String formatLongToDisplay(long dateLong, TimeZone timeZone, String pattern) {
		return formatDateToDisplay(dateLong, TIME_PATTERN_DEFAULT, timeZone, pattern);
	}

	/**
	 * 根据用户设定的timeZoneIdInt，把utcLong(yyyyMMddHHmmssSSS)转化为显示的时间
	 * 
	 * @param utcLong
	 *            数据库中保存的大部分都是yyyyMMddHHmmssSSS
	 * @param patternUtc
	 *            utcLong格式yyyyMMddHHmmssSSS
	 * @param timeZone
	 *            用户设定的时区
	 * @param pattern
	 *            转化为显示的时间格式
	 * @return INIT_UTC_DATETIME_LONG||INIT_UTC_DATE_LONG直接返回""
	 */
	public static String formatDateHbaseUtcToDisplay(long utcLong, String patternUtc, TimeZone timeZone, String pattern) {
		if (utcLong == UTC_DATETIME_LONG_DEFAULT || utcLong == UTC_DATE_LONG_DEFAULT) {
			return "";
		}
		if (StrUtil.isBlank(patternUtc)) {
			patternUtc = TIME_PATTERN_DEFAULT;
		}

		if (StrUtil.isBlank(pattern)) {
			pattern = TIME_PATTERN_DISPLAY_DEFAULT;
		}

		Date date = toDate(String.valueOf(utcLong), patternUtc, UTC_TIME_ZONE);
		String result = formatDate(date, pattern, timeZone);
		return result;
	}

	/**
	 * 根据用户设定的timeZoneIdInt，把utcLong(yyyyMMddHHmmssSSS)转化为显示的时间
	 * 
	 * @param dateLong
	 *            数据库中保存的大部分都是yyyyMMddHHmmssSSS
	 * @param patternLong
	 *            dateLong格式yyyyMMddHHmmssSSS
	 * @param timeZone
	 *            用户设定的时区
	 * @param pattern
	 *            转化为显示的时间格式
	 * @return INIT_UTC_DATETIME_LONG||INIT_UTC_DATE_LONG直接返回""
	 */
	public static String formatDateToDisplay(long dateLong, String patternLong, TimeZone timeZone, String pattern) {
		if (dateLong == UTC_DATETIME_LONG_DEFAULT || dateLong == UTC_DATE_LONG_DEFAULT) {
			return "";
		}
		if (StrUtil.isBlank(patternLong)) {
			patternLong = TIME_PATTERN_DEFAULT;
		}

		if (StrUtil.isBlank(pattern)) {
			pattern = TIME_PATTERN_DISPLAY_DEFAULT;
		}

		Date date = toDate(String.valueOf(dateLong), patternLong, BEIJING_TIME_ZONE);
		String result = formatDate(date, pattern, timeZone);
		return result;
	}

	/**
	 * 
	 * @param date
	 *            北京时间
	 * @param pattern
	 *            显示模版
	 * @return
	 */
	public static String formatDateToDisplay(Date date, String pattern) {
		if (StrUtil.isBlank(pattern)) {
			pattern = TIME_PATTERN_DISPLAY_DEFAULT;
		}
		String result = formatDate(date, pattern, BEIJING_TIME_ZONE);
		return result;
	}

	/**
	 * 根据用户设定的timeZoneIdInt，把utcLong(yyyyMMddHHmmssSSS)转化为显示的时间
	 * 
	 * @param utcLong
	 *            数据库中保存的大部分都是yyyyMMddHHmmssSSS
	 * @param patternUtc
	 *            utcLong格式yyyyMMddHHmmssSSS
	 * @param timeZoneIdInt
	 *            用户设定的时区
	 * @param pattern
	 *            转化为显示的时间格式
	 * @return INIT_UTC_DATETIME_LONG||INIT_UTC_DATE_LONG直接返回""
	 */
	public static String formatDateHbaseUtcToDisplay(long utcLong, String patternUtc, int timeZoneIdInt, String pattern) {
		return formatDateHbaseUtcToDisplay(utcLong, patternUtc, getTimeZone(timeZoneIdInt), pattern);
	}

	/**
	 * 
	 * @param dateStr
	 * @param pattern
	 * @param sourceTimeZone
	 * @return
	 */
	public static Date toAnotherTimeZone(String dateStr, String pattern, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
		try {
			Date sourceDate = toDate(dateStr, pattern, sourceTimeZone);
			String targetDateStr = formatDate(sourceDate, pattern, targetTimeZone);
			return toDate(targetDateStr, pattern, targetTimeZone);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static Date toAnotherTimeZone(Date date, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
		try {
			String sourceDateStr = formatDate(date, TIME_PATTERN_DISPLAY_DEFAULT, sourceTimeZone);
			return toAnotherTimeZone(sourceDateStr, TIME_PATTERN_DISPLAY_DEFAULT, sourceTimeZone, targetTimeZone);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static void printTimeZoneAvailableIDs() {
		String[] ids = TimeZone.getAvailableIDs();
		TimeZone timeZone;
		if (LOG.isDebugEnabled()) {
			LOG.debug(TimeZone.getDefault().toString());
		}

		for (int i = 0; i < ids.length; i++) {
			System.out.println("");
			timeZone = TimeZone.getTimeZone(ids[i]);
			if (LOG.isDebugEnabled()) {
				LOG.debug("ids[" + i + "]=" + ids[i] + " timeZone=" + timeZone);
				LOG.debug(timeZone.getDisplayName() + "	" + timeZone.getDisplayName(true, 1, Locale.US));
			}
			if (timeZone.equals(TimeZone.getDefault())) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("printTimeZoneAvailableIDs ****************************i=" + i);
				}
			}
		}
	}

	public static void printAvailableLocales() {
		Locale[] localeArr = Locale.getAvailableLocales();
		if (LOG.isDebugEnabled()) {
			for (int i = 0; i < localeArr.length; i++) {
				LOG.debug("localeArr[" + i + "].getCountry()=" + localeArr[i].getCountry() + " localeArr[i].getDisplayCountry()=" + localeArr[i].getDisplayCountry() + " localeArr[" + i + "]=" + localeArr[i]);
			}
		}
	}

	public static Date nullToDate(Date date) {
		if (date == null) {
			return new Date();
		}
		return date;
	}

	/**
	 * @param date
	 * @param amount
	 * @param unit
	 *            Calendar.YEAR=1 Calendar.MONTH=2 Calendar.DATE=5 <br>
	 *            Calendar.HOUR=10 Calendar.MINUTE=12 Calendar.SECOND=13
	 *            Calendar.MILLISECOND=14
	 * @return
	 */
	public static Date toAnotherDate(Date date, long amount, int unit) {
		if (date == null) {
			throw new NullPointerException("date == null");
		}
		if (unit == 14) {
			return new Date(date.getTime() + amount);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(unit, (int) amount);
		return calendar.getTime();
	}

	public static Date toAnotherDate(long amount, int unit) {
		return toAnotherDate(new Date(), amount, unit);
	}

	/**
	 * 
	 * @param dateAnother
	 * @param birthDate
	 * @return
	 */
	public static int getAge(Date dateAnother, Date birthDate) {
		if (dateAnother == null) {
			dateAnother = new Date();
		}
		if (birthDate == null) {
			birthDate = new Date();
		}
		return getYear(dateAnother) - getYear(birthDate);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */

	public static int getWeek(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(3);
	}

	public static int getWeek() {
		return getWeek(null);
	}

	public static int getYearWeek(Date date) {
		if (date == null) {
			date = new Date();
		}
		int yearInt = getYear(date);
		int weekInt = getWeek(date);
		return yearInt * 100 + weekInt;
	}

	public static int getYearWeek() {
		return getYearWeek(null);
	}

	/**
	 * 返回给定月份的最大日期
	 * 
	 * @param year
	 *            year >0
	 * @param month
	 *            [0,11]
	 * @return
	 */
	public static int getDayOfMonthMax(int year, int month) {
		if (year < 1) {
			throw new IllegalArgumentException("getDayOfMonthMax(int year, int month) year < 1 year=" + year + " month=" + month);
		}
		if (month < 0 || month > 11) {
			throw new IllegalArgumentException("getDayOfMonthMax(int year, int month) month < 0 || month > 11 year=" + year + " month=" + month);
		}

		if (month != 2) {
			return DAY_OF_MONTH_MAX[month];
		} else {// 四年一闰；百年不闰,四百年再闰。
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {// 闰年
				return 29;
			}
			return 28;
		}
	}

	/**
	 * 
	 * @param startTime
	 *            UTC时间，格式:yyyyMMddHHmmssSSS
	 * @param endTime
	 *            UTC时间，格式:yyyyMMddHHmmssSSS
	 * @return 缩小后的endTime
	 */
	public static long shrinkTimeInterval(long startTime, long endTime) {
		return shrinkTimeInterval(startTime, endTime, TIME_INTERVAL_MILLISECOND);
	}

	/**
	 * 自动缩小时间区间
	 * 
	 * @param startTime
	 *            UTC时间，格式:yyyyMMddHHmmssSSS
	 * @param endTime
	 *            UTC时间，格式:yyyyMMddHHmmssSSS
	 * @param timeIntervalMax
	 *            允许的最大毫秒数
	 * @return 缩小后的endTime
	 */
	public static long shrinkTimeInterval(long startTime, long endTime, long timeIntervalMax) {
		if (startTime < endTime) {
			Date startTimeDate = toDate(String.valueOf(startTime), TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
			Date endTimeDate = toDate(String.valueOf(endTime), TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
			if (endTimeDate.getTime() - startTimeDate.getTime() > timeIntervalMax) {
				startTimeDate.setTime(startTimeDate.getTime() + timeIntervalMax);
				long endTimeShrink = MathUtil.toLong(formatDate(startTimeDate, TIME_PATTERN_DEFAULT, UTC_TIME_ZONE));
				if (LOG.isDebugEnabled()) {
					LOG.debug("timeIntervalMax=" + timeIntervalMax);
					LOG.debug("startTime=" + startTime);
					LOG.debug("endTime=" + endTime);
					LOG.debug("endTimeShrink=" + endTimeShrink);
				}
				endTime = endTimeShrink;
			}
		}
		return endTime;
	}

	/**
	 * 判断有效期<br>
	 * expire 期满；文件、协议等（因到期而）失效；断气；逝世
	 * 
	 * @param timeLongUtc
	 *            yyyyMMddHHmmssSSS
	 * @return true=有效期内 false=失效
	 */
	public static boolean expiryDateCheck(String timeLongUtc) {
		return expiryDateCheck(timeLongUtc, VALID_MILLISECOND_SHORT);
	}

	public static boolean expiryDateCheck(String timeLongUtc, long validMillisecond) {
		Date dateUtc = DateUtil.toDate(timeLongUtc, TIME_PATTERN_DEFAULT, getUtcTimeZone());
		Date dateNowLocal = new Date();
		return (dateNowLocal.getTime() - dateUtc.getTime() <= validMillisecond);
	}

	public static String getTimeDurationDisplay(int timeDuration, Locale locale, MessageSource messageSource) {
		StringBuilder resultSb = new StringBuilder();
		String msg = "";
		if (timeDuration <= 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.millisecond", null, locale);
			return "0" + msg;
		}

		int days = timeDuration / (24 * 60 * 60 * 1000);
		if (days > 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.day" + (days > 1 ? "s" : ""), null, locale);
			resultSb.append(days);
			resultSb.append(msg);
		}

		timeDuration = timeDuration - days * (24 * 60 * 60 * 1000);
		int hours = timeDuration / (60 * 60 * 1000);
		if (hours > 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.hour" + (hours > 1 ? "s" : ""), null, locale);
			resultSb.append(hours);
			resultSb.append(msg);
			if (days > 0) {
				return resultSb.toString();
			}
		}

		timeDuration = timeDuration - hours * (60 * 60 * 1000);
		int minutes = timeDuration / (60 * 1000);
		if (minutes > 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.minute" + (minutes > 1 ? "s" : ""), null, locale);
			resultSb.append(minutes);
			resultSb.append(msg);
			if (hours > 0) {
				return resultSb.toString();
			}
		}

		timeDuration = timeDuration - minutes * (60 * 1000);
		int seconds = timeDuration / 1000;
		if (seconds > 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.second" + (seconds > 1 ? "s" : ""), null, locale);
			resultSb.append(seconds);
			resultSb.append(msg);
			if (minutes > 0) {
				return resultSb.toString();
			}
		}

		int milliseconds = timeDuration - seconds * 1000;
		if (milliseconds > 0) {
			msg = MessageSourceUtil.getMessage(messageSource, "common.time.millisecond" + (milliseconds > 1 ? "s" : ""), null, locale);
			resultSb.append(milliseconds);
			resultSb.append(msg);
			if (seconds > 0) {
				return resultSb.toString();
			}
		}
		return resultSb.toString();
	}

	public static long utcDateAdd(long millisecond) {
		Date date = new Date();
		date.setTime(date.getTime() + millisecond);
		String result = formatDate(date, TIME_PATTERN_DEFAULT, UTC_TIME_ZONE);
		return MathUtil.toLong(result);
	}
	public static Date currDateAdd(Date currDate, long millisecond) {
		Date newDate = new Date();
    	if(currDate == null)
    		return null;
    	newDate.setTime(currDate.getTime() + millisecond);
		return newDate;
    }
	/**
	 * calculateRemainTime 计算剩余时间: xx天xx小时xx分钟(xx秒钟)
	 * 
	 * @param expiredDate
	 *            失效时间
	 * @param showSecond
	 *            显示秒钟
	 * @return
	 * @author jonex 2015年4月16日
	 * 
	 */
	public static String calculateRemainTime(Date expiredDate, boolean showSecond) {
		Calendar expiredCalendar = Calendar.getInstance();
		expiredCalendar.setTime(expiredDate);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(new Date());
		StringBuffer buffer = new StringBuffer();
		long expiredTime = expiredCalendar.getTimeInMillis();
		long currentTime = currentCalendar.getTimeInMillis();
		long dif = Math.abs(expiredTime - currentTime);
		int days = new Long(dif / (1000 * 60 * 60 * 24)).intValue();
		if (days != 0) {
			buffer.append(days).append("天");
		}
		int hours = new Long((dif % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)).intValue();
		if (hours != 0) {
			buffer.append(hours).append("小时");
		}
		int minutes = new Long(((dif % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60)) / (1000 * 60)).intValue();
		if (hours != 0) {
			buffer.append(minutes).append("分钟");
		}
		int seconds = new Long(((((dif % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60))) % (1000 * 60)) / 1000).intValue();
		if ((hours == 0 || showSecond) && seconds != 0) {
			buffer.append(seconds).append("秒");
		}
		return buffer.toString();
	}

	/**
	 * 获取今天还剩下多少秒 
	 * @return
	 */
	public static int getMiao() {
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE) + 1, 0, 0, 0);
		return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
	}
	public static int getMonth(Date date){
	 	Calendar cal = Calendar.getInstance();
	 	if(date!=null)cal.setTime(date);
	    int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	public static int getDay(Date date){
	 	Calendar cal = Calendar.getInstance();
	 	if(date!=null)cal.setTime(date);
	    int day = cal.get(Calendar.DATE);
		return day;
	}

	/**
	 * 日期统一格式化，把xxxx年xx月xx日统一为xxxx-xx-xx
	 * @param dateStr
	 * @return
	 */
	public static String dateFormatUnify(String dateStr) {
		if(StrUtil.isBlank(dateStr)) {
			return dateStr;
		}
		String year = "";
		String month = "";
		String date = "";
		StringBuilder dateResult = new StringBuilder();
		int yearIndex = dateStr.indexOf("年");
		int charIndex = dateStr.indexOf("-");
		if(yearIndex > 0) {
			year = dateStr.substring(0, yearIndex);
			dateStr = dateStr.substring(yearIndex + 1);
			int monthIndex = dateStr.indexOf("月");
			if(monthIndex > 0) {
				month = dateStr.substring(0, monthIndex);
				dateStr = dateStr.substring(monthIndex + 1);
			}
			int dateIndex = dateStr.indexOf("日");
			if(dateIndex > 0) {
				date = dateStr.substring(0, dateIndex);
			}
		} else {
			if(charIndex > 0) {
				String[] dateStrs = dateStr.split("-");
				if(dateStrs.length > 2) {
					year = dateStrs[0];
					month = dateStrs[1];
					date = dateStrs[2];
				}
			}
		}
		if(year.trim().length() == 4) {
			dateResult.append(year.trim());
			dateResult.append("-");
		}
		
		if(month.trim().length() == 1) {
			dateResult.append("0" + month.trim());
			dateResult.append("-");
		}else if(month.trim().length() == 2) {
			dateResult.append(month.trim());
			dateResult.append("-");
		}
		
		if(date.trim().length() == 1) {
			dateResult.append("0" + date.trim());
			dateResult.append("-");
		}else if(date.trim().length() == 2) {
			dateResult.append(date.trim());
			dateResult.append("-");
		}
		if(dateResult.charAt(dateResult.length() - 1) == '-') {
			dateResult.deleteCharAt(dateResult.length() - 1);
		}
		return dateResult.toString();
	}
	/**
	 * 得到当前时间的前N天
	 * @param date
	 * @param beforeDay 前几天
	 * @return long yyyyMMddHHmmssSSS 格式
	 */
	public static long getSpecifiedDayBefore(Date date, int beforeDay) { 
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);   
        calendar.set(Calendar.DATE, day - beforeDay);  
        return getLongDate(calendar.getTime());
    }  
	
	public static Date getTodayWithStr(Date date,String hourMinuteSecondStr){
		String formatDate = DateUtil.formatDate(date, DateUtil.TIME_PATTERN_DAY) + hourMinuteSecondStr;
		Date todayMorning = DateUtil.toDate(formatDate,DateUtil.TIME_PATTERN_DISPLAY);
		return todayMorning; 
	}
	
	public static int getMonthDiff(Date date,Date anotherDate){
		int start = getMonth(date);
		int end = getMonth(anotherDate);
		return Math.abs(start-end);
	}
	
	public static Date getLastDayOfMonth(Date date){
		int dayOfMonthMax = getDayOfMonthMax(date.getYear(), date.getMonth());
		int day = getDay(date);
		Date anotherDate = DateUtil.toAnotherDate(date, dayOfMonthMax-day, Calendar.DATE);
		//System.out.println(formatDate(anotherDate,DateUtil.TIME_PATTERN_DISPLAY));
		return anotherDate;
	}
	
	public static Date getLargestMonth(Date date){
		
		int dayOfMonthMax = getDayOfMonthMax(date.getYear(), date.getMonth());
		int day = getDay(date);
		Date anotherDate = DateUtil.toAnotherDate(date, dayOfMonthMax-day, Calendar.DATE);
		String formatDate = formatDate(anotherDate, TIME_PATTERN_DISPLAY);
		formatDate = formatDate.substring(0, 11) + "23:59:59";
		return toDate(formatDate, TIME_PATTERN_DISPLAY);
	}
	
	public static String getMonthDate(Integer month){
        Date date = new Date();
        int month2 =  month - DateUtil.getMonth(date);
        System.out.println(DateUtil.getMonth(date));
        System.out.println(month);
        Date anotherDate = DateUtil.toAnotherDate(date, month2, Calendar.MONTH);
        String formatDate = formatDate(anotherDate, "yyyy-MM");
        return formatDate;
    }
	
	public static Date getMinestMonth(Date date){
		String formatDate = formatDate(date, TIME_PATTERN_DISPLAY);
		formatDate = formatDate.substring(0, 8) + "01 00:00:00";
		return toDate(formatDate, TIME_PATTERN_DISPLAY);
	}
	
	/*public static void main(String[] args){
		Date startTime = new Date();
		Date anotherDate = DateUtil.toAnotherDate(startTime, 2, Calendar.MONTH);
		String str = DateUtil.formatDate(anotherDate, DateUtil.TIME_PATTERN_DISPLAY);
			Date largestMonth = DateUtil.getLargestMonth(anotherDate);
		String str1 = DateUtil.formatDate(largestMonth, DateUtil.TIME_PATTERN_DISPLAY);
			Date endTime = DateUtil.getTodayWithStr(anotherDate, " 23:59:59");
		String str2 = DateUtil.formatDate(endTime, DateUtil.TIME_PATTERN_DISPLAY);
		
		System.out.println(formatDate(getLargestMonth(new Date()),DateUtil.TIME_PATTERN_DISPLAY));
		for (int i = 1; i < 13; i++) {
			Integer tempMonth = i % 3;
			Integer monthDiff = 0;
			if(tempMonth == 1){
				monthDiff = 2;
			}else if(tempMonth == 2){
				monthDiff = 1;
			}
			System.out.println(i + "." + (i + monthDiff));
			
		}
		Date anotherDate = DateUtil.toAnotherDate(startTime, monthDiff, Calendar.MONTH);
		
		System.out.println(formatDate(getMinestMonth(new Date()),DateUtil.TIME_PATTERN_DISPLAY));
		//System.out.println(formatDate(getLastDayOfMonth(new Date()),DateUtil.TIME_PATTERN_DISPLAY));
		Calendar calendar = Calendar.getInstance();  
		Date date = new Date();
		date = toAnotherDate(date, -4, Calendar.DAY_OF_WEEK);
		Date anotherDate1 = toAnotherDate(date, 6, Calendar.DATE);
		System.out.println(formatDate(anotherDate1,DateUtil.TIME_PATTERN_DISPLAY));
		System.out.println(anotherDate1);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		for (int i = 0; i < 5; i++) {
			Date anotherDate = toAnotherDate(date, i, Calendar.DATE);
			System.out.println(formatDate(anotherDate,DateUtil.TIME_PATTERN_DISPLAY));
		}
		System.out.println();
	}*/
	
}