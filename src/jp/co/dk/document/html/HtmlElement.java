package jp.co.dk.document.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.FormControl;

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
	protected net.htmlparser.jericho.Segment element;
	
	/** 要素生成ファクトリ */
	protected HtmlElementFactory elementFactory;
	
	/** 本要素のタグインスタンス */
	protected net.htmlparser.jericho.StartTag startTag;
	
	/** 本要素のタグ */
	protected HtmlElementName cache_elementName;
	
	/** 全要素キャッシュ */
	protected List<jp.co.dk.document.Element> cache_allElement;
	
	/** 全子要素キャッシュ */
	protected List<jp.co.dk.document.Element> cache_childElement;
	
	/** IDのキャッシュ */
	protected String cache_id;
	
	/** Nameのキャッシュ */
	protected String cache_name;
	
	/** Classのキャッシュ */
	protected List<String> cache_class;
	
	/** Contentのキャッシュ */
	protected String cache_content;
	
	/** タグパターン */
	private static Pattern tagPattern = Pattern.compile("<.*?>");
	
	public HtmlElement(String elementStr, HtmlElementFactory elementFactory) throws HtmlDocumentException {
		if (elementStr == null || elementStr.equals("")) throw new HtmlDocumentException(ERROR_ELEMENT_STRING_IS_NOT_SET);
		if (elementFactory == null) throw new HtmlDocumentException(ERROR_ELEMENT_FACTORY_IS_NOT_SET);
		net.htmlparser.jericho.Source element = new net.htmlparser.jericho.Source(elementStr); 
		this.element  = element;
		this.startTag = element.getFirstStartTag();
		if (this.startTag == null) throw new HtmlDocumentException(ERROR_ELEMENT_STRING_CONVERT);
		this.elementFactory = elementFactory;
	}
	
	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	protected HtmlElement(net.htmlparser.jericho.Segment element, HtmlElementFactory elementFactory) {
		this.element  = element;
		this.startTag = element.getFirstStartTag();
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
		this.startTag = this.element.getFirstStartTag();
		this.elementFactory = htmlElement.elementFactory;
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement() {
		List<net.htmlparser.jericho.Element> elementList = this.element.getAllElements();
		return this.convertList(elementList);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(jp.co.dk.document.ElementName elementName) {
		List<net.htmlparser.jericho.Element> elementList = this.element.getAllElements(elementName.getName());
		return this.convertList(elementList);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(jp.co.dk.document.ElementSelector elementSelector) {
		List<jp.co.dk.document.Element> returnList  = new ArrayList<jp.co.dk.document.Element>();
		List<jp.co.dk.document.Element> elementList = this.getElement();
		for (jp.co.dk.document.Element element : elementList) {
			if (elementSelector.judgment(element)) {
				returnList.add(element);
			}
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
		if (this.element.getChildElements().size() == 1) {
			return this.convertList(this.element.getChildElements().get(0).getChildElements());
		}else {
			return this.convertList(this.element.getChildElements());
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
		boolean isElement = false;
		String tagName = this.startTag.getName();
		if (tagName.equals(elementName.getName())) {
			isElement = true;
		}
		return isElement;
	}
	
	@Override
	public String getAttribute(String name) {
		return this.element.getFirstElement().getAttributeValue(name);
	}
	
	@Override
	public boolean hasAttribute(String attributeName) {
		boolean hasAttribute = false;
		net.htmlparser.jericho.Attributes attributes = this.element.getFirstElement().getAttributes();
		if (attributes == null) {
			return false;
		}
		for (net.htmlparser.jericho.Attribute attribute : attributes) {
			String tmpAttributeName = attribute.getName();
			if (tmpAttributeName.endsWith("/")) tmpAttributeName = tmpAttributeName.substring(0, tmpAttributeName.length()-1);
			if (tmpAttributeName.equals(attributeName)) {
				hasAttribute = true;
				break;
			}
		}
		return hasAttribute;
	}
		
	@Override
	public String getTagName() {
		return this.startTag.getName();
	}
	
	@Override
	public String getContent() {
		if (this.cache_content == null) {
			Matcher matcher = HtmlElement.tagPattern.matcher(this.toString());
			this.cache_content = matcher.replaceAll("");
		}
		return this.cache_content;
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
		if (this.cache_id == null) {
			String getId = this.getAttribute(HtmlAttributeName.ID.getName());
			if (getId == null) {
				getId = "";
			}
			this.cache_id = getId;
		}
		return this.cache_id;
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
		if (this.cache_name == null) {
			String getName = this.getAttribute(HtmlAttributeName.NAME.getName());
			if (getName == null) {
				getName = "";
			}
			this.cache_name = getName;
		}
		return this.cache_name;
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
		List<String> classList = new ArrayList<String>();
		
		if (this.cache_class == null) {
			String getClass = this.getAttribute(HtmlAttributeName.CLASS.getName());
			if (getClass != null ) {
				classList = Arrays.asList(getClass.split(" "));
			}
			this.cache_class = classList;
		}
		return this.cache_class;
	}
	
	/**
	 * 指定IDの要素を取得
	 * 
	 * 本要素内に保持する全子要素から指定のIDと一致する要素の一覧を返却する。
	 * 
	 * @param id 取得対象ID
	 * @return 要素一覧
	 */
	public List<HtmlElement> getElementById(String id) {
		List<HtmlElement> httpElementList = new ArrayList<HtmlElement>();
		List<net.htmlparser.jericho.Element> allElement = this.element.getAllElements();
		for (net.htmlparser.jericho.Element element : allElement) {
			String elementId = this.getAttributeValue(element, HtmlAttributeName.ID.getName());
			if (elementId!=null && elementId.equals(id)) {
				httpElementList.add(this.createHtmlElement(element));
			}
		}
		return httpElementList;
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
		List<net.htmlparser.jericho.Element> allElement = this.element.getAllElements();
		for (net.htmlparser.jericho.Element element : allElement) {
			String elementName = this.getAttributeValue(element, HtmlAttributeName.NAME.getName());
			if (elementName!=null && elementName.equals(name) ) {
				httpElementList.add(this.createHtmlElement(element));
			}
		}
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
		if (this.cache_elementName == null) {
			for (HtmlElementName element : HtmlElementName.values()) {
				if (this.isElement(element)){
					this.cache_elementName = element;
					break;
				}
			}
			return this.cache_elementName;
		}
		return this.cache_elementName;
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
		List<net.htmlparser.jericho.Element> hrefList = this.element.getAllElements(net.htmlparser.jericho.HTMLElementName.A);
		if (hrefList.size() != 0) {
			for (net.htmlparser.jericho.Element element: hrefList) {
				urlList.add(this.createHtmlElement(element));
			}
			
		}
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
		for (FormControl formControl : this.element.getFormControls()) list.add(this.createHtmlElement(formControl));
		return list;
	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	/**
	 * 要素の指定属性取得<br/>
	 * 
	 * 本要素に設定されている指定の属性を取得します。
	 * 
	 * @param name 属性名
	 * @return 属性値
	 */
	private String getAttributeValue(net.htmlparser.jericho.Segment element, String name) {
		return element.getFirstElement().getAttributeValue(name);
	}

	/**
	 * HTMLパーサの要素インスタンスリスト変換
	 * HTMLパーサの要素インスタンスを本クラスのインスタンスでラップしたリストを返却する。
	 * 
	 * @param elementList HTMLパーサ一覧
	 * @return 本インスタンス一覧
	 */
	private List<jp.co.dk.document.Element> convertList(List<net.htmlparser.jericho.Element> elementList) {
		List<jp.co.dk.document.Element> httpElementList = new ArrayList<jp.co.dk.document.Element>();
		if (elementList.size() > 0) {
			for (net.htmlparser.jericho.Element element : elementList) {
				httpElementList.add(this.createHtmlElement(element));
			}
		}
		return httpElementList;
	}
	
	/**
	 * 要素オブジェクトを元にHTML要素を生成します。
	 * 
	 * @param element 要素オブジェクト
	 * @return 生成された要素オブジェクト
	 */
	private HtmlElement createHtmlElement(net.htmlparser.jericho.Segment element) {
		try {
			return (HtmlElement)this.elementFactory.convert(new HtmlElement(element, this.elementFactory));
		} catch (DocumentException e) {
			// TODO 要素生成に失敗したのにデフォルト要素を返却？
			return new HtmlElement(element, this.elementFactory);
		}
	}
}
