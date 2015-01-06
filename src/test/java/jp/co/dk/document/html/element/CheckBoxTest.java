package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElementFactory;

import org.junit.Test;

public class CheckBoxTest extends DocumentFoundationTest {
	
	@Test
	public void isChecked() {
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\" checked/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(checkBox.isChecked(), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\" checked=\"checked\"/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(checkBox.isChecked(), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていなかった場合、falseが返却されること
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\"/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(checkBox.isChecked(), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
		
	}
	
	@Test
	public void checked() {
		
		// Html要素にchecked属性が設定されていた場合、チェック状態へfalseを設定した場合
		// チェック状態がfalse、デフォルトのチェック状態がtrueであること
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\" checked/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			checkBox.checked(false);
			assertThat(checkBox.isChecked(), is(false));
			assertThat(checkBox.getDefaltChecked(), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// Html要素にchecked属性が設定されていなかった場合、チェック状態へtrueを設定した場合
		// チェック状態がtrue、デフォルトのチェック状態がfalseであること
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\"/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			checkBox.checked(true);
			assertThat(checkBox.isChecked(), is(true));
			assertThat(checkBox.getDefaltChecked(), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
		
	}
	
	@Test
	public void getMessage() {
		// checkedが設定されていなかった場合、空文字列が返却されること。
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\"/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(checkBox.getMessage(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
				
		// checkedが設定されていた場合、nameとvalueを=で結合した文字列が返却されること。
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\" checked/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));  
			assertThat(checkBox.getMessage(), is("test=checkboxvalue"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		
		// checkedが設定されていなかった場合、且つcheckedを実行した場合、nameとvalueを=で結合した文字列が返却されること。
		try {
			String tag = "<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\"/>";
			CheckBox checkBox = new CheckBox(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			checkBox.checked(true);
			assertThat(checkBox.getMessage(), is("test=checkboxvalue"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
}
