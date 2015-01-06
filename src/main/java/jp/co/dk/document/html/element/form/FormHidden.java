package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class FormHidden extends jp.co.dk.document.html.element.Hidden {

	protected Form form;
	
	public FormHidden(HtmlElement element, Form form) {
		super(element);
		this.form = form;
	}

}
