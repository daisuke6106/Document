package jp.co.dk.document.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;

import jp.co.dk.document.ElementName;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * HtmlElementは、HTMLの要素を表すクラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 * 
 */
public class HtmlElement implements jp.co.dk.document.Element{
	
	/** 要素インスタンス */
	protected org.jsoup.nodes.Element element;
	
	/** 要素生成ファクトリ */
	protected HtmlElementFactory elementFactory;
	
	public HtmlElement(String elementStr, HtmlElementFactory elementFactory) throws HtmlDocumentException {
		if (elementStr == null || elementStr.equals("")) throw new HtmlDocumentException(ERROR_ELEMENT_STRING_IS_NOT_SET);
		if (elementFactory == null) throw new HtmlDocumentException(ERROR_ELEMENT_FACTORY_IS_NOT_SET);
		this.element = Jsoup.parse(elementStr);
		this.elementFactory = elementFactory;
	}
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	protected HtmlElement(org.jsoup.nodes.Element element, HtmlElementFactory elementFactory) {
		this.element  = element;
		this.elementFactory = elementFactory;
	}
	
	/**
	 * コンストラクタ
	 * 
	 * 指定のHTML要素から新しいHTMLの要素のインスタンスを生成する。
	 * 
	 * @param htmlElement HTML要素インスタンス
	 */
	protected HtmlElement(HtmlElement htmlElement) {
		this.element  = htmlElement.element;
		this.elementFactory = htmlElement.elementFactory;
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement() {
		List<org.jsoup.nodes.Element> elementList = this.element.getAllElements();
		return this.convertList(elementList);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(jp.co.dk.document.ElementName elementName) {
		List<org.jsoup.nodes.Element> elementList = this.element.getElementsByTag(elementName.getName());
		return this.convertList(elementList);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(jp.co.dk.document.ElementSelector elementSelector) {
		List<jp.co.dk.document.Element> returnList  = new ArrayList<jp.co.dk.document.Element>();
		List<jp.co.dk.document.Element> elementList = this.getElement();
		for (jp.co.dk.document.Element element : elementList) {
			if (elementSelector.judgment(element)) returnList.add(element);
		}
		return returnList;
	}
	
	@Override
	public boolean hasElement(jp.co.dk.document.ElementName elementName) {
		List<jp.co.dk.document.Element> elementList = this.getElement();
		for (jp.co.dk.document.Element element : elementList) {
			HtmlElement htmlElement = (HtmlElement) element;
			if (elementName.getName().equals(htmlElement.getElementType().getName())) { 
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement() {
		List<org.jsoup.nodes.Element> childList = this.element.children();
		if (childList.size() == 1) {
			return this.convertList(childList.get(0).children());
		}else {
			return this.convertList(childList);
		}
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement(jp.co.dk.document.ElementName elementName) {
		List<jp.co.dk.document.Element> returnList = new ArrayList<jp.co.dk.document.Element>();
		List<jp.co.dk.document.Element> list = this.getChildElement(); 
		for (jp.co.dk.document.Element element : list) {
			if (element.isElement(elementName)) {
				returnList.add(element);
			}
		}
		return returnList;
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement(jp.co.dk.document.ElementSelector elementSelector) {
		List<jp.co.dk.document.Element> returnList  = new ArrayList<jp.co.dk.document.Element>();
		List<jp.co.dk.document.Element> elementList = this.getChildElement();
		for (jp.co.dk.document.Element element : elementList) {
			if (elementSelector.judgment(element)) {
				returnList.add(element);
			}
		}
		return returnList;
	}
	
	@Override
	public boolean hasChildElement() {
		List<jp.co.dk.document.Element> list = this.getChildElement();
		if (list.size()==0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean isElement(ElementName elementName){
		if (this.element.tagName().equals(elementName.getName())) return true;
		return false;
	}
	
	@Override
	public String getAttribute(String name) {
		String value = this.element.attr(name);
		if (value == null) return "";
		return value;
	}
	
	@Override
	public boolean hasAttribute(String attributeName) {
		return this.element.hasAttr(attributeName);
	}
		
	@Override
	public String getTagName() {
		return this.element.tagName();
	}
	
	@Override
	public String getContent() {
		return this.element.text();
	}
	
	/**
	 * IDの取得
	 * 要素に設定されているID名を取得する。
	 * 
	 * IDが指定されていなかった場合、空文字が返却されます。
	 * 
	 * @return 要素に指定されているID
	 */
	public String getId() {
		return this.getAttribute(HtmlAttributeName.ID.getName());
	}
	
	/**
	 * Nameの取得
	 * 要素に設定されているName名を取得する。
	 * 
	 * Nameが指定されていなかった場合、空文字が返却されます。
	 * 
	 * @return 要素に指定されているName
	 */
	public String getName() {
		return this.getAttribute(HtmlAttributeName.NAME.getName());
	}
	
	/**
	 * Classの取得
	 * 要素に設定されているClassの一覧を取得する。
	 * 
	 * Classが指定されていなかった場合、空のリストインスタンスが返却されます。
	 * 
	 * @return 要素に指定されているClass一覧
	 */
	public List<String> getClassList() {
		Set<String> classNames = this.element.classNames();
		if (classNames.size() == 0) return new ArrayList<String>();
		return new ArrayList<String>(classNames);
	}
	
	/**
	 * 指定IDの要素を取得
	 * 
	 * 本要素内に保持する全子要素から指定のIDと一致する要素の一覧を返却する。
	 * 
	 * @param id 取得対象ID
	 * @return 要素一覧
	 */
	public HtmlElement getElementById(String id) {
		return this.createHtmlElement(this.element.getElementById(id));
	}
	
	/**
	 * 指定Nameの要素を取得
	 * 
	 * 本要素内に保持する全子要素から指定のNameと一致する要素の一覧を返却する。
	 * 
	 * @param name 取得対象Name
	 * @return 要素一覧
	 */
	public List<HtmlElement> getElementsByName(String name) {
		List<HtmlElement> httpElementList = new ArrayList<HtmlElement>();
		
		return httpElementList;
	}
	
	/**
	 * 要素タイプ返却
	 * 本要素のタイプを返却する。
	 * 
	 * 該当する要素がない場合、nullが返却される
	 * 
	 * @return 該当のHTMLタグ
	 */
	public HtmlElementName getElementType(){
		for (HtmlElementName element : HtmlElementName.values()) if (this.isElement(element)) return element;
		return null;
	}
	
	/**
	 * Href要素の取得
	 * 
	 * 本要素に保持するHref要素の一覧を取得する。
	 * 
	 * @return Href要素一覧
	 */
	public List<HtmlElement> getHrefList(){
		List<HtmlElement> urlList = new ArrayList<HtmlElement>();
		List<org.jsoup.nodes.Element> hrefList = this.element.getElementsByTag("a");
		if (hrefList.size() != 0) for (org.jsoup.nodes.Element element: hrefList) urlList.add(this.createHtmlElement(element));
		return urlList;
	}
	
	@Override
	public int hashCode() {
		return this.element.toString().hashCode() * 17;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof HtmlElement)) return false;
		if (this == object) return true;
		HtmlElement htmlElment = (HtmlElement)object;
		return this.element.toString().equals(htmlElment.element.toString());
	}
	
	@Override
	public String toString() {
		return this.element.toString();
	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	/**
	 * FORM要素取得
	 * 指定されたFORM要素からFORM要素一覧を取得する
	 * 
	 * @return FORM要素一覧
	 */
	protected List<HtmlElement> getFormElementList() {
		List<HtmlElement> list = new ArrayList<HtmlElement>();
		for (org.jsoup.nodes.Element formElement : this.element.getElementsByTag("form")) list.add(this.createHtmlElement(formElement));
		return list;
	}
	
	/**
	 * HTMLパーサの要素インスタンスリスト変換
	 * HTMLパーサの要素インスタンスを本クラスのインスタンスでラップしたリストを返却する。
	 * 
	 * @param elementList HTMLパーサ一覧
	 * @return 本インスタンス一覧
	 */
	private List<jp.co.dk.document.Element> convertList(List<org.jsoup.nodes.Element> elementList) {
		List<jp.co.dk.document.Element> httpElementList = new ArrayList<jp.co.dk.document.Element>();
		if (elementList.size() > 0) for (org.jsoup.nodes.Element element : elementList) httpElementList.add(this.createHtmlElement(element));
		return httpElementList;
	}
	
	/**
	 * 要素オブジェクトを元にHTML要素を生成します。
	 * 
	 * @param element 要素オブジェクト
	 * @return 生成された要素オブジェクト
	 */
	private HtmlElement createHtmlElement(org.jsoup.nodes.Element element) {
		try {
			return (HtmlElement)this.elementFactory.convert(new HtmlElement(element, this.elementFactory));
		} catch (DocumentException e) {
			return new HtmlElement(element, this.elementFactory);
		}
	}
}
