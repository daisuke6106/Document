package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Scriptは、HTMLのScript要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Script extends AbstractMovableHtmlElement {

	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public Script(HtmlElement element) {
		super(element);
	}
	
	/**
	 * このアンカーが示すHREF属性の設定値を取得する。<br/>
	 * 
	 * SRCが設定されていない場合、空文字を返却する。
	 * 
	 * @return src設定値
	 */
	public String getSrc() {
		String src = super.getAttribute(HtmlAttributeName.SRC.getName());
		if (src == null) {
			return "";
		} else {
			return src;
		}
	}

	@Override
	public String getUrl() {
		return this.getSrc();
	}
	
}
