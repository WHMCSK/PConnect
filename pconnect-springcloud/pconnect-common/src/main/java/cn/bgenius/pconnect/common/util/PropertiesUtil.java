package cn.bgenius.pconnect.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {
	private static final Logger LOG = LogManager.getLogger(PropertiesUtil.class);

	private PropertiesUtil() {
	}
	
	public static Properties getProperties(URL fileURL) {
		return getProperties(fileURL.getPath());
	}
	public static Properties getProperties(String filePath) {
		Properties properties = new Properties();
		InputStreamReader inputStream = null;
		try {
			inputStream = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
			properties.load(inputStream);
			return properties;
		} catch (Exception e) {
			LOG.error(e, e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public static void save(Properties propertie, URL fileUrl, String description) {
		save(propertie, fileUrl.getPath(), description);
	}
	
	public static void save(Properties propertie, String filePath, String description) {
		if (propertie == null) {
			throw new NullPointerException("propertie == null");
		}
		OutputStreamWriter outputStream = null;
		try {
			outputStream = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
			propertie.store(outputStream, description);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}
}