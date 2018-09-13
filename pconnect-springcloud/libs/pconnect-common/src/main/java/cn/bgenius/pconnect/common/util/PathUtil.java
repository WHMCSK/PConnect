package cn.bgenius.pconnect.common.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 11.适当考虑Java的跨平台性，尽量使用Java提供给你的功能。
 * 比如，换行回车到底是\r\n还是\r或者是\n，不同的系统是不一样的。所以考虑一下使用Java提供的功能吧<br>
 * System类中提供public static String getProperty(String key)方法，其中key可以取如下的值。<br>
 * file.separator 文件分隔符（在 UNIX 系统中是“/”）<br>
 * path.separator 路径分隔符（在 UNIX 系统中是“:”）<br>
 * line.separator 行分隔符（在 UNIX 系统中是“/n”）<br>
 * 
 * filter里面 ServletContext context; public void doFilter(ServletRequest request,
 * ServletResponse response, FilterChain chain) throws IOException,
 * ServletException { HttpServletRequest r = (HttpServletRequest)request; String
 * path = context.getRealPath(r.getRequestURI().toString());
 * System.out.println(path); }
 * 
 * public void init(FilterConfig filterConfig) throws ServletException { // TODO
 * Auto-generated method stub context = filterConfig.getServletContext(); }
 * 
 * listener方式 public void contextInitialized(ServletContextEvent event) {
 * ServletContext ctx = event.getServletContext(); String webPath =
 * ctx.getRealPath("/"); logger.info(">>>>> web path is:" + webPath); }
 * 
 * 
 * 
 * public class CountStartListener extends HttpServlet implements
 * ServletContextListener public class onlineListener implements
 * HttpSessionListener,HttpSessionAttributeListener ServletContext
 * servletContext=HttpSessionEvent.getSession().getServletContext();
 * 
 * @author huangym
 */
public class PathUtil {
	private static final Logger LOG = LoggerFactory.getLogger(PathUtil.class);
	private static String BASE_REAL_PATH = getRealPathByClass();

	private PathUtil() {
	}

	/**
	 * 通过ServletContext上下文获取应用绝对路径
	 * 
	 * @param request
	 * @param s
	 * @return 参数s值来决定,""||null后面有File.separator <br>
	 *         "/abc"后面也没有File.separator<br>
	 *         /abc/ 后面有File.separator
	 */
	public static String getRealPath(HttpServletRequest request, String s) {
		String realPath = getBaseRealPath(request);
		if (StrUtil.isBlank(s)) {
			return realPath;
		}
		s = toPath(s);
		if (!s.startsWith(File.separator) && !realPath.endsWith(File.separator)) {
			realPath = realPath + File.separator;
		}
		return toPath(realPath + s);
	}

	/**
	 * 通过ServletContext上下文获取应用绝对路径
	 * 
	 * @param request
	 * @return 后面有File.separator
	 */
	public static String getBaseRealPath(HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		if (servletContext == null) {
			throw new NullPointerException("servletContext == null");
		}
		String realPath = servletContext.getRealPath("/");
		if (realPath == null) {
			throw new NullPointerException("servletContext.getRealPath(\"\") == null");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("getBaseRealPath(HttpServletRequest request) realPath=" + realPath);
		}
		return toPath(realPath);
	}

	/**
	 * 获得应用的的绝对路径,如果与getBaseRealPath(HttpServletRequest request)<br>
	 * 得到的不一样,应该以getBaseRealPath(HttpServletRequest request)为准<br>
	 * 注意：只有发布路径是标准的才可以正确获得 <br>
	 * 应用基础路径/WEB-INF/classes/... <br>
	 * 应用基础路径/WEB-INF/lib/xxx.jar
	 * 
	 * @return 后面有File.separator
	 */
	public static String getBaseRealPath() {
		return BASE_REAL_PATH;
	}

	/**
	 * 获得应用的的绝对路径,<br>
	 * 注意：只有发布路径是标准的 <br>
	 * 应用基础路径/WEB-INF/classes/... <br>
	 * 应用基础路径/WEB-INF/lib/xxx.jar,才可以正确获得
	 * 
	 * @param s
	 * @return 参数s值来决定,""||null后面有File.separator <br>
	 *         "/abc"后面也没有File.separator<br>
	 *         /abc/ 后面有File.separator
	 */
	public static String getRealPath(String s) {
		String baseRealPath = getBaseRealPath();
		if (StrUtil.isBlank(s)) {
			return baseRealPath;
		}
		s = toPath(s);
		if (!s.startsWith(File.separator) && !baseRealPath.endsWith(File.separator)) {
			baseRealPath = baseRealPath + File.separator;
		}
		return toPath(baseRealPath + s);
	}

