package jp.co.dk.document.html;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.mozilla.universalchardet.UniversalDetector;

import jp.co.dk.document.Document;
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
import jp.co.dk.morphologicalanalysis.Alanalysis;
import jp.co.dk.morphologicalanalysis.Token;

/**
 * HtmlRootElementは、HTMLドキュメントの最上位を表すクラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class HtmlDocument extends File implements Document{
	
	protected String encode;
	
	protected org.jsoup.nodes.Document document;
	
	protected HtmlElement html;
	
	protected HtmlElement head;
	
	protected HtmlElement body;
	
	protected HtmlElementFactory elementFactory;
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @throws DocumentException HTMLの読み込みに失敗した場合
	 */
	public HtmlDocument(InputStream inputStream) throws DocumentException{
		this(inputStream, new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @param elementFactory 要素生成ファクトリ
	 * @throws DocumentException HTMLの読み込みに失敗した場合 
	 */
	public HtmlDocument(InputStream inputStream, HtmlElementFactory elementFactory) throws DocumentException{
		super(inputStream);
		InputStream is = super.fileData.getStream();
		try {
			this.encode = this.getEncodeByData();
			if (this.encode == null) throw new HtmlDocumentException(DocumentMessage.ERROR_FAILED_TO_PARSE_HTML);
			this.document = Jsoup.parse(is, encode, "");
			this.html     = new HtmlElement(this.document       , elementFactory);
			this.head     = new HtmlElement(this.document.head(), elementFactory);
			this.body     = new HtmlElement(this.document.body(), elementFactory);
			this.elementFactory = elementFactory;
		} catch (IOException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_FAILED_TO_PARSE_HTML, e);
		}
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @param encode 文字コード
	 * @throws DocumentException HTMLの読み込みに失敗した場合  
	 */
	public HtmlDocument(InputStream inputStream, String encode) throws DocumentException{
		this(inputStream, encode, new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param inputStream 読み込み対象のストリーム
	 * @param encode 文字コード
	 * @param elementFactory 要素生成ファクトリ
	 * @throws DocumentException HTMLの読み込みに失敗した場合 
	 */
	public HtmlDocument(InputStream inputStream, String encode, HtmlElementFactory elementFactory) throws DocumentException{
		super(inputStream);
		InputStream is = super.fileData.getStream();
		try {
			this.encode   = encode;
			this.document = Jsoup.parse(is, this.encode, "");
			this.html     = new HtmlElement(this.document       , elementFactory);
			this.head     = new HtmlElement(this.document.head(), elementFactory);
			this.body     = new HtmlElement(this.document.body(), elementFactory);
			this.elementFactory = elementFactory;
		} catch (IOException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_FAILED_TO_PARSE_HTML, e);
		}
	}
	
	public HtmlDocument(String html) throws DocumentException{
		this(html, new HtmlElementFactory());
	}
	
	HtmlDocument(String html, HtmlElementFactory elementFactory) throws DocumentException{
		super(new ByteArrayInputStream(html.getBytes()));
		this.document = Jsoup.parse(html);
		this.html = new HtmlElement(this.document       , elementFactory);
		this.head = new HtmlElement(this.document.head(), elementFactory);
		this.body = new HtmlElement(this.document.body(), elementFactory);
		this.elementFactory = elementFactory;
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
	
	/**
	 * HTMLパーサの要素インスタンスリスト変換
	 * HTMLパーサの要素インスタンスを本クラスのインスタンスでラップしたリストを返却する。
	 * 
	 * @param elementList HTMLパーサ一覧
	 * @return 本インスタンス一覧
	 */
	protected List<jp.co.dk.document.Element> convertList(List<org.jsoup.nodes.Element> elementList) {
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
	protected HtmlElement createHtmlElement(org.jsoup.nodes.Element element) {
		try {
			return (HtmlElement)this.elementFactory.convert(new HtmlElement(element, this.elementFactory));
		} catch (DocumentException e) {
			return new HtmlElement(element, this.elementFactory);
		}
	}
	
	/**
	 * <p>バイナリデータを基に、文字コードを判定します。</p>
	 * 判定できなかった場合、nullが返却されます。
	 * @return 文字コード
	 * @throws IOException バイナリデータの読み込みに失敗した場合
	 */
	protected String getEncodeByData() throws IOException {
		InputStream is = super.fileData.getStream();
		byte[] buf = new byte[1024];
		UniversalDetector detector = new UniversalDetector(null);
		int nread;
		while ((nread = is.read(buf)) > 0 && !detector.isDone()) detector.handleData(buf, 0, nread);
		detector.dataEnd();
		return detector.getDetectedCharset();
	}
	
	/**
	 * <p>タイトルから名詞一覧を取得する。</p>
	 * ページがHTMLでない、またはタイトルが存在しなかった場合、空のリストを返却します。
	 * 
	 * @return 名詞一覧
	 */
	public List<Token> getNounsByTitle() {
		String title = this.getTitle();
		if (title == null || title.equals("")) return new ArrayList<>();
		Alanalysis alanalisys = new Alanalysis(title);
		return alanalisys.getNouns();
	}

	/**
	 * <p>コンテンツから名詞一覧を取得する。</p>
	 * ページがHTMLでない、またはコンテンツが存在しなかった場合、空のリストを返却します。
	 * 
	 * @return 名詞一覧
	 */
	public List<Token> getNounsByBody() {
		return this.body.getNouns();
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
		return this.html.getNode(selector);
	}
}
