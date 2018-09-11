package cn.bgenius.pconnect.common.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtil {
	private static final Logger LOG = Logger.getLogger(SpringUtil.class);
	private static ApplicationContext applicationContext;

	private SpringUtil() {
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		if (LOG.isInfoEnabled()) {
			LOG.info("setApplicationContext(ApplicationContext applicationContext) start");
		}
		SpringUtil.applicationContext = applicationContext;
		if (LOG.isInfoEnabled()) {
			LOG.info("SpringUtil.applicationContext=" + applicationContext);
			LOG.info("setApplicationContext(ApplicationContext applicationContext) end");
		}
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static Object getBean(String name, Class<?> cls) {
		return applicationContext.getBean(name, cls);
	}

	public static ApplicationContext getApplicationContext(ServletContext servletContext) {
		if (servletContext == null) {
			throw new NullPointerException("servletContext=" + servletContext);
		}
		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	public static ApplicationContext getApplicationContext(ServletConfig servletConfig) {
		if (servletConfig == null) {
			throw new NullPointerException("servletConfig=" + servletConfig);
		}
		return getApplicationContext(servletConfig.getServletContext());
	}

	public static Object getBean(ServletContext servletContext, String name) {
		Object result = null;
		try {
			if (getApplicationContext(servletContext).containsBean(name)) {
				result = getApplicationContext(servletContext).getBean(name);
			}
		} catch (NoSuchBeanDefinitionException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	public static Object getBean(ServletContext servletContext, String name, Class<?> cls) {
		Object result = null;
		try {
			if (getApplicationContext(servletContext).containsBean(name)) {
				result = getApplicationContext(servletContext).getBean(name, cls);
			}
		} catch (NoSuchBeanDefinitionException e) {
			LOG.error(e.getMessage(), e);
		} catch (BeanNotOfRequiredTypeException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

}