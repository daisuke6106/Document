package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

public class ATest extends DocumentFoundationTest {
	
	@Test
	public void getHref() {
		
		// hrefが設定されていなかった場合、空文字を返却すること。
		try {
			String tag = "<a href=\"\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getHref(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
				
		// hrefが設定されていた、且つ空文字で合った場合、空文字を返却すること。
		try {
			String tag = "<a href=\"\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getHref(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// hrefが設定されていた、且つ空文字でなかった場合、設定されている文字を返却すること。
		try {
			String tag = "<a href=\"test\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getHref(), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
	}

	@Test
	public void getUrl() {
		
		// hrefが設定されていなかった場合、空文字を返却すること。
		try {
			String tag = "<a href=\"\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getUrl(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
				
		// hrefが設定されていた、且つ空文字で合った場合、空文字を返却すること。
		try {
			String tag = "<a href=\"\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getUrl(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// hrefが設定されていた、且つ空文字でなかった場合、設定されている文字を返却すること。
		try {
			String tag = "<a href=\"test\"/>";
			A anchor = new A(new jp.co.dk.document.html.HtmlElement(tag, new HtmlElementFactory()));
			assertThat(anchor.getUrl(), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
	}


}
