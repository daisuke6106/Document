package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class RadioTest extends DocumentFoundationTest{
	
	@Test
	public void constractor() {
		// Html要素にchecked属性が設定されていた場合
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.defaltChecked, is (false));
			assertThat(sut.checked      , is (false));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていた場合
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\" checked>";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.defaltChecked, is (true));
			assertThat(sut.checked      , is (true));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void isChecked() {
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isChecked(), is (false));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\" checked>";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isChecked(), is (true));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void checked() {
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\" checked>";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isChecked(), is (true));
			sut.checked(false);
			assertThat(sut.isChecked(), is (false));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.isChecked(), is (false));
			sut.checked(true);
			assertThat(sut.isChecked(), is (true));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getDefaltChecked() {
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getDefaltChecked(), is (false));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\" checked>";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getDefaltChecked(), is (true));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getMessage() {
		// Html要素にchecked属性がfalseの場合、nameとvalueに設定されていた場合でも空文字が返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性がtrue、かつnameとvalueに値が設定されていた場合、"name=value"が返却されること
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\" checked>";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is ("sendname=sendvalue"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		try {
			String tag = "<input type=\"radio\" name=\"sendname\" value=\"sendvalue\">";
			Radio sut = new Radio(new HtmlElement(tag, new HtmlElementFactory()));
			sut.checked(true);
			assertThat(sut.getMessage(), is ("sendname=sendvalue"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
	}
}
