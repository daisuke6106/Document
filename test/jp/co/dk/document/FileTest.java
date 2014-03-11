package jp.co.dk.document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

import org.junit.Test;

public class FileTest extends DocumentFoundationTest{
	
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
			java.io.File saveFile = file.save(saveTmpDir, "JPEG1.jpg");
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
			java.io.File saveFile = file.save(fraudTestTmpDir, "JPEG2.jpg");
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
			java.io.File saveFile = file.save(saveTmpFile, "JPEG3.jpg");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY) {
				fail(e);
			}
		}
		
		/**
		 * 保存に失敗すること。
		 * 保存先パスがすでに存在し、それがディレクトリでない場合、DocumentExceptionが発生すること。
		 * 例外メッセージが ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY であること。
		 */
		try {
			java.io.File saveFile = file.save(saveTmpDir, "JPEG3.jpg");
			file.save(saveTmpDir, "JPEG3.jpg");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH) {
				fail(e);
			}
		}
		
		/**
		 * 保存に失敗すること。
		 * 保存先のディレクトリに書き込み権限がない場合、DocumentExceptionが発生すること。
		 * 例外メッセージが ERROR_FAILED_TO_SAVE_FILE であること。
		 */
		try {
			java.io.File saveFile = file.save(super.getTestTmpDirReadOnly(), "JPEG4.jpg");
			fail();
		} catch (DocumentException e) {
			if (e.getMessageObj() != DocumentMessage.ERROR_FAILED_TO_SAVE_FILE) {
				fail(e);
			}
		}
	}
}
