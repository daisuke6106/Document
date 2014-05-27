package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Imageは、HTMLのImage要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Image extends AbstractMovableHtmlElement{
	
	public Image(HtmlElement element) {
		super(element);
	}
	
	public String getSrc() {
		String src = this.getAttribute(HtmlAttributeName.SRC.getName());
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
