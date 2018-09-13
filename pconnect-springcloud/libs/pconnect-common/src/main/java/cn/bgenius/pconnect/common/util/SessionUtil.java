package cn.bgenius.pconnect.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SessionUtil {
	
	private static Map<HttpSession,String> sessionMap = new HashMap<HttpSession,String>();

	public static Map<HttpSession, String> getSessionMap() {
		return sessionMap;
	}


}
