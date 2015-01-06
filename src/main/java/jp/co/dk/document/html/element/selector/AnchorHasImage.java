package jp.co.dk.document.html.element.selector;

import java.util.List;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlElementName;

/**
 * AnchorHasImageは、イメージタグを保持するアンカータグを選択するクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class AnchorHasImage implements ElementSelector {
	
	@Override
	public boolean judgment(Element element) {
		HtmlElement htmlElement = (HtmlElement)element;
		if (HtmlElementName.A == htmlElement.getElementType() ) {
			List<Element> childElementList = element.getChildElement();
			if (childElementList.size() == 1) {
				HtmlElement childElement = (HtmlElement)childElementList.get(0);
				if (HtmlElementName.IMG == childElement.getElementType()) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
}
