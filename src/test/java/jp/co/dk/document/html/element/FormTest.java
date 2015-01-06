package jp.co.dk.document.html.element;

import java.util.List;
import java.util.Map;

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
		
		// Form要素内に <input>、<select>、<textarea> などのフォーム部品が存在した場合
		// のリストを返却すること。
		// キャッシュされていること
		// 再度getFormElementListを実行した際に、キャッシュされているリストが返却されていること
		try {
			String tag = 
					"<form>" +
						"<input type=\"text\"     name=\"textname\"/>" +
						"<input type=\"password\" name=\"passwordname\"/>" +
						"<input type=\"file\"     name=\"filename\"/>" +
						"<input type=\"checkbox\" name=\"checkboxname\"/>" +
						"<input type=\"radio\"    name=\"radioname\"/>" +
						"<input type=\"hidden\"   name=\"hiddenname\"/>" +
						"<input type=\"submit\"   name=\"submitname\"/>" +
						"<input type=\"reset\"    name=\"resetname\"/>" +
						"<input type=\"button\"   name=\"buttonname\"/>" +
						"<input type=\"image\"    name=\"imagename\"/>" +
						"<select name=\"selectname\">" +
							"<option value=\"option1\">オプション１" +
							"<option value=\"option2\">オプション２" +
							"<option value=\"option3\">オプション３" +
						"</select>" +
						"<form>" +
					"</form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			List<HtmlElement> result = sut.getFormElementList();
			assertThat(result.size(), is(9));
			
			checkElement(result.get(0), "input", "text"    , "textname");
			checkElement(result.get(1), "input", "password", "passwordname");
			checkElement(result.get(2), "input", "file"    , "filename");
			checkElement(result.get(3), "input", "checkbox", "checkboxname");
			checkElement(result.get(4), "input", "radio"   , "radioname");
			checkElement(result.get(5), "input", "hidden"  , "hiddenname");
			checkElement(result.get(6), "input", "submit"  , "submitname");
			checkElement(result.get(7), "input", "image"   , "imagename");
			checkElement(result.get(8), "select", null    , "selectname");
			
			assertThat(sut.cache_formElementList, is (result));
			assertThat(sut.getFormElementList(), is (result));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getFormElementMap() {
		try {
			String tag = 
					"<form>" +
						"<input type=\"text\"     name=\"textname\"/>" +
						"<input type=\"password\" name=\"passwordname\"/>" +
						"<input type=\"file\"     name=\"filename\"/>" +
						"<input type=\"checkbox\" name=\"checkboxname\" value=\"checkbox1\"/>" +
						"<input type=\"checkbox\" name=\"checkboxname\" value=\"checkbox2\"/>" +
						"<input type=\"checkbox\" name=\"checkboxname\" value=\"checkbox3\"/>" +
						"<input type=\"radio\"    name=\"radioname\" value=\"radio1\"/>" +
						"<input type=\"radio\"    name=\"radioname\" value=\"radio2\"/>" +
						"<input type=\"radio\"    name=\"radioname\" value=\"radio3\"/>" +
						"<input type=\"hidden\"   name=\"hiddenname\"/>" +
						"<input type=\"submit\"   name=\"submitname\"/>" +
						"<input type=\"reset\"    name=\"resetname\"/>" +
						"<input type=\"button\"   name=\"buttonname\"/>" +
						"<input type=\"image\"    name=\"imagename\"/>" +
						"<select name=\"selectname\">" +
							"<option value=\"option1\">オプション１" +
							"<option value=\"option2\">オプション２" +
							"<option value=\"option3\">オプション３" +
						"</select>" +
					"</form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			Map<String, List<HtmlElement>> result = sut.getFormElementMap();
			assertThat(result.size(), is(9));
			
			assertThat(result.get("textname").size()    , is(1));
			assertThat(result.get("passwordname").size(), is(1));
			assertThat(result.get("filename").size()    , is(1));
			assertThat(result.get("checkboxname").size(), is(3));
			assertThat(result.get("radioname").size()   , is(3));
			assertThat(result.get("hiddenname").size()  , is(1));
			assertThat(result.get("submitname").size()  , is(1));
			assertThat(result.get("imagename").size()   , is(1));
			assertThat(result.get("selectname").size()  , is(1));
			
			checkElement(result.get("textname").get(0)    , "input" , "text"    , "textname");
			checkElement(result.get("passwordname").get(0), "input" , "password", "passwordname");
			checkElement(result.get("filename").get(0)    , "input" , "file"    , "filename");
			checkElement(result.get("checkboxname").get(0), "input" , "checkbox", "checkboxname");
			checkElement(result.get("checkboxname").get(1), "input" , "checkbox", "checkboxname");
			checkElement(result.get("checkboxname").get(2), "input" , "checkbox", "checkboxname");
			checkElement(result.get("radioname").get(0)   , "input" , "radio"   , "radioname");
			checkElement(result.get("radioname").get(1)   , "input" , "radio"   , "radioname");
			checkElement(result.get("radioname").get(2)   , "input" , "radio"   , "radioname");
			checkElement(result.get("hiddenname").get(0)  , "input" , "hidden"  , "hiddenname");
			checkElement(result.get("submitname").get(0)  , "input" , "submit"  , "submitname");
			checkElement(result.get("imagename").get(0)   , "input" , "image"   , "imagename");
			checkElement(result.get("selectname").get(0)  , "select", null     , "selectname");
			
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void createMessage() {
		// FORM内に何もない場合、空のメッセージが返却されること。w
		try {
			String tag = "<form></form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			String result = sut.createMessage();
			assertThat(result, is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// FORMの全要素にvalueが設定されていない場合、name=空文字で返却されること。
		try {
			String tag = 
					"<form>" +
						"<input type=\"text\"     name=\"textname\"/>" +
						"<input type=\"password\" name=\"passwordname\"/>" +
						"<input type=\"file\"     name=\"filename\"/>" +
						"<input type=\"hidden\"   name=\"hiddenname\"/>" +
						"<input type=\"submit\"   name=\"submitname\"/>" +
						"<input type=\"image\"    name=\"imagename\"/>" +
					"</form>";
			Form sut = new Form(new HtmlElement(tag, new HtmlElementFactory()));
			String result = sut.createMessage();
			assertThat(result, is("textname=&passwordname=&filename=&hiddenname="));
			
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	private void checkElement(HtmlElement htmlElement, String tagName, String type, String name) {
		assertThat(htmlElement.getTagName(), is(tagName));
		assertThat(htmlElement.getAttribute("type"), is(type));
		assertThat(htmlElement.getAttribute("name"), is(name));
	}
	
}
