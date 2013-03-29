package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class File extends jp.co.dk.document.html.element.File {
	
	private Form form;
	
	public File(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
