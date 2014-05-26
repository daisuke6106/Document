package jp.co.dk.document.html.constant;

/**
 * HttpEquivNameは、HTTPヘッダ名称を定義する定数クラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum HttpEquivName {
	/** Content-Type */
	CONTENT_TYPE("Content-Type"),
	
	/** Content-Style-Type */
	CONTENT_STYLE_TYPE("Content-Style-Type"),
	
	/** Content-Script-Type */
	CONTENT_SCRIPT_TYPE("Content-Script-Type"),
	
	/** refresh */
	REFRESH("refresh"),
	;
	
	/** HTTPヘッダ名称 */
	private String httpEquivName;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のHTTPヘッダ名称を元にHTTPヘッダ名称のインスタンスを生成します。
	 * 
	 * @param httpEquivName HTTPヘッダ名称
	 */
	private HttpEquivName(String httpEquivName) {
		this.httpEquivName = httpEquivName;
	}
	
	/**
	 * 指定の文字列がこのHTTPヘッダ名と一致するか判定します。<p>
	 * 
	 * @param httpEquivName HTTPヘッダ名文字列
	 * @return 判定結果（true=一致,false=一致しない）
	 */
	public boolean match(String httpEquivName) {
		if (httpEquivName == null || httpEquivName.equals("")) return false;
		return this.httpEquivName.toUpperCase().equals(httpEquivName.toUpperCase());
	}
}
