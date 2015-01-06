package jp.co.dk.document.xml;

import javax.xml.transform.OutputKeys;

import jp.co.dk.document.DocumentFoundationTest;
import org.junit.Test;

public class DocumentPropertyTypeTest extends DocumentFoundationTest {

	@Test
	public void getKey() {
		// OutputKeys.ENCODINGの値が取得できること。
		assertThat(DocumentPropertyType.SHIFT_JIS.getKey(), is(DocumentPropertyType.SHIFT_JIS.key));
		assertThat(DocumentPropertyType.SHIFT_JIS.getKey(), is(OutputKeys.ENCODING));
		
		assertThat(DocumentPropertyType.UTF_8.getKey()    , is(DocumentPropertyType.UTF_8.key));
		assertThat(DocumentPropertyType.UTF_8.getKey()    , is(OutputKeys.ENCODING));
	}
	
	@Test
	public void getType() {
		// SHIFT_JISの場合、"Shift_JIS"が取得できること。
		assertThat(DocumentPropertyType.SHIFT_JIS.getType(), is("Shift_JIS"));
		// UTF-8の場合、"UTF-8"が取得できること。
		assertThat(DocumentPropertyType.UTF_8.getType(), is("UTF-8"));
	}

}
