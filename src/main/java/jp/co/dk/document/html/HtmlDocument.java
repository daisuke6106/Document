package jp.co.dk.document.html;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jsoup.Jsoup;

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
	
	protected org.jsoup.nodes.Document document;
	
	protected HtmlElement html;
	
	protected HtmlElement head;
	
	protected HtmlElement body;
	
	protected ElementFactory elementFactory;
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @throws DocumentException 
	 */
	public HtmlDocument(InputStream inputStream) throws DocumentException{
		this(inputStream, "UTF-8", new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @throws DocumentException 
	 */
	public HtmlDocument(InputStream inputStream, String encording) throws DocumentException{
		this(inputStream, encording, new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @param elementFactory 要素生成ファクトリ
	 * @throws DocumentException 
	 */
	public HtmlDocument(InputStream inputStream, String encording, HtmlElementFactory elementFactory) throws DocumentException{
		super(inputStream);
		try {
			this.document = Jsoup.parse(inputStream, encording, "");
			this.html = new HtmlElement(this.document       , elementFactory);
			this.head = new HtmlElement(this.document.head(), elementFactory);
			this.body = new HtmlElement(this.document.body(), elementFactory);
			this.elementFactory = elementFactory;
		} catch (IOException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_FAILED_TO_PARSE_HTML, e);
		}
	}
	
	/**
	 * ページタイトル取得<p>
	 * ページのタイトルを取得する。
	 * 
	 * @return ページタイトル
	 */
	public String getTitle() {
		return this.document.title();
	}
	
	/**
	 * ページエンコード取得<p>
	 * このページのエンコードを取得します。<br/>
	 * 設定されていない場合、nullを返却します。
	 * 
	 * @return キャラクタセット
	 */
	public HtmlCharSetName getEncode() {
		List<Element> elementList = this.head.getElement(HtmlElementName.META);
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
		return this.html.getElement();
	}

	@Override
	public List<Element> getElement(ElementName elementName) {
		return this.html.getElement(elementName);
	}

	@Override
	public List<Element> getElement(ElementSelector elementSelector) {
		return this.html.getElement(elementSelector);
	}
	
	@Override
	public List<Element> getChildElement() {
		return this.html.getChildElement();
	}

	@Override
	public List<Element> getChildElement(ElementName elementName) {
		return this.html.getChildElement(elementName);
	}
	
	@Override
	public List<Element> getChildElement(ElementSelector elementSelector) {
		return this.html.getChildElement(elementSelector);
	}

	@Override
	public boolean hasChildElement() {
		return this.html.hasChildElement();
	}

	@Override
	public boolean isElement(ElementName elementName) {
		return this.html.isElement(elementName);
	}

	@Override
	public boolean hasElement(ElementName elementName) {
		return this.html.hasElement(elementName);
	}

	@Override
	public String getAttribute(String name) {
		return this.html.getAttribute(name);
	}

	@Override
	public boolean hasAttribute(String attributeName) {
		return this.html.hasAttribute(attributeName);
	}

	@Override
	public String getTagName() {
		return this.html.getTagName();
	}

	@Override
	public String getContent() {
		return this.html.getContent();
	}
	
	@Override
	public String toString() {
		return this.html.toString();
	}
}
