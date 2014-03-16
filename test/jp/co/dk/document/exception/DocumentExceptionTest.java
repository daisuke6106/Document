package jp.co.dk.document.exception;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.message.MessageInterface;

import org.junit.Test;
import static jp.co.dk.document.message.DocumentMessage.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class DocumentExceptionTest extends DocumentFoundationTest {

	@Test
	public void constractor() {
		
		// メッセージオブジェクトのみ指定して実行した場合
		// 埋め込み文字列、throwクラスは取得できないこと
		DocumentException sut1 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE);
		assertThat(sut1.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut1.getEmbeddedStrList().size(), is(0));
		assertThat(sut1.getThrowable(), nullValue());
		
		// メッセージオブジェクト、単一文字のみ指定して実行した場合
		// 指定の埋め込み文字列が取得できること
		// throwクラスは取得できないこと
		DocumentException sut2 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE, "http://www.google.com");
		assertThat(sut2.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut2.getEmbeddedStrList().size(), is(1));
		assertThat(sut2.getEmbeddedStrList().get(0), is("http://www.google.com"));
		assertThat(sut2.getThrowable(), nullValue());
		
		// メッセージオブジェクト、単一文字のみ指定して実行した場合
		// 埋め込み文字列が取得できないこと
		// throwクラスは取得できること
		DocumentException sut3 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE, new NullPointerException());
		assertThat(sut3.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut3.getEmbeddedStrList().size(), is(0));
		assertThat(sut3.getThrowable(), instanceOf(NullPointerException.class));
		
		// メッセージオブジェクト、単一文字のみ指定して実行した場合
		// 指定の埋め込み文字列が取得できること
		// throwクラスが取得できること
		DocumentException sut4 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE, "http://www.google.com", new NullPointerException());
		assertThat(sut4.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut4.getEmbeddedStrList().size(), is(1));
		assertThat(sut4.getEmbeddedStrList().get(0), is("http://www.google.com"));
		assertThat(sut4.getThrowable(), instanceOf(NullPointerException.class));
		
		// メッセージオブジェクト、単一文字のみ指定して実行した場合
		// 指定の埋め込み文字列が取得できること
		// throwクラスが取得できること
		List<String> strList = new ArrayList<String>();
		strList.add("http://www.google.com");
		DocumentException sut5 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE, strList, new NullPointerException());
		assertThat(sut5.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut5.getEmbeddedStrList().size(), is(1));
		assertThat(sut5.getEmbeddedStrList().get(0), is("http://www.google.com"));
		assertThat(sut5.getThrowable(), instanceOf(NullPointerException.class));
		
		
		// メッセージオブジェクト、単一文字のみ指定して実行した場合
		// 指定の埋め込み文字列が取得できること
		// throwクラスが取得できること
		String[] strArray = {"http://www.google.com"};
		DocumentException sut6 = new DocumentException(ERROR_FALLED_TO_CREATE_URI_INSTANCE, strArray, new NullPointerException());
		assertThat(sut6.getMessageObj(), is((MessageInterface)ERROR_FALLED_TO_CREATE_URI_INSTANCE));
		assertThat(sut6.getEmbeddedStrList().size(), is(1));
		assertThat(sut6.getEmbeddedStrList().get(0), is("http://www.google.com"));
		assertThat(sut6.getThrowable(), instanceOf(NullPointerException.class));
	}
	
}
