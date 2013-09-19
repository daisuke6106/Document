package jp.co.dk.document.html;

import static jp.co.dk.document.message.DocumentMessage.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.element.selector.AnchorHasImage;
import jp.co.dk.document.html.exception.HtmlDocumentException;

public class TestHtmlElement extends TestDocumentFoundation {
	
	@Test
	public void constractor() throws DocumentException {
		// nullにてインスタンス生成した場合、例外を送出すること
		try {
			String elementStr = null;
			HtmlElement htmlElement = new HtmlElement(elementStr, new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_IS_NOT_SET);
		}
		
		// 空文字にてインスタンス生成した場合、例外を送出すること
		try {
			HtmlElement htmlElement = new HtmlElement("", new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_IS_NOT_SET);
		}
				
		// 要素を表す文字列、ファクトリにnullが設定されていた場合、例外が送出すること
		try {
			HtmlElement htmlElement = new HtmlElement("<input type=\"test\" name=\"name\" value=\"value\"/>", null);
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_FACTORY_IS_NOT_SET);
		}
		
		// 不正な文字列にてインスタンス生成した場合、例外を送出すること
		try {
			HtmlElement htmlElement = new HtmlElement("あいう", new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_CONVERT);
		}
		
		// 不正な文字列にてインスタンス生成した場合、例外を送出すること
		try {
			HtmlElement htmlElement = new HtmlElement("abc", new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_CONVERT);
		}
		
