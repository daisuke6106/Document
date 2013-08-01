package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Submit extends jp.co.dk.document.html.element.Submit {

	private Form form;
	
	public Submit(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
