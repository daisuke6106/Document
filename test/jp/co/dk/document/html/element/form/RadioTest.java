package jp.co.dk.document.html.element.form;

import org.junit.Test;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.element.Form;

public class RadioTest extends DocumentFoundationTest{
	@Test
	public void constractor() {
		// コンストラクタに引数で渡したFORMが保持されていること。
		try {
			String             tag     = "<input type=\"radio\" name=\"test\" value=\"radiovalue\"/>";
			HtmlElementFactory factory = new HtmlElementFactory();
			Form               form    = new Form(new jp.co.dk.document.html.HtmlElement("<form></form>", factory));
			FormRadio              sut     = new FormRadio(new jp.co.dk.document.html.HtmlElement(tag, factory), form);
			assertThat(sut.form, is(form));
		} catch (DocumentException e) {
			fail(e);
		}
	}
}
