package jp.co.dk.document.html;

import static jp.co.dk.document.message.DocumentMessage.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.exception.HtmlDocumentException;

public class HtmlElementTest extends DocumentFoundationTest {
	
	@Test
	public void constractor() throws DocumentException {
		// nullにてインスタンス生成した場合、例外を送出すること
		try {
			String elementStr = null;
			new HtmlElement(elementStr, new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_IS_NOT_SET);
		}
		
		// 空文字にてインスタンス生成した場合、例外を送出すること
		try {
			new HtmlElement("", new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_IS_NOT_SET);
		}
				
		// 要素を表す文字列、ファクトリにnullが設定されていた場合、例外が送出すること
		try {
			new HtmlElement("<input type=\"test\" name=\"name\" value=\"value\"/>", null);
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_FACTORY_IS_NOT_SET);
		}
		
		// 不正な文字列にてインスタンス生成した場合、例外を送出すること
		try {
			new HtmlElement("あいう", new HtmlElementFactory());
			fail();
		} catch (HtmlDocumentException e) {	
			assertEquals(e.getMessageObj(), ERROR_ELEMENT_STRING_CONVERT);
		}
		
		// 不正な文字列にてインスタンス生成した場合、例外を送出すること
		try {
			new HtmlElement("abc", new HtmlElementFactory());
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
			assertTrue  (htmlElement.hasElement(HtmlElementName.A));
			assertEquals(htmlElement.getChildElement().size(), 0);
			assertFalse (htmlElement.hasChildElement());
			assertTrue  (htmlElement.isElement(HtmlElementName.A));
			assertEquals(htmlElement.getAttribute("href"), "a.shtml");
			assertTrue  (htmlElement.hasAttribute(HtmlAttributeName.HREF.getName()));
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
			assertEquals(htmlElement.getContent(), "サンプル1サンプル2サンプル3");
			assertTrue(htmlElement.hasAttribute(HtmlAttributeName.NAME.getName()));
			assertEquals(htmlElement.getTagName(),"select");
			assertEquals(htmlElement.getContent(), "サンプル1サンプル2サンプル3");
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
	public void getElement() throws DocumentException {
		// HTML内に存在するすべての要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getElement();
			assertThat(htmlElements.size(), is(7));
			assertThat(htmlElements.get(0).getTagName(), is("#root"));
			assertThat(htmlElements.get(1).getTagName(), is("html"));
			assertThat(htmlElements.get(2).getTagName(), is("head"));
			assertThat(htmlElements.get(3).getTagName(), is("meta"));
			assertThat(htmlElements.get(4).getTagName(), is("title"));
			assertThat(htmlElements.get(5).getTagName(), is("body"));
			assertThat(htmlElements.get(6).getTagName(), is("p"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する指定の要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getElement(HtmlElementName.HTML);
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("html"));
			
			List<Element> pElements = htmlElement.getElement(HtmlElementName.P);
			assertThat(pElements.size(), is(2));
			assertThat(pElements.get(0).getTagName(), is("p"));
			assertThat(pElements.get(1).getTagName(), is("p"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する指定の要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getElement(new ElementSelector(){
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
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getChildElement();
			assertThat(htmlElements.size(), is(2));
			assertThat(htmlElements.get(0).getTagName(), is("head"));
			assertThat(htmlElements.get(1).getTagName(), is("body"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getChildElement(HtmlElementName.HEAD);
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("head"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getChildElement(HtmlElementName.P);
			assertThat(htmlElements.size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getChildElement(new ElementSelector() {
				@Override
				public boolean judgment(Element element) {
					if (element.getTagName().equals("head")) return true; 
					return false;
				}
			});
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("head"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に存在する子要素を取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			List<Element> htmlElements = htmlElement.getChildElement(new ElementSelector() {
				@Override
				public boolean judgment(Element element) {
					if (element.getTagName().equals("p")) return true; 
					return false;
				}
			});
			assertThat(htmlElements.size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getAllElement() throws DocumentException {
//		jp.co.dk.document.html.HtmlElement htmlElement = super.createHtmlElement("jp/co/dk/document/html/HTML_getDocument001.html");
//		List<jp.co.dk.document.Element> elementList = htmlElement.getChildElement();
//		List<jp.co.dk.document.Element> allElementList = ((HtmlElement)elementList.get(0)).getAllElement();
//		assertEquals(allElementList.size(), 0);
	}
	
	@Test
	public void hasChildElement() throws DocumentException {
		// HTML内に子要素が存在した場合、trueを返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasChildElement(), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内に子要素が存在しなかった場合、falseを返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<body>");
			html.append("</body>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasChildElement(), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTML内のcontentのみしかなかった場合、falseが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<body>");
			html.append("this is test.");
			html.append("</body>"); 
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasChildElement(), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void isElement() throws DocumentException {
		try {
			StringBuilder html = new StringBuilder();
			html.append("<body>");
			html.append("this is test.");
			html.append("</body>"); 
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.isElement(HtmlElementName.BODY), is(true));
			assertThat(htmlElement.isElement(HtmlElementName.P), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void hasElement() throws DocumentException {
		// 指定の子要素が存在した場合、trueが返却されていること、存在しない要素を指定した場合、falseが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			// 最上位要素の場合
			assertThat(htmlElement.hasElement(HtmlElementName.HTML), is(true));
			// 最上位要素直下の場合
			assertThat(htmlElement.hasElement(HtmlElementName.HEAD), is(true));
			// 最上位要素直下より更に深い場合
			assertThat(htmlElement.hasElement(HtmlElementName.P),    is(true));
			// 存在しない要素の場合
			assertThat(htmlElement.hasElement(HtmlElementName.AUDIO),is(false));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getAttribute() throws DocumentException {
		// タグに属性が存在する場合、且つ存在する属性を取得した場合、正常に取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html id=\"test\">");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"test\">");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getAttribute("id"), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
		
			
		// タグに属性が存在する場合、且つ存在しない属性を取得した場合、nullが取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html id=\"test\">");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"test\">");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getAttribute("name"), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// タグに属性が存在しない場合、nullが取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"test\">");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getAttribute("id"), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void hasAttribute() throws DocumentException {
		// タグに属性が存在する場合、且つ存在する属性を取得した場合、正常に取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<a id=\"test\">");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasAttribute("id"), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
			
		// タグに属性が存在する場合、且つ存在しない属性を取得した場合、nullが取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<a id=\"test\">");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasAttribute("name"), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// タグに属性が存在しない場合、nullが取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<a/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasAttribute("id"), is(false));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 属性があるが値が設定されていない場合でもtrueが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<a test/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasAttribute("test"), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 属性があるが値が設定されていない場合でもtrueが返却されること（属性が複数の場合）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"checkbox\" name=\"test\" value=\"checkboxvalue\" checked />");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.hasAttribute("checked"), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
	}
	
	@Test
	public void getTagName() throws DocumentException {
		// 要素のタグ名称を取得した場合、正常に取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"test\">");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getTagName(), is("#root"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素のタグ名称を取得した場合、正常に取得できること
		// 大文字でも小文字として取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<HTML>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p id=\"test\">");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</HTML>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getTagName(), is("#root"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getContent() throws DocumentException {
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("this is test.");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getContent(), is("this is test."));
		} catch (DocumentException e) {
			fail(e);
		}
		
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("this is line 1.");
			html.append("<p>");
			html.append("this is line 2.");
			html.append("</p>");
			html.append("<p>");
			html.append("this is line 3.");
			html.append("</p>");
			html.append("this is line 4.");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			StringBuilder result = new StringBuilder();
			result.append("this is title");
			result.append("this is line 1.");
			result.append("this is line 2.");
			result.append("this is line 3.");
			result.append("this is line 4.");
			assertThat(htmlElement.getContent(), is(result.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getId() throws DocumentException {
		// IDが設定されていない場合空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getId(), is(""));
			assertThat(htmlElement.getId(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// IDが設定されていた場合そのIDが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\" id=\"test\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getId(), is("test"));
			assertThat(htmlElement.getId(), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getName() throws DocumentException {
		// NAMEが設定されていない場合空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getName(), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// NAMEが設定されていた場合そのNAMEが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\" name=\"test\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getName(), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getClassList() throws DocumentException {
		// CLASSが設定されていない場合空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getClassList().size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// CLASSが設定されていた場合そのIDが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\" class=\"test\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getClassList().size(), is(1));
			assertThat(htmlElement.getClassList().get(0), is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// CLASSが設定されていた場合そのIDが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<input type=\"text\" class=\"test1 test2\"/>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getClassList().size(), is(2));
			assertThat(htmlElement.getClassList().get(0), is("test1"));
			assertThat(htmlElement.getClassList().get(1), is("test2"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getElementById() throws DocumentException {
		// 要素にIDが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementById("test"), notNullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のIDが存在する場合、指定の要素が取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\" id=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementById("test").getTagName(), is("input"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のIDが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\" id=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementById("nothing"), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のIDが存在する場合、指定の要素が取得できること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\"     id=\"test\"/>");
			html.append("<input type=\"checkbox\" id=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementById("test").getTagName(), is("input"));
			assertThat(htmlElement.getElementById("test").getAttribute("type"), is("text"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のIDが存在する場合、指定の要素が取得できること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p id=\"test\">");
			html.append("<input type=\"text\"     id=\"test\"/>");
			html.append("<input type=\"checkbox\" id=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementById("test").getTagName(), is("p"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getElementByName() throws DocumentException {
		// 要素にNAMEが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementsByName("test").size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のNAMEが存在する場合、指定の要素が取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\" name=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementsByName("test").size(), is(1));
			assertThat(htmlElement.getElementsByName("test").get(0).getTagName(), is("input"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のNAMEが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\" name=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementsByName("nothing").size(), is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のNAMEが存在する場合、指定の要素が取得できること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<input type=\"text\"     name=\"test\"/>");
			html.append("<input type=\"checkbox\" name=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementsByName("test").size(), is(2));
			assertThat(htmlElement.getElementsByName("test").get(0).getTagName(), is("input"));
			assertThat(htmlElement.getElementsByName("test").get(0).getAttribute("type"), is("text"));
			assertThat(htmlElement.getElementsByName("test").get(1).getTagName(), is("input"));
			assertThat(htmlElement.getElementsByName("test").get(1).getAttribute("type"), is("checkbox"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素に指定のNAMEが存在する場合、指定の要素が取得できること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p name=\"test\">");
			html.append("<input type=\"text\"     name=\"test\"/>");
			html.append("<input type=\"checkbox\" name=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementsByName("test").size(), is(3));
			assertThat(htmlElement.getElementsByName("test").get(0).getTagName(), is("p"));
			assertThat(htmlElement.getElementsByName("test").get(1).getTagName(), is("input"));
			assertThat(htmlElement.getElementsByName("test").get(1).getAttribute("type"), is("text"));
			assertThat(htmlElement.getElementsByName("test").get(2).getTagName(), is("input"));
			assertThat(htmlElement.getElementsByName("test").get(2).getAttribute("type"), is("checkbox"));
			
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getElementType() throws DocumentException {
		// HtmlElementNameに存在するタグで合った場合、そのHtmlElementNameが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementType() , is(HtmlElementName.P));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HtmlElementNameに存在しないタグで合った場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<nothing>");
			html.append("this is test.");
			html.append("</nothing>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getElementType() , nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getHrefList() throws DocumentException {
		// 子要素にアンカーが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getHrefList().size() , is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 子要素にアンカーが存在した場合、アンカーを保持した一覧が返却されること（単一）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<a href=\"test\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getHrefList().size() , is(1));
			assertThat(htmlElement.getHrefList().get(0).getTagName() , is("a"));
			assertThat(htmlElement.getHrefList().get(0).getAttribute("href") , is("test"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 子要素にアンカーが存在した場合、アンカーを保持した一覧が返却されること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("<a href=\"test1\"/>");
			html.append("<a href=\"test2\"/>");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getHrefList().size() , is(2));
			assertThat(htmlElement.getHrefList().get(0).getTagName() , is("a"));
			assertThat(htmlElement.getHrefList().get(0).getAttribute("href") , is("test1"));
			assertThat(htmlElement.getHrefList().get(1).getTagName() , is("a"));
			assertThat(htmlElement.getHrefList().get(1).getAttribute("href") , is("test2"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 自身の要素がアンカーである場合、且つアンカーがネストしていた場合でも自身と、子要素のアンカーが取得できること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<a href=\"test1\">");
			html.append("<a href=\"test2\"/>");
			html.append("</a>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getHrefList().size() , is(2));
			assertThat(htmlElement.getHrefList().get(0).getTagName() , is("a"));
			assertThat(htmlElement.getHrefList().get(0).getAttribute("href") , is("test1"));
			assertThat(htmlElement.getHrefList().get(1).getTagName() , is("a"));
			assertThat(htmlElement.getHrefList().get(1).getAttribute("href") , is("test2"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void equals() throws DocumentException {
		try {
			
			jp.co.dk.document.html.HtmlElement htmlElement1 = new jp.co.dk.document.html.HtmlElement("<a href=\"test1\"/>", new HtmlElementFactory());
			jp.co.dk.document.html.HtmlElement htmlElement2 = new jp.co.dk.document.html.HtmlElement("<a href=\"test1\"/>", new HtmlElementFactory());
			jp.co.dk.document.html.HtmlElement htmlElement3 = new jp.co.dk.document.html.HtmlElement("<a href=\"test1\"/>", new HtmlElementFactory());
			
			List list = new ArrayList();
			jp.co.dk.document.html.HtmlElement difHtmlElement1 = new jp.co.dk.document.html.HtmlElement("<p/>", new HtmlElementFactory());
			jp.co.dk.document.html.HtmlElement difHtmlElement2 = new jp.co.dk.document.html.HtmlElement("<a href=\"test2\"/>", new HtmlElementFactory());
			list.add(difHtmlElement1);
			list.add(difHtmlElement2);
			
			testEquals(htmlElement1, htmlElement2, htmlElement3, list);
		} catch (DocumentException e) {
			fail(e);
		}
		
	}
	
	@Test
	public void getFormElementList() {
		// 要素にFORMが存在しない場合、空のリストが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getFormElementList().size() , is(0));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素にFORMが存在する場合、FORMを保持したリストが返却されること（単一）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<form>");
			html.append("<input type=\"text\"/>");
			html.append("</form>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getFormElementList().size() , is(1));
			assertThat(htmlElement.getFormElementList().get(0).getTagName() , is("input"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 要素にFORMが存在する場合、FORMを保持したリストが返却されること（複数）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<form>");
			html.append("<input type=\"text\"/>");
			html.append("</form>");
			html.append("<form>");
			html.append("<select><option value=\"\">test</option></select>");
			html.append("</form>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlElement htmlElement = new jp.co.dk.document.html.HtmlElement(html.toString(), new HtmlElementFactory());
			assertThat(htmlElement.getFormElementList().size() , is(2));
			assertThat(htmlElement.getFormElementList().get(0).getTagName() , is("input"));
			assertThat(htmlElement.getFormElementList().get(1).getTagName() , is("select"));
		} catch (DocumentException e) {
			fail(e);
		}
	}
}
