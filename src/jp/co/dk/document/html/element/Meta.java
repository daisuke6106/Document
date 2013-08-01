package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HttpEquivName;

/**
 * Metaは、HTMLのMeta要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Meta extends HtmlElement {
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public Meta(HtmlElement element) {
		super(element);
	}
	
	/**
	 * HTTPヘッダ名を取得する。<p>
	 * このMETA要素のHTTPヘッダ名を取得します。設定されていない場合、nullを返却します。
	 * 
	 * @return HTTPヘッダ名
	 */
	public HttpEquivName getHttpEquiv() {
		String httpEquiv = super.getAttribute(HtmlAttributeName.HTTP_EQUIV.getName());
		for (HttpEquivName httpEquivName : HttpEquivName.values()) {
			if (httpEquivName.match(httpEquiv)) return httpEquivName;
		}
		return null;
	}
	
	/**
	 * コンテンツを取得する。<p>
	 * このMETA要素のコンテンツを取得します。設定されていない場合、空文字を返却します。
	 * 
	 * @return コンテンツ
	 */
	public String getContent() {
		String content = super.getAttribute(HtmlAttributeName.CONTENT.getName());
		if (content == null || content.equals("")) {
			return "";
		} else {
			return content;
		}
	}
}
