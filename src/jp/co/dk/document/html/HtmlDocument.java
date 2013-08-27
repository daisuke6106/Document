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
	
	private HtmlElement htmlElement;
	
	private ElementFactory elementFactory;
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public HtmlDocument(InputStream inputStream) throws HtmlDocumentException{
		this(inputStream, new HtmlElementFactory());
	}
	
	/**
	 * コンストラクタ<p/>
	 * InputStreamから要素を生成します。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 * @param elementFactory 要素生成ファクトリ
	 */
	public HtmlDocument(InputStream inputStream, HtmlElementFactory elementFactory) throws HtmlDocumentException{
		super(inputStream);
		try {
			this.htmlElement    = new HtmlElement(new net.htmlparser.jericho.Source(inputStream), elementFactory);
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
		if (title == null || title.size() == 0) {
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
					if (charset.isEncording(meta.getContent())) return charset;
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
		return this.htmlElement.getContent();
		this.htmlElement.
	}
	
	@Override
	public java.io.File save (java.io.File dir, String filename ) throws DocumentException {
		HtmlCharSetName charset = this.getEncode();
		if (charset != null) {
			return this.save(dir, filename, charset.getEncoding());
		} else {
			return this.save(dir, filename, HtmlCharSetName.UTF_8.getEncoding());
		}
	}
	
	/**
	 * このオブジェクトが表すファイルを指定のディレクトリへ指定のファイル名、指定の文字コードにて保存する。<p>
	 * 正常に保存した場合、保存したファイルオブジェクトを返却する。
	 * <br/>
	 * 以下の条件の場合、例外を発生する。<br/>
	 * ・引数が設定されていない場合<br/>
	 * ・指定された保存先ディレクトリが存在しない場合<br/>
	 * ・保存先のファイルがすでに存在する場合<br/>
	 * ・その他の理由で保存に失敗した場合<br/>
	 * 
	 * @param dir 保存先ディレクトリ
	 * @param filename ファイル名
	 * @param charset 文字コード
	 * @return 保存したファイル
	 * @throws DocumentException 保存に失敗した場合
	 */
	public java.io.File save (java.io.File dir, String filename, String charset) throws DocumentException {
		if (dir == null || filename == null || charset == null) throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE_PARAM_IS_NOT_SET);
		if (!dir.exists()) throw new DocumentException(DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND, dir.getAbsolutePath());
		if (!dir.isDirectory()) throw new DocumentException(DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY, dir.getAbsolutePath());
		
		java.io.File path = new java.io.File(new StringBuilder(dir.getAbsolutePath()).append('/').append(filename).toString());
		if (path.exists()) throw new DocumentException(DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH, dir.getAbsolutePath());
		try {
			FileOutputStream fos = new FileOutputStream(path);
			 byte [] byteArray = this.htmlElement.toString().getBytes(charset);
			fos.write(byteArray);
			fos.close();
		} catch (UnsupportedEncodingException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		}
		return path;
	}
	
	@Override
	public String toString() {
		return this.htmlElement.toString();
	}
}
