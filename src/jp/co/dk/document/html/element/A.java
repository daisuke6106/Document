package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Aは、HTMLのアンカー要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class A extends AbstractMovableHtmlElement {

	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public A(HtmlElement element) {
		super(element);
	}
	
	/**
	 * このアンカーが示すHREF属性の設定値を取得する。<br/>
	 * 
	 * HREFが設定されていない場合、空文字を返却する。
	 * 
	 * @return href設定値
	 */
	public String getHref() {
		String href = super.getAttribute(HtmlAttributeName.HREF.getName());
		if (href == null) {
			return "";
		} else {
			return href;
		}
	}
	

	@Override
	public String getUrl() {
		return this.getHref();
	}
}
