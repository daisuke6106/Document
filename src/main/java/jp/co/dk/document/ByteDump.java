package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.exception.DocumentFatalException;
import jp.co.dk.document.message.DocumentMessage;
import jp.co.dk.logger.Logger;
import jp.co.dk.logger.LoggerFactory;
import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * ByteDumpは、指定のストリームから読み込んだデータ本体を保持するクラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class ByteDump {
	
	/** バイトバッファ */
	protected ByteBuffer buffer ;
	
	/** ロガーインスタンス */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * コンストラクタ<br/>
	 * 読み込み対象のストリームからデータを読み込み、本クラス内に保持し、ストリームを閉じます。<br/>
	 * データの読み込みに失敗した場合、もしくはクローズ処理に失敗した場合、例外を送出します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @throws DocumentException 読み込みに失敗、もしくはクローズ処理に失敗した場合
	 */
	public ByteDump(InputStream inputStream) throws DocumentException {
		this.logger.constractor(this.getClass(), inputStream);
		List<BytesTmp> bytes = new ArrayList<BytesTmp>();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			this.logger.info("read start");
			long readsize = 0;
			while ((len = inputStream.read(buffer)) > 0) {
				bytes.add(new BytesTmp(Arrays.copyOfRange(buffer, 0, len)));
				readsize++;
				if (readsize % 1024 == 0) this.logger.info(readsize / 1024 + " MByte");
			}
			this.logger.info("read fin");
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
	 * 本データを「Base64」へ変換し、文字列として返却します。
	 * 
	 * @return 本データのBase64表現
	 */
	public String getBytesToBase64String() {
		return Base64.getEncoder().encodeToString(this.getBytes());
	}
	
	/**
	 * 本データをストリームにし、返却します。
	 * @return 本データのストリーム
	 */
	public InputStream getStream() {
		return new  ByteArrayInputStream(buffer.array());
	}
	
	/**
	 * このデータをSHA-256アルゴリズムにて暗号化した文字列を返却します。<p/>
	 * このデータをSHA-256アルゴリズムにて暗号し、その結果を16進数の文字列にて整形した文字列を返却します。
	 * 
	 * @return 暗号化済みの16進数表記文字列（SHA-1）
	 * @throws DocumentFatalException 暗号化処理にて致命的例外が発生した場合
	 */
	public String getHash() throws DocumentFatalException {
		return this.getHash(Algorithm.SHA_256);
	}
	
	/**
	 * このデータを指定のアルゴリズムにて暗号化した文字列を返却します。<p/>
	 * このデータを指定のアルゴリズムにて暗号し、その結果を16進数の文字列にて整形した文字列を返却します。<br/>
	 * <br/>
	 * 指定のアルゴリズムが指定されていなかった、または指定のアルゴリズムが不明な文字列が指定されていた場合、DocumentFatalExceptionが発生します。<br/>
	 * 
	 * @return 指定のアルゴリズムにて暗号化済みの16進数表記文字列
	 * @throws DocumentFatalException 暗号化処理にて致命的例外が発生した場合
	 */
	public String getHash(Algorithm algorithm) throws DocumentFatalException {
        try {
        	if (algorithm == null) throw new DocumentFatalException(ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT_ALGORITHM_NAME_IS_NOT_SET);
        	MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithmName());
        	messageDigest.reset();
        	byte[] hashBytes = messageDigest.digest(this.getBytes());
        	StringBuilder hash = new StringBuilder();
        	for (byte hashByte : hashBytes) {
        		hash.append(String.format("%02x", new Byte(hashByte)));
        	}
        	return hash.toString();
        } catch (NoSuchAlgorithmException na) {
            throw new DocumentFatalException(ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT ,na);
        }
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


