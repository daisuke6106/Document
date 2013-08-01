package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class TestOption {
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getValue() {
		// Option要素に"value"の属性が設定されていない場合、空文字を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = null;
		}};
		Option option1 = new Option(htmlElement);
		assertEquals(option1.getValue(), "");
		
		// Option要素に"value"の属性が設定されていない場合、空文字を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "";
		}};
		Option option2 = new Option(htmlElement);
		assertEquals(option2.getValue(), "");
		
		// Option要素に"value"の属性が設定されていた場合、その文字列を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.VALUE.getName());
			result = "test";
		}};
		Option option3 = new Option(htmlElement);
		assertEquals(option3.getValue(), "test");
	}

}
