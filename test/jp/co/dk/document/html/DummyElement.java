package jp.co.dk.document.html;

import java.util.List;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.ElementSelector;

public class DummyElement implements Element {
	@Override
	public List<Element> getElement() {	return null;}
	@Override
	public List<Element> getElement(ElementName elementName) {return null;}
	@Override
	public List<Element> getElement(ElementSelector elementSelector) {return null;}
	@Override
	public boolean hasElement(ElementName elementName) {return false;}
	@Override
	public List<Element> getChildElement() {return null;}
	@Override
	public List<Element> getChildElement(ElementName elementName) {	return null;}
	@Override
	public List<Element> getChildElement(ElementSelector elementSelector) {	return null;}
	@Override
	public boolean hasChildElement() {	return false;}
	@Override
	public boolean isElement(ElementName elementName) {return false;	}
	@Override
	public String getAttribute(String name) {return null;	}
	@Override
	public boolean hasAttribute(String attributeName) {return false;}
	@Override
	public String getTagName() {return null;}
	@Override
	public String getContent() {return null;}
}
