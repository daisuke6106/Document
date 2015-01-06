package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class InputTest extends DocumentFoundationTest{
	
	@Test
	public void getValue() {
		// value属性が設定されている場合、その値を取得できること
		try {
			String tag = "<input type=\"text\" value=\"test\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(),  is ("test"));
			assertThat(sut.cache_value, is ("test"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// value属性が設定されていない場合、空文字を取得できること。
		try {
			String tag = "<input type=\"text\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(),  is (""));
			assertThat(sut.cache_value, is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// value属性が設定されているかつ、空文字である場合、その値を取得できること
		try {
			String tag = "<input type=\"text\" value=\"\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(),  is (""));
			assertThat(sut.cache_value, is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void setValue() {
		// value属性が設定されている場合でその値を上書きした場合、その値を取得できること
		try {
			String tag = "<input type=\"text\" value=\"test\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.cache_value, nullValue());
			assertThat(sut.getValue(),  is ("test"));
			assertThat(sut.cache_value, is ("test"));
			sut.setValue("override");
			assertThat(sut.cache_value, is ("override"));
			assertThat(sut.getValue(),  is ("override"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
	}
	
	@Test
	public void isDisabled() {
		// disabled属性が設定されていない場合、falseが返却されること
		try {
			String tag = "<input type=\"text\" value=\"value\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isDisabled(), is(false));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// disabled属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"text\" value=\"value\" disabled>";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isDisabled(), is(true));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getMessage(){
		// disabled属性が設定されていた場合、空文字が返却されること
		try {
			String tag = "<input type=\"text\" name=\"sendname\" value=\"sendvalue\" disabled>";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// name属性が設定されていなかった場合、空文字が返却されること
		try {
			String tag = "<input type=\"text\" value=\"sendvalue\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// name属性が設定され、value属性が設定されていなかった場合、値が入っていない送信値が取得されること
		try {
			String tag = "<input type=\"text\" name=\"sendname\" >";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is("sendname="));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
				
		// name属性、value属性が設定されていた場合、正常に送信値が取得できること
		try {
			String tag = "<input type=\"text\" name=\"sendname\" value=\"sendvalue\">";
			Input sut = new Input(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is("sendname=sendvalue"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
