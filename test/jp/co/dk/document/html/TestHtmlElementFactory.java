package jp.co.dk.document.html;

import static org.junit.Assert.*;

import java.util.List;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.message.DocumentMessage;

public class TestHtmlElementFactory  {
	
//	@Mocked
//	private jp.co.dk.document.html.HtmlElement htmlElement;
//	
//	@Test
//	public void convert() {
//		jp.co.dk.document.html.HtmlDocument htmlDocument = createHtmlDocument();
//		jp.co.dk.document.xml.XmlDocument   xmlDocument  = createXmlDocument();
//		HtmlElementFactory factory = new HtmlElementFactory();
//		
//		// HTML要素以外を渡した場合、例外が発生すること。
//		try {
//			factory.convert(xmlDocument.getElement().get(0));
//		} catch (DocumentException e) {
//			if (e.getMessageObj() != DocumentMessage.ERROR_ELEMENT_CONVERT) {
//				fail(e);
//			}
//		}
//		
//		// HTML要素から要素タイプを検索した際にnullが返却された場合、HTML要素がそのまま返却されること。
//		
//		// モックオブジェクトを取得
//		new Expectations() {{htmlElement.getElementType();result = HtmlElementName.A;}};
//		try {
////			Element htmlElement   = htmlDocument.getElement().get(0);
//			Element returnElement = factory.convert(this.htmlElement);
//			assertEquals(htmlElement, returnElement);
//		} catch (DocumentException e) {
//			if (e.getMessageObj() != DocumentMessage.ERROR_ELEMENT_CONVERT) {
//				fail(e);
//			}
//		}
//	}
	
	@Mocked
	List<String> stub; // スタブオブジェクトを宣言

	@Test
	public void スタブメソッドの定義() {
	    new NonStrictExpectations() {{
	        stub.get(0); result = "Hello"; // スタブメソッドの定義
	    }};
	    System.out.println(stub.get(0));
//	    assertTrue(stub.get(0).equals("Hello")); // 検証
	}
}