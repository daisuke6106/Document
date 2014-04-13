package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class OptionTest extends DocumentFoundationTest{
	
	@Test
	public void getValue() {
		// Option要素に"value"の属性が設定されていない場合、空文字を返却すること。
		try {
			String tag = "<option>";
			Option sut = new Option(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(), is (""));
			assertThat(sut.cache_value, is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Option要素に"value"の属性が設定されていない場合、空文字を返却すること。
		try {
			String tag = "<option value=\"\">";
			Option sut = new Option(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(), is (""));
			assertThat(sut.cache_value, is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Option要素に"value"の属性が設定されていた場合、その文字列を返却すること。
		try {
			String tag = "<option value=\"text\">";
			Option sut = new Option(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(), is ("text"));
			assertThat(sut.cache_value, is ("text"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}

}
