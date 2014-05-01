package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class CheckBox extends jp.co.dk.document.html.element.CheckBox {
	
	protected Form form;
	
	public CheckBox(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
