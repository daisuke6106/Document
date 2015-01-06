package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormFile extends jp.co.dk.document.html.element.File {
	
	protected Form form;
	
	public FormFile(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
