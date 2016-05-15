package jp.co.dk.document.html;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.message.DocumentMessage;
import jp.co.dk.message.MessageInterface;

public class HtmlDocumentTest extends DocumentFoundationTest {
	
	@Test
	public void constractor() {
		// 読み込み可能なInputStreamを渡した場合、正常にインスタンス作成が行われているかを確認
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.document, notNullValue());
			assertThat(htmlDocument.html, notNullValue());
			assertThat(htmlDocument.head, notNullValue());
			assertThat(htmlDocument.body, notNullValue());
			assertThat(htmlDocument.elementFactory, notNullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// 読み込み不可能なInputStreamを渡した場合、例外が発生することを確認
		try {
			InputStream dummyInputStream = new InputStream() {
				@Override
				public int read() throws IOException {
					throw new IOException("dummy io exception");
				}
			};
			new jp.co.dk.document.html.HtmlDocument(dummyInputStream);
			fail();
		} catch (DocumentException e) {
			assertThat(e.getMessageObj(), is((MessageInterface)DocumentMessage.ERROR_FILE_READ));
			assertThat(e.getThrowable(), instanceOf(IOException.class));
			assertThat(e.getThrowable().getMessage(), is("dummy io exception"));
		}
		
		// HTML要素でないテキストを読み込んだとしても、正常にインスタンス作成が行われているかを確認
		try {
			StringBuilder html = new StringBuilder();
			html.append("not html text.");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.document, notNullValue());
			assertThat(htmlDocument.html, notNullValue());
			assertThat(htmlDocument.head, notNullValue());
			assertThat(htmlDocument.body, notNullValue());
			assertThat(htmlDocument.elementFactory, notNullValue());
			assertThat(htmlDocument.elementFactory, notNullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// バイナリデータを読み込んだ場合、正常にインスタンス作成が行われているかを確認
		try {
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			fail();
		} catch (DocumentException e) {
			assertThat(e.getMessageObj(), is((MessageInterface)DocumentMessage.ERROR_FAILED_TO_PARSE_HTML));
		} catch (IOException e) {
			fail(e);
		}
		
		// UTF-8のHTMLを読み込んだ場合でも正常にう処理できること。
		try {
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML_UTF-8.html"));
			assertThat(htmlDocument.getTitle(), is("UTF-8の文書"));
		} catch (DocumentException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
		
		// Shift_JISのHTMLを読み込んだ場合でも正常にう処理できること。
		try {
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML_SJIS.html"));
			assertThat(htmlDocument.getTitle(), is("Shift_JISの文書"));
		} catch (DocumentException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
		
		// EUC-JPのHTMLを読み込んだ場合でも正常にう処理できること。
		try {
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML_EUC-JP.html"));
			assertThat(htmlDocument.getTitle(), is("EUC-JPの文書"));
		} catch (DocumentException e) {
			fail(e);
		} catch (IOException e) {
			fail(e);
		}
	}
	
	@Test
	public void getTitle() {
		
		// TITLEタグがない場合、空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTitle(), is (""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その文字列が返却されること（英字）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTitle(), is ("this is title"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その文字列が返却されること（日本語）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
			html.append("<title>これはタイトルです</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTitle(), is ("これはタイトルです"));
		} catch (DocumentException e) {
			fail(e);
		}
		// TITLEタグが複数ある場合、最初の要素を抽出されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
			html.append("<title>これはタイトルです１行目</title>");
			html.append("<title>これはタイトルです２行目</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTitle(), is ("これはタイトルです１行目"));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// TITLEタグがある場合、その中にタグが存在していた場合、空文字が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
			html.append("<title>これはタイトルです<br/>改行</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTitle(), is ("これはタイトルです<br/>改行"));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていた場合、且つUTF８が設定されていた場合、
		// HtmlCharSetName.UTF_8が返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getEncode(), is (HtmlCharSetName.UTF_8));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていなかった場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}
		
		// METAタグがあり、charsetが設定されていた場合、且つ不明な文字列が返却されていた場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getEncode(), nullValue());
		} catch (DocumentException e) {
			fail(e);
		}

		// METAタグがあり、http-equivがcontent-typeでなかった場合、nullが返却されること
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
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
			html.append("<!DOCTYPE html>");
			html.append("<html>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>this is title</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<div>");
			html.append("this is test.");
			html.append("</div>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getElement();
			assertThat(htmlElements.size(), is(7));
			assertThat(htmlElements.get(0).getTagName(), is("#root"));
			assertThat(htmlElements.get(1).getTagName(), is("html"));
			assertThat(htmlElements.get(2).getTagName(), is("head"));
			assertThat(htmlElements.get(3).getTagName(), is("meta"));
			assertThat(htmlElements.get(4).getTagName(), is("title"));
			assertThat(htmlElements.get(5).getTagName(), is("body"));
			assertThat(htmlElements.get(6).getTagName(), is("div"));
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
			html.append("<div>");
			html.append("this is test1.");
			html.append("</div>");
			html.append("<div>");
			html.append("this is test2.");
			html.append("</div>");
			html.append("</body>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getElement(HtmlElementName.HTML);
			assertThat(htmlElements.size(), is(1));
			assertThat(htmlElements.get(0).getTagName(), is("html"));
			
			List<Element> pElements = htmlDocument.getElement(HtmlElementName.DIV);
			assertThat(pElements.size(), is(2));
			assertThat(pElements.get(0).getTagName(), is("div"));
			assertThat(pElements.get(1).getTagName(), is("div"));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getChildElement(HtmlElementName.HEAD);
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getChildElement(HtmlElementName.P);
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getChildElement(new ElementSelector() {
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			List<Element> htmlElements = htmlDocument.getChildElement(new ElementSelector() {
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
	public void hasChildElement() throws DocumentException {
		// #rootが最上位となるため、以下なる状況でも子要素は存在する。
		// HTML内に子要素が存在した場合、trueを返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<body>");
			html.append("<p>");
			html.append("this is test.");
			html.append("</p>");
			html.append("</body>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.hasChildElement(), is(true));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// HTMLでない文字列でも、trueが返却されること。
		try {
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument("");
			assertThat(htmlDocument.hasChildElement(), is(true));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.isElement(HtmlElementName.ROOT), is(true));
			assertThat(htmlDocument.isElement(HtmlElementName.BODY), is(false));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			// 最上位要素の場合
			assertThat(htmlDocument.hasElement(HtmlElementName.HTML), is(true));
			// 最上位要素直下の場合
			assertThat(htmlDocument.hasElement(HtmlElementName.HEAD), is(true));
			// 最上位要素直下より更に深い場合
			assertThat(htmlDocument.hasElement(HtmlElementName.P),    is(true));
			// 存在しない要素の場合
			assertThat(htmlDocument.hasElement(HtmlElementName.AUDIO),is(false));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getAttribute("id"), is(""));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getAttribute("name"), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// タグに属性が存在しない場合、nullが取得できること
		try {
			StringBuilder html = new StringBuilder();
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getAttribute("id"), is(""));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void hasAttribute() throws DocumentException {
		// HtmlDocumentの最上位は#rootとなるため、hasAttributeは常にfalseであること。
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.hasAttribute("id"), is(false));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.hasAttribute("name"), is(false));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.hasAttribute("id"), is(false));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTagName(), is("#root"));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getTagName(), is("#root"));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			assertThat(htmlDocument.getContent(), is("this is test."));
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
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(html.toString());
			StringBuilder result = new StringBuilder();
			result.append("this is title ");
			result.append("this is line 1. ");
			result.append("this is line 2. ");
			result.append("this is line 3.");
			result.append("this is line 4.");
			assertThat(htmlDocument.getContent(), is(result.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	

	@Test
	public void save() throws DocumentException, IOException {
		// 存在するディレクトリに保存した場合、正常に保存できること。
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			java.io.File saveFile = file.save(super.getTestTmpDir(), "HTML.html");
			super.assertStreamEquals(new FileInputStream(saveFile), super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML.html"));
		} catch (DocumentException | FileNotFoundException e) {
			fail(e);
		}
		
		// 存在しないディレクトリに保存した場合、例外が発生すること
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			file.save(super.getFraudTestTmpDir(), "HTML.html");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND) {
				fail(e);
			}
		}
		
		// 保存先にファイルを指定した場合、例外が発生すること。
		try {
			jp.co.dk.document.html.HtmlDocument file = super.createHtmlDocument();
			file.save(super.getTestTmpFile(), "HTML.html");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY) {
				fail(e);
			}
		}
		
		// 保存先に同じ名前のファイルが存在した場合、例外が発生すること。
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
		
		// HtmlDocumentから文字コードを取得出来なかった場合、UTF8で保存されること。
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
		
		// 日本語を含む文字列で保存した場合（metaヘッダあり）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<HTML>");
			html.append("<head>");
			html.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
			html.append("<title>タイトル</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("これは本文です。");
			html.append("</p>");
			html.append("</body>");
			html.append("</HTML>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(new ByteArrayInputStream(html.toString().getBytes("UTF-8")));
			htmlDocument.save(super.getTestTmpDir(), "HTML2.html");
			assertStreamEquals(new FileInputStream (getTestFileInstance("HTML2.html")), new ByteArrayInputStream(html.toString().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			fail(e);
		} catch (DocumentException e) {
			fail(e);
		} catch (FileNotFoundException e) {
			fail(e);
		}
		
		// 日本語を含む文字列で保存した場合（metaヘッダなし）
		try {
			StringBuilder html = new StringBuilder();
			html.append("<!DOCTYPE html>");
			html.append("<HTML>");
			html.append("<head>");
			html.append("<title>タイトル</title>");
			html.append("</head>");
			html.append("<body>");
			html.append("<p>");
			html.append("これは本文です。");
			html.append("</p>");
			html.append("</body>");
			html.append("</HTML>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(new ByteArrayInputStream(html.toString().getBytes("UTF-8")));
			htmlDocument.save(super.getTestTmpDir(), "HTML3.html");
			assertStreamEquals(new FileInputStream (getTestFileInstance("HTML3.html")), new ByteArrayInputStream(html.toString().getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			fail(e);
		} catch (DocumentException e) {
			fail(e);
		} catch (FileNotFoundException e) {
			fail(e);
		}
	}
}
