package cn.bgenius.pconnect.common.util;

public class VersionUtil {
	private static final String SOFT_VERSION_PATTERN = "^([0-9]\\.[0-9]{1,2}\\.[0-9]{1,3})$";

	/**
	 * 检测版本号字符串是否合法
	 * 
	 * @param versionName
	 * @return
	 */
	public static boolean validVersion(String versionName) {
		return StrUtil.isMatcher(versionName, SOFT_VERSION_PATTERN);
	}

	/**
	 * 将版本号信息转换成数据信息
	 * 
	 * @param versionName
	 * @return
	 */
	public static int toVersionInt(String versionName) {
		int result = 0;
		if (!validVersion(versionName)) {
			return 0;
		}
		String[] versionDisplays = versionName.split("\\.");
		result = MathUtil.toInt(versionDisplays[0].substring(0)) * 100000 + MathUtil.toInt(versionDisplays[1]) * 1000 + MathUtil.toInt(versionDisplays[2]);
		return result;
	}

	/**
	 * 将版本数据转换成版本名称
	 * 
	 * @param versionInt
	 * @return
	 */
	public static String toVersionName(int versionInt) {
		if (versionInt <= 0) {
			return null;
		}
		if (String.valueOf(versionInt).length() != 6) { // 判断是不是六位数
			return null;
		}
		String result = "";
		result = String.valueOf(versionInt).substring(0, 1) + "." + String.valueOf(versionInt % 100000 / 1000) + "." + String.valueOf(versionInt % 100000 % 1000);
		return result;
	}
}
