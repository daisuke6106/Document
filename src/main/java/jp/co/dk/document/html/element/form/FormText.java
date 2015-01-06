package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormText extends jp.co.dk.document.html.element.Text {
	
	protected Form form;
	
	public FormText(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
