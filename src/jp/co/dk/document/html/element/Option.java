package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Optionは、HTMLのOption要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Option extends HtmlElement{
	
	/** Valueのキャッシュ */
	protected String cache_value;
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public Option(HtmlElement element) {
		super(element);
	}
		
	/**
	 * Valueの取得
	 * 要素に設定されているValueの一覧を取得します。
	 * 
	 * Valueが指定されていなかった場合、空のリストインスタンスが返却されます。
	 * 
	 * @return 要素に指定されているValue
	 */
	public String getValue() {
		if (this.cache_value == null) {
			String getValue = super.getAttribute(HtmlAttributeName.VALUE.getName());
			if (getValue == null) {
				getValue = "";
			}
			this.cache_value = getValue;
		}
		return this.cache_value;
	}
}
