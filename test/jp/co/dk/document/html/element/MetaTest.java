package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HttpEquivName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class MetaTest extends DocumentFoundationTest{
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getHttpEquiv() {
		// Meta要素に"http-equiv"の属性が設定されていない場合、nullを返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HTTP_EQUIV.getName());
			result = null;
		}};
		Meta meta1 = new Meta(htmlElement);
		assertNull(meta1.getHttpEquiv());
		
		// Meta要素に"http-equiv"の属性に"test"が設定されていた場合、nullを返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HTTP_EQUIV.getName());
			result = "test";
		}};
		Meta meta2 = new Meta(htmlElement);
		assertNull(meta2.getHttpEquiv());
		
		// Meta要素に"http-equiv"の属性に"Content-Type"が設定されていた場合、nullを返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HTTP_EQUIV.getName());
			result = "Content-Type";
		}};
		Meta meta3 = new Meta(htmlElement);
		assertEquals(meta3.getHttpEquiv(), HttpEquivName.CONTENT_TYPE);
		
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.HTTP_EQUIV.getName());
			result = "CONTENT-TYPE";
		}};
		Meta meta4 = new Meta(htmlElement);
		assertEquals(meta4.getHttpEquiv(), HttpEquivName.CONTENT_TYPE);
	}
	
	@Test
	public void getContent() {
		// Meta要素に"content"の属性が設定されていない場合、空文字を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.CONTENT.getName());
			result = null;
		}};
		Meta meta1 = new Meta(htmlElement);
		assertEquals(meta1.getMetaContent(), "");
		
		// Meta要素に"content"の属性に空文字が設定されていた場合、空文字を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.CONTENT.getName());
			result = "";
		}};
		Meta meta2 = new Meta(htmlElement);
		assertEquals(meta2.getMetaContent(), "");
		
		// Meta要素に"content"の属性に空文字が設定されていた場合、空文字を返却すること。
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.CONTENT.getName());
			result = "text/html; charset=shift_jis";
		}};
		Meta meta3 = new Meta(htmlElement);
		assertEquals(meta3.getMetaContent(), "text/html; charset=shift_jis");
	}
}
