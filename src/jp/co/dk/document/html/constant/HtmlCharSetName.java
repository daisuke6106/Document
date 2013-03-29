package jp.co.dk.document.html.constant;

/**
 * HtmlCharSetNameは、HTTPヘッダに含まれるコンテンツタイプに設定されているキャラクタセットを表す定数クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum HtmlCharSetName {
	
	/** ISO-8859-1 */
	ISO_8859_1("ISO-8859-1"),
	
	/** ISO_2002_JP */
	ISO_2002_JP("ISO-2022-JP"),
	
	/** Shift_JIS */
	SHIFT_JIS("Shift_JIS"),
	
	/** EUC_JP */
	EUC_JP("EUC-JP"),
	
	/** UTF_8 */
	UTF_8("UTF-8"),
	
	/** UTF_8 */
	UTF_16("UTF-16"),
	;
	
	private String charset;
	
	private HtmlCharSetName(String charset) {
		this.charset = charset;
	}
	
	/**
	 * コンテンツタイプにこのキャラクタセットが含まれるか判定する。
	 * 
	 * @param contentType コンテンツタイプ
	 * @return 判定結果（true=, false=）
	 */
	public boolean isEncording(String contentType) {
		if (contentType == null) return false;
		if (contentType.toUpperCase().indexOf(this.charset.toUpperCase()) != -1) return true;
		return false;
	}
	
	/**
	 * キャラクターセット文字列を取得する。
	 * 
	 * @param contentType コンテンツタイプ
	 * @return 判定結果（true=, false=）
	 */
	public String getEncoding() {
		return this.charset;
	}
	
}
