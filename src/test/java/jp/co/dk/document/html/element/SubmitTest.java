package jp.co.dk.document.html.element;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class SubmitTest extends DocumentFoundationTest{

	@Test
	public void getMessage() {
		// getMessageをを実行した際には必ず空文字を取得できること
		try {
			String tag = "<input type=\"submit\"/>";
			Submit sut = new Submit(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// getMessageをを実行した際には必ず空文字を取得できること
		try {
			String tag = "<input type=\"submit\" name=\"sendname\" value=\"sebdvalue\"/>";
			Submit sut = new Submit(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getMessage(), is(""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
}
