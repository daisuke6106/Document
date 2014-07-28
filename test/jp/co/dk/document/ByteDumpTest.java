package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.exception.DocumentFatalException;
import jp.co.dk.document.message.DocumentMessage;
import mockit.Expectations;

import org.junit.Test;

import static jp.co.dk.document.message.DocumentMessage.*;

public class ByteDumpTest extends DocumentFoundationTest {

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
			new ByteDump(inputStream);
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
	
	@Test
	public void getHash() {
		// 使用するアルゴリズムが指定せずに呼び出した場合、SHA-256が使用された結果が返却されること
		// 使用するバイト配列は空文字のバイト配列
		try {
			ByteDump byteDump = new ByteDump(new ByteArrayInputStream("".getBytes()));
			assertEquals(byteDump.getHash(), "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			fail(e);
		}
		
		// 使用するアルゴリズムが指定せずに呼び出した場合、SHA-256が使用された結果が返却されること
		// 使用するバイト配列は特定のファイルのバイト配列
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			assertEquals(byteDump.getHash(), "ebaca9eec1f8a70de904299045043b54c8d8534214414bd7f0a1f9d1b026c67b");
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			fail(e);
		}
				
		// 使用するアルゴリズム名称を指定して（MD5）呼び出した場合、MD5が使用された結果が返却されること
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			assertEquals(byteDump.getHash("MD5"), "369923ad90a022996c82992ea023b60c");
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			fail(e);
		}
		
		// 使用するアルゴリズム名称をnullで呼び出した場合、例外が発生すること
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			byteDump.getHash(null);
			fail();
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			assertEquals(e.getMessageObj(), ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT_ALGORITHM_NAME_IS_NOT_SET);
		}
		
		// 使用するアルゴリズム名称を空文字で呼び出した場合、例外が発生すること
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			byteDump.getHash("");
			fail();
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			assertEquals(e.getMessageObj(), ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT_ALGORITHM_NAME_IS_NOT_SET);
		}
		
		// 使用するアルゴリズム名称を不正な文字列で呼び出した場合、例外が発生すること
		try {
			ByteDump byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			byteDump.getHash("unknown");
			fail();
		} catch (DocumentException e) {
			fail(e);
		} catch (DocumentFatalException e ) {
			assertEquals(e.getMessageObj(), ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT);
		}
		
	}

}
