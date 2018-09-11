package cn.bgenius.pconnect.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestEnvironmentUtil {
	private static ApplicationContext applicationContext;

	static {
		try {
			applicationContext = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
			// applicationContext = new
			// ClassPathXmlApplicationContext("applicationContext-magicBean-test.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TestEnvironmentUtil() {
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
