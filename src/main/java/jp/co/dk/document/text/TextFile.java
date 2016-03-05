package jp.co.dk.document.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.File;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * TextFileは、txtファイルを表すテキストファイルオブジェクトです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class TextFile extends File {
	
	/**
	 * <p>コンストラクタ</p>
	 * 読み込みファイルを元にオブジェクトを生成します。<br/>
	 * 尚、指定されたファイルの存在チェックを行い、以下のような場合は例外を送出します。<br/>
	 * ・fileオブジェクトがnullの場合
	 * ・指定されたパスにファイルが存在しない場合
	 * ・指定されたパスのファイルがディレクトリの場合
	 * 
	 * @param file 読み込みファイル
	 * @throws DocumentException 上記条件にてファイルの読み込みに失敗した場合 
	 */
	public TextFile(java.io.File file) throws DocumentException {
		super(file);
	}
	
	/**
	 * ファイルに記述された各行を文字列に一覧にして返却します。
	 * 
	 * @return 全行の文字列一覧
	 * @throws DocumentException 読み込みに失敗した場合
	 */
	public List<String> getLines() throws DocumentException {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.fileData.getStream()))) {
			String line ;
			while((line = bufferedReader.readLine())!=null) lines.add(line);
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.FILE_INPUT_OUTPUT_ERROR);
		} 
		return lines;
	}
}
