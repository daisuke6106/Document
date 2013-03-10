package jp.co.dk.document.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import jp.co.dk.document.Attribute;
import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.xml.exception.XmlDocumentException;

import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * Elementは、XMLの要素を表すクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class XmlElement implements jp.co.dk.document.Element{
	
//	private org.w3c.dom.Node element;
	
	/** タグ名称 */
	private String tagName;
	
	/** 属性一覧 */
	private List<Attribute> attributes;
	
	/** 小要素一覧 */
	private List<XmlElement> elements;
	
	/** 内容 */
	private StringBuilder textNode; 
	
	
	/**
	 * コンストラクタ
	 * @param inputStream 入力ストリーム
	 */
	XmlElement(org.w3c.dom.Node element) {
		this.tagName = element.getNodeName();
		NamedNodeMap nodeMap = element.getAttributes();
		for (int i = 0; i<nodeMap.getLength(); i++) {
			Node node = nodeMap.item(i);
		}
		
	}
	
	/**
	 * コンストラクタ<p/>
	 * 指定のタグ名称
	 * 
	 * @param tagName
	 * @throws XmlDocumentException
	 */
	public XmlElement(String tagName) throws XmlDocumentException{
		if (tagName == null || tagName.equals("")) throw new XmlDocumentException(ERROR_CREATE_ELEMENT); 
		this.tagName = tagName;
		this.attributes = new ArrayList<Attribute>();
		this.elements   = new ArrayList<XmlElement>();
		this.textNode   = new StringBuilder();
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement() {
		List<jp.co.dk.document.Element> list =  new ArrayList<jp.co.dk.document.Element>();
		this.getElement(list, this);
		return list;
	}
	
	private void getElement(List<jp.co.dk.document.Element> list, jp.co.dk.document.Element element) {
		List<jp.co.dk.document.Element> childNodes = element.getChildElement();
		list.addAll(childNodes);
		for (jp.co.dk.document.Element childElement : childNodes) {
			if (childElement.hasChildElement()) {
				this.getElement(list, childElement);
			}
		}
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(ElementName elementName) {
		List<jp.co.dk.document.Element> list = new ArrayList<jp.co.dk.document.Element>();
		List<jp.co.dk.document.Element> allList = this.getElement();
		for (jp.co.dk.document.Element element : allList) {
			if (element.isElement(elementName)) {
				list.add(element);
			}
		}
		return list;
	}
	
	@Override
	public List<Element> getElement(ElementSelector elementSelector) {
		List<Element> returnList  = new ArrayList<Element>();
		List<Element> elementList = this.getElement();
		for (Element element : elementList) {
			if (elementSelector.judgment(element)) {
				returnList.add(element);
			}
		}
		return returnList;
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement() {
		return new ArrayList<jp.co.dk.document.Element>(this.elements);
	}

	@Override
	public List<jp.co.dk.document.Element> getChildElement(	ElementName elementName) {
		List<jp.co.dk.document.Element> list = new ArrayList<jp.co.dk.document.Element>();
		for (XmlElement element : this.elements) {
			if (element.getTagName().equals(elementName.getName())) list.add(element);
		}
		return list;
	}
	
	@Override
	public List<Element> getChildElement(ElementSelector elementSelector) {
		List<Element> returnList  = new ArrayList<Element>();
		List<Element> elementList = this.getChildElement();
		for (Element element : elementList) {
			if (elementSelector.judgment(element)) {
				returnList.add(element);
			}
		}
		return returnList;
	}
	
	@Override
	public boolean hasChildElement() {
		return this.elements.size() > 0;
	}
	
	@Override
	public boolean isElement(ElementName elementName) {
		return this.getTagName().equals(elementName.getName());
	}

	@Override
	public boolean hasElement(ElementName elementName) {
		List<jp.co.dk.document.Element> allElementList =  this.getElement();
		for (jp.co.dk.document.Element element : allElementList) {
			if (element.getTagName().equals(elementName.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getAttribute(String name) {
//		NamedNodeMap nodeMap = this.element.getAttributes();
//		Node node = nodeMap.getNamedItem(name);
//		return node.getNodeValue();
		for (Attribute attribute : this.attributes) {
			if (attribute.getKey().equals(name)) return attribute.getValue(); 
		}
		return "";
	}

	@Override
	public boolean hasAttribute(String attributeName) {
//		String value = this.getAttribute(attributeName);
//		if (value == null || value.equals("")) {
//			return false;
//		} else {
//			return true;
//		}
		for (Attribute attribute : this.attributes) {
			if (attribute.getKey().equals(attributeName)) return true;
		}
		return false;
	}
	
	@Override
	public String getTagName() {
		return this.tagName;
	}
	
	@Override
	public String getContent() {
		return this.textNode.toString();
	}
	
	/**
	 * 指定された属性を追加する。
	 * 
	 * @param attribute 属性 
	 */
	public void addAttribute(Attribute attribute) {
		this.attributes.add(attribute);
	}
	
	/**
	 * 指定された属性が存在するか判定する。
	 * 
	 * @param attribute 属性
	 * @return 判定結果（true=存在する、false=存在しない）
	 */
	public boolean hasAttribute(Attribute attribute) {
		for (Attribute attributeObject : this.attributes){
			if (attributeObject.equals(attribute)) return true;
		}
		return false;
	}
	
	/**
	 * 自身の要素が保持する属性の一覧を返却する。
	 * 
	 * @return 属性一覧
	 */
	public List<Attribute> getAttribute() {
		return new ArrayList<Attribute>(this.attributes);
	}
	
	/**
	 * 自身の要素の子要素として指定された要素を追加する。
	 * 
	 * @param element 要素
	 */
	public void appendChild(XmlElement element) {
		this.elements.add(element);
	}
	
	/**
	 * 自身の要素の子要素として指定された要素を追加する。
	 * 
	 * @param element 要素
	 * @throws XmlDocumentException XMLオブジェクトの変換に失敗した場合
	 */
	public void appendChild(XmlConvertable convertableObject) throws XmlDocumentException {
		this.elements.add(convertableObject.convert());
	}
	
	/**
	 * 指定の要素が小要素に存在するか判定する。
	 * 
	 * @param element 要素
	 * @return 判定結果（true=存在する、false=存在しない）
	 */
	public boolean hasChild(Element element) {
		for (Element elementObject : this.elements) {
			if (elementObject.equals(element)) return true; 
		}
		return false;
	}
	
	/**
	 * 自身の小要素の一覧を返却する。
	 * 
	 * @return 小要素一覧
	 */
	public List<Element> getChild() {
		return new ArrayList<Element>(this.elements);
	}
	
	/**
	 * 自身の要素へ内容を設定する。
	 * すでに内容が設定されていた場合、上書きされる。
	 * 
	 * @param text 内容
	 */
	public void setTextNode(String text) {
		if (text == null) {
			this.textNode = new StringBuilder();
			return;
		}
		this.textNode = new StringBuilder(text);
	}
	
	/**
	 * 自身の要素へ内容を追記する。
	 * すでに内容が存在する場合、追記される。
	 * 
	 * @param text 内容
	 */
	public void appendTextNode(String text) {
		if (text == null) return ;
		this.textNode.append(text);
	}
	
	/**
	 * 自身の要素へ内容を追記する。
	 * 
	 * @return 
	 */
	public String getTextNode() {
		return this.textNode.toString();
	}
	
	/**
	 * ドキュメントオブジェクトを要素オブジェクトへ変換を行う。
	 * 
	 * @param document ドキュメントオブジェクト
	 * @return 要素オブジェクト
	 */
	org.w3c.dom.Element combertElement(org.w3c.dom.Document document) {
		org.w3c.dom.Element element = document.createElement(this.tagName);
		for (Attribute attribute : this.attributes) {
			element.setAttribute(attribute.getKey(), attribute.getValue());
		}
		for (XmlElement elementObject : this.elements) {
			element.appendChild(elementObject.combertElement(document));
		}
		org.w3c.dom.Text text       = document.createTextNode(this.getTextNode());
		element.appendChild(text);
		return element;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof XmlElement)) return false;
		XmlElement element = (XmlElement) object;
		boolean tagEquals       = this.tagName.equals(element.tagName);
		boolean childEquals     = this.elements.equals(element.elements);
		boolean attributeEquals = this.attributes.equals(element.attributes);
		boolean textEquals      = this.textNode.toString().equals(element.textNode.toString());
		if (tagEquals && childEquals && attributeEquals && textEquals) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashE = -1;
		int hashA = -1;
		for (Element e : this.elements) hashE += e.hashCode();
		for (Attribute a : this.attributes) hashA += a.hashCode();
		return 17 * this.tagName.hashCode() * hashE * hashA * this.textNode.hashCode();
	}
}
