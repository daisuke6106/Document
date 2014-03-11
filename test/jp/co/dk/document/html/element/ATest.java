package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

public class ATest extends TestDocumentFoundation {
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getHref() {
		
		// hrefがnullを返却した場合、空文字を返却すること。
		new Expectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HREF.getName());result=null;
		}};
		A anchor1 = new A(htmlElement);
		assertEquals(anchor1.getHref(), "");
		
		// hrefが空文字を返却した場合、空文字を返却すること。
		new Expectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HREF.getName());result="";
		}};
		A anchor2 = new A(htmlElement);
		assertEquals(anchor2.getHref(), "");
		
		// hrefが指定文字を返却した場合、空文字を返却すること。
		new Expectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HREF.getName());result="test";
		}};
		A anchor3 = new A(htmlElement);
		assertEquals(anchor3.getHref(), "test");
	}

}
