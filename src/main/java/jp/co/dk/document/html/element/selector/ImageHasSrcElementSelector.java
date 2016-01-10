package jp.co.dk.document.html.element.selector;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlElementName;

/**
 * ImageHasSrcElementSelectorは、画像参照先が存在するIMG要素を選択するセレクターです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class ImageHasSrcElementSelector implements ElementSelector {
	@Override
	public boolean judgment(Element element) {
		if (!(element instanceof HtmlElement)) return false;
		HtmlElement htmlElement = (HtmlElement)element;
		if (htmlElement.getElementType() == HtmlElementName.IMG && htmlElement.hasAttribute("src") && (!htmlElement.getAttribute("src").equals(""))) return true;
		return false;
	}
}
