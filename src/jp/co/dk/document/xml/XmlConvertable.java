package jp.co.dk.document.xml;

import jp.co.dk.document.xml.exception.XmlDocumentException;

/**
 * XMLへ変換を行うことができるクラスが実装するインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 * 
 */
public interface XmlConvertable {
	
	/**
	 * 自身のクラスをXMLへ変換する。<p/>
	 * 
	 * Elementをこのクラスにて生成し、自身のクラスの要素を設定し、返却してください。<br/>
	 * 例：<br/>
	 * public Element convert(Element element){<br/>
	 * Element element = new Element("tagname");<br/>
	 * element.addAttribute(new Attribute("key", "value"));<br/>
	 * element.appendChild(this.object.convert());<br/>
	 * element.setTextNode("text");<br/>
	 * return element;<br/>
	 * }<br/>
	 * 
	 * @return 加工した要素
	 * @throws XmlDocumentException XML変換に失敗した場合
	 */
	public XmlElement convert() throws XmlDocumentException ;
}
