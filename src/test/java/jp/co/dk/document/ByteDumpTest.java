package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.exception.DocumentFatalException;
import jp.co.dk.document.message.DocumentMessage;
import mockit.Expectations;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static jp.co.dk.document.message.DocumentMessage.*;

@RunWith(Enclosed.class)
public class ByteDumpTest {
	
	public static class コンストラクタ extends DocumentFoundationTest{
		
		@Test
		public void 読み込み時に例外が発生した場合() throws IOException {
			
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
	}
	
	public static class 正常にインスタンスが生成できた場合 extends DocumentFoundationTest{
		
		protected ByteDump byteDump;
		
		@Before
		public void init() throws DocumentException, IOException {
			this.byteDump = new ByteDump(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
			assertThat(this.byteDump.buffer.limit(), is(505061));
		}
		
		@Test
		public void length() {
			assertThat(this.byteDump.length(), is(505061));
		}
		
		@Test
		public void getStream() {
			assertThat((this.byteDump.getStream() instanceof ByteArrayInputStream), is(true));
		}
		
		@Test
		public void getBytes() {
			assertThat(this.byteDump.getBytes().length, is(505061));
		}
		
		@Test
		public void getBytesToBase64String() {
			assertThat(this.byteDump.getBytesToBase64String().length(), is(673416));
		}
		
		@Test
		public void getHash() {
			
			// 使用するアルゴリズム名称をnullで呼び出した場合、例外が発生すること
			try {
				this.byteDump.getHash(null);
				fail();
			} catch (DocumentFatalException e ) {
				assertEquals(e.getMessageObj(), ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT_ALGORITHM_NAME_IS_NOT_SET);
			}
			
			// デフォルト
			assertThat(byteDump.getHash(), is("ebaca9eec1f8a70de904299045043b54c8d8534214414bd7f0a1f9d1b026c67b"));
			
			// MD5
			assertThat(byteDump.getHash(Algorithm.MD5), is("369923ad90a022996c82992ea023b60c"));
			
			// SHA_256
			assertThat(byteDump.getHash(Algorithm.SHA_256), is("ebaca9eec1f8a70de904299045043b54c8d8534214414bd7f0a1f9d1b026c67b"));
			
			// SHA_512
			assertThat(byteDump.getHash(Algorithm.SHA_512), is("b653018bbd91818b30b720986445d1a66418654db880aade5a8e19819dd81747dc8249c3b4d6a947481288840d20d88c599a99b5e2cc8bdca098f86a67bd54b5"));
		}
	}
}