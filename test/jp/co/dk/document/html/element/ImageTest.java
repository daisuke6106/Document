package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class ImageTest extends DocumentFoundationTest{
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getSrc() {
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.SRC.getName());
			result = null;
		}};
		Image image1 = new Image(htmlElement);
		assertEquals(image1.getSrc(), "");
		
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.SRC.getName());
			result = "";
		}};
		Image image2 = new Image(htmlElement);
		assertEquals(image2.getSrc(), "");
		
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.SRC.getName());
			result = "test";
		}};
		Image image3 = new Image(htmlElement);
		assertEquals(image3.getSrc(), "test");
	}

}