		// 要素を表す文字列にてインスタンス生成した場合、正常に生成できること
		try {
			HtmlElement htmlElement = new HtmlElement("<a href=\"a.shtml\">content</a>", new HtmlElementFactory());
			assertEquals(htmlElement.getElement().size(),1);
			assertEquals(htmlElement.getElement().get(0).getTagName(),"a");
			assertEquals(htmlElement.getElement(HtmlElementName.A).size(),1);
			assertEquals(htmlElement.getElement(HtmlElementName.A).get(0).getTagName(),"a");
			assertTrue(htmlElement.hasElement(HtmlElementName.A));
			assertEquals(htmlElement.getChildElement().size(), 0);
			assertFalse(htmlElement.hasChildElement());
			assertTrue(htmlElement.isElement(HtmlElementName.A));
			assertEquals(htmlElement.getAttribute("href"), "a.shtml");
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.HREF.getName()));
			assertEquals(htmlElement.getTagName(),"a");
			assertEquals(htmlElement.getContent(), "content");
			assertEquals(htmlElement.getElementType(), HtmlElementName.A);
			assertEquals(htmlElement.getElement().size(), 1);
			
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		
		// 要素を表す文字列にてインスタンス生成した場合、正常に生成できること
		try {
			HtmlElement htmlElement = new HtmlElement("<input type=\"test\" name=\"name\" value=\"value\"/>", new HtmlElementFactory());
			assertEquals(htmlElement.getElement().size(),1);
			assertEquals(htmlElement.getElement().get(0).getTagName(),"input");
			assertEquals(htmlElement.getElement(HtmlElementName.INPUT).size(),1);
			assertEquals(htmlElement.getElement(HtmlElementName.INPUT).get(0).getTagName(),"input");
			assertTrue(htmlElement.hasElement(HtmlElementName.INPUT));
			assertEquals(htmlElement.getChildElement().size(), 0);
			assertFalse(htmlElement.hasChildElement());
			assertTrue(htmlElement.isElement(HtmlElementName.INPUT));
			assertEquals(htmlElement.getAttribute("type"), "test");
			assertEquals(htmlElement.getAttribute("name"), "name");
			assertEquals(htmlElement.getAttribute("value"), "value");
			assertEquals(htmlElement.getContent(), "");
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.TYPE.getName()));
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.NAME.getName()));
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.VALUE.getName()));
			assertEquals(htmlElement.getTagName(),"input");
			assertEquals(htmlElement.getContent(), "");
			assertEquals(htmlElement.getElementType(), HtmlElementName.INPUT);
			
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// 要素を表す文字列にてインスタンス生成した場合、正常に生成できること。
		// 要素は小要素を保持するselect要素です。
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"example\">");
		sb.append("<option value=\"サンプル1\">サンプル1</option>");
		sb.append("<option value=\"サンプル2\">サンプル2</option>");
		sb.append("<option value=\"サンプル3\">サンプル3</option>");
		sb.append("</select>");

		try {
			HtmlElement htmlElement = new HtmlElement(sb.toString(), new HtmlElementFactory());
			assertEquals(htmlElement.getElement().size(),4);
			assertEquals(htmlElement.getElement().get(0).getTagName(),"select");
			assertEquals(htmlElement.getElement().get(1).getTagName(),"option");
			assertEquals(htmlElement.getElement().get(2).getTagName(),"option");
			assertEquals(htmlElement.getElement().get(3).getTagName(),"option");
			assertEquals(htmlElement.getElement(HtmlElementName.SELECT).size(),1);
			assertEquals(htmlElement.getElement(HtmlElementName.SELECT).get(0).getTagName(),"select");
			assertEquals(htmlElement.getElement(HtmlElementName.OPTION).size(),3);
			assertEquals(htmlElement.getElement(HtmlElementName.OPTION).get(0).getTagName(),"option");
			assertEquals(htmlElement.getElement(HtmlElementName.OPTION).get(1).getTagName(),"option");
			assertEquals(htmlElement.getElement(HtmlElementName.OPTION).get(2).getTagName(),"option");
			assertTrue(htmlElement.hasElement(HtmlElementName.SELECT));
			assertTrue(htmlElement.hasElement(HtmlElementName.OPTION));
			assertEquals(htmlElement.getChildElement().size(), 3);
			assertEquals(htmlElement.getChildElement().get(0).getTagName(), "option");
			assertEquals(htmlElement.getChildElement().get(1).getTagName(), "option");
			assertEquals(htmlElement.getChildElement().get(2).getTagName(), "option");
			assertTrue(htmlElement.hasChildElement());
			assertTrue(htmlElement.isElement(HtmlElementName.SELECT));
			assertEquals(htmlElement.getAttribute("name"), "example");
			assertEquals(htmlElement.getContent(), "");
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.NAME.getName()));
			assertEquals(htmlElement.getTagName(),"select");
			assertEquals(htmlElement.getContent(), "");
			assertEquals(htmlElement.getElementType(), HtmlElementName.SELECT);
			List<jp.co.dk.document.Element> elements = htmlElement.getChildElement();
			assertEquals(elements.get(0).getTagName(), "option");
			assertEquals(elements.get(0).getAttribute("value"), "サンプル1");
			assertEquals(elements.get(0).getContent(), "サンプル1");
			assertEquals(elements.get(1).getTagName(), "option");
			assertEquals(elements.get(1).getAttribute("value"), "サンプル2");
			assertEquals(elements.get(1).getContent(), "サンプル2");
			assertEquals(elements.get(2).getTagName(), "option");
			assertEquals(elements.get(2).getAttribute("value"), "サンプル3");
			assertEquals(elements.get(2).getContent(), "サンプル3");
			assertEquals(htmlElement.getElement().size(), 4);
			assertEquals(htmlElement.getChildElement().size(), 3);
			assertTrue(htmlElement.hasChildElement());
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getEncode() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		assertThat(htmlDocument.getEncode(), is (HtmlCharSetName.UTF_8));
	}
	
	@Test
	public void getElement() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<jp.co.dk.document.Element> elementList1 = htmlDocument.getElement();
		assertEquals(elementList1.get(0).getTagName(), "!doctype");
		assertEquals(elementList1.get(1).getTagName(), "html");
		for (HtmlElementName html : HtmlElementName.values()) {
			List<jp.co.dk.document.Element> elementList2 = htmlDocument.getElement(html);
			for(jp.co.dk.document.Element element : elementList2){
				assertEquals(element.getTagName(), html.getName());
			}
		}
		List<jp.co.dk.document.Element> elementList3 = htmlDocument.getElement(new AnchorHasImage());
		for(jp.co.dk.document.Element element : elementList3){
			assertEquals(element.getTagName(), "a");
			assertEquals(element.getChildElement().size(), 1);
			assertEquals(element.getChildElement().get(0).getTagName(), "image");
		} 
	}
	
	@Test
	public void getChildElement() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<jp.co.dk.document.Element> elementList1 = htmlDocument.getChildElement();
		assertEquals(elementList1.get(0).getTagName(), "!doctype");
		assertEquals(elementList1.get(1).getTagName(), "html");
		
		List<jp.co.dk.document.Element> elementList2 = htmlDocument.getChildElement(HtmlElementName.HTML);
		for(jp.co.dk.document.Element element : elementList2){
			assertEquals(element.getTagName(), HtmlElementName.HTML.getName());
		}
		
		List<jp.co.dk.document.Element> elementList3 = htmlDocument.getChildElement(new AnchorHasImage());
		assertEquals(elementList3.size(), 0);
	}
	
	@Test
	public void getAllElement() throws DocumentException {
//		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument("jp/co/dk/document/html/HTML_getDocument001.html");
//		List<jp.co.dk.document.Element> elementList = htmlDocument.getChildElement();
//		List<jp.co.dk.document.Element> allElementList = ((HtmlElement)elementList.get(0)).getAllElement();
//		assertEquals(allElementList.size(), 0);
	}
	
	@Test
	public void hasChildElement() throws DocumentException {
			jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
			if (!htmlDocument.hasChildElement()) {
				fail();
			}
			if (htmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement()) {
				fail();
			}
	}
	
	@Test
	public void isElement() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		boolean trueHtml = htmlDocument.getElement(HtmlElementName.HTML).get(0).isElement(HtmlElementName.HTML);
		if (!trueHtml) {
			fail();
		}
	}
	
	@Test
	public void hasElement() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		assertThat(htmlDocument.hasChildElement(), is (true) );
		assertThat(htmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement(), is (false));
	}
	
	@Test
	public void getAttribute() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		Element element = htmlDocument.getElement(HtmlElementName.IMG).get(0);
		
		assertThat(element.getAttribute(HtmlAttributeName.ALT.getName()), is ("ウィキペディア"));
		assertThat(element.getAttribute("test"), nullValue());
		
	}
	
	@Test
	public void hasAttribute() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement(HtmlElementName.IMG);
		Element element = elementList.get(0);
		if( !element.hasAttribute("src") ) {
			fail();
		}
		if( !element.hasAttribute("width")) {
			fail();
		}
		if( !element.hasAttribute("height")) {
			fail();
		}
		if( element.hasAttribute("test")) {
			fail();
		}
	}
	
	@Test
	public void getTagName() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement(HtmlElementName.IMG);
		String name = elementList.get(0).getTagName();
		if (!name.equals("img")) {
			fail();
		}
	}
	
	@Test
	public void getContent() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement(HtmlElementName.LABEL);
		Element element = elementList.get(0);
		if (!element.getContent().equals("検索")) {
			fail();
		}
	}
	
	@Test
	public void getId() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement();
		for (Element element: elementList) {
			if (element.hasAttribute("id")) {
				HtmlElement htmlElmenet = (HtmlElement)element;
				String id = htmlElmenet.getId();
				if (id.equals("")) {
					fail();
				}
			} else {
				HtmlElement htmlElmenet = (HtmlElement)element;
				String id = htmlElmenet.getId();
				if (!id.equals("")) {
					fail();
				}
			}
		}
	}
	
	@Test
	public void getName() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement();
		for (Element element: elementList) {
			if (element.hasAttribute("name")) {
				HtmlElement htmlElmenet = (HtmlElement)element;
				String name = htmlElmenet.getName();
				if (name.equals("")) {
					fail();
				}
			} else {
				HtmlElement htmlElmenet = (HtmlElement)element;
				String name = htmlElmenet.getName();
				if (!name.equals("")) {
					fail();
				}
			}
		}
	}
	
	@Test
	public void getClassList() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement();
		for (Element element: elementList) {
			if (element.hasAttribute("class")) {
				HtmlElement htmlElmenet = (HtmlElement)element;
				List<String> classList = htmlElmenet.getClassList();
				if (classList.size()==0) {
					fail();
				}
			} else {
				HtmlElement htmlElmenet = (HtmlElement)element;
				List<String> classList = htmlElmenet.getClassList();
				if (classList.size()!=0) {
					fail();
				}
			}
		}
	}
	
	@Test
	public void getElementById() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<HtmlElement> idList = element.getElementById("bodyContent");
		if (idList.size() == 0) {
			fail();
		}
		List<HtmlElement> idNonList = element.getElementById("aaa");
		if (idNonList.size() != 0) {
			fail();
		}
	}
	
	@Test
	public void getElementByName() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<HtmlElement> nameList = element.getElementsByName("search");
		if (nameList.size() != 2) {
			fail();
		}
		List<HtmlElement> nameNonList = element.getElementsByName("aaa");
		if (nameNonList.size() != 0) {
			fail();
		}
	}
	
	@Test
	public void getElementType() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		HtmlElementName elementType = element.getElementType();
		if (elementType != HtmlElementName.HTML) {
			fail();
		}
	}
	
	@Test
	public void getHrefList() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<HtmlElement> anchorList = element.getHrefList();
		if (anchorList.size() != 437) {
			fail();
		}
	}
	
	@Test
	public void getFormElement() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<Element> elementList = element.getElement(HtmlElementName.FORM);
		if (elementList.size() != 3) {
			fail();
		}
	}
	
	@Test
	public void equals() throws DocumentException {
		jp.co.dk.document.html.HtmlDocument htmlDocument1 = super.createHtmlDocument();
		List<Element> list1 =  htmlDocument1.getElement(HtmlElementName.HTML);
		HtmlElement element1 = (HtmlElement)list1.get(0);
		
		jp.co.dk.document.html.HtmlDocument htmlDocument2 = super.createHtmlDocument();
		List<Element> list2 =  htmlDocument2.getElement(HtmlElementName.HTML);
		HtmlElement element2 = (HtmlElement)list2.get(0);
		
		assertThat (element1.equals(element1), is (true) );
		assertThat (element1.equals(null), is (false) );
		assertThat (element1.equals(""), is (false) );
		assertThat (element1.equals(element2), is (true) );
		
	}
}
