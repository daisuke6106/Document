package jp.co.dk.document.html;


import static org.junit.Assert.*;

import java.util.List;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.InputTypeName;
import jp.co.dk.document.html.element.A;
import jp.co.dk.document.html.element.Form;
import jp.co.dk.document.html.element.Image;
import jp.co.dk.document.html.element.Meta;
import jp.co.dk.document.message.DocumentMessage;

public class TestHtmlElementFactory extends TestDocumentFoundation {
	
	@Mocked
	private jp.co.dk.document.html.HtmlElement mockHtmlElement;
	
	@Test
	public void convert() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = createHtmlDocument();
		jp.co.dk.document.xml.XmlDocument   xmlDocument  = createXmlDocument();
		HtmlElementFactory factory = new HtmlElementFactory();
		
		// HTML要素以外を渡した場合、例外が発生すること。
		try {
			factory.convert(xmlDocument.getElement().get(0));
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_ELEMENT_CONVERT) {
				fail(e);
			}
		}
		
		// HTML要素から要素タイプを検索した際にnullが返却された場合、HTML要素がそのまま返却されること。
		// モックオブジェクトを取得
		new Expectations() {{mockHtmlElement.getElementType();result = null;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertEquals(mockHtmlElement, returnElement);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// アンカー要素を変換した場合、アンカーオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.A;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof A);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// イメージ要素を変換した場合、イメージオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.IMG;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Image);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// メタ要素を変換した場合、メタオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.META;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Meta);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// メタ要素を変換した場合、メタオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.FORM;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Form);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.FORM;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Form);
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getInpuTypeName() {
		// 引数に設定された要素がインプット要素でない場合、nullが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = null;}};
		HtmlElementFactory factory = new HtmlElementFactory();
		try {
			assertNull((InputTypeName)super.executePrivateMethod(factory, "getInpuTypeName", mockHtmlElement));
		} catch (Throwable e1) {
			fail(e1);
		}
		
		// インプット要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
				mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
				mockHtmlElement.getAttribute("type");result = "text";
		}};
		try {
			InputTypeName result = (InputTypeName)super.executePrivateMethod(factory, "getInpuTypeName", mockHtmlElement);
			assertEquals(InputTypeName.TEXT, result);
		} catch (Throwable e) {
			fail(e);
		}
	}
}
