package jp.co.dk.document.html.element;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;

/**
 * Selectは、HTMLのSelect要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Select extends HtmlElement{
	
	/** オプション一覧 */
	protected List<Option> option;
	
	/** デフォルト選択オプション */
	protected Option defaltSelected;
	
	/** 選択オプション */
	protected Option selected;
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 * @param page 本要素を保持しているHTMLへのコネクションインスタンス
	 */
	public Select(HtmlElement element) {
		super(element);
		this.option         = this.getOptionList();
		this.defaltSelected = this.selectedOption(this.option);
		this.selected       = this.defaltSelected;
	}
	
	/**
	 * OPTION要素取得
	 * 
	 * 本SELECT要素に設定されているOPTION要素一覧を取得する
	 * 
	 * @return OPTION要素一覧
	 */
	public List<Option> getOption() {
		return this.option;
	}
	
	/**
	 * 選択OPTION要素取得
	 * 
	 * 本SELECT要素に設定されているOPTION要素を取得する
	 * 
	 * @return 選択済みOPTION要素
	 */
	public Option getSelectedOption() {
		return this.selected;
	}
	
	/**
	 * デフォルト選択OPTION要素取得
	 * 
	 * 本SELECT要素にデフォルトで設定されているOPTION要素を取得する
	 * 
	 * @return デフォルト選択OPTION要素
	 */
	public Option getDefaltSelectedOption() {
		return this.defaltSelected;
	}
	
	/**
	 * OPTION選択
	 * 
	 * 指定された番号のOPTIONを選択状態にする
	 * (OPTIONの指定番号は0始り)
	 * 
	 * @param 選択対象とするOPTIONの番号
	 * @return 設定判定 true=正常に設定 false=設定を失敗 (指定の番号がない等で設定できなかった場合)
	 */
	public boolean select(int optionIndex) {
		// 指定の番号が要素数を上回っている場合
		if ((optionIndex + 1) > this.option.size()) {
			return false;
		}
		this.selected = this.option.get(optionIndex);
		return true;
	}
	
	/**
	 * OPTION選択
	 * 
	 * 指定された文字列と同じVALUEを持つOPTIONを選択状態にする
	 * 
	 * @param 選択対象の文字列
	 * @return 設定判定 true=正常に設定 false=設定を失敗
	 *         (指定の文字列をOPTIONが保持していない等の場合)
	 */
	/* 現在不要で有るため、除外、使用する際にはテストを記載すること
	public boolean select(String value) {
		for (Option option : this.option) {
			String optionValue = option.getValue();
			if (optionValue!=null && optionValue.equals(value)) {
				this.selected = option;
				return true;
			}
		}
		return false;
	}
	*/
	
	/**
	 * 値を取得する。<p/>
	 * 選択済みのオプションの値を取得する。<br/>
	 * 本SELECTオブジェクトに属するオプションがない場合は空文字を返却。
	 * オプションが選択済みでない場合、最初のオプションの値を返却。
	 * 
	 * @return オプション値
	 */
	public String getValue() {
		if (this.option.size() == 0) return "";
		if (this.selected != null ) return this.selected.getValue();
		return this.option.get(0).getValue();
		
	}
	
	/**
	 * この要素の無効状態を取得します。<br/>
	 * この要素に"disabled"が設定されていた場合、true、設定されていなかった場合、falseを返却します。
	 * 
	 * @return 無効状態(true=無効、false=無効でない)
	 */
	public boolean isDisabled() {
		return super.hasAttribute(HtmlAttributeName.DISABLED.getName());
	}
	
	/**
	 * 送信メッセージ生成<br/>
	 * 
	 * この要素から送信メッセージを生成します。<br/>
	 * <br/>
	 * 以下の場合、"name1=value1" が文字列として返却されます。<br/>
	 * 例：&lt;input tyoe="text" name="name1" value="value1"/&gt;<br/>
	 * 
	 * 本要素がdisabledの場合、空文字が返却されます。
	 * 
	 * @return 送信用メッセージ
	 */
	protected String getMessage() {
		if (this.isDisabled()) return "";
		String name  = this.getName();
		if (name.equals("")) return "";
		String value = this.getValue();
		StringBuilder sb = new StringBuilder(name).append('=').append(value);
		return sb.toString();
	}
	
	// ---------------------------------------------------------------------------------------------------------------
	
	/**
	 * 本SELECT要素保持オプション一覧
	 * 
	 * 本SELECT要素に保持しているOPTION要素の一覧を取得する。
	 * 
	 * @return OPTION要素一覧
	 */
	protected List<Option> getOptionList() {
		List<Option> optionList = new ArrayList<Option>();
		List<jp.co.dk.document.Element> childElementList = super.getChildElement();
		for (jp.co.dk.document.Element element : childElementList) {
			HtmlElement htmlElement = (HtmlElement)element;
			HtmlElementName elementName = htmlElement.getElementType();
			if (HtmlElementName.OPTION == elementName) optionList.add(new Option(htmlElement));
		}
		return optionList;
	}
	
	/**
	 * デフォルト選択済みオプション取得
	 * 本SELECT要素に保持しているOPTION要素からSELECTED属性が定義されている選択済みのオプションを返却する。
	 * 
	 * @return 選択済みOPTION要素
	 */
	protected Option selectedOption(List<Option> optionList ) {
		Option selectedOption = null;
		for (Option option : optionList) {
			if (option.hasAttribute(HtmlAttributeName.SELECTED.getName())) {
				return option;
			}
		}
		return selectedOption;
	}
}