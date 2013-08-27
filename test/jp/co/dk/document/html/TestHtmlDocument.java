package jp.co.dk.document.html;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.element.selector.AnchorHasImage;
import jp.co.dk.document.message.DocumentMessage;

public class TestHtmlDocument extends TestDocumentFoundation {
	
	@Test
	public void getTitle() {
		jp.co.dk.document.html.HtmlDocument htmlDocument = super.createHtmlDocument();
		assertThat(htmlDocument.getTitle(), is ("HyperText Markup Language - Wikipedia"));
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
//		jp.co.dk.document.html.HtmlDocument htmlDocument1 = super.createHtmlDocument();
//		List<Element> elementList = htmlDocument1.getElement(HtmlElementName.LABEL);
//		Element element = elementList.get(0);
//		if (!element.getContent().equals("検索")) {
//			fail();
//		}
		
		// ドキュメントに対して実行して実行した、ドキュメント内に定義されているすべてのコンテキスト要素を取得できること
		jp.co.dk.document.html.HtmlDocument htmlDocument2 = super.createHtmlDocument("jp/co/dk/document/html/HTML_getDocument001.html");
		assertEquals(htmlDocument2.getContent(),"文書のタイトル\n文書の本体\n");
		
		
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
	public void getAName() {
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
	public void save() {
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			java.io.File saveFile = file.save(super.getTestTmpDir(), "HTML.html");
			super.assertStreamEquals(new FileInputStream(saveFile), super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML.html"));
		} catch (DocumentException | FileNotFoundException e) {
			fail(e);
		}
		
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			file.save(super.getFraudTestTmpDir(), "HTML.html");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND) {
				fail(e);
			}
		}
		
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			file.save(super.getTestTmpFile(), "HTML.html");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY) {
				fail(e);
			}
		}
		
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			file.save(super.getTestTmpDir(), super.getTestTmpFile().getName());
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH) {
				fail(e);
			}
		}
	}
}
