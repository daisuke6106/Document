package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;

/**
 * Submitは、HTMLのSubmit要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Submit extends Input{
	
	public Submit(HtmlElement element) {
		super(element);
	}
	
	@Override
	public String getMessage() {
		return "";
	}
}
