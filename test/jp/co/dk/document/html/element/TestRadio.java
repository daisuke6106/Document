package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class TestRadio {
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void isChecked() {
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = false;
		}};
		Radio radio1 = new Radio(htmlElement);
		assertFalse(radio1.isChecked());
		assertFalse(radio1.getDefaltChecked());
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = true;
		}};
		Radio radio2 = new Radio(htmlElement);
		assertTrue(radio2.isChecked());
		assertTrue(radio2.getDefaltChecked());
	}
	
	@Test
	public void checked() {
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = false;
		}};
		Radio radio1 = new Radio(htmlElement);
		radio1.checked(true);
		assertTrue(radio1.isChecked());
		assertFalse(radio1.getDefaltChecked());
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = true;
		}};
		Radio radio2 = new Radio(htmlElement);
		radio2.checked(false);
		assertFalse(radio2.isChecked());
		assertTrue(radio2.getDefaltChecked());
	}
	
	
	@Test
	public void getMessage() {
		// Html要素にchecked属性がfalseの場合、nameとvalueに設定されていた場合でも空文字が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = false;
			htmlElement.getName();
			result = "name";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "value";
		}};
		Radio radio1 = new Radio(htmlElement);
		assertEquals(radio1.getMessage(), "");
		
		// Html要素にchecked属性がtrue、かつnameとvalueに値が設定されていた場合、"name=value"が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = true;
			htmlElement.getName();
			result = "name";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "value";
		}};
		Radio radio2 = new Radio(htmlElement);
		assertEquals(radio2.getMessage(), "name=value");
		
		// Html要素にchecked属性がtrue、かつvalueに値が設定されていた場合でも、nameには空文字の場合""が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = true;
			htmlElement.getName();
			result = "";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "value";
		}};
		Radio radio3 = new Radio(htmlElement);
		assertEquals(radio3.getMessage(), "");
	}
}
