package jp.co.dk.document.html.constant;

/**
 * InputTypeNameは、HTMLのINPUT要素の種別を定義する定数クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum InputTypeName {
	
	/** テキスト */
	TEXT("text"),
	
	/** パスワード */
	PASSWORD("password"),
	
	/** チェックボックス */
	CHECKBOX("checkbox"),
	
	/** ラジオボタン */
	RADIO("radio"),
	
	/** 送信ボタン */
	SUBMIT("submit"),
	
	/** イメージ */
	IMAGE("image"),
	
	/** リセット */
	RESET("reset"),
	
	/** ファイル */
	FILE("file"),
	
	/** 隠し要素 */
	HIDDEN("hidden");
	
	/** HTMLのINPUT要素種別名称 */
	private String name;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のHTMLのINPUT要素種別名称を元にHTMLのINPUT要素種別名称のインスタンスを生成します。
	 * 
	 * @param httpEquivName HTMLのINPUT要素種別名称
	 */
	private InputTypeName(String name) {
		this.name = name;
	}
	
	/**
	 * HTMLのINPUT要素種別名称を取得します。<br/>
	 * 
	 * @return HTMLのINPUT要素種別名称
	 */
	public String getName() {
		return this.name;
	}
}
