package jp.co.dk.document.html.element.form;

import org.junit.Test;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.element.Form;

public class SelectTest extends DocumentFoundationTest{
	@Test
	public void constractor() {
		// コンストラクタに引数で渡したFORMが保持されていること。
		try {
			String             tag     = "<select name=\"selectname\"><option value=\"value1\">option1</option></select>";
			HtmlElementFactory factory = new HtmlElementFactory();
			Form               form    = new Form(new jp.co.dk.document.html.HtmlElement("<form></form>", factory));
			FormSelect             sut     = new FormSelect(new jp.co.dk.document.html.HtmlElement(tag, factory), form);
			assertThat(sut.form, is(form));
		} catch (DocumentException e) {
			fail(e);
		}
	}
}
