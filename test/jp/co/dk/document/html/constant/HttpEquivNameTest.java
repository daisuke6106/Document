package jp.co.dk.document.html.constant;

import static org.junit.Assert.*;
import jp.co.dk.document.foundation.TestDocumentFoundation;

import org.junit.Test;

public class HttpEquivNameTest extends TestDocumentFoundation{

	@Test
	public void match() {
		// 引数にnullを設定した場合、falseを返却すること。
		assertFalse(HttpEquivName.CONTENT_TYPE.match(null));
		
		// 引数に空文字を設定した場合、falseを返却すること。
		assertFalse(HttpEquivName.CONTENT_TYPE.match(""));
		
		// 引数に"Content-Type"を設定した場合、trueを返却すること。
		assertTrue(HttpEquivName.CONTENT_TYPE.match("Content-Type"));
		
		// 引数に"Content-Type"(大文字)を設定した場合、trueを返却すること。
		assertTrue(HttpEquivName.CONTENT_TYPE.match("CONTENT-TYPE"));
		
		// 引数に"Content_Type"を設定した場合、falseを返却すること。
		assertFalse(HttpEquivName.CONTENT_TYPE.match("Content_Type"));
	}

}
