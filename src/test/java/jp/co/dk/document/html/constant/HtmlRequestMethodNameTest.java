package jp.co.dk.document.html.constant;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;

import org.junit.Test;

public class HtmlRequestMethodNameTest extends DocumentFoundationTest {

	@Test
	public void isMethod() {
		// nullを渡した場合、falseが返却されること。
		assertFalse(HtmlRequestMethodName.GET.isMethod(null));
		
		// 空文字を渡した場合、falseが返却されること。
		assertFalse(HtmlRequestMethodName.GET.isMethod(""));
		
		// "get"を渡した場合、trueが返却されること。
		assertTrue(HtmlRequestMethodName.GET.isMethod("get"));
		
		// "GET"を渡した場合、trueが返却されること。
		assertTrue(HtmlRequestMethodName.GET.isMethod("GET"));
		
		// "post"を渡した場合、falseが返却されること。
		assertTrue(HtmlRequestMethodName.GET.isMethod("GET"));
	}
	
	
	@Test
	public void getMethod() {
		assertThat(HtmlRequestMethodName.GET.getMethod(), is("GET"));
	}
}
