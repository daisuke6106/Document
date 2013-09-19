package jp.co.dk.document;

import java.io.IOException;
import java.io.InputStream;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.message.DocumentMessage;

import mockit.Expectations;

import org.junit.Test;

public class TestByteDump extends TestDocumentFoundation {

	@Test
	public void constractor() throws IOException {
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			assertEquals(byteDump.buffer.limit(), 505061);
		} catch (DocumentException e) {
			fail(e);
		}
		
		try {
			final InputStream inputStream = super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg");
			new Expectations(inputStream) {{
				inputStream.read(new byte[1024]);result = new IOException("モックにより作成されたIO例外");
			}};
			ByteDump byteDump = new ByteDump(inputStream);
			fail();
		} catch (DocumentException e) {
			assertEquals(e.getMessageObj(), DocumentMessage.ERROR_FILE_READ);
		}
	}
	
	@Test
	public void length() {
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			assertEquals(byteDump.length(), 505061);
		} catch (DocumentException e) {
			fail(e);
		}
	}

}
