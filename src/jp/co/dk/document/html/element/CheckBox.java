package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

/**
 * CheckBoxは、HTMLのチェックボックス要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class CheckBox extends Input{

	/** チェックフラグ */
	private boolean checked;
	
	/** デフォルトチェックフラグ */
	private boolean defaltChecked;
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public CheckBox(HtmlElement element) {
		super(element);
		this.defaltChecked = this.hasAttribute(HtmlAttributeName.CHECKED.getName());
		this.checked       = this.defaltChecked;
	}
	
	/**
	 * チェック状態取得
	 * 本ラジオボタンのチェック状態を返却します。
	 * 
	 * @return チェック状態
	 */
	public boolean isChecked() {
		return this.checked;
	}
	
	/**
	 * チェック状態設定
	 * 本ラジオボタンのチェック状態を設定します。
	 * 
	 * @param checked チェック状態 true:チェック済み false:未チェック
	 */
	public void checked(boolean checked) {
		this.checked = checked;
	}
	
	/**
	 * デフォルトチェック状態設定
	 * 本ラジオボタンのHTMLにデフォルトで設定されていたチェック状態を返却します。
	 * 
	 * @param checked チェック状態 true:チェック済み false:未チェック
	 */
	public boolean getDefaltChecked() {
		return this.defaltChecked;
	}
	
	/**
	 * 送信メッセージ生成<br/>
	 * 
	 * この要素から送信メッセージを生成します。<br/>
	 * <br/>
	 * 以下の場合、"name1=value1" が文字列として返却されます。<br/>
	 * 例：&lt;input type="text" name="name1" value="value1"/&gt;<br/>
	 * 
	 * 本要素がdisabled、またはチェック状態でない場合、空文字が返却されます。
	 * 
	 * @return 送信用メッセージ
	 */
	@Override
	protected String getMessage() {
		if (!this.isChecked()) {
			return ""; 
		}
		return super.getMessage();
	}
}