	/**
	 * @return 最后有File.separator
	 */
	public static String getClassesRealPath() {
		String classesRealPath = "";
		// /D:/java/tomcat5.5/webapps/Yjx/WEB-INF/lib/Yjx.jar
		classesRealPath = PathUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (LOG.isDebugEnabled()) {
			LOG.debug("PathUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()={}", classesRealPath);
		}

		if (StrUtil.isBlank(classesRealPath)) {
			throw new NullPointerException("classesRealPath==null");
		}
		classesRealPath = classesRealPath.trim();
		while (classesRealPath.indexOf(":") > 0 && (classesRealPath.startsWith("/") || classesRealPath.startsWith("\\"))) {// windows下删除第一个
			// linux下不要删除
			classesRealPath = classesRealPath.substring(1);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("getClassesRealPath() classesRealPath=" + classesRealPath);
		}
		return classesRealPath;
	}

	/**
	 * 
	 * @return 后面有File.separator
	 */
	public static String getRealPathByClass() {
		String realPath = getClassesRealPath();
		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 1 realPath=" + realPath);
		}
		int indexTemp = -1;
		String strTemp = "";
		if (!File.separator.equals("/")) {// 系统的文件路径分隔符 统一转换为 /
			indexTemp = realPath.indexOf(File.separator);
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = realPath.substring(0, indexTemp);
				realPath = strTemp + '/' + realPath.substring(indexTemp + 1);
				indexTemp = realPath.indexOf(File.separator);
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 2 realPath=" + realPath);
		}

