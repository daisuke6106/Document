package jp.co.dk.document.excel;

import org.junit.Test;

import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.excel.WorkBook;
import jp.co.dk.document.excel.exception.ExcelDocumentException;

public class WorkBookTest  extends DocumentFoundationTest {
	
	@Test
	public void constractor() {
		try {
			WorkBook workBook = new WorkBook(new java.io.File("/tmp/sample.xls"));
			WorkSheet sheet   = workBook.createSheet("sample");
			sheet.getLastRow().addCell("aaa");
			sheet.getLastBrankRow().addCell("bbb");
			
			workBook.write();
		} catch (ExcelDocumentException e) {
			fail(e);
		}
	}
}
