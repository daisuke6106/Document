package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HttpEquivName;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class MetaTest extends DocumentFoundationTest{
	
	@Test
	public void getHttpEquiv() {
		// Meta要素に"http-equiv"の属性が設定されていない場合、nullを返却すること。
		try {
			String tag = "<meta content=\"text/html; charset=shift_jis\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHttpEquiv(), nullValue());
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Meta要素に"http-equiv"の属性に"test"が設定されていた場合、nullを返却すること。
		try {
			String tag = "<meta http-equiv=\"test\" content=\"text/html; charset=shift_jis\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHttpEquiv(), nullValue());
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Meta要素に"http-equiv"の属性に"Content-Type"が設定されていた場合、HttpEquivName.CONTENT_TYPEを返却すること。
		try {
			String tag = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=shift_jis\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHttpEquiv(), is(HttpEquivName.CONTENT_TYPE));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Meta要素に"http-equiv"の属性に"CONTENT-TYPE"が設定されていた場合、HttpEquivName.CONTENT_TYPEを返却すること。
		try {
			String tag = "<meta http-equiv=\"CONTENT-TYPE\" content=\"text/html; charset=shift_jis\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getHttpEquiv(), is(HttpEquivName.CONTENT_TYPE));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getContent() {
		// Meta要素に"content"の属性が設定されていない場合、空文字を返却すること。
		try {
			String tag = "<meta http-equiv=\"Content-Type\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMetaContent(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Meta要素に"content"の属性に空文字が設定されていた場合、空文字を返却すること。
		try {
			String tag = "<meta http-equiv=\"Content-Type\" content=\"\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMetaContent(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Meta要素に"content"の属性に空文字が設定されていた場合、その設定値を返却すること。
		try {
			String tag = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=shift_jis\">";
			Meta sut = new Meta(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMetaContent(), is("text/html; charset=shift_jis"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
