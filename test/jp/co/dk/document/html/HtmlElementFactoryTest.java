package jp.co.dk.document.html;


import static org.junit.Assert.*;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.InputTypeName;
import jp.co.dk.document.html.element.A;
import jp.co.dk.document.html.element.CheckBox;
import jp.co.dk.document.html.element.Form;
import jp.co.dk.document.html.element.Image;
import jp.co.dk.document.html.element.Meta;
import jp.co.dk.document.html.element.Password;
import jp.co.dk.document.html.element.Radio;
import jp.co.dk.document.html.element.Text;
import jp.co.dk.document.html.element.File;
import jp.co.dk.document.html.element.Hidden;
import jp.co.dk.document.html.element.Reset;
import jp.co.dk.document.html.element.Submit;
import jp.co.dk.document.message.DocumentMessage;

public class HtmlElementFactoryTest extends DocumentFoundationTest {
	
	@Mocked
	private jp.co.dk.document.html.HtmlElement mockHtmlElement;
	
	@Test
	public void convert() throws DocumentException {
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
		
		// フォーム要素を変換した場合、フォームオブジェクトが返却されること。
		new Expectations() {{mockHtmlElement.getElementType();result = HtmlElementName.FORM;}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Form);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がtest）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "test";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Text);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がtext）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "text";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Text);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がpassword）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "password";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Password);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がcheckbox）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "checkbox";
			mockHtmlElement.hasAttribute("checked");result = new Boolean(true);
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof CheckBox);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がradio）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "radio";
			mockHtmlElement.hasAttribute("checked");result = new Boolean(true);
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Radio);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がsubmit）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "submit";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Submit);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がimage）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "image";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Image);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がreset）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "reset";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Reset);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がfile）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "file";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof File);
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がhidden）の要素を変換した場合、インプットオブジェクトが返却されること。
		new Expectations() {{
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
			mockHtmlElement.getAttribute("type");result = "hidden";
		}};
		try {
			Element returnElement = factory.convert(mockHtmlElement);
			assertTrue(returnElement instanceof Hidden);
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
		
		// インプット要素からタイプ属性を取得し、その値が"text"の場合、InputTypeName.TEXTが返却されること。
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
		
		// インプット要素からタイプ属性を取得し、その値がInputTypeNameに定義されていない値の場合、nullが返却されること。
		new Expectations() {{
				mockHtmlElement.getElementType();result = HtmlElementName.INPUT;
				mockHtmlElement.getAttribute("type");result = "test";
		}};
		try {
			InputTypeName result = (InputTypeName)super.executePrivateMethod(factory, "getInpuTypeName", mockHtmlElement);
			assertNull(result);
		} catch (Throwable e) {
			fail(e);
		}
	}
}
