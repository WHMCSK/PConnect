package cn.bgenius.pconnect.common.adapter;


import cn.bgenius.pconnect.common.model.HttpCallObj;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HttpCall {

	public String httpCall(String apiUrl, String method);

	public String httpCall(String apiUrl, String method, Map<String, String> parameMap);

	public HttpCallObj getResponse(String apiUrl, String method) throws IOException;

	public HttpCallObj getResponse(String apiUrl, String method, Map<String, String> parameMap) throws IOException;
	
	public String uploadFile(String apiUrl, File file, String requestParamInputName) throws IllegalStateException;
	
	public String uploadFile(String apiUrl, List<File> fileList, String requestParamInputName) throws IllegalStateException;
	
	public void setPostDataEncode(String postDataEncode);
	
	public void setResponseContextEncode(String responseContextEncode);
	
	public String uploadFileByByte(String fileUrl, byte[] fileByte, String requestParamInputName) throws IllegalStateException;
	/*
	 * @param requestParamInputName 请求参数html input表单名称(如<input name="imageFile"
	 * type="file"/>中的imageFile)。
	 * 
	 * @param entityName 持久化Model名，通常对应数据库表名
	 */
	public String uploadFile(String systemName, String entityName, String apiUrl, List<File> fileList,
                             String requestParamInputName) throws IllegalStateException;

	public byte[] downloadFile(String fileUrl) throws IllegalStateException;
	public String uploadFileToWeedFS(String url, InputStream input) throws IllegalStateException;

	public String uploadFileToWeedFS(String url, File file) throws IllegalStateException;
	public String deleteFile(String deleteURL) throws IllegalStateException;
	public void setReqHeader(Map<String, String> reqHeader);
}
