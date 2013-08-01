package jp.co.dk.document.html.element.selector;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlElementName;

/**
 * Selectは、selectタグを選択するクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Select implements ElementSelector {
	
	@Override
	public boolean judgment(Element element) {
		HtmlElement htmlElement = (HtmlElement)element;
		if (HtmlElementName.SELECT == htmlElement.getElementType()) {
			return true;
		}
		return false;
	}
}
