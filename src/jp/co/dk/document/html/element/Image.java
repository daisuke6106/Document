package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * Imageは、HTMLのImage要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Image extends Input{
	
	public Image(HtmlElement element) {
		super(element);
	}
	
	public String getSrc() {
		String src = this.getAttribute(HtmlAttributeName.SRC.getName());
		if (src == null || src.equals("")) {
			return "";
		} else {
			return src;
		}
	}
}
