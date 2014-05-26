package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;

/**
 * AbstractMovableHtmlElementは、URLなどの外部参照属性を保持する可能なHTML要素が実装する遷移可能要素基底クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public abstract class AbstractMovableHtmlElement extends HtmlElement{
	
	/**
	 * コンストラクタ
	 * 
	 * 遷移可能なHTMLの要素のインスタンスを生成する。
	 * 
	 * @param htmlElement HTMLパーサの要素インスタンス
	 */
	protected AbstractMovableHtmlElement(HtmlElement htmlElement) {
		super(htmlElement);
	}
	
	/**
	 * この要素に定義されているURLを取得します。<p/>
	 * URLが定義されている属性の値を取得します。<br/>
	 * 取得に失敗した場合、空文字を返却します。
	 * 
	 * @return URLを表す文字列
	 */
	public abstract String getUrl();
}
