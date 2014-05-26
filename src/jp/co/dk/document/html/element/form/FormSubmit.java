package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormSubmit extends jp.co.dk.document.html.element.Submit {

	protected Form form;
	
	public FormSubmit(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
