package jp.co.dk.document.xml;

import javax.xml.transform.OutputKeys;

/**
 * DocumentPropertyTypeは、文字コードを表すクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum DocumentPropertyType {
	
	SHIFT_JIS(OutputKeys.ENCODING, "Shift_JIS"),
	
	UTF_8(OutputKeys.ENCODING, "UTF-8");
	
	/** encoding文字列 */
	protected String key;
	
	/** 文字コードを表す文字列 */
	protected String type;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のキーと、文字コードを表す文字列を元に文字コードをあらわすインスタンスを生成します。
	 * 
	 * @param key encoding文字列
	 * @param type 文字コードを表す文字列
	 */
	private DocumentPropertyType(String key, String type) {
		this.key  = key;
		this.type = type;
	}
	
	/**
	 * キーを返却します。
	 * @return キー文字列
	 */
	String getKey() {
		return this.key;
	}
	
	/**
	 * 文字コードを表す文字列を返却します。
	 * @return 文字コードを表す文字列
	 */
	String getType() {
		return this.type;
	}
}
