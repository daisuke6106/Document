package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormReset extends jp.co.dk.document.html.element.Reset {

	protected Form form;
	
	public FormReset(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
