package cn.bgenius.pconnect.common.model;

public class HttpCallObj {
    private byte[] content;
    private String cookieStr;
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getCookieStr() {
		return cookieStr;
	}
	public void setCookieStr(String cookieStr) {
		this.cookieStr = cookieStr;
	}
}
