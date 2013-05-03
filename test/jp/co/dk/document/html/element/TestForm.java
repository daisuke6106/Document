package jp.co.dk.document.html.element;

import static org.junit.Assert.*;

import java.util.List;

import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlRequestMethodName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class TestForm extends TestDocumentFoundation{
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void getMethod() {
		// Form要素が設定されていなかった場合、GETが返却されること
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.METHOD.getName());
			result = null;
		}};
		Form form1 = new Form(htmlElement);
		assertEquals(form1.getMethod(), HtmlRequestMethodName.GET);
		
		// Form要素が"GET"が設定されていた場合、GETが返却されること
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.METHOD.getName());
			result = "GET";
		}};
		Form form2 = new Form(htmlElement);
		assertEquals(form2.getMethod(), HtmlRequestMethodName.GET);
		
		// Form要素が"get"が設定されていた場合、GETが返却されること
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.METHOD.getName());
			result = "get";
		}};
		Form form3 = new Form(htmlElement);
		assertEquals(form3.getMethod(), HtmlRequestMethodName.GET);
		
		// Form要素が"POST"が設定されていた場合、GETが返却されること
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.METHOD.getName());
			result = "POST";
		}};
		Form form4 = new Form(htmlElement);
		assertEquals(form4.getMethod(), HtmlRequestMethodName.POST);
	}
	
	@Test
	public void getFormElementList() {
		// Form要素が設定されていなかった場合、GETが返却されること
		new NonStrictExpectations() {{
			htmlElement.getAttribute(HtmlAttributeName.METHOD.getName());
			result = null;
		}};
		Form form1 = new Form(htmlElement);
		List<HtmlElement> formList = form1.getFormElementList();
		// TODO 考え中 orz
	}
}
