package jp.co.dk.document.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.morphologicalanalysis.Alanalysis;
import jp.co.dk.morphologicalanalysis.Token;
import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * HtmlElementは、HTMLの要素を表すクラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class HtmlElement implements jp.co.dk.document.Element{
	
	/** 要素インスタンス */
	protected org.jsoup.nodes.Element element;
	
	/** 要素生成ファクトリ */
	protected HtmlElementFactory elementFactory;

	/**
	 * <p>コンストラクタ</p>
	 * 単一のHTML要素を表す文字列からHTML要素インスタンスを生成する。
	 * 
	 * @param element 単一のHTML要素を表す文字列
	 * @param elementFactory HTMLファクトリ
	 * @throws HtmlDocumentException 単一のHTML要素、HTMLファクトリが指定されていない、もしくは単一のHTML要素で無い場合
	 */
	public HtmlElement(String elementStr, HtmlElementFactory elementFactory) throws HtmlDocumentException {
		if (elementStr == null || elementStr.equals("")) throw new HtmlDocumentException(ERROR_ELEMENT_STRING_IS_NOT_SET);
		if (elementFactory == null) throw new HtmlDocumentException(ERROR_ELEMENT_FACTORY_IS_NOT_SET);
		org.jsoup.nodes.Document document = Jsoup.parse(elementStr);
		org.jsoup.nodes.Element body = document.body();
		List<org.jsoup.nodes.Node> nodeList = body.childNodes();
		if (nodeList.size() != 1) throw new HtmlDocumentException(ERROR_FAILED_TO_PARSE_HTML);
		this.element = (org.jsoup.nodes.Element)nodeList.get(0);
		this.elementFactory = elementFactory;
	}
	
	/**
	 * <p>コンストラクタ</p>
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 * @param elementFactory HTMLファクトリ
	 */
	protected HtmlElement(org.jsoup.nodes.Element element, HtmlElementFactory elementFactory) {
		this.element  = element;
		this.elementFactory = elementFactory;
	}
	
	/**
	 * <p>コンストラクタ</p>
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
	
	public jp.co.dk.document.Element getParentElement() {
		org.jsoup.nodes.Element parent = this.element.parent();
		return this.createHtmlElement(parent);
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
		if (list.size() == 0) {
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
		org.jsoup.nodes.Element element = this.element.getElementById(id);
		if (element == null) return null;
		return this.createHtmlElement(element);
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
		List<HtmlElement> nameElementList = new ArrayList<HtmlElement>();
		List<jp.co.dk.document.Element> allElementList = this.getElement();
		for (jp.co.dk.document.Element element : allElementList) {
			String nameAttr = element.getAttribute("name");
			if (nameAttr != null && nameAttr.equals(name)) {
				nameElementList.add((HtmlElement)element);
			}
		}
		return nameElementList;
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
	
	/**
	 * コンテンツから名詞一覧を取得する。
	 * ページがHTMLでない、またはコンテンツが存在しなかった場合、空のリストを返却します。
	 * 
	 * @return 名詞一覧
	 */
	public List<Token> getNouns() {
		String content = this.getContent();
		if (content == null || content.equals("")) return new ArrayList<>();
		Alanalysis alanalisys = new Alanalysis(content);
		return alanalisys.getNouns();
	}
	

	/**
	 * <p>指定フォーマットで定義された要素指定文字列を基に該当する要素を取得する。</p>
	 * ・タグを指定する場合、タグ名称のみ記述する。（例：head body 等）<br/>
	 * ・IDを指定する場合、#を付与し、ID名を記述する。（例：#ID1 等）<br/>
	 * ・クラスを指定する場合、.を付与し、クラス名を記述する。（例：.className 等）<br/>
	 * 
	 * @param selector 要素取得のためのフォーマット文字列
	 * @return 該当要素一覧
	 */
	public List<HtmlElement> getNode(String selector) {
		String[] splitedSelector = selector.split(" ");
		List<HtmlElement> resultElementList = new ArrayList<>();
		resultElementList.add(this);
		for (String selectorStr : splitedSelector) resultElementList = this.getNode(resultElementList, selectorStr);
		return resultElementList;
	}
	
	/**
	 * <p>指定フォーマットで定義された要素指定文字列を基に該当する要素を取得する。</p>
	 * 
	 * @param elementList 要素一覧
	 * @param selector 要素取得のためのフォーマット文字列
	 * @return 該当要素一覧
	 */
	private List<HtmlElement> getNode(List<HtmlElement> elementList, String selector) {
		List<HtmlElement> reulstElementList = new ArrayList<>();
		for (HtmlElement element : elementList) reulstElementList.addAll(element.getNodeByStr(selector));
		return reulstElementList;
	}
	
	
	/**
	 * <p>指定フォーマットで定義された要素指定文字列を基に該当する要素を取得する。</p>
	 * 
	 * @param selector 要素取得のためのフォーマット文字列
	 * @return 該当要素一覧
	 */
	private List<HtmlElement> getNodeByStr(String selector) {
		List<HtmlElement> nodes = new ArrayList<>();
		if (selector.startsWith("#")) {
			String idName = selector.substring(1);
			nodes.add(this.getElementById(idName));
			return nodes;
		} else if (selector.startsWith(".")) {
			String className = selector.substring(1);
			List<Element> elementList = this.getElement(e -> ((HtmlElement)e).getClassList().contains(className));
			for (Element element : elementList) nodes.add((HtmlElement)element);
			return nodes;
		} else if (selector.startsWith("!")) {
			String contentePattern = selector.substring(1);
			List<Element> elementList = this.getElement(e -> ((HtmlElement)e).getContent().matches(contentePattern));
			for (Element element : elementList) nodes.add((HtmlElement)element);
			return nodes;
		} else {
			HtmlElementName htmlElementName = HtmlElementName.getName(selector);
			if (htmlElementName != null) {
				List<Element> elementList = this.getElement(htmlElementName);
				for (Element element : elementList) nodes.add((HtmlElement)element);
			}
		}
		return nodes;
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
		for (org.jsoup.nodes.Element formElement : this.element.getElementsByTag("input")) {
			list.add(this.createHtmlElement(formElement));
		}
		for (org.jsoup.nodes.Element formElement : this.element.getElementsByTag("select")) {
			list.add(this.createHtmlElement(formElement));
		}
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
