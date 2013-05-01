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
	
	/**
	 * このHTMLの送信メソッド名称を返却する。
	 * 
	 * @return 送信メソッド名称
	 */
	public String getMethod() {
		return this.method;
	}
	
	/**
	 * 指定された文字列がこのメソッド名称と一致するか判定する。<p/>
	 * 判定する際に文字列の大文字小文字は差異の対象としない。
	 * 
	 * @param method メソッド名称
	 * @return 判定結果(true=一致、false=不一致)
	 */
	public boolean isMethod(String method) {
		if (method != null && this.method.equals(method.toUpperCase()) ) {
			return true;
		} else {
			return false;
		}
	}
	
}
