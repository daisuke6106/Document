package jp.co.dk.document.exception;

import java.util.List;

import jp.co.dk.message.MessageInterface;
import jp.co.dk.message.exception.AbstractMessageException;

/**
 * DocumentExceptionは、ドキュメントへの操作にて例外が発生したことを通知する例外クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DocumentException extends AbstractMessageException{
	
	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = 3282715622934852357L;

	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg){
		super(msg);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg, String str){
		super(msg, str);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg, Throwable throwable){
		super(msg, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg, String str, Throwable throwable){
		super(msg, str, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param list メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg, List<String> list, Throwable throwable){
		super(msg, list, throwable);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public DocumentException(MessageInterface msg, String[] str, Throwable throwable){
		super(msg, str, throwable);
	}
}
