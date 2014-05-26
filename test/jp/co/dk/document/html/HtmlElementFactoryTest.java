package jp.co.dk.document.html;

import org.junit.Test;

import jp.co.dk.document.Element;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.InputTypeName;
import jp.co.dk.document.html.element.A;
import jp.co.dk.document.html.element.CheckBox;
import jp.co.dk.document.html.element.File;
import jp.co.dk.document.html.element.Form;
import jp.co.dk.document.html.element.Hidden;
import jp.co.dk.document.html.element.Image;
import jp.co.dk.document.html.element.Meta;
import jp.co.dk.document.html.element.Option;
import jp.co.dk.document.html.element.Password;
import jp.co.dk.document.html.element.Radio;
import jp.co.dk.document.html.element.Reset;
import jp.co.dk.document.html.element.Select;
import jp.co.dk.document.html.element.Submit;
import jp.co.dk.document.html.element.Text;
import jp.co.dk.document.message.DocumentMessage;

public class HtmlElementFactoryTest extends DocumentFoundationTest {
	
	@Test
	public void convert() throws DocumentException {
		jp.co.dk.document.html.HtmlElementFactory sut = new HtmlElementFactory();
		
		// HTML要素以外を渡した場合、例外が発生すること。
		try {
			StringBuilder xml = new StringBuilder();
			xml.append("<parent>");
			xml.append("  <child1>");
			xml.append("    <child1_1>");
			xml.append("    </child1_1>");
			xml.append("  </child1>");
			xml.append("  <child2>");
			xml.append("  </child2>");
			xml.append("</parent>");
			jp.co.dk.document.xml.XmlDocument xmlDocument = new jp.co.dk.document.xml.XmlDocument(createDocumentStream(xml.toString()));
			sut.convert(xmlDocument.getChildElement().get(0));
			fail();
		} catch (DocumentException e) {
			assertThat((DocumentMessage)e.getMessageObj(), is(DocumentMessage.ERROR_ELEMENT_CONVERT));
		}
		
		// HTML要素から要素タイプを検索した際にnullが返却された場合、HTML要素がそのまま返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<unknown/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(HtmlElement.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// アンカー要素を変換した場合、アンカーオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<a/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(A.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// イメージ要素を変換した場合、イメージオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<img/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Image.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// メタ要素を変換した場合、メタオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<meta/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Meta.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// フォーム要素を変換した場合、フォームオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<form/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Form.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がtest）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"test\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Text.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がtext）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"text\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Text.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がpassword）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"password\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Password.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がcheckbox）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"checkbox\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(CheckBox.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がradio）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"radio\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Radio.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がsubmit）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"submit\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Submit.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がimage）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"image\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Image.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がreset）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"reset\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Reset.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がfile）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"file\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(File.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット（属性がhidden）の要素を変換した場合、インプットオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"hidden\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Hidden.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// アンカー要素を変換した場合、セレクトオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<select/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Select.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// アンカー要素を変換した場合、オプションオブジェクトが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<option/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			Element result = sut.convert(htmlDocument.getChildElement().get(0));
			assertThat(result.getClass().toString(), is(Option.class.toString()));
		} catch (DocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getInpuTypeName() {
		
		jp.co.dk.document.html.HtmlElementFactory sut = new HtmlElementFactory();
		
		// 引数に設定された要素がインプット要素でない場合、nullが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<unknown/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			HtmlElement htmlElement = (HtmlElement)htmlDocument.getChildElement().get(0);
			assertThat(sut.getInpuTypeName(htmlElement), is(null));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット要素からタイプ属性を取得し、その値が"text"の場合、InputTypeName.TEXTが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"text\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			HtmlElement htmlElement = (HtmlElement)htmlDocument.getChildElement().get(0);
			assertThat(sut.getInpuTypeName(htmlElement), is(InputTypeName.TEXT));
		} catch (DocumentException e) {
			fail(e);
		}
		
		// インプット要素からタイプ属性を取得し、その値がInputTypeNameに定義されていない値の場合、nullが返却されること。
		try {
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<input type=\"unknown\"/>");
			html.append("</html>");
			jp.co.dk.document.html.HtmlDocument htmlDocument = new jp.co.dk.document.html.HtmlDocument(createDocumentStream(html.toString()));
			HtmlElement htmlElement = (HtmlElement)htmlDocument.getChildElement().get(0);
			assertThat(sut.getInpuTypeName(htmlElement), is(null));
		} catch (DocumentException e) {
			fail(e);
		}
	}
}
