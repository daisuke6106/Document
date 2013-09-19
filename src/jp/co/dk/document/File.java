package jp.co.dk.document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * Fileは、ネットワーク上に存在するファイルを表すファイルオブジェクトです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class File {
	
	protected ByteDump fileData;
	
	/**
	 * コンストラクタ<br/>
	 * 
	 * 読み込みストリームを元にオブジェクトを生成する。
	 * 
	 * @param inputStream 読み込みストリーム
	 * @throws DocumentException 読み込みストリームからの読み込みに失敗した場合
	 */
	public File(InputStream inputStream) throws DocumentException {
		this.fileData = new ByteDump(inputStream);
	}
	
	/**
	 * このオブジェクトが表すファイルを指定のディレクトリへ指定のファイル名にて保存する。<p>
	 * 保存が成功した場合、保存したファイルのファイルオブジェクトを返却する。
	 * <br/>
	 * 以下の条件の場合、例外を発生する。<br/>
	 * ・指定された保存先ディレクトリが存在しない場合<br/>
	 * ・指定された保存先ディレクトリがディレクトリが存在しない場合<br/>
	 * ・保存先のファイルがすでに存在する場合<br/>
	 * 
	 * @param dir 保存先ディレクトリ
	 * @param filename ファイル名
	 * @return 保存したファイルオブジェクト
	 * @throws DocumentException 保存に失敗した場合
	 */
	public java.io.File save (java.io.File dir, String filename ) throws DocumentException {
		if (!dir.exists()) throw new DocumentException(DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND, dir.getAbsolutePath());
		if (!dir.isDirectory()) throw new DocumentException(DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY, dir.getAbsolutePath());
		
		java.io.File path = new java.io.File(new StringBuilder(dir.getAbsolutePath()).append('/').append(filename).toString());
		if (path.exists()) throw new DocumentException(DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH, dir.getAbsolutePath());
		try {
			OutputStream outputStream = new FileOutputStream(path);
			outputStream.write(this.fileData.getBytes());
			outputStream.close();
		} catch (MalformedURLException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		}
		return path;
	}
}
