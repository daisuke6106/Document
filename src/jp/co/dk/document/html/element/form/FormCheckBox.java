package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormCheckBox extends jp.co.dk.document.html.element.CheckBox {
	
	protected Form form;
	
	public FormCheckBox(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
