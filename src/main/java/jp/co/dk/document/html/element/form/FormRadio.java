package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormRadio extends jp.co.dk.document.html.element.Radio {
	
	protected Form form;
	
	public FormRadio(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
