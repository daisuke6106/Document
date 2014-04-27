package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class ScriptTest extends DocumentFoundationTest{

	@Test
	public void getHref() {
		// src属性が設定されていた場合、値が取得できること
		try {
			String tag = "<script src=\"test.js\">";
			Script sut = new Script(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getSrc(), is("test.js"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// src属性が設定されていない場合、空文字が取得できること
		try {
			String tag = "<script>";
			Script sut = new Script(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getSrc(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getUrl() {
		// src属性が設定されていた場合、値が取得できること
		try {
			String tag = "<script src=\"test.js\">";
			Script sut = new Script(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is("test.js"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// src属性が設定されていない場合、空文字が取得できること
		try {
			String tag = "<script>";
			Script sut = new Script(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
