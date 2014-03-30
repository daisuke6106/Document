package jp.co.dk.document.html.element;

import static org.junit.Assert.*;

import java.net.URL;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.message.DocumentMessage;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class ActionTest extends DocumentFoundationTest{
	
	@Test
	public void getUrl() {
		// Formタグにアクションが設定されていない場合、例外を返却すること。
		
		
		try {
			Action action = new Action(form);
			action1.getURL();
			fail();
		} catch (HtmlDocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_AN_INVALID_URL_WAS_SPECIFIED) fail(e);
		}
		
		// Formタグにアクションが設定されていない場合、例外を返却すること。
		new NonStrictExpectations(){{
				form.getAttribute(HtmlAttributeName.ACTION.getName());
				result = "";
		}};
		Action action2 = new Action(form);
		try {
			action2.getURL();
			fail();
		} catch (HtmlDocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_AN_INVALID_URL_WAS_SPECIFIED) fail(e);
		}
		
		// Formタグにアクションが設定されていない場合、正常に値を取得できること。
		new NonStrictExpectations(){{
				form.getAttribute(HtmlAttributeName.ACTION.getName());
				result = "http://www.google.com";
		}};
		Action action3 = new Action(form);
		try {
			URL url = action3.getURL();
			assertEquals(url.toString(), "http://www.google.com");
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void toStringTest() {
		try {
			String tag = "<form><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Action sut = new Action(new Form(new HtmlElement(tag, new HtmlElementFactory())));
			assertThat(sut.toString(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		try {
			String tag = "<form action=\"http://www.google.com\"><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Action sut = new Action(new Form(new HtmlElement(tag, new HtmlElementFactory())));
			assertThat(sut.toString(), is("http://www.google.com"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
}
