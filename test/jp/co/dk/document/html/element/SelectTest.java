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
	
	@Test
	public void getDefaultSlectedOption() {
		
		try {
			StringBuilder sb1 = new StringBuilder();
			sb1.append("<select name=\"example\">");
			sb1.append("<option value=\"サンプル1\">サンプル1</option>");
			sb1.append("<option value=\"サンプル2\">サンプル2</option>");
			sb1.append("<option value=\"サンプル3\">サンプル3</option>");
			sb1.append("</select>");
			Select select = new Select(new HtmlElement(sb1.toString(), new HtmlElementFactory()));
			Option selectedOption = select.getDefaltSelectedOption();
			assertNull(selectedOption);
		} catch (HtmlDocumentException e) {	fail(e);}
		
		try {
			StringBuilder sb2 = new StringBuilder();
			sb2.append("<select name=\"example\">");
			sb2.append("<option value=\"サンプル1\" selected>サンプル1</option>");
			sb2.append("<option value=\"サンプル2\">サンプル2</option>");
			sb2.append("<option value=\"サンプル3\">サンプル3</option>");
			sb2.append("</select>");
			Select select = new Select(new HtmlElement(sb2.toString(), new HtmlElementFactory()));
			Option selectedOption = select.getDefaltSelectedOption();
			assertThat(selectedOption.getAttribute("value"), is ("サンプル1"));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		try {
			StringBuilder sb3 = new StringBuilder();
			sb3.append("<select name=\"example\">");
			sb3.append("<option value=\"サンプル1\">サンプル1</option>");
			sb3.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb3.append("<option value=\"サンプル3\">サンプル3</option>");
			sb3.append("</select>");
			Select select = new Select(new HtmlElement(sb3.toString(), new HtmlElementFactory()));
			Option selectedOption = select.getDefaltSelectedOption();
			assertThat(selectedOption.getAttribute("value"), is ("サンプル2"));
		} catch (HtmlDocumentException e) {	fail(e);}
	}
	
	@Test
	public void select() {
		// OPTION用を保持していないselectに対してインデックスを指定した場合、falseが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.select(0), is(false));
			assertNull(select.getSelectedOption());
			assertNull(select.getDefaltSelectedOption());
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// 3のみOPTION要素を保持するSELECTに対して4つ目を選択するようにした場合、falseが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.select(4), is(false));
			assertThat(select.getSelectedOption().getValue(), is ("サンプル2"));
			assertThat(select.getDefaltSelectedOption().getValue(), is ("サンプル2"));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// 存在するインデックスを指定した場合、trueが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.select(4), is(false));
			assertThat(select.getSelectedOption().getValue(), is ("サンプル2"));
			assertThat(select.getDefaltSelectedOption().getValue(), is ("サンプル2"));
		} catch (HtmlDocumentException e) {	fail(e);}
	}
	
	@Test
	public void isDisabled() {
		// disabledタグが設定されていない場合、falseが返却されること
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.isDisabled(), is(false));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// disabledタグが設定されていない場合、trueが返却されること
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\" disabled>");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.isDisabled(), is(true));
		} catch (HtmlDocumentException e) {	fail(e);}
	}
	
	@Test
	public void getMessage() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is("example="));
		} catch (HtmlDocumentException e) {fail(e);}
	}
}
