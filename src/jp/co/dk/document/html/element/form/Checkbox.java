package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Checkbox extends jp.co.dk.document.html.element.CheckBox {
	
	private Form form;
	
	public Checkbox(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
