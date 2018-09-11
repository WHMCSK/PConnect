package cn.bgenius.pconnect.common.adapter;

import cn.bgenius.pconnect.common.model.HttpCallObj;
import cn.bgenius.pconnect.common.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.util.JsonUtil;
import cn.bgenius.pconnect.common.util.URLEncodeUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

public class HttpCallSSL implements HttpCall {
	private static final Logger LOG = LogManager.getLogger(HttpCallSSL.class);
	private ConnectionConfig connConfig;
	private SocketConfig socketConfig;
	private ConnectionSocketFactory connectionSocket;
	private KeyStore trustStore;
	private SSLContext sslContext;
	private LayeredConnectionSocketFactory plainConnectionSocket;
	private Registry<ConnectionSocketFactory> registry;
	private PoolingHttpClientConnectionManager connManager;
	private volatile HttpClient httpClient;
	private volatile BasicCookieStore cookieStore;
	//public static String defaultEncoding = "utf-8";
	private String postDataEncode = "UTF-8";
	private String responseContextEncode = "UTF-8";
	private Map<String, String> reqHeader;
	private RequestConfig config = null;
	private String accessToken;
	{
		connConfig = ConnectionConfig.custom().setCharset(Charset.forName(postDataEncode)).build();
		socketConfig = SocketConfig.custom().setSoTimeout(100000).build();
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create();
		connectionSocket = new PlainConnectionSocketFactory();
		registryBuilder.register("http", connectionSocket);
		// 指定信任密钥存储对象和连接套接字工厂
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new AnyTrustStrategy()).build();
			plainConnectionSocket = new SSLConnectionSocketFactory(sslContext);
			registryBuilder.register("https", plainConnectionSocket);
		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		registry = registryBuilder.build();
		// 设置连接管理器
		connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setDefaultConnectionConfig(connConfig);
		connManager.setDefaultSocketConfig(socketConfig);
		// 指定cookie存储对象
		cookieStore = new BasicCookieStore();
		// 构建客户端
		httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).setConnectionManager(connManager).build();
	}
	public HttpCallSSL() {
	}
	
	public HttpCallSSL(int connetTimeOut,int socketTimeOut) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connetTimeOut).setSocketTimeout(socketTimeOut)
				.build();
		this.config = config;
	}
	
	public HttpCallSSL(RequestConfig config) {
		this.config = config;
	}
	/**
	 * 系统使用为外部提供接口 httpCall
	 * 
	 * @param apiUrl
	 *            url路径
	 * @param method
	 *            提交方式 POST或GET 默认为get提交方式
	 * @return
	 */
	@Override
	public String httpCall(String apiUrl, String method) {
		String result = "";
		if (method != null && "POST".equals(method.toUpperCase())) {
			HttpPost httpPost = getHttpPost();
			result = httpPostCall(apiUrl, httpPost);
			httpPost.releaseConnection();
		} else {
			HttpGet httpGet = getHttpGet();
			result = httpGetCall(apiUrl, httpGet);
			httpGet.releaseConnection();
		}
		return result;
	}
	@Override
	public HttpCallObj getResponse(String apiUrl, String method) throws IOException {
		HttpCallObj result = new HttpCallObj();
		if (method != null && "POST".equals(method.toUpperCase())) {
			HttpPost httpPost = getHttpPost();
			result = responseBytes(getPostResponse(apiUrl, httpPost));
			httpPost.releaseConnection();
		} else {
			HttpGet httpGet = getHttpGet();
			result = responseBytes(getGetResponse(apiUrl, httpGet));
			httpGet.releaseConnection();
		}
		return result;
	}
	
	private HttpResponse getGetResponse(String apiUrl, HttpGet httpGet) throws IOException {
		try{
			httpGet.setURI(new URI(apiUrl));
			if(reqHeader != null) {
				Iterator<String> iterator = reqHeader.keySet().iterator();
				while(iterator.hasNext()) {
					String key = iterator.next();
					httpGet.addHeader(key, reqHeader.get(key));
				}
			}
			return httpClient.execute(httpGet);
		}catch(Exception e){
	    	throw new IllegalStateException(e);
	    }
	}
	
	private HttpResponse getPostResponse(String apiUrl, HttpPost httpPost) throws IOException {
		try{
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String[] urlAndParame = apiUrl.split("\\?");
			String apiUrlNoParame = urlAndParame[0];
			
			Map<String, String> parameMap = StrUtil.splitUrlToParameMap(apiUrl);
			
			nvps = paramsConverter(parameMap);
	
			httpPost.setURI(new URI(apiUrlNoParame));
			if(reqHeader != null) {
				Iterator<String> iterator = reqHeader.keySet().iterator();
				while(iterator.hasNext()) {
					String key = iterator.next();
					httpPost.addHeader(key, reqHeader.get(key));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, postDataEncode));
			return httpClient.execute(httpPost);
		}catch(Exception e){
	    	throw new IllegalStateException(e);
	    }
	}
	
	@Override
	public String httpCall(String apiUrl, String method, Map<String, String> parameMap) {
		String result = "";
		if (parameMap != null) {
			StringBuilder urlParame = new StringBuilder();
			Iterator<String> parameKey = parameMap.keySet().iterator();
			while (parameKey.hasNext()) {
				String parameName = parameKey.next();
				String parameVal = parameMap.get(parameName);
				if (!StrUtil.isBlank(parameVal)) {
					urlParame.append(parameName);
					urlParame.append("=");
					urlParame.append(parameVal);
					urlParame.append("&");
				}
			}
			if (urlParame.length() > 0) {
				if (urlParame.lastIndexOf("&") == urlParame.length() - 1) {
					urlParame.deleteCharAt(urlParame.length() - 1);
				}
				if (apiUrl.indexOf("?") != -1) {
					apiUrl += "&" + urlParame.toString();
				} else {
					apiUrl += "?" + urlParame.toString();
				}
			}
		}
		if (method != null && "POST".equals(method.toUpperCase())) {
			HttpPost httpPost = getHttpPost();
			result = httpPostCall(apiUrl, httpPost);
			httpPost.releaseConnection();
		} else {
			HttpGet httpGet = getHttpGet();
			result = httpGetCall(apiUrl, httpGet);
			httpGet.releaseConnection();
		}
		return result;
	}
	
	@Override
	public HttpCallObj getResponse(String apiUrl, String method, Map<String, String> parameMap) throws IOException {
		HttpCallObj result = new HttpCallObj();
		if (parameMap != null) {
			StringBuilder urlParame = new StringBuilder();
			Iterator<String> parameKey = parameMap.keySet().iterator();
			while (parameKey.hasNext()) {
				String parameName = parameKey.next();
				String parameVal = parameMap.get(parameName);
				if (!StrUtil.isBlank(parameVal)) {
					urlParame.append(parameName);
					urlParame.append("=");
					urlParame.append(parameVal);
					urlParame.append("&");
				}
			}
			if (urlParame.length() > 0) {
				if (urlParame.lastIndexOf("&") == urlParame.length() - 1) {
					urlParame.deleteCharAt(urlParame.length() - 1);
				}
				if (apiUrl.indexOf("?") != -1) {
					apiUrl += "&" + urlParame.toString();
				} else {
					apiUrl += "?" + urlParame.toString();
				}
			}
		}
		if (method != null && "POST".equals(method.toUpperCase())) {
			HttpPost httpPost = getHttpPost();
			result = responseBytes(getPostResponse(apiUrl, httpPost));
			httpPost.releaseConnection();
		} else {
			HttpGet httpGet = getHttpGet();
			result = responseBytes(getGetResponse(apiUrl, httpGet));
			httpGet.releaseConnection();
		}
		return result;
	}
	
	private String httpGetCall(String apiUrl, HttpGet httpGet) {
		HttpResponse response = null;
		try {
			response = getGetResponse(apiUrl, httpGet);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			//String contentType = entity.getContentType().getValue();
			//LOG.info("contentType=" + contentType);
			if (statusCode.indexOf("30") == 0) {
				Header[] hs = response.getHeaders("Location");
				if(hs != null && hs.length > 0){    
		            return httpGetCall(hs[0].toString().replaceAll("Location: ", ""), httpGet);  
		        } else {
		        	try{
			        	JSONObject resultJsonObject = JSONObject.parseObject(StrUtil.readStream(entity.getContent(), responseContextEncode));
						String location = resultJsonObject.getString("location");
						if (location == null) {
							return "";
						}
						return httpGetCall(location, httpGet);
		        	}catch(Exception el) {
		        		
		        	}
		        }
			} else if (statusCode.indexOf("20") == 0) {
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			} else if (statusCode.indexOf("40") == 0) {

				LOG.error("Page: " + apiUrl + " no find");
				return "Page no find";

			} else {
				return "返回状态码：[" + statusCode + "]";
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	private String httpPostCall(String apiUrl, HttpPost httpPost) throws IllegalStateException {
		HttpResponse response = null;
		try {
			response = getPostResponse(apiUrl, httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			//String contentType = entity.getContentType().getValue();
			//LOG.info("contentType=" + contentType);
			
			if (statusCode.indexOf("30") == 0) {
				Header[] hs = response.getHeaders("Location");
				if(hs != null && hs.length > 0){    
		            return httpPostCall(hs[0].toString().replaceAll("Location: ", ""), httpPost);  
		        } else {
		        	try{
			        	JSONObject resultJsonObject = JSONObject.parseObject(StrUtil.readStream(entity.getContent(), responseContextEncode));
						String location = resultJsonObject.getString("location");
						if (location == null) {
							return "";
						}
						return httpPostCall(location, httpPost);
		        	}catch(Exception el){
		        		
		        	}
		        }
			} else if (statusCode.indexOf("20") == 0) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("contentType=" + entity.getContentType().getValue());
				}
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			} else if (statusCode.indexOf("40") == 0) {
				LOG.error("Page: " + apiUrl + " no find");
				return "Page no find";

			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return JsonUtil.getFailJsonObject().toJSONString();
	}

	@Override
	public String uploadFile(String apiUrl, File file, String requestParamInputName) throws IllegalStateException {
		List<File> files = new ArrayList<File>();
		files.add(file);
		return uploadFile(null, null, apiUrl, files, requestParamInputName);
	}
	
	@Override
	public String uploadFile(String apiUrl, List<File> fileList, String requestParamInputName) throws IllegalStateException {
		return uploadFile(null, null, apiUrl, fileList, requestParamInputName);
	}

	/*
	 * @param requestParamInputName 请求参数html input表单名称(如<input name="imageFile"
	 * type="file"/>中的imageFile)。
	 * 
	 * @param entityName 持久化Model名，通常对应数据库表名
	 */
	@Override
	public String uploadFile(String systemName, String entityName, String apiUrl, List<File> fileList, String requestParamInputName) throws IllegalStateException {
		HttpResponse response = null;
		HttpPost httpPost = getHttpPost();
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			String[] urlAndParame = apiUrl.split("\\?");
			String apiUrlNoParame = urlAndParame[0];
			Map<String, String> parameMap = StrUtil.splitUrlToParameMap(apiUrl);
			Iterator<String> parameIterator = parameMap.keySet().iterator();

			httpPost.setURI(new URI(apiUrlNoParame));
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			while (parameIterator.hasNext()) {
				String parameName = parameIterator.next();
				nvps.add(new BasicNameValuePair(parameName, parameMap.get(parameName)));
				multipartEntity.addPart(parameName, new StringBody(parameMap.get(parameName), ContentType.create("text/plain", Consts.UTF_8)));
			}

			if (!StrUtil.isBlank(systemName)) {
				multipartEntity.addPart("systemName", new StringBody(systemName, ContentType.create("text/plain", Consts.UTF_8)));
			}
			if (!StrUtil.isBlank(entityName)) {
				multipartEntity.addPart("entityName", new StringBody(entityName, ContentType.create("text/plain", Consts.UTF_8)));
			}
			// 多文件上传 获取文件数组前台标签name值
			if (!StrUtil.isBlank(requestParamInputName)) {
				multipartEntity.addPart("filesName", new StringBody(requestParamInputName, ContentType.create("text/plain", Consts.UTF_8)));
			}

			if (fileList != null) {
				for (int i = 0, size = fileList.size(); i < size; i++) {
					File file = fileList.get(i);
					multipartEntity.addBinaryBody(requestParamInputName, file, ContentType.DEFAULT_BINARY, file.getName());
				}
			}
			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpClient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				// String contentType = entity.getContentType().getValue();//
				// application/json;charset=ISO-8859-1
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			}  else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return null;
	}
	/*
	 * @param requestParamInputName 请求参数html input表单名称(如<input name="imageFile"
	 * type="file"/>中的imageFile)。
	 * 
	 */
	@Override
	public String uploadFileByByte(String fileUrl, byte[] fileByte, String requestParamInputName) throws IllegalStateException {
		HttpResponse response = null;
		HttpPost httpPost = getHttpPost();
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			String[] urlAndParame = fileUrl.split("\\?");
			String apiUrlNoParame = urlAndParame[0];
			Map<String, String> parameMap = StrUtil.splitUrlToParameMap(fileUrl);
			Iterator<String> parameIterator = parameMap.keySet().iterator();
			httpPost.setURI(new URI(apiUrlNoParame));
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			while (parameIterator.hasNext()) {
				String parameName = parameIterator.next();
				nvps.add(new BasicNameValuePair(parameName, parameMap.get(parameName)));
				multipartEntity.addPart(parameName, new StringBody(parameMap.get(parameName), ContentType.create("text/plain", Consts.UTF_8)));
			}
			// 多文件上传 获取文件数组前台标签name值
			if (!StrUtil.isBlank(requestParamInputName)) {
				multipartEntity.addPart("filesName", new StringBody(requestParamInputName, ContentType.create("text/plain", Consts.UTF_8)));
			}

			multipartEntity.addBinaryBody(requestParamInputName, fileByte);
			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpClient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				// String contentType = entity.getContentType().getValue();//
				// application/json;charset=ISO-8859-1
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			}  else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return null;
	}
	
	@Override
	public byte[] downloadFile(String fileUrl) throws IllegalStateException {
		HttpResponse response = null;
		HttpGet httpGet = new HttpGet(fileUrl);
		try {
			response = httpClient.execute(httpGet);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				HttpEntity entity = response.getEntity();
				InputStream fileStream = entity.getContent();
				ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		        byte[] buff = new byte[1024];  
		        int rc = 0;  
		        while ((rc = fileStream.read(buff, 0, 1024)) > 0) {  
		            swapStream.write(buff, 0, rc);  
		        }  
		        swapStream.flush();
		        return swapStream.toByteArray();
			} else {
				throw new IllegalStateException("返回状态码：[" + statusCode + "]");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}
	
	@Override
	public String uploadFileToWeedFS(String url, InputStream input) throws IllegalStateException {
		HttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			// multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntity.addBinaryBody("fileName", input);
			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpClient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			} else if (statusCode.indexOf("40") == 0) {
				LOG.error("Page: " + url + " no find");
				return "Page no find";
			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return null;
	}

	public String uploadFileToWeedFS(String url, File file) throws IllegalStateException {
		try {
			return uploadFileToWeedFS(url, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteFile(String deleteURL) throws IllegalStateException {
		HttpResponse response = null;
		HttpDelete delete = new HttpDelete(deleteURL);
		try {
			response = httpClient.execute(delete);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				HttpEntity entity = response.getEntity();
				String contentType = entity.getContentType().getValue();
				LOG.info("contentType=" + contentType);
				return StrUtil.readStream(entity.getContent(), responseContextEncode);
			} else if (statusCode.indexOf("40") == 0) {
				LOG.error("Page: " + deleteURL + " no find");
				return "Page no find";
			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			delete.releaseConnection();
		}
		return null;
	}
	/**
	 * 获取当前Http客户端状态中的Cookie
	 * 
	 * @param domain
	 *            作用域
	 * @param port
	 *            端口 传null 默认80
	 * @param path
	 *            Cookie路径 传null 默认"/"
	 * @param useSecure
	 *            Cookie是否采用安全机制 传null 默认false
	 * @return
	 */
	public Map<String, Cookie> getCookie(String domain, Integer port, String path, Boolean useSecure) {
		if (domain == null) {
			return null;
		}
		if (port == null) {
			port = 80;
		}
		if (path == null) {
			path = "/";
		}
		if (useSecure == null) {
			useSecure = false;
		}
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies == null || cookies.isEmpty()) {
			return null;
		}

		CookieOrigin origin = new CookieOrigin(domain, port, path, useSecure);
		DefaultCookieSpec cookieSpec = new DefaultCookieSpec();
		Map<String, Cookie> retVal = new HashMap<String, Cookie>();
		for (Cookie cookie : cookies) {
			if (cookieSpec.match(cookie, origin)) {
				retVal.put(cookie.getName(), cookie);
			}
		}
		return retVal;
	}
	private HttpCallObj responseBytes(HttpResponse httpResponse) {
		InputStream inStream = null;
		try{
			inStream = httpResponse.getEntity().getContent();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
	        byte[] buff = new byte[1024];  
	        int rc = 0;  
	        while ((rc = inStream.read(buff, 0, 1024)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        swapStream.flush();
	        byte[] bytes = swapStream.toByteArray();
	        
	        HttpCallObj httpObj = new HttpCallObj();
			Header[] headers = httpResponse.getHeaders("Set-Cookie");
			String cookieStr = "";
			for(int i = 0; i < headers.length; i++) {
				cookieStr += headers[i].getValue() + ";";
        	}
	        httpObj.setCookieStr(cookieStr);
	        httpObj.setContent(bytes);
	        return httpObj;
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
		return null;
	}
	/**
	 * 批量设置Cookie
	 * 
	 * @param cookies
	 *            cookie键值对图
	 * @param domain
	 *            作用域 不可为空
	 * @param path
	 *            路径 传null默认为"/"
	 * @param useSecure
	 *            是否使用安全机制 传null 默认为false
	 * @return 是否成功设置cookie
	 */
	public boolean setCookie(Map<String, String> cookies, String domain, String path, Boolean useSecure) {
		synchronized (cookieStore) {
			if (domain == null) {
				return false;
			}
			if (path == null) {
				path = "/";
			}
			if (useSecure == null) {
				useSecure = false;
			}
			if (cookies == null || cookies.isEmpty()) {
				return true;
			}
			Set<Entry<String, String>> set = cookies.entrySet();
			String key = null;
			String value = null;
			for (Entry<String, String> entry : set) {
				key = entry.getKey();
				if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
					throw new IllegalArgumentException("cookies key and value both can not be empty");
				}
				BasicClientCookie cookie = new BasicClientCookie(key, value);
				cookie.setDomain(domain);
				cookie.setPath(path);
				cookie.setSecure(useSecure);
				cookieStore.addCookie(cookie);
			}
			return true;
		}
	}

	/**
	 * 设置单个Cookie
	 * 
	 * @param key
	 *            Cookie键
	 * @param value
	 *            Cookie值
	 * @param domain
	 *            作用域 不可为空
	 * @param path
	 *            路径 传null默认为"/"
	 * @param useSecure
	 *            是否使用安全机制 传null 默认为false
	 * @return 是否成功设置cookie
	 */
	public boolean setCookie(String key, String value, String domain, String path, Boolean useSecure) {
		Map<String, String> cookies = new HashMap<String, String>();
		cookies.put(key, value);
		return setCookie(cookies, domain, path, useSecure);
	}

	private static List<NameValuePair> paramsConverter(Map<String, String> params) {
		List<NameValuePair> nvps = new LinkedList<NameValuePair>();
		Set<Entry<String, String>> paramsSet = params.entrySet();
		for (Entry<String, String> paramEntry : paramsSet) {
			nvps.add(new BasicNameValuePair(paramEntry.getKey(), URLEncodeUtils.decodeURL(paramEntry.getValue())));
		}
		return nvps;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public void setPostDataEncode(String postDataEncode) {
		this.postDataEncode = postDataEncode;
	}
	public void setResponseContextEncode(String responseContextEncode) {
		this.responseContextEncode = responseContextEncode;
	}
	@Override
	public void setReqHeader(Map<String, String> reqHeader) {
		this.reqHeader = reqHeader;
	}
	private HttpPost getHttpPost() {
		HttpPost httpPost = new HttpPost();
		if(config != null) {
			httpPost.setConfig(config);  
		}
		return httpPost;
	}
	
	private HttpGet getHttpGet() {
		HttpGet httpGet = new HttpGet();
		if(config != null) {
			httpGet.setConfig(config);  
		}
		return httpGet;
	}
	class AnyTrustStrategy implements TrustStrategy {

		@Override
		public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			return true;
		}

	}
}