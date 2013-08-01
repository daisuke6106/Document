package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Radio extends jp.co.dk.document.html.element.Radio {
	
	private Form form;
	
	public Radio(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
