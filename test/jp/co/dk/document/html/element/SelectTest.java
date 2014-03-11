package jp.co.dk.document.html.element;

import static org.junit.Assert.*;

import java.util.List;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import mockit.Mocked;

import org.junit.Test;

public class SelectTest extends DocumentFoundationTest {

	@Test
	public void getOption() {
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"example\">");
		sb.append("<option value=\"サンプル1\">サンプル1</option>");
		sb.append("<option value=\"サンプル2\">サンプル2</option>");
		sb.append("<option value=\"サンプル3\">サンプル3</option>");
		sb.append("</select>");
		try {
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			List<Option> optionList = select.getOption();
			assertEquals(optionList.size(), 3);
			assertEquals(optionList.get(0).getTagName(), "option");
			assertEquals(optionList.get(0).getAttribute("value"), "サンプル1");
			assertEquals(optionList.get(0).getContent(), "サンプル1");
			assertEquals(optionList.get(1).getTagName(), "option");
			assertEquals(optionList.get(1).getAttribute("value"), "サンプル2");
			assertEquals(optionList.get(1).getContent(), "サンプル2");
			assertEquals(optionList.get(2).getTagName(), "option");
			assertEquals(optionList.get(2).getAttribute("value"), "サンプル3");
			assertEquals(optionList.get(2).getContent(), "サンプル3");
			
		} catch (HtmlDocumentException e) {	fail(e);}
	}
	
	@Test
	public void getSelectedOption() {
		StringBuilder sb = new StringBuilder();
		sb.append("<select name=\"example\">");
		sb.append("<option value=\"サンプル1\">サンプル1</option>");
		sb.append("<option value=\"サンプル2\">サンプル2</option>");
		sb.append("<option value=\"サンプル3\">サンプル3</option>");
		sb.append("</select>");
		try {
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			Option selectedOption = select.getSelectedOption();
			assertNull(selectedOption);
		} catch (HtmlDocumentException e) {	fail(e);}
	}
}
