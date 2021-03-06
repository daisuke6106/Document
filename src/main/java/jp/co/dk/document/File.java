package jp.co.dk.document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;	
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;
import jp.co.dk.logger.Logger;
import jp.co.dk.logger.LoggerFactory;

/**
 * Fileは、ネットワーク上に存在するファイルを表すファイルオブジェクトです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class File {
	
	/** 読み込んだバイトデータ */
	protected ByteDump fileData;
	
	/** ロガーインスタンス */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * コンストラクタ<br/>
	 * 
	 * 読み込みファイルを元にオブジェクトを生成します。<br/>
	 * 尚、指定されたファイルの存在チェックを行い、以下のような場合は例外を送出します。<br/>
	 * ・fileオブジェクトがnullの場合
	 * ・指定されたパスにファイルが存在しない場合
	 * ・指定されたパスのファイルがディレクトリの場合
	 * 
	 * @param file 読み込みファイル
	 * @throws DocumentException 上記条件にてファイルの読み込みに失敗した場合
	 */
	public File(java.io.File file) throws DocumentException {
		if (file == null)       throw new DocumentException(DocumentMessage.ERROR_FILE_IS_NOT_SET);
		if (!file.exists())     throw new DocumentException(DocumentMessage.ERROR_FILE_NOT_EXISTS_IN_THE_SPECIFIED_PATH, file.toString());
		if (file.isDirectory()) throw new DocumentException(DocumentMessage.ERROR_SPECIFIED_PATH_IS_DIRECTORY, file.toString());
		try {
			InputStream inputStream = new FileInputStream(file);
			this.fileData = new ByteDump(inputStream);
		} catch (FileNotFoundException e) {
			throw new DocumentException(DocumentMessage.ERROR_FILE_READ, e);
		}
	}
	
	/**
	 * コンストラクタ<br/>
	 * 
	 * 読み込みストリームを元にオブジェクトを生成する。
	 * 
	 * @param inputStream 読み込みストリーム
	 * @throws DocumentException 読み込みストリームからの読み込みに失敗した場合
	 */
	public File(InputStream inputStream) throws DocumentException {
		this.logger.constractor(this.getClass(), inputStream);
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
		if (path.exists()) throw new DocumentException(DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH, path.getAbsolutePath());
		
		this.logger.info("save path=[" + path.getPath() + "]");
		
		try (OutputStream outputStream = new FileOutputStream(path)) {
			outputStream.write(this.fileData.getBytes());
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		}
		return path;
	}
}