		int index = realPath.indexOf("/WEB-INF/");
		if (index > -1) {
			realPath = realPath.substring(0, index);// 获取应用的绝对路径
		}
		while (realPath.indexOf(":") > 0 && realPath.startsWith("/")) {// windows下删除第一个
			// linux下不要删除
			realPath = realPath.substring(1);
		}
		// 替换成 File.separator realPathStr.replaceAll("/",
		// File.separator);File.separator==\ 时，会出现异常
		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 3 realPath=" + realPath);
		}
		if (realPath.toLowerCase().endsWith(".jar")) {
			index = realPath.lastIndexOf('/');
			if (index > -1) {
				realPath = realPath.substring(0, index);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 4 realPath=" + realPath);
		}

		index = realPath.indexOf("/target/classes/");
		if (index > -1) {
			realPath = realPath.substring(0, index + 1);
		} else if (realPath.endsWith("/target/classes")) {
			index = realPath.indexOf("/target/classes");
			if (index > -1) {
				realPath = realPath.substring(0, index + 1);
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 5 realPath=" + realPath);
		}

		if (!File.separator.equals("/")) {// 恢复为系统的文件路径分隔符
			indexTemp = realPath.indexOf('/');
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = realPath.substring(0, indexTemp);
				realPath = strTemp + File.separator + realPath.substring(indexTemp + 1);
				indexTemp = realPath.indexOf('/');
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() 6 realPath=" + realPath);
		}

		if (!realPath.endsWith(File.separator)) {// 去掉最后的 File.separator
			realPath = realPath + File.separator;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("getRealPathByClass() end realPath=" + realPath);
		}
		return realPath;
	}

	/**
	 * 功能：后面没有/
	 * 
	 * @param request
	 * @param scheme
	 */
	public static String getBaseUrl(HttpServletRequest request, String scheme) {
		// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		StringBuilder baseUrlSb = new StringBuilder();
		if (StrUtil.isBlank(scheme)) {
			scheme = request.getScheme();
		}
		int serverPort = request.getServerPort();
		baseUrlSb.append(scheme).append("://").append(request.getServerName());
		if (serverPort != 80) {
			baseUrlSb.append(":").append(serverPort);
		}
		baseUrlSb.append(request.getContextPath());
		return baseUrlSb.toString();
	}

	public static String getBaseUrl(HttpServletRequest request) {
		return getBaseUrl(request, "");
	}

	/**
	 * 返回当前访问页面的完整URL地址，即浏览器当前的完整地址，如：http://192.168.10.190:8080/Yjx/index.jsp
	 * ?ab=dsds&sdds=12<br>
	 * request.getRequestURI()=/Yjx/index.jsp <br>
	 * request.getRequestURL()=http://192.168.10.190:8080/Yjx/index.jsp<br>
	 * request.getQueryString()=ab=dsds&sdds=12
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuilder urlSb = new StringBuilder(request.getRequestURL());
		String queryString = request.getQueryString();
		if (!StrUtil.isBlank(queryString)) {
			urlSb.append("?").append(queryString);
		}
		return urlSb.toString();
	}

	/**
	 * 功能：获取Post的参数串
	 * 
	 * @param request
	 * @return 返回一个数组，值如下： abc=12 def=34 ... hij=xx
	 */
	public static List<String> getRequestParameterList(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		List<String> resultList;
		Set<Entry<String, String[]>> parameterSet = parameterMap.entrySet();
		if (parameterSet == null || parameterSet.isEmpty()) {
			return Collections.emptyList();
		}
		resultList = new ArrayList<String>();
		String value;
		for (Iterator<Entry<String, String[]>> iterator = parameterSet.iterator(); iterator.hasNext();) {
			Entry<String, String[]> entry = iterator.next();
			String key = entry.getKey();
			String[] valueObject = entry.getValue();

			if (valueObject == null) {
				value = "";
			} else  {
				String[] valueArr = valueObject;
				if (valueArr.length == 1) {
					value = valueArr[0];
				} else {
					value = Arrays.deepToString((String[]) valueObject);
				}
			} 
			resultList.add(key + "=" + value);
		}
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	public static List<String> getRequestHeaderList(HttpServletRequest request) {
		Enumeration headerNames = request.getHeaderNames();
		List<String> resultList;
		if (headerNames == null || !headerNames.hasMoreElements()) {
			return Collections.emptyList();
		}
		resultList = new ArrayList<String>();
		while (headerNames != null && headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement().toString();
			String headerValue = "";
			Enumeration header = request.getHeaders(headerName);
			while (header != null && header.hasMoreElements()) {
				headerValue = headerValue + "," + header.nextElement().toString();
			}
			if (headerValue.length() > 0) {
				headerValue = headerValue.substring(1, headerValue.length());
			}
			resultList.add(headerName + "=" + headerValue);
		}
		return resultList;
	}

	public static boolean isEqualsIgnoreCase(String path0, String path1) {
		path0 = toWebPath(path0);
		path1 = toWebPath(path1);
		return isEquals(path0, path1);
	}

	/**
	 * 比较2个路径是不是同一个
	 * 
	 * @param path0
	 * @param path1
	 * @return
	 */
	public static boolean isEquals(String path0, String path1) {
		path0 = toWebPath(path0);
		path1 = toWebPath(path1);
		if (path0.endsWith("/")) {
			path0 = path0.substring(0, path0.length() - 1);
		}
		if (path1.endsWith("/")) {
			path1 = path1.substring(0, path1.length() - 1);
		}
		return path0.equals(path1);
	}

	/**
	 * 功能：把web路径转换为真实路径形式,前后的路径由 s格式来决定
	 * 
	 * @param s
	 *            如：front/person/templet/，不支持../路径
	 * @return 
	 *         front(File.separator)person(File.separator)templet(File.separator)
	 */
	public static String toPath(String s) {
		if (StrUtil.isBlank(s)) {
			return "";
		}
		s = s.trim().replaceAll("\\\\", "/");
		s = s.replaceAll("\\.\\./", "/");
		s = s.replaceAll("//", "/");
		while (s.indexOf("//") > -1) {
			s = s.replaceAll("//", "/");
		}
		int indexTemp = -1;
		String strTemp = "";
		if (File.separatorChar != '/') {// 把 /转化为系统的分割符号
			indexTemp = s.indexOf('/');
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = s.substring(0, indexTemp);
				s = strTemp + File.separatorChar + s.substring(indexTemp + 1);
				indexTemp = s.indexOf('/');
			}
		}
		return s;
	}

	public static String toWebPath(String s) {
		if (StrUtil.isBlank(s)) {
			return "";
		}
		s = s.trim().replaceAll("\\\\", "/");
		s = s.replaceAll("\\.\\./", "/");
		s = s.replaceAll("//", "/");
		while (s.indexOf("//") > -1) {
			s = s.replaceAll("//", "/");
		}
		if (File.separatorChar == '/') {
			return s;
		}

		int indexTemp = -1;
		String strTemp = "";

		if (File.separatorChar != '/') {// 把系统的分割符号转化为 /
			indexTemp = s.indexOf(File.separator);
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = s.substring(0, indexTemp);
				s = strTemp + '/' + s.substring(indexTemp + 1);
				indexTemp = s.indexOf(File.separator);
			}
		}
		return s;
	}

	/**
	 * 该方法仅仅是开发的时候使用
	 * 
	 * @return
	 */
	public static String getEclipseWorkspaceProjectPath() {
		return getEclipseWorkspaceProjectPath(null);
	}

	/**
	 * 该方法仅仅是开发的时候使用
	 * 
	 * @param projectName
	 * @return
	 */
	public static String getEclipseWorkspaceProjectPath(String projectName) {
		String projectPath = toWebPath(getRealPath(""));
		if (LOG.isDebugEnabled()) {
			LOG.debug("getEclipseWorkspaceProjectPath(String projectName)projectName=" + projectName);
			LOG.debug("projectPath=" + projectPath);
		}
		if (StrUtil.isBlank(projectName)) {
			int index = projectPath.indexOf("/WEB-INF/classes/");
			if (index > -1) {
				projectPath = projectPath.substring(0, index);
			}
		} else {
			int index = projectPath.indexOf(projectName);
			if (index > -1) {
				String s = projectPath.substring(0, index);
				projectPath = s + projectName;
			}
		}
		return toPath(projectPath);
	}

	public static String getResourceFilePath(String fileName) {
		URL url = PathUtil.class.getClassLoader().getResource(fileName);
		String filePath = (url != null ? url.getPath() : "");

		if (!(new File(filePath)).isFile()) {
			LOG.info("1 filePath=" + filePath + " is not exists!");
			filePath = PathUtil.getBaseRealPath() + "/WEB-INF/classes/" + fileName;
			if (!(new File(filePath)).isFile()) {
				LOG.info("2 filePath=" + filePath + " is not exists!");
				filePath = PathUtil.getBaseRealPath() + "/WEB-INF/" + fileName;
				if (!(new File(filePath)).isFile()) {
					LOG.info("3 filePath=" + filePath + " is not exists!");
					filePath = PathUtil.getBaseRealPath() + "/" + fileName;
					if (!(new File(filePath)).isFile()) {
						LOG.info("4 filePath=" + filePath + " is not exists!");
						// TODO 该方法确实非常特殊，与开发环境偶合了
						filePath = PathUtil.getBaseRealPath() + "/webapp/WEB-INF/" + fileName;
						if (!(new File(filePath)).isFile()) {
							LOG.info("5 filePath=" + filePath + " is not exists!");
						}
					}
				}
			}
		}

		while (filePath.indexOf(":") > 0 && (filePath.startsWith("/") || filePath.startsWith("\\"))) {// windows下删除第一个
			// linux下不要删除
			filePath = filePath.substring(1);
		}
		filePath = toPath(filePath);
		if (!(new File(filePath)).isFile()) {
			LOG.info("6 filePath=" + filePath + " is not exists!");
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("getResourceFilePath(String fileName) fileName=" + fileName + " filePath=" + filePath);
		}
		return filePath;
	}

	public static String getDownloadPort(HttpServletRequest request) {
		String port = "80"; // 默认80端口
		String dataPlatformUrl = getBaseUrl(request);
		int index = dataPlatformUrl.indexOf(":", dataPlatformUrl.indexOf("//"));
		if (index != -1) {
			String dataPlatformUrlSuf = dataPlatformUrl.substring(index + ":".length());
			port = (dataPlatformUrlSuf.indexOf("/") != -1 ? dataPlatformUrlSuf.substring(0, dataPlatformUrlSuf.indexOf("/")) : dataPlatformUrlSuf);
		}
		return port;
	}

	public static String getDownloadUrl(HttpServletRequest request) {
		String downloadUrl = "";
		String host = request.getHeader("Host");
		if (host.indexOf(":") != -1) {
			host = host.substring(0, host.indexOf(":"));
		}
		if (isInner(host)) {
			downloadUrl = "";
		} else {
			String dataPlatformUrl = getBaseUrl(request);
			int index = dataPlatformUrl.indexOf(":", dataPlatformUrl.indexOf("//"));
			if (index != -1) {
				String dataPlatformUrlPrex = dataPlatformUrl.substring(0, index);
				String dataPlatformUrlSuf = dataPlatformUrl.substring(index + ":".length());
				downloadUrl = dataPlatformUrlPrex + (dataPlatformUrlSuf.indexOf("/") != -1 ? dataPlatformUrlSuf.substring(dataPlatformUrlSuf.indexOf("/")) : "");
			} else {
				downloadUrl = dataPlatformUrl;
			}
		}
		return downloadUrl;
	}

	public static boolean isInner(String ip) {
		String reg = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(ip);
		return matcher.find();
	}

}