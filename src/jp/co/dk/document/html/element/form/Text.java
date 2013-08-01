package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Text extends jp.co.dk.document.html.element.Text {
	
	private Form form;
	
	public Text(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
