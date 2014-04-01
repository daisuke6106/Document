package jp.co.dk.document.html.element;

import java.util.List;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlRequestMethodName;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class FormTest extends DocumentFoundationTest{
	
	@Test
	public void getMethod() {
		// Form要素が設定されていなかった場合、GETが返却されること
		try {
			String tag = "<form><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMethod(), is (HtmlRequestMethodName.GET));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Form要素が"GET"が設定されていた場合、GETが返却されること
		try {
			String tag = "<form method=\"GET\"><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMethod(), is (HtmlRequestMethodName.GET));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Form要素が"get"が設定されていた場合、GETが返却されること
		try {
			String tag = "<form method=\"get\"><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMethod(), is (HtmlRequestMethodName.GET));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// Form要素が"POST"が設定されていた場合、GETが返却されること
		try {
			String tag = "<form method=\"POST\"><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMethod(), is (HtmlRequestMethodName.POST));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getAcrion(){
		// Form要素が"POST"が設定されていた場合、GETが返却されること
		try {
			String tag = "<form method=\"GET\"><input type=\"text\" name=\"test\" value=\"test\"/></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			Action action = sut.getAction();
			assertThat(action.form, is (sut));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	
	@Test
	public void getFormElementList() {
		// Form要素内に <input>、<select>、<textarea> などのフォーム部品が存在しなかった場合
		// 空のリストを返却すること。
		// キャッシュされていること
		// 再度getFormElementListを実行した際に、キャッシュされているリストが返却されていること
		try {
			String tag = "<form></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			List<HtmlElement> result = sut.getFormElementList();
			assertThat(result.size(), is(0));
			assertThat(sut.cache_formElementList, is (result));
			assertThat(sut.getFormElementList(), is (result));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
