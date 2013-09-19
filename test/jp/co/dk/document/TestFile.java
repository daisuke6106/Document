package jp.co.dk.document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.message.DocumentMessage;

import org.junit.Test;

public class TestFile extends TestDocumentFoundation{
	
	@Test
	public void save() throws DocumentException {
		
		jp.co.dk.document.File     file = super.createFileDocument();
		java.io.File         saveTmpDir = super.getTestTmpDir();
		java.io.File    fraudTestTmpDir = super.getFraudTestTmpDir();
		java.io.File        saveTmpFile = super.getTestTmpFile();
		
		/**
		 * 正常に保存ができること。
		 * 保存したファイルがコピー元と一致すること。
		 */
		try {
			java.io.File saveFile = file.save(saveTmpDir, "JPEG.jpg");
			super.assertStreamEquals(new FileInputStream(saveFile), super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
		} catch (DocumentException | FileNotFoundException e) {
			fail(e);
		}
		
		/**
		 * 保存に失敗すること。
		 * 保存先ディレクトリが存在しない場合、DocumentExceptionが発生すること。
		 * 例外メッセージが ERROR_DOWNLOAD_DIR_IS_NOT_FOUND であること。
		 */
		try {
			file.save(fraudTestTmpDir, "JPEG.jpg");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND) {
				fail(e);
			}
		}
		
		/**
		 * 保存に失敗すること。
		 * 保存先パスがすでに存在し、それがディレクトリでない場合、DocumentExceptionが発生すること。
		 * 例外メッセージが ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY であること。
		 */
		try {
			file.save(saveTmpFile, "JPEG.jpg");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY) {
				fail(e);
			}
		}
		
		/**
		 * 保存に失敗すること。
		 * 保存先にすでにファイルが存在する場合、DocumentExceptionが発生すること。
		 * 例外メッセージが ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY であること。
		 */
		try {
			file.save(super.getTestTmpDir(), super.getTestTmpFile().getName());
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH) {
				fail(e);
			}
		}
	}
}
