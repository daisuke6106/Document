package jp.co.dk.document.html.element;

import java.util.List;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import org.junit.Test;

public class SelectTest extends DocumentFoundationTest {
	
	@Test
	public void constractor() {
		// OPTION要素が設定されていないSELECT要素であった場合、正常にインスタンスが生成されること。
		// オプション一覧フィールドが期待値通りであること。
		// デフォルトオプションフィールドが期待値通りであること。
		// 選択オプションフィールドが期待値通りであること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.option.size(), is(0));
			assertNull(select.defaltSelected);
			assertNull(select.selected);
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// OPTION要素が設定されているSELECT要素であった場合、正常にインスタンスが生成されること。
		// オプション一覧フィールドが期待値通りであること。
		// デフォルトオプションフィールドが期待値通りであること。
		// 選択オプションフィールドが期待値通りであること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.option.size(), is(3));
			assertThat(select.option.get(0).getValue(), is("サンプル1"));
			assertThat(select.option.get(1).getValue(), is("サンプル2"));
			assertThat(select.option.get(2).getValue(), is("サンプル3"));
			assertNull(select.defaltSelected);
			assertNull(select.selected);
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// selected属性を保持するOPTION要素が設定されているSELECT要素であった場合、正常にインスタンスが生成されること。
		// オプション一覧フィールドが期待値通りであること。
		// デフォルトオプションフィールドが期待値通りであること。
		// 選択オプションフィールドが期待値通りであること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\" selected>サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.option.size(), is(3));
			assertThat(select.option.get(0).getValue() , is("サンプル1"));
			assertThat(select.option.get(1).getValue() , is("サンプル2"));
			assertThat(select.option.get(2).getValue() , is("サンプル3"));
			assertThat(select.defaltSelected.getValue(), is("サンプル1"));
			assertThat(select.selected.getValue()      , is("サンプル1"));
		} catch (HtmlDocumentException e) {	fail(e);}
	}
	
	@Test
	public void getOption() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
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
	public void getValue() {
		// OPTIONタグが設定されていない場合、空文字が返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getValue(), is(""));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// OPTIONタグが設定されている、且つ、selectメソッドにて、指定の要素が選択状態で合った場合、
		// その指定の要素のvalueが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			select.select(0);
			assertThat(select.getValue(), is("サンプル1"));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// OPTIONタグが設定されている、且つ、selected属性が付与されている場合、
		// その要素のvalueが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\" selected>サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getValue(), is("サンプル1"));
		} catch (HtmlDocumentException e) {	fail(e);}
		
		// OPTIONタグが設定されている、且つselected属性、またはselectメソッドが実施されていない場合、
		// OPTIONの最初の属性のvalueが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\" selected>サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getValue(), is("サンプル1"));
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
		// OPTIONが存在しない且つ、nameが存在しないselect要素で合った場合、空のメッセージが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is(""));
		} catch (HtmlDocumentException e) {fail(e);}
				
		// OPTIONが存在しない且つ、nameが存在するselect要素で合った場合、nameのみのメッセージが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is("example="));
		} catch (HtmlDocumentException e) {fail(e);}
		
		// OPTIONが存在しない且つ、nameが存在するselect要素で合った場合、nameのみのメッセージが返却されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\" disabled>");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is(""));
		} catch (HtmlDocumentException e) {fail(e);}
		
				
		// OPTIONが存在する且つ、selectedが存在しない、且つ、何も選択していない場合、
		// 最初の要素が選択状態としてメッセージが生成されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is("example=サンプル1"));
		} catch (HtmlDocumentException e) {fail(e);}
		
		// OPTIONが存在する且つ、selectedが存在する、且つ、何も選択していない場合、
		// selectedの要素が選択状態としてメッセージが生成されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			assertThat(select.getMessage(), is("example=サンプル2"));
		} catch (HtmlDocumentException e) {fail(e);}
		
		// OPTIONが存在する且つ、selectedが存在しない、且つ、要素を選択した場合、
		// 選択した要素が選択状態としてメッセージが生成されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\">サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			select.select(2);
			assertThat(select.getMessage(), is("example=サンプル3"));
		} catch (HtmlDocumentException e) {fail(e);}
		
		// OPTIONが存在する且つ、selectedが存在する、且つ、要素を選択した場合、
		// 選択した要素が選択状態としてメッセージが生成されること。
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<select name=\"example\">");
			sb.append("<option value=\"サンプル1\">サンプル1</option>");
			sb.append("<option value=\"サンプル2\" selected>サンプル2</option>");
			sb.append("<option value=\"サンプル3\">サンプル3</option>");
			sb.append("</select>");
			Select select = new Select(new HtmlElement(sb.toString(), new HtmlElementFactory()));
			select.select(2);
			assertThat(select.getMessage(), is("example=サンプル3"));
		} catch (HtmlDocumentException e) {fail(e);}
		
	}
}
