package jp.co.dk.document.html.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.dk.document.Element;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.HtmlRequestMethodName;
import jp.co.dk.document.html.constant.InputTypeName;

/**
 * Formは、HTMLのForm要素をを表すクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Form extends HtmlElement{
	
	protected FormElemenetFactory formElementFactory;
	
	protected List<HtmlElement> cache_formElementList;
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public Form(HtmlElement element) {
		super(element);
		this.formElementFactory = new FormElemenetFactory(this);
	}
	
	/**
	 * FORMメソッド取得<br/>
	 * 
	 * 本FORM要素からメソッドを取得する。<br/>
	 * 指定されていない場合、デフォルトのGETを返却する。<br/>
	 * 
	 * @return メソッド名
	 */
	public HtmlRequestMethodName getMethod(){
		String methodName = this.getAttribute(HtmlAttributeName.METHOD.getName());
		for (HtmlRequestMethodName method : HtmlRequestMethodName.values()) {
			if (method.isMethod(methodName)) {
				return method;
			}
		}
		return HtmlRequestMethodName.GET;
	}
	
	/**
	 * Action取得
	 * 本FORM要素に設定されているアクションを表すオブジェクトを取得する。
	 * 
	 * @return アクション文字列
	 */
	public Action getAction(){
		return new Action(this);
	}

	/**
	 * FORM要素取得
	 * このFORM要素からFORM一覧を取得する。
	 * 
	 * 取得されるFORMに属する要素として以下の要素を取得する。<br/>
	 * <br/>
	 * ・TEXT要素<br/>
	 * ・PASSWORD要素<br/>
	 * ・FILE要素<br/>
	 * ・CHECKBOX要素<br/>
	 * ・RADIO要素<br/>
	 * ・HIDDEN要素<br/>
	 * ・SUBMIT要素<br/>
	 * ・RESET要素<br/>
	 * ・BUTTON要素<br/>
	 * ・IMAGE要素<br/>
	 * ・SELECT要素<br/>
	 * 
	 * @return FORM要素一覧
	 */
	public List<HtmlElement> getFormElementList() {
		if (this.cache_formElementList == null) {
			List<HtmlElement> list = new ArrayList<HtmlElement>();
			List<HtmlElement> form = super.getFormElementList();
			for (HtmlElement htmlElement : form) {
				HtmlElement formElement = (HtmlElement) this.formElementFactory.convert(htmlElement);
				list.add(formElement);
			}
			this.cache_formElementList = list;
		}
		return this.cache_formElementList;
	}
	
	/** 
	 * FORM要素取得
	 * 指定されたFORMの要素内から送信用要素の一覧をマップ形式で返却します。
	 * 
	 * 取得されるFORMに属する要素として以下の要素を取得する。<br/>
	 * <br/>
	 * ・TEXT要素<br/>
	 * ・PASSWORD要素<br/>
	 * ・FILE要素<br/>
	 * ・CHECKBOX要素<br/>
	 * ・RADIO要素<br/>
	 * ・HIDDEN要素<br/>
	 * ・SUBMIT要素<br/>
	 * ・IMAGE要素<br/>
	 * ・SELECT要素<br/>
	 * 
	 * @return 送信用要素一覧のマップ形式（key=nameの名称、value=nameを保持する要素の一覧）
	 */
	public Map<String, List<HtmlElement>> getFormElementMap() {
		List<HtmlElement> formElementList = this.getFormElementList();
		Map<String, List<HtmlElement>> map = new HashMap<String, List<HtmlElement>>();
		for (HtmlElement formElement : formElementList) {
			String name = formElement.getName();
			if (name.equals("")) continue;
			List<HtmlElement> addedList = map.get(name);
			if (addedList == null) {
				List<HtmlElement> addList = new ArrayList<HtmlElement>();
				addList.add(formElement);
				map.put(name, addList);
			} else {
				addedList.add(formElement);
			}
		}
		return map;
	}
	
	/**
	 * 送信メッセージ生成
	 * 
	 * @return 送信メッセージ
	 */
	public String createMessage () {
		List<HtmlElement> formElementList = this.getFormElementList();
		StringBuilder sb = new StringBuilder();
		for (HtmlElement formElement : formElementList) {
			String message = null;
			if (formElement instanceof Input) {
				Input input = (Input)formElement;
				message = input.getMessage();
			} else if (formElement instanceof Select) {
				Select select = (Select)formElement;
				message = select.getMessage();
			}
			if (message != null && !message.equals("")) sb.append(message).append('&');
		}
		if (sb.length() == 0) return "";
		return sb.substring(0, sb.length()-1);
	}
	
}

class FormElemenetFactory extends HtmlElementFactory {
	
	private Form form;
	
	FormElemenetFactory(Form form) {
		this.form = form;
	}
	
	@Override
	public Element convert(Element element) {
		
		HtmlElement htmlElement = (HtmlElement) element;
		HtmlElementName htmlElementName = htmlElement.getElementType();
		
		if (HtmlElementName.INPUT == htmlElementName) {
			InputTypeName input = this.cornvertInputTypeName(htmlElement);
			if (input == null) return new jp.co.dk.document.html.element.form.FormText(htmlElement, this.form);
			switch (input) {
				case CHECKBOX:
					return new jp.co.dk.document.html.element.form.FormCheckBox(htmlElement, this.form);
				case FILE:
					return new jp.co.dk.document.html.element.form.FormFile(htmlElement, this.form);
				case HIDDEN:
					return new jp.co.dk.document.html.element.form.FormHidden(htmlElement, this.form);
				case PASSWORD:
					return new jp.co.dk.document.html.element.form.FormPassword(htmlElement, this.form);
				case RADIO:
					return new jp.co.dk.document.html.element.form.FormRadio(htmlElement, this.form);
				case RESET:
					return new jp.co.dk.document.html.element.form.FormReset(htmlElement, this.form);
				case SUBMIT:
					return new jp.co.dk.document.html.element.form.FormSubmit(htmlElement, this.form);
				case TEXT:
					return new jp.co.dk.document.html.element.form.FormText(htmlElement, this.form);
				default:
					return element;
			}
		} else if (HtmlElementName.SELECT == htmlElementName) { 
			return new jp.co.dk.document.html.element.form.FormSelect(htmlElement, this.form);
		} else {
			return element;
		}
		
	}
	
	/** 
	 * 指定のElementオブジェクトのINPUT要素からINPUTタイプ要素を取得する。<br/>
	 * 該当のINPUTタイプ要素がない場合、nullを返却する。
	 * 
	 * @return HTML要素名称
	 */
	private InputTypeName cornvertInputTypeName(HtmlElement element) {
		String type = element.getAttribute(HtmlAttributeName.TYPE.getName());
		for (InputTypeName typeName: InputTypeName.values()) {
			if (typeName.getName().equals(type)) return typeName;
		}
		return null;
	}
}
