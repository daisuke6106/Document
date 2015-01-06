package jp.co.dk.document.html.constant;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;

import org.junit.Test;

public class HtmlCharSetNameTest extends DocumentFoundationTest {

	@Test
	public void isEncording() {
		// 引数にnullを設定した場合、falseを返却されること。
		assertFalse(HtmlCharSetName.UTF_8.isEncording(null));
		
		// 引数に"utf-8"を設定した場合、trueを返却
		assertTrue(HtmlCharSetName.UTF_8.isEncording("utf-8"));
		
		// 引数に"UTF-8"を設定した場合、trueを返却
		assertTrue(HtmlCharSetName.UTF_8.isEncording("UTF-8"));
		
		// 引数に"utf"を設定した場合、falseを返却
		assertFalse(HtmlCharSetName.UTF_8.isEncording("utf"));
		
		// 引数に"8"を設定した場合、falseを返却
		assertFalse(HtmlCharSetName.UTF_8.isEncording("8"));
		
		// 引数に空文字を設定した場合、falseを返却
		assertFalse(HtmlCharSetName.UTF_8.isEncording(""));
		
		// 引数に異なる文字列を設定した場合、falseを返却
		assertFalse(HtmlCharSetName.UTF_8.isEncording("shift_jis"));
	}

}
