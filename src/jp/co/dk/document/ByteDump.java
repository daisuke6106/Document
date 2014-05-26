package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * ByteDumpは、指定のストリームから読み込んだデータ本体を保持するクラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class ByteDump {
	
	/** バイトバッファ */
	protected ByteBuffer buffer ;
	
	/**
	 * コンストラクタ<br/>
	 * 読み込み対象のストリームからデータを読み込み、本クラス内に保持し、ストリームを閉じます。<br/>
	 * データの読み込みに失敗した場合、もしくはクローズ処理に失敗した場合、例外を送出します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @throws DocumentException 読み込みに失敗、もしくはクローズ処理に失敗した場合
	 */
	public ByteDump(InputStream inputStream) throws DocumentException {
		List<BytesTmp> bytes = new ArrayList<BytesTmp>();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) > 0) bytes.add(new BytesTmp(Arrays.copyOfRange(buffer, 0, len)));
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FILE_READ, e);
		}
		int length = 0;
		for(BytesTmp bytetmp : bytes) length += bytetmp.length();
		this.buffer = ByteBuffer.allocate(length);
		for(BytesTmp bytetmp : bytes) this.buffer.put(bytetmp.getBytes());
		try {
			inputStream.close();
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FILE_READ, e);
		}
	}
	
	/**
	 * 本データの長さを取得する。
	 * @return データ長
	 */
	public int length() {
		return this.buffer.limit();
	}
	
	/**
	 * 本データをバイト配列にし、返却します。
	 * @return 本データのバイト配列
	 */
	public byte[] getBytes() {
		return this.buffer.array();
	}
	
	/**
	 * 本データをストリームにし、返却します。
	 * @return 本データのストリーム
	 */
	public InputStream getStream() {
		return new  ByteArrayInputStream(buffer.array());
	}
}

class BytesTmp {
	
	private byte[] bytes;
	
	BytesTmp(byte[] bytes) {
		this.bytes = bytes;
	}
	int length() {
		return this.bytes.length;
	}
	byte[] getBytes() {
		return this.bytes;
	}
}

