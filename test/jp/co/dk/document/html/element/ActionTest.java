package jp.co.dk.document.html.element;

import static org.junit.Assert.*;

import java.net.URL;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.message.DocumentMessage;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class ActionTest extends DocumentFoundationTest{
	
	@Mocked
	private Form form;
	
	@Test
	public void getUrl() {
		// Formタグにアクションが設定されていない場合、例外を返却すること。
		new NonStrictExpectations(){{
				form.getAttribute(HtmlAttributeName.ACTION.getName());
				result = null;
		}};
		Action action1 = new Action(form);
		try {
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
		
		// Formタグにアクションが設定されていない場合、例外を返却すること。
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

}
