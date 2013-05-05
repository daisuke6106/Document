package jp.co.dk.document.html;

import static jp.co.dk.document.message.DocumentMessage.*;

import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.element.selector.AnchorHasImage;
import jp.co.dk.document.html.exception.HtmlDocumentException;

public class TestHtmlElement extends TestDocumentFoundation {
	
	@Test
	public void constractor() {
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
			HtmlElement htmlElement = new HtmlElement("<input type=\"test\" name=\"name\" value=\"value\"/>", new HtmlElementFactory());
			assertEquals(htmlElement.getElementType(), HtmlElementName.INPUT);
			assertEquals(htmlElement.getAttribute("type"), "test");
			assertEquals(htmlElement.getAttribute("name"), "name");
			assertEquals(htmlElement.getAttribute("value"), "value");
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		// 要素を表す文字列にてインスタンス生成した場合、正常に生成できること。
		// 要素は小要素を保持するselect要素です。
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"name\">");
		sb.append("<option value=\"value1\"/>");
		sb.append("<option value=\"value2\"/>");
		sb.append("<option value=\"value3\"/>");
		sb.append("</select>");
		try {
			HtmlElement htmlElement = new HtmlElement(sb.toString(), new HtmlElementFactory());
			assertEquals(htmlElement.getElementType(), HtmlElementName.SELECT);
			assertEquals(htmlElement.getAttribute("name"), "name");
			List<jp.co.dk.document.Element> elements = htmlElement.getChildElement();
			assertEquals(elements.get(0).getTagName(), "option");
			assertEquals(elements.get(0).getAttribute("value"), "value1");
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		
	}
	
	@Test
	public void getEncode() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		assertThat(htmlDocument.getEncode(), is (HtmlCharSetName.UTF_8));
	}
	
	@Test
	public void getElement() {
		// デバックで目視確認
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<jp.co.dk.document.Element> elementList1 = htmlDocument.getElement();
		for (HtmlElementName html : HtmlElementName.values()) {
			List<jp.co.dk.document.Element> elementList2 = htmlDocument.getElement(html);
		}
		List<jp.co.dk.document.Element> elementList3 = htmlDocument.getElement(new AnchorHasImage());
	}
	
	@Test
	public void getChildElement() {
			jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
			List<jp.co.dk.document.Element> elementList1 = htmlDocument.getChildElement();
			for (HtmlElementName html : HtmlElementName.values()) {
				List<jp.co.dk.document.Element> elementList2 = htmlDocument.getChildElement(html);
			}
			List<jp.co.dk.document.Element> elementList3 = htmlDocument.getChildElement(new AnchorHasImage());
	}
	
	@Test
	public void hasChildElement() {
			jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
			if (!htmlDocument.hasChildElement()) {
				fail();
			}
			if (htmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement()) {
				fail();
			}
	}
	
	@Test
	public void isElement() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		boolean trueHtml = htmlDocument.getElement(HtmlElementName.HTML).get(0).isElement(HtmlElementName.HTML);
		if (!trueHtml) {
			fail();
		}
	}
	
	@Test
	public void hasElement() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		assertThat(htmlDocument.hasChildElement(), is (true) );
		assertThat(htmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement(), is (false));
	}
	
	@Test
	public void getAttribute() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		Element element = htmlDocument.getElement(HtmlElementName.IMG).get(0);
		
		assertThat(element.getAttribute(HtmlAttributeName.ALT.getName()), is ("ウィキペディア"));
		assertThat(element.getAttribute("test"), nullValue());
		
	}
	
	@Test
	public void hasAttribute() {
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
	public void getTagName() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement(HtmlElementName.IMG);
		String name = elementList.get(0).getTagName();
		if (!name.equals("img")) {
			fail();
		}
	}
	
	@Test
	public void getContent() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> elementList = htmlDocument.getElement(HtmlElementName.LABEL);
		Element element = elementList.get(0);
		if (!element.getContent().equals("検索")) {
			fail();
		}
	}
	
	@Test
	public void getId() {
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
	public void getName() {
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
	public void getClassList() {
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
	public void getElementById() {
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
	public void getElementByName() {
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
	public void getElementType() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		HtmlElementName elementType = element.getElementType();
		if (elementType != HtmlElementName.HTML) {
			fail();
		}
	}
	
	@Test
	public void getHrefList() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<HtmlElement> anchorList = element.getHrefList();
		if (anchorList.size() != 437) {
			fail();
		}
	}
	
	@Test
	public void getFormElement() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		List<Element> list =  htmlDocument.getElement(HtmlElementName.HTML);
		HtmlElement element = (HtmlElement)list.get(0);
		List<Element> elementList = element.getElement(HtmlElementName.FORM);
		if (elementList.size() != 3) {
			fail();
		}
	}
	
	@Test
	public void equals() {
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
