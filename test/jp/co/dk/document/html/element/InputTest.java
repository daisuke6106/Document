package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class InputTest extends TestDocumentFoundation{
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getValue() {
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = null;
		}};
		Input input1 = new Input(htmlElement);
		assertEquals(input1.getValue(), "");
		
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "";
		}};
		Input input2 = new Input(htmlElement);
		assertEquals(input2.getValue(), "");
		
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "test";
		}};
		Input input3 = new Input(htmlElement);
		assertEquals(input3.getValue(), "test");
	}
	
	@Test
	public void setValue() {
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "test";
		}};
		Input input3 = new Input(htmlElement);
		assertEquals(input3.getValue(), "test");
		input3.setValue("test2");
		assertEquals(input3.getValue(), "test2");
	}
	
	@Test
	public void isDisabled() {
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(true);
		}};
		Input input1 = new Input(htmlElement);
		assertTrue(input1.isDisabled());
		
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(false);
		}};
		Input input2 = new Input(htmlElement);
		assertFalse(input2.isDisabled());
	}
	
	@Test
	public void getMessage(){
		// INPUT要素がdisable状態の場合、空文字が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(true);
		}};
		Input input1 = new Input(htmlElement);
		assertEquals(input1.getMessage(), "");
		
		// INPUT要素がdisable状態でない場合、かつnameとvalueの値が空の場合、空文字が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(false);
			htmlElement.getName();
			result = "";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "";
		}};
		Input input2 = new Input(htmlElement);
		assertEquals(input2.getMessage(), "");
		
		// INPUT要素がdisable状態でない場合、かつnameとvalueの値が空でない場合、"name=value"が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(false);
			htmlElement.getName();
			result = "name";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "value";
		}};
		Input input3 = new Input(htmlElement);
		assertEquals(input3.getMessage(), "name=value");
		
		// INPUT要素がdisable状態でない場合、かつnameが空、valueの値が空でない場合、空文字が返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.DISABLED.getName());
			result = new Boolean(false);
			htmlElement.getName();
			result = "";
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "value";
		}};
		Input input4 = new Input(htmlElement);
		assertEquals(input4.getMessage(), "");
	}
}
