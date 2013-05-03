package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class TestMeta extends TestDocumentFoundation{
	
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
	}

}
