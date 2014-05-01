package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Password extends jp.co.dk.document.html.element.Password {
	
	protected Form form;
	
	public Password(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
