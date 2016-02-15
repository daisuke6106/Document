package jp.co.dk.document.message;

import jp.co.dk.message.AbstractMessage;

/**
 * DocumentMessageは、HTML要素の操作にて使用するメッセージを定義する定数クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DocumentMessage extends AbstractMessage{
	
	/** HTML解析に失敗しました。 */
	public static final DocumentMessage ERROR_FAILED_TO_PARSE_HTML = new DocumentMessage("E001");
	
	/** URIインスタンスの生成に失敗しました。URI=[{0}] */
	public static final DocumentMessage ERROR_FALLED_TO_CREATE_URI_INSTANCE = new DocumentMessage("E002");
	
	/** 不正なURLが指定されました。URI=[{0}] */
	public static final DocumentMessage ERROR_AN_INVALID_URL_WAS_SPECIFIED = new DocumentMessage("E003");
	
	/** ダウンロードディレクトリが存在しません。PATH=[{0}] */
	public static final DocumentMessage ERROR_DOWNLOAD_DIR_IS_NOT_FOUND = new DocumentMessage("E004");
	
	/** 指定のパスはディレクトリではありません。PATH=[{0}] */
	public static final DocumentMessage ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY = new DocumentMessage("E005");
	
	/** 指定のパスにすでにファイルが存在します。PATH=[{0}] */
	public static final DocumentMessage ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH = new DocumentMessage("E006");
	
	/** ダウンロードに失敗しました。URL=[{0}] */
	public static final DocumentMessage ERROR_FAILED_TO_DOWNLOAD = new DocumentMessage("E007");
	
	/** XMLインスタンスの生成に失敗しました。 */
	public static final DocumentMessage FAILED_TO_GENERATE_THE_XML_INSTANCE = new DocumentMessage("E008");
	
	/** XMLインスタンスの生成に失敗しました。 */
	public static final DocumentMessage PARSE_CONFIGURATION_ERROR = new DocumentMessage("E009");
	
	/** SAXの一般的なエラーまたは警告を検出しました。 */
	public static final DocumentMessage SAX_WARNING_OR_ERROR = new DocumentMessage("E010");
	
	/** ファイル入出力にて例外が発生しました。 */
	public static final DocumentMessage FILE_INPUT_OUTPUT_ERROR = new DocumentMessage("E011");
	
	/** XMLファイルが既に存在します。 */
	public static final DocumentMessage ERROR_XML_FILE_ALREADY_EXISTS = new DocumentMessage("E012");
	
	/** ファイルの保存に失敗しました。 */
	public static final DocumentMessage ERROR_FAILED_TO_SAVE_FILE = new DocumentMessage("E013");
	
	/** ファイルの保存に失敗しました。パラメータが設定されていません。 */
	public static final DocumentMessage ERROR_FAILED_TO_SAVE_FILE_PARAM_IS_NOT_SET = new DocumentMessage("E014");
	
	/** 要素の変換処理に失敗しました。 */
	public static final DocumentMessage ERROR_ELEMENT_CONVERT = new DocumentMessage("E014");
	
	/** 要素文字列の変換処理に失敗しました。要素文字列=[{0}] */
	public static final DocumentMessage ERROR_ELEMENT_STRING_CONVERT = new DocumentMessage("E015");
	
	/** 要素の変換処理ファクトリが設定されていません。 */
	public static final DocumentMessage ERROR_ELEMENT_FACTORY_IS_NOT_SET = new DocumentMessage("E016");
	
	/** 要素文字列の変換処理に失敗しました。要素文字列が設定されていません。 */
	public static final DocumentMessage ERROR_ELEMENT_STRING_IS_NOT_SET = new DocumentMessage("E017");
	
	/** ファイル読み込みに失敗しました。 */
	public static final DocumentMessage ERROR_FILE_READ = new DocumentMessage("E018");
	
	/** ドキュメントのバイト配列からのハッシュ値算出に失敗しました。 */
	public static final DocumentMessage ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT = new DocumentMessage("E019");
	
	/** ドキュメントのバイト配列からのハッシュ値算出に失敗しました。使用するアルゴリズムが設定されていません。 */
	public static final DocumentMessage ERROR_CALCULATE_THE_HASH_FROM_BYTE_ARRAY_OF_DOCUMENT_ALGORITHM_NAME_IS_NOT_SET = new DocumentMessage("E020");
	
	/** ドキュメントのJSONへの変換処理に失敗しました。 */
	public static final DocumentMessage ERROR_DECORD_TO_JSON = new DocumentMessage("E021");
	
	protected DocumentMessage(String messageId) {
		super(messageId);
	}

}
