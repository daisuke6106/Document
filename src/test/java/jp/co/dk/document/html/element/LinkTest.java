package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class LinkTest extends DocumentFoundationTest{

	@Test
	public void getHref() {
		// href属性が設定されていた場合、値が取得できること
		try {
			String tag = "<link href=\"test.html\">";
			Link sut = new Link(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHref(), is("test.html"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// href属性が設定されていない場合、空文字が取得できること
		try {
			String tag = "<link>";
			Link sut = new Link(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHref(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getUrl() {
		// href属性が設定されていた場合、値が取得できること
		try {
			String tag = "<link href=\"test.html\">";
			Link sut = new Link(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is("test.html"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// href属性が設定されていない場合、空文字が取得できること
		try {
			String tag = "<link>";
			Link sut = new Link(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
