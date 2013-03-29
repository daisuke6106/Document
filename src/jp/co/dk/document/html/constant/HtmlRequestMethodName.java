package jp.co.dk.document.html.constant;

/**
 * HttpRequestMethodは、HTMLの送信メソッドを定義する定数クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum HtmlRequestMethodName {
	
	GET("GET"),
	
	POST("POST"),
	
	HEAD("HEAD"),
	
	PUT("PUT"),
	
	DELETE("DELETE"),
	
	TRACE("TRACE");
	
	private final String method;
	
	private HtmlRequestMethodName(String method){
		this.method = method;
	}
	
	public String getMethod() {
		return this.method;
	}
	
	public boolean isMethod(String method) {
		if (method != null && this.method.equals(method.toUpperCase()) ) {
			return true;
		} else {
			return false;
		}
	}
	
}
