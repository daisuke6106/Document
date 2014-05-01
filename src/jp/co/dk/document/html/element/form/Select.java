package jp.co.dk.document.html.element.form;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.element.Form;

public class Select extends jp.co.dk.document.html.element.Select {

	protected Form form;
	
	public Select(HtmlElement element, Form form) {
		super(element);
		this.form = form ;
	}

}
