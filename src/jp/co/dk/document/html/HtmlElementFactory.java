package jp.co.dk.document.html;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementFactory;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.InputTypeName;
import jp.co.dk.document.html.element.A;
import jp.co.dk.document.html.element.CheckBox;
import jp.co.dk.document.html.element.File;
import jp.co.dk.document.html.element.Form;
import jp.co.dk.document.html.element.Hidden;
import jp.co.dk.document.html.element.Image;
import jp.co.dk.document.html.element.Link;
import jp.co.dk.document.html.element.Meta;
import jp.co.dk.document.html.element.Option;
import jp.co.dk.document.html.element.Password;
import jp.co.dk.document.html.element.Radio;
import jp.co.dk.document.html.element.Reset;
import jp.co.dk.document.html.element.Script;
import jp.co.dk.document.html.element.Select;
import jp.co.dk.document.html.element.Submit;
import jp.co.dk.document.html.element.Text;
import jp.co.dk.document.message.DocumentMessage;

/**
 * HtmlElementFactoryは、HTML要素の生成を行うファクトリクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class HtmlElementFactory implements ElementFactory {

	@Override
	public Element convert(Element element) throws DocumentException {
		HtmlElement htmlElement = null;
		try {

			htmlElement = (HtmlElement)element;
		} catch (ClassCastException e) {
			throw new DocumentException(DocumentMessage.ERROR_ELEMENT_CONVERT, e);
		}
		HtmlElementName name = htmlElement.getElementType();
		if (name == null) return htmlElement;
		switch (name) {
			case A:
				return createAnchor(htmlElement);
			case IMG:
				return createImage(htmlElement);
			case SCRIPT:
				return createImage(htmlElement);
			case LINK:
				return createImage(htmlElement);
			case META:
				return createMeta(htmlElement);
			case FORM:
				return createForm(htmlElement);
			case INPUT:
				InputTypeName inputTypeName = getInpuTypeName(htmlElement);
				if (inputTypeName == null) return createText(htmlElement);
				switch(inputTypeName) {
					case TEXT:
						return createText(htmlElement);
					case PASSWORD:
						return createPassword(htmlElement);
					case CHECKBOX:
						return createCheckbox(htmlElement);
					case RADIO:
						return createRadioButton(htmlElement);
					case SUBMIT:
						return createSubmit(htmlElement);
					case IMAGE:
						return createImage(htmlElement);
					case RESET:
						return createReset(htmlElement);
					case FILE:
						return createFile(htmlElement);
					case HIDDEN:
						return createHidden(htmlElement);
					default:
						return element;
				}
			case SELECT:
				return createSelect(htmlElement);
			case OPTION:
				return createOption(htmlElement);
			default:
				return htmlElement;
		}
	}
	
	/**
	 * HTML要素オブジェクトを元にアンカーオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return アンカーオブジェクト
	 */
	protected A createAnchor(HtmlElement htmlElement) {
		return new A(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にスクリプトオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return スクリプトオブジェクト
	 */
	protected Script createScript(HtmlElement htmlElement) {
		return new Script(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にスクリプトオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return スクリプトオブジェクト
	 */
	protected Link createLink(HtmlElement htmlElement) {
		return new Link(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にMETAオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return METAオブジェクト
	 */
	protected Meta createMeta(HtmlElement htmlElement) {
		return new Meta(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にFormオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Formオブジェクト
	 */
	protected Form createForm(HtmlElement htmlElement) {
		return new Form(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にCheckBoxオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return CheckBoxオブジェクト
	 */
	protected CheckBox createCheckbox(HtmlElement htmlElement) {
		return new CheckBox(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にFileオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Fileオブジェクト
	 */
	protected File createFile(HtmlElement htmlElement) {
		return new File(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にHiddenオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Hiddenオブジェクト
	 */
	protected Hidden createHidden(HtmlElement htmlElement) {
		return new Hidden(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にImageオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Imageオブジェクト
	 */
	protected Image createImage(HtmlElement htmlElement) {
		return new Image(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にPasswordオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Passwordオブジェクト
	 */
	protected Password createPassword(HtmlElement htmlElement) {
		return new Password(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にRadioButtonオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return RadioButtonオブジェクト
	 */
	protected Radio createRadioButton(HtmlElement htmlElement) {
		return new Radio(htmlElement);
	}

	/**
	 * HTML要素オブジェクトを元にResetオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Resetオブジェクト
	 */
	protected Reset createReset(HtmlElement htmlElement) {
		return new Reset(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にSelectオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Selectオブジェクト
	 */
	protected Select createSelect(HtmlElement htmlElement) {
		return new Select(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にSubmitオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Submitオブジェクト
	 */
	protected Submit createSubmit(HtmlElement htmlElement) {
		return new Submit(htmlElement);
	}
	
	/**
	 * HTML要素オブジェクトを元にOptionオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Optionオブジェクト
	 */
	protected Option createOption(HtmlElement htmlElement) {
		return new Option(htmlElement);
	}

	/**
	 * HTML要素オブジェクトを元にSubmitオブジェクトを生成します。
	 * 
	 * @param htmlElement HTML要素オブジェクト
	 * @return Submitオブジェクト
	 */
	protected Text createText(HtmlElement htmlElement) {
		return new Text(htmlElement);
	}
	
	/**
	 * Input種別取得<p/>
	 * テキストボックス、ラジオボタン、チェックボックス等、Input要素に属する種別を取得します。<br/>
	 * 設定された要素がINPUTでない場合、または該当の種別がない場合、nullを返却する。
	 * 
	 * @param element Input要素取得対象要素
	 * @return Input要素
	 */
	private InputTypeName getInpuTypeName(HtmlElement element){
		if (HtmlElementName.INPUT != element.getElementType() ) return null;
		String type = element.getAttribute(HtmlAttributeName.TYPE.getName());
		for (InputTypeName inputTypeName : InputTypeName.values()) {
			if (inputTypeName.getName().equals(type)) return inputTypeName ;
		}
		return null;
	}
}
