package jp.co.dk.document.xml;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementFactory;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * HtmlElementFactoryは、XML要素の生成を行うファクトリクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class XmlElementFactory implements ElementFactory {

	@Override
	public Element convert(Element element) throws DocumentException {
		try {
			return (XmlElement)element;
		} catch (ClassCastException e) {
			throw new DocumentException(DocumentMessage.ERROR_ELEMENT_CONVERT, e);
		}
	}
	
}
