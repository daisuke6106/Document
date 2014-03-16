package jp.co.dk.document.html;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import mockit.Expectations;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.element.selector.AnchorHasImage;
import jp.co.dk.document.message.DocumentMessage;
import jp.co.dk.message.MessageInterface;

public class HtmlDocumentTest extends DocumentFoundationTest {
	
	@Test
	public void getTitle() {
		
		// TITLEタグがない場合、空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getTitle(), is (""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その文字列が返却されること（英字）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getTitle(), is ("this is title"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その文字列が返却されること（日本語）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>これはタイトルです</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getTitle(), is ("これはタイトルです"));
		} catch (DocumentException e) {
			fail(e);
		}
		// TITLEタグが複数ある場合、最初の要素を抽出されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>これはタイトルです１行目</title>");
			html.append("<title>これはタイトルです２行目</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getTitle(), is ("これはタイトルです１行目"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その中にタグが存在していた場合、空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>これはタイトルです<br/>改行</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getTitle(), is (""));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getEncode() throws DocumentException {
		// METAタグがない場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていた場合、且つUTF８が設定されていた場合、
		// HtmlCharSetName.UTF_8が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), is (HtmlCharSetName.UTF_8));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていなかった場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html;\">");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていた場合、且つ不明な文字列が返却されていた場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=unknown\">");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、http-equivが設定されていなかった場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}

		// METAタグがあり、http-equivがcontent-typeでなかった場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-style-type\" content=\"text/html; charset=unknown\">");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getElement() throws DocumentException {
		// HTML内に存在するすべての要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getElement();
			assertThat(htmlElements.size(), is(6));
			assertThat(htmlElements.get(0).getTagName(), is("html"));
			assertThat(htmlElements.get(1).getTagName(), is("head"));
			assertThat(htmlElements.get(2).getTagName(), is("meta"));
			assertThat(htmlElements.get(3).getTagName(), is("title"));
			assertThat(htmlElements.get(4).getTagName(), is("body"));
			assertThat(htmlElements.get(5).getTagName(), is("p"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する指定の要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test1.");
			html.append("</p>");
			html.append("<p>");
			html.append("this is test2.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getElement(HtmlElementName.HTML);
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("html"));
			
			List<Element> pElements = htmlDocument.getElement(HtmlElementName.P);
			assertThat(pElements.size(), is(2));
			assertThat(pElements.get(0).getTagName(), is("p"));
			assertThat(pElements.get(1).getTagName(), is("p"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する指定の要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"p1\">");
			html.append("this is test1.");
			html.append("</p>");
			html.append("<p id=\"p2\">");
			html.append("this is test2.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getElement(new ElementSelector(){
				@Override
				public boolean judgment(Element element) {
					if (element.getTagName().equals("p") && (element.getAttribute("id").equals("p1") || element.getAttribute("id").equals("p2"))) return true;
					return false;
				}
			});
			assertThat(htmlElements.size(), is(2));
			assertThat(htmlElements.get(0).getTagName(), is("p"));
			assertThat(htmlElements.get(0).getAttribute("id"), is("p1"));
			assertThat(htmlElements.get(1).getTagName(), is("p"));
			assertThat(htmlElements.get(1).getAttribute("id"), is("p2"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getChildElement() throws DocumentException {
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getChildElement();
			assertThat(htmlElements.size(), is(2));
			assertThat(htmlElements.get(0).getTagName(), is("head"));
			assertThat(htmlElements.get(1).getTagName(), is("body"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getChildElement(HtmlElementName.HEAD);
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("head"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createHtmlDocument(html.toString()));
			List<Element> htmlElements = htmlDocument.getChildElement(HtmlElementName.P);
			assertThat(htmlElements.size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
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
//		jp.co.dk.document.html.HtmlDocument htmlDocument1 = super.createHtmlDocument();
//		List<Element> elementList = htmlDocument1.getElement(HtmlElementName.LABEL);
//		Element element = elementList.get(0);
//		if (!element.getContent().equals("検索")) {
//			fail();
//		}
		
		// ドキュメントに対して実行して実行した、ドキュメント内に定義されているすべてのコンテキスト要素を取得できること
//		jp.co.dk.document.html.HtmlDocument htmlDocument2 = super.createHtmlDocument("jp/co/dk/document/html/HTML_getDocument001.html");
//		assertEquals(htmlDocument2.getContent(),"文書のタイトル\n文書の本体\n");
//		
//		jp.co.dk.document.html.HtmlDocument htmlDocument3 = super.createHtmlDocument("jp/co/dk/document/html/HTML.html");
//		assertEquals(htmlDocument3.getContent(),"文書のタイトル\n文書の本体\n");
		
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
	public void getAName() throws DocumentException {
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
	public void save() throws DocumentException {
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
			file.save(super.getTestTmpDir(), "HTML1.html");
			file.save(super.getTestTmpDir(), "HTML1.html");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH) {
				fail(e);
			}
		}
	}
	
	private InputStream createHtmlDocument(String htmlStr) {
		try {
			return new ByteArrayInputStream(htmlStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			fail(e);
		}
		return null;
	}
}
