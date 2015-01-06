package jp.co.dk.document.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.ElementSelector;

/**
 * Elementは、XMLの要素を表すクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class XmlElement implements jp.co.dk.document.Element{
	
	private org.w3c.dom.Node element;
	
	/**
	 * コンストラクタ
	 * @param inputStream 入力ストリーム
	 */
	XmlElement(org.w3c.dom.Node element) {
		this.element = element;
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
		List<jp.co.dk.document.Element> list = new ArrayList<jp.co.dk.document.Element>();
		NodeList nodeList = this.element.getChildNodes();
		for (int i=0; i<nodeList.getLength(); i++) {
			list.add(new jp.co.dk.document.xml.XmlElement(nodeList.item(i)));
		}
		return list;
	}

	@Override
	public List<jp.co.dk.document.Element> getChildElement(	ElementName elementName) {
		List<jp.co.dk.document.Element> list = new ArrayList<jp.co.dk.document.Element>();
		NodeList nodeList = this.element.getChildNodes();
		for (int i=0; i<nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String nodeName = nodeList.item(i).getNodeName();
			if (nodeName.equals(elementName.getName())) {
				list.add(new jp.co.dk.document.xml.XmlElement(node));
			}
			
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
		return this.element.hasChildNodes();
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
		NamedNodeMap nodeMap = this.element.getAttributes();
		Node node = nodeMap.getNamedItem(name);
		return node.getNodeValue();
	}

	@Override
	public boolean hasAttribute(String attributeName) {
		String value = this.getAttribute(attributeName);
		if (value == null || value.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String getTagName() {
		return this.element.getNodeName();
	}
	
	@Override
	public String getContent() {
		return this.element.getTextContent();
	}
	
}
