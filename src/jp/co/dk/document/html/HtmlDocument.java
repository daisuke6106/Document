package jp.co.dk.document.html;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import jp.co.dk.document.Document;
import jp.co.dk.document.ElementFactory;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.File;
import jp.co.dk.document.Element;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.constant.HtmlCharSetName;
import jp.co.dk.document.html.constant.HtmlElementName;
import jp.co.dk.document.html.constant.HttpEquivName;
import jp.co.dk.document.html.element.Meta;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * HtmlRootElementは、HTMLドキュメントの最上位を表すクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class HtmlDocument extends File implements Document{
	
	protected HtmlElement htmlElement;
	
	protected ElementFactory elementFactory;
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 * @throws DocumentException 
	 */
	public HtmlDocument(InputStream inputStream) throws DocumentException{
		this(inputStream, new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 * @param elementFactory 要素生成ファクトリ
	 * @throws DocumentException 
	 */
	public HtmlDocument(InputStream inputStream, HtmlElementFactory elementFactory) throws DocumentException{
		super(inputStream);
		try {
			this.htmlElement    = new HtmlElement(new net.htmlparser.jericho.Source(super.fileData.getStream()), elementFactory);
			this.elementFactory = elementFactory;
		} catch (IOException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_FAILED_TO_PARSE_HTML, e);
		}
	}
	
	/**
	 * ページタイトル取得<p>
	 * 
	 * ページのタイトルを取得する。
	 * 
	 * @return ページタイトル
	 */
	public String getTitle() {
		List<jp.co.dk.document.Element> title = this.htmlElement.getElement(HtmlElementName.TITLE);
		if (title.size() == 0) {
			return "";
		} else {
			return title.get(0).getContent().toString();
		}
	}
	
	/**
	 * ページエンコード取得<p>
	 * 
	 * このページのエンコードを取得します。<br/>
	 * 設定されていない場合、nullを返却します。
	 */
	public HtmlCharSetName getEncode() {
		List<Element> elementList = this.htmlElement.getElement(HtmlElementName.META);
		for (Element element : elementList) {
			Meta meta = (Meta) element;
			HttpEquivName httpEquiv = meta.getHttpEquiv();
			if (HttpEquivName.CONTENT_TYPE == httpEquiv) {
				for (HtmlCharSetName charset :HtmlCharSetName.values()) {
					if (charset.isEncording(meta.getMetaContent())) return charset;
				}
			}
		}
		return null;
	}

	@Override
	public List<Element> getElement() {
		return this.htmlElement.getElement();
	}

	@Override
	public List<Element> getElement(ElementName elementName) {
		return this.htmlElement.getElement(elementName);
	}

	@Override
	public List<Element> getElement(ElementSelector elementSelector) {
		return this.htmlElement.getElement(elementSelector);
	}
	
	@Override
	public List<Element> getChildElement() {
		return this.htmlElement.getChildElement();
	}

	@Override
	public List<Element> getChildElement(ElementName elementName) {
		return this.htmlElement.getChildElement(elementName);
	}
	
	@Override
	public List<Element> getChildElement(ElementSelector elementSelector) {
		return this.htmlElement.getChildElement(elementSelector);
	}

	@Override
	public boolean hasChildElement() {
		return this.htmlElement.hasChildElement();
	}

	@Override
	public boolean isElement(ElementName elementName) {
		return this.htmlElement.isElement(elementName);
	}

	@Override
	public boolean hasElement(ElementName elementName) {
		return this.htmlElement.hasElement(elementName);
	}

	@Override
	public String getAttribute(String name) {
		return this.htmlElement.getAttribute(name);
	}

	@Override
	public boolean hasAttribute(String attributeName) {
		return this.htmlElement.hasAttribute(attributeName);
	}

	@Override
	public String getTagName() {
		return this.htmlElement.getTagName();
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		this.getContect(sb, this.htmlElement);
		return sb.toString();
	}
	
	private void getContect(StringBuilder sb, HtmlElement element) {
		sb.append(htmlElement.getContent());
		for (jp.co.dk.document.Element childElement : element.getChildElement()){
			HtmlElement htmlElement = (HtmlElement)childElement;
			sb.append(htmlElement.getContent());
			getContect(sb, htmlElement);
		}
	}
	
	@Override
	public String toString() {
		return this.htmlElement.toString();
	}
}
