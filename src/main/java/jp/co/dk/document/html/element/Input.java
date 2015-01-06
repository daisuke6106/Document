package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Inputは、HTMLのInput要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
class Input extends HtmlElement{
	
	protected String cache_value;
	
	Input(HtmlElement element) {
		super(element);
	}
	
	/**
	 * Valueの取得
	 * 要素に設定されているValueの一覧を取得します。
	 * 
	 * Valueが指定されていなかった場合、空文字が返却されます。
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
	
	/**
	 * Valueの設定
	 * この要素に指定の値を設定します。
	 * 
	 * @param value この要素に指定する値
	 */
	public void setValue(String value) {
		this.cache_value = value;
	}
	
	/**
	 * この要素の無効状態を取得します。<br/>
	 * この要素に"disabled"が設定されていた場合、true、設定されていなかった場合、falseを返却します。
	 * 
	 * @return 無効状態(true=無効、false=無効でない)
	 */
	public boolean isDisabled() {
		return super.hasAttribute(HtmlAttributeName.DISABLED.getName());
	}
	
	/**
	 * 送信メッセージ生成<br/>
	 * 
	 * この要素から送信メッセージを生成します。<br/>
	 * <br/>
	 * 以下の場合、"name1=value1" が文字列として返却されます。<br/>
	 * 例：&lt;input tyoe="text" name="name1" value="value1"/&gt;<br/>
	 * 
	 * 本要素がdisabledの場合、空文字が返却されます。
	 * 
	 * @return 送信用メッセージ
	 */
	protected String getMessage() {
		if (this.isDisabled()) 	return "";
		String name  = this.getName();
		String value = this.getValue();
		if (name.equals("")) return "";
		StringBuilder sb = new StringBuilder(name).append('=').append(value);
		return sb.toString();
	}
}
