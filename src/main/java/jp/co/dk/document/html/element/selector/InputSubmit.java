package jp.co.dk.document.html.element.selector;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.InputTypeName;

/**
 * InputSubmitは、Imageタグを選択するクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class InputSubmit implements ElementSelector {
	
	@Override
	public boolean judgment(Element element) {
		HtmlElement htmlElement = (HtmlElement)element;
		if (HtmlElementName.INPUT == htmlElement.getElementType() 
				&& InputTypeName.SUBMIT.getName().equals(htmlElement.getAttribute(HtmlAttributeName.TYPE.getName()))) {
			return true;
		}
		return false;
	}
}
