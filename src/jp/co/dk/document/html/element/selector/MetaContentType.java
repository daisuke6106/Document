package jp.co.dk.document.html.element.selector;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlElementName;

/**
 * MetaContentTypeは、メタ情報のContent-Typeの要素を選択します。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class MetaContentType implements ElementSelector {
	
	@Override
	public boolean judgment(Element element) {
		HtmlElement htmlElement = (HtmlElement)element;
		if (HtmlElementName.META == htmlElement.getElementType() && htmlElement.hasAttribute("http-equiv")) {
			return true;
		}
		return false;
	}
}
