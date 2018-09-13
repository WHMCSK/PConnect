package cn.bgenius.pconnect.common.util;



public class ByteAndHexUtil {
	private ByteAndHexUtil() {
	}

	public static byte hexToByte(char hex) {
		int k = 0;
		if (hex >= '0' && hex <= '9')
			k = hex - '0';
		else if (hex >= 'A' && hex <= 'F')
			k = 10 + hex - 'A';
		else if (hex >= 'a' && hex <= 'f')
			k = 10 + hex - 'a';
		else {
			System.out.println("Wrong hex digit!");
		}
		return (byte) (k & 0xFF);
	}

	/**
	 * 功能:这个是什么功能??
	 * 
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static byte hexToByte(char a1, char a2) {
		int k;

		if (a1 >= '0' && a1 <= '9')
			k = (int) (a1 - '0');
		else if (a1 >= 'a' && a1 <= 'f')
			k = (int) (a1 - 'a' + 10);
		else if (a1 >= 'A' && a1 <= 'F')
			k = (int) (a1 - 'A' + 10);
		else
			k = 0;

		k <<= 4;// 高4位

		if (a2 >= '0' && a2 <= '9')
			k += (int) (a2 - '0');
		else if (a2 >= 'a' && a2 <= 'f')
			k += (int) (a2 - 'a' + 10);
		else if (a2 >= 'A' && a2 <= 'F')
			k += (int) (a2 - 'A' + 10);
		else
			k += 0;

		return (byte) (k & 0xFF);
	}

	public static String bytesToHexs(byte[] byteContent) { // 二行制转字符串
		// 字节-->16进制表示
		if (byteContent == null || byteContent.length < 1) {
			return "";
		}
		StringBuffer hexValue = new StringBuffer("");
		int val = 0;
		for (int i = 0; i < byteContent.length; i++) {
			val = byteContent[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString(); // 32位的加密
	}

	public static byte[] hexsToBytes(String str) {
		int len = str.length();
		if (len % 2 != 0) {
			System.out.println("十六进制字符串的长度为" + len + ",不为2的倍数！");
			return null;
		}
		byte[] r = new byte[len / 2];
		int k = 0;
		for (int i = 0; i < str.length() - 1; i += 2) {
			r[k] = hexToByte(str.charAt(i), str.charAt(i + 1));
			k++;
		}
		return r;
	}
}