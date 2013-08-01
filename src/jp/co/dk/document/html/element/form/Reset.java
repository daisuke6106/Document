package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Reset extends jp.co.dk.document.html.element.Reset {

	private Form form;
	
	public Reset(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
