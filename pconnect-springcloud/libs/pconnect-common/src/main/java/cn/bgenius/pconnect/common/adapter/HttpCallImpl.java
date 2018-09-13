package cn.bgenius.pconnect.common.adapter;

import cn.bgenius.pconnect.common.model.HttpCallObj;
import cn.bgenius.pconnect.common.util.JsonUtil;
import cn.bgenius.pconnect.common.util.StrUtil;
import cn.bgenius.pconnect.common.util.URLEncodeUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpCallImpl implements HttpCall {

	private static final Logger LOG = LogManager.getLogger(HttpCallImpl.class);
	private CloseableHttpClient httpclient = HttpClients.createDefault();
	private CookieStore cookieStore = new BasicCookieStore();
	private HttpClientContext localContext = HttpClientContext.create();
	private String accessToken;
	private Map<String, String> reqHeader;
	private RequestConfig config = null;
	private String postDataEncode = "UTF-8";
	private String responseContextEncode = "UTF-8";

	public HttpCallImpl() {

	}

	public HttpCallImpl(int connetTimeOut, int socketTimeOut) {
		// 设置连接主机超时和超时时间
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connetTimeOut).setSocketTimeout(socketTimeOut)
				.build();
		this.config = config;
	}

	public HttpCallImpl(RequestConfig config) {
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

	private CloseableHttpResponse getGetResponse(String apiUrl, HttpGet httpGet) throws IllegalStateException {
		localContext.setCookieStore(cookieStore);
		try {
			httpGet.setURI(new URI(apiUrl));
			if (reqHeader != null) {
				Iterator<String> iterator = reqHeader.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					httpGet.addHeader(key, reqHeader.get(key));
				}
			}
			return httpclient.execute(httpGet, localContext);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private CloseableHttpResponse getPostResponse(String apiUrl, HttpPost httpPost) throws IOException {
		localContext.setCookieStore(cookieStore);
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			String[] urlAndParame = apiUrl.split("\\?");
			String apiUrlNoParame = urlAndParame[0];
			Map<String, String> parameMap = StrUtil.splitUrlToParameMap(apiUrl);
			Iterator<String> parameIterator = parameMap.keySet().iterator();

			while (parameIterator.hasNext()) {
				String parameName = parameIterator.next();
				nvps.add(new BasicNameValuePair(parameName, URLEncodeUtils.decodeURL(parameMap.get(parameName))));
			}

			httpPost.setURI(new URI(apiUrlNoParame));
			if (reqHeader != null) {
				Iterator<String> iterator = reqHeader.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					httpPost.addHeader(key, reqHeader.get(key));
				}
			}
			if (urlAndParame.length > 1) {
				urlAndParame[1] = URLEncodeUtils.decodeURL(urlAndParame[1]);
			}

			if (urlAndParame.length > 1 && StrUtil.isEnChar(urlAndParame[1])) {
				httpPost.setEntity(new StringEntity(urlAndParame[1]));
			} else {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, postDataEncode));
			}
			return httpclient.execute(httpPost, localContext);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private String httpGetCall(String apiUrl, HttpGet httpGet) {
		CloseableHttpResponse response = null;
		try {
			response = getGetResponse(apiUrl, httpGet);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			String contentType = entity.getContentType().getValue();
			LOG.info("contentType=" + contentType);
			if (statusCode.indexOf("30") == 0) {
				Header[] hs = response.getHeaders("Location");
				if (hs != null && hs.length > 0) {
					return httpGetCall(hs[0].toString().replaceAll("Location: ", ""), httpGet);
				} else {
					try {
						JSONObject resultJsonObject = JSONObject.parseObject(responseContext(entity.getContent()));
						String location = resultJsonObject.getString("location");
						if (location == null) {
							return "";
						}
						return httpGetCall(location, httpGet);
					} catch (Exception el) {
						return "";
					}
				}
			} else if (statusCode.indexOf("20") == 0) {
				return responseContext(entity.getContent());
			} else if (statusCode.indexOf("40") == 0) {
				LOG.error("Page: " + apiUrl + " no find");
				return "Page no find";

			} else {
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
		return null;
	}

	private String httpPostCall(String apiUrl, HttpPost httpPost) throws IllegalStateException {
		CloseableHttpResponse response = null;
		try {
			response = getPostResponse(apiUrl, httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			// String contentType = entity.getContentType().getValue();
			// LOG.info("contentType=" + contentType);
			if (statusCode.indexOf("30") == 0) {
				Header[] hs = response.getHeaders("Location");
				if (hs != null && hs.length > 0) {
					return httpPostCall(hs[0].toString().replaceAll("Location: ", ""), httpPost);
				} else {
					String location = "";
					try {
						JSONObject resultJsonObject = JSONObject.parseObject(responseContext(entity.getContent()));
						location = resultJsonObject.getString("location");
						if (location == null) {
							return "";
						}
					} catch (Exception el) {
					}
					return httpPostCall(location, httpPost);
				}
			} else if (statusCode.indexOf("20") == 0) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("contentType=" + entity.getContentType().getValue());
				}
				return responseContext(entity.getContent());
			} else if (statusCode.indexOf("40") == 0) {

				LOG.error("Page: " + apiUrl + " no find");
				return "Page no find";

			} else {

				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
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
	public String uploadFile(String apiUrl, List<File> fileList, String requestParamInputName)
			throws IllegalStateException {
		return uploadFile(null, null, apiUrl, fileList, requestParamInputName);
	}

	/*
	 * @param requestParamInputName 请求参数html input表单名称(如<input name="imageFile"
	 * type="file"/>中的imageFile)。
	 * 
	 * @param entityName 持久化Model名，通常对应数据库表名
	 */
	@Override
	public String uploadFile(String systemName, String entityName, String apiUrl, List<File> fileList,
                             String requestParamInputName) throws IllegalStateException {
		// localContext.setCookieStore(cookieStore);
		CloseableHttpResponse response = null;
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
				multipartEntity.addPart(parameName,
						new StringBody(parameMap.get(parameName), ContentType.create("text/plain", Consts.UTF_8)));
			}

			if (!StrUtil.isBlank(systemName)) {
				multipartEntity.addPart("systemName",
						new StringBody(systemName, ContentType.create("text/plain", Consts.UTF_8)));
			}
			if (!StrUtil.isBlank(entityName)) {
				multipartEntity.addPart("entityName",
						new StringBody(entityName, ContentType.create("text/plain", Consts.UTF_8)));
			}
			// 多文件上传 获取文件数组前台标签name值
			if (!StrUtil.isBlank(requestParamInputName)) {
				multipartEntity.addPart("filesName",
						new StringBody(requestParamInputName, ContentType.create("text/plain", Consts.UTF_8)));
			}

			if (fileList != null) {
				for (int i = 0, size = fileList.size(); i < size; i++) {
					File file = fileList.get(i);
					multipartEntity.addBinaryBody(requestParamInputName, file, ContentType.DEFAULT_BINARY,
							URLEncodeUtils.encodeURL(file.getName()));
				}
			}
			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpclient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				// String contentType = entity.getContentType().getValue();//
				// application/json;charset=ISO-8859-1
				return responseContext(entity.getContent());
			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
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
	public String uploadFileByByte(String fileUrl, byte[] fileByte, String requestParamInputName)
			throws IllegalStateException {
		// localContext.setCookieStore(cookieStore);
		CloseableHttpResponse response = null;
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
				multipartEntity.addPart(parameName,
						new StringBody(parameMap.get(parameName), ContentType.create("text/plain", Consts.UTF_8)));
			}
			// 多文件上传 获取文件数组前台标签name值
			if (!StrUtil.isBlank(requestParamInputName)) {
				multipartEntity.addPart("filesName",
						new StringBody(requestParamInputName, ContentType.create("text/plain", Consts.UTF_8)));
			}
			multipartEntity.addBinaryBody(requestParamInputName, fileByte);

			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpclient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				// String contentType = entity.getContentType().getValue();//
				// application/json;charset=ISO-8859-1
				return responseContext(entity.getContent());
			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
			httpPost.releaseConnection();
		}
		return null;
	}

	@Override
	public byte[] downloadFile(String fileUrl) throws IllegalStateException {
		// localContext.setCookieStore(cookieStore);
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(fileUrl);
		try {
			response = httpclient.execute(httpGet);
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
				LOG.error("Page: " + fileUrl + " no find");
				throw new IllegalStateException("Page no find");
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			httpGet.releaseConnection();
		}
		return null;
	}

	@Override
	public String uploadFileToWeedFS(String url, InputStream input) throws IllegalStateException {
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			// multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			multipartEntity.addBinaryBody("fileName", input);
			HttpEntity entity = multipartEntity.build();
			httpPost.setEntity(entity);

			response = httpclient.execute(httpPost);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				entity = response.getEntity();
				return responseContext(entity.getContent());
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
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
			httpPost.releaseConnection();
		}
		return null;
	}

	@Override
	public String uploadFileToWeedFS(String url, File file) throws IllegalStateException {
		try {
			return uploadFileToWeedFS(url, new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteFile(String deleteURL) throws IllegalStateException {
		// localContext.setCookieStore(cookieStore);
		CloseableHttpResponse response = null;
		HttpDelete delete = new HttpDelete(deleteURL);
		try {
			response = httpclient.execute(delete);
			String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
			if (statusCode.indexOf("20") == 0) {
				HttpEntity entity = response.getEntity();
				String contentType = entity.getContentType().getValue();
				LOG.info("contentType=" + contentType);
				return responseContext(entity.getContent());
			} else if (statusCode.indexOf("40") == 0) {
				LOG.error("Page: " + deleteURL + " no find");
				return "Page no find";
			} else {
				LOG.error("返回状态码：[" + statusCode + "]");
				return "返回状态码：[" + statusCode + "]";
			}
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
			delete.releaseConnection();
		}
		return null;
	}

	private HttpCallObj responseBytes(HttpResponse httpResponse) {
		InputStream inStream = null;
		try {
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
			for (int i = 0; i < headers.length; i++) {
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

	private String responseContext(InputStream input) {
		try {
			BufferedReader readContent = new BufferedReader(new InputStreamReader(input, responseContextEncode));
			String outStr = readContent.readLine();
			StringBuilder sb = new StringBuilder();
			while (outStr != null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug(outStr);
				}
				sb.append(outStr);
				sb.append("\r");
				outStr = readContent.readLine();
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
		return "";
	}

	public void closeHttpclient() {
		try {
			httpclient.close();
		} catch (Exception e) {

		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

	}

	public CloseableHttpClient getHttpclient() {
		return httpclient;
	}

	public void setHttpclient(CloseableHttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public HttpClientContext getLocalContext() {
		return localContext;
	}

	public void setLocalContext(HttpClientContext localContext) {
		this.localContext = localContext;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public void setReqHeader(Map<String, String> reqHeader) {
		this.reqHeader = reqHeader;
	}

	public void setPostDataEncode(String postDataEncode) {
		this.postDataEncode = postDataEncode;
	}

	public void setResponseContextEncode(String responseContextEncode) {
		this.responseContextEncode = responseContextEncode;
	}

	private HttpPost getHttpPost() {
		HttpPost httpPost = new HttpPost();
		if (config != null) {
			httpPost.setConfig(config);
		}
		return httpPost;
	}

	private HttpGet getHttpGet() {
		HttpGet httpGet = new HttpGet();
		if (config != null) {
			httpGet.setConfig(config);
		}
		return httpGet;
	}
}
