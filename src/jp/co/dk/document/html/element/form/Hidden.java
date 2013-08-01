package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Hidden extends jp.co.dk.document.html.element.Hidden {

	private Form form;
	
	public Hidden(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
