package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormPassword extends jp.co.dk.document.html.element.Password {
	
	protected Form form;
	
	public FormPassword(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
