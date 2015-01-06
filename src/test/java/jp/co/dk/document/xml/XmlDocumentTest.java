package jp.co.dk.document.xml;

import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.element.Form;
import jp.co.dk.document.html.element.selector.AnchorHasImage;
import jp.co.dk.document.message.DocumentMessage;

public class XmlDocumentTest extends DocumentFoundationTest {
	
//	@Test
//	public void getElement() {
//		// デバックで目視確認
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<jp.co.dk.document.Element> elementList1 = xmlDocument.getElement();
//		for (XmlElementName xml : XmlElementName.values()) {
//			List<jp.co.dk.document.Element> elementList2 = xmlDocument.getElement(xml);
//		}
//		List<jp.co.dk.document.Element> elementList3 = xmlDocument.getElement(new AnchorHasImage());
//	}
//	
//	@Test
//	public void hasElement() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		boolean trueElement  = xmlDocument.hasChildElement();
//		boolean falseElement = xmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement();
//		if (!trueElement) {
//			fail();
//		}
//		if (falseElement) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getChildElement() {
//			jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//			List<jp.co.dk.document.Element> elementList1 = xmlDocument.getChildElement();
//			for (HtmlElementName html : HtmlElementName.values()) {
//				List<jp.co.dk.document.Element> elementList2 = xmlDocument.getChildElement(html);
//			}
//			List<jp.co.dk.document.Element> elementList3 = xmlDocument.getChildElement(new AnchorHasImage());
//	}
//	
//	@Test
//	public void hasChildElement() {
//			jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//			if (!xmlDocument.hasChildElement()) {
//				fail();
//			}
//			if (xmlDocument.getElement(HtmlElementName.IMG).get(0).hasChildElement()) {
//				fail();
//			}
//	}
//	
//	@Test
//	public void isElement() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		boolean trueHtml = xmlDocument.getElement(HtmlElementName.HTML).get(0).isElement(HtmlElementName.HTML);
//		if (!trueHtml) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getAttribute() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		Element element = xmlDocument.getElement(HtmlElementName.IMG).get(0);
//		String attribute1 = element.getAttribute(HtmlAttributeName.ALT.getName());
//		String attribute2 = element.getAttribute("test");
//		
//		if (!attribute1.equals("ウィキペディア")) {
//			fail();
//		}
//		if (attribute2 != null) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void hasAttribute() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement(HtmlElementName.IMG);
//		Element element = elementList.get(0);
//		if( !element.hasAttribute("src") ) {
//			fail();
//		}
//		if( !element.hasAttribute("width")) {
//			fail();
//		}
//		if( !element.hasAttribute("height")) {
//			fail();
//		}
//		if( element.hasAttribute("test")) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getTagName() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement(HtmlElementName.IMG);
//		String name = elementList.get(0).getTagName();
//		if (!name.equals("img")) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getContent() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement(HtmlElementName.LABEL);
//		Element element = elementList.get(0);
//		if (!element.getContent().equals("検索")) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getId() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement();
//		for (Element element: elementList) {
//			if (element.hasAttribute("id")) {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				String id = htmlElmenet.getId();
//				if (id.equals("")) {
//					fail();
//				}
//			} else {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				String id = htmlElmenet.getId();
//				if (!id.equals("")) {
//					fail();
//				}
//			}
//		}
//	}
//	
//	@Test
//	public void getAName() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement();
//		for (Element element: elementList) {
//			if (element.hasAttribute("name")) {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				String name = htmlElmenet.getName();
//				if (name.equals("")) {
//					fail();
//				}
//			} else {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				String name = htmlElmenet.getName();
//				if (!name.equals("")) {
//					fail();
//				}
//			}
//		}
//	}
//	
//	@Test
//	public void getClassList() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> elementList = xmlDocument.getElement();
//		for (Element element: elementList) {
//			if (element.hasAttribute("class")) {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				List<String> classList = htmlElmenet.getClassList();
//				if (classList.size()==0) {
//					fail();
//				}
//			} else {
//				HtmlElement htmlElmenet = (HtmlElement)element;
//				List<String> classList = htmlElmenet.getClassList();
//				if (classList.size()!=0) {
//					fail();
//				}
//			}
//		}
//	}
//	
//	@Test
//	public void getElementById() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> list =  xmlDocument.getElement(HtmlElementName.HTML);
//		HtmlElement element = (HtmlElement)list.get(0);
//		List<HtmlElement> idList = element.getElementById("bodyContent");
//		if (idList.size() == 0) {
//			fail();
//		}
//		List<HtmlElement> idNonList = element.getElementById("aaa");
//		if (idNonList.size() != 0) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getElementByName() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> list =  xmlDocument.getElement(HtmlElementName.HTML);
//		HtmlElement element = (HtmlElement)list.get(0);
//		List<HtmlElement> nameList = element.getElementsByName("search");
//		if (nameList.size() != 2) {
//			fail();
//		}
//		List<HtmlElement> nameNonList = element.getElementsByName("aaa");
//		if (nameNonList.size() != 0) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getElementType() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> list =  xmlDocument.getElement(HtmlElementName.HTML);
//		HtmlElement element = (HtmlElement)list.get(0);
//		HtmlElementName elementType = element.getElementType();
//		if (elementType != HtmlElementName.HTML) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getHrefList() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> list =  xmlDocument.getElement(HtmlElementName.HTML);
//		HtmlElement element = (HtmlElement)list.get(0);
//		List<HtmlElement> anchorList = element.getHrefList();
//		if (anchorList.size() != 437) {
//			fail();
//		}
//	}
//	
//	@Test
//	public void getFormElement() {
//		jp.co.dk.document.xml.XmlDocument xmlDocument = super.createXmlDocument();
//		List<Element> list =  xmlDocument.getElement(HtmlElementName.HTML);
//		HtmlElement element = (HtmlElement)list.get(0);
////		List<Form> formList = element.getFormElement();
////		if (formList.size() != 3) {
////			fail();
////		}
//	}
//	
//	@Test
//	public void save() {
//		try {
//			jp.co.dk.document.xml.XmlDocument file = super.createXmlDocument();
//			java.io.File saveFile = file.save(super.getTestTmpDir(), "XML.xml");
//			super.assertFileEquals(saveFile, super.getFileByOwnClass("XML.xml"));
//		} catch (DocumentException e) {
//			fail(e);
//		}
//		
//		try {
//			jp.co.dk.document.xml.XmlDocument file = super.createXmlDocument();
//			file.save(super.getFraudTestTmpDir(), "XML.xml");
//			fail();
//		} catch (DocumentException e) {
//			if (e.getMessageObj() != DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND) {
//				fail(e);
//			}
//		}
//		
//		try {
//			jp.co.dk.document.xml.XmlDocument file = super.createXmlDocument();
//			file.save(super.getTestTmpFile(), "XML.xml");
//			fail();
//		} catch (DocumentException e) {
//			if (e.getMessageObj() != DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY) {
//				fail(e);
//			}
//		}
//		
//		try {
//			jp.co.dk.document.xml.XmlDocument file = super.createXmlDocument();
//			file.save(super.getTestTmpDir(), super.getTestTmpFile().getName());
//			fail();
//		} catch (DocumentException e) {
//			if (e.getMessageObj() != DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH) {
//				fail(e);
//			}
//		}
//	}
//}
//
//enum XmlElementName implements ElementName {
//	会員情報("会員情報"),
//	
//	会員("会員"),
//	
//	ID("ID"),
//	
//	住所("住所"),
//	
//	メール("メール"),
//	
//	;
//	
//	private String str;
//	
//	private XmlElementName (String str) {
//		this.str = str;
//	}
//	
//	@Override
//	public String getName() {
//		return this.str;
//	} 
	@Test
	public void empty() {
		
	}
}

class XmlELementSelecter implements ElementSelector {

	@Override
	public boolean judgment(Element element) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
