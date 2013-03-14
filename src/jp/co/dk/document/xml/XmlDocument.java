package jp.co.dk.document.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.xml.sax.SAXException;

import jp.co.dk.document.Document;
import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.File;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.document.exception.DocumentException;
import static jp.co.dk.document.message.DocumentMessage.*;

/**
 * Documentは、XMLドキュメントのドキュメントルートを表すクラスです。<br>
 * 
 * XMLドキュメント操作を行うことができます。
 * 
 * @version 1.0
 * @author D.Kanno
 * 
 */
public class XmlDocument extends File implements Document {
	
	private org.w3c.dom.Document document;
	
	private jp.co.dk.document.xml.XmlElement xmlElement;
	
	/**
	 * コンストラクタ<br/>
	 * 
	 * 読み込みストリームを元にオブジェクトを生成する。
	 * 
	 * @param inputStream  読み込みストリーム
	 */
	public XmlDocument(InputStream inputStream) throws XmlDocumentException{
		super(inputStream);
		this.document   = this.createDocument(inputStream);
		this.xmlElement = new jp.co.dk.document.xml.XmlElement(this.document.getDocumentElement());
	}
	
	/**
	 * コンストラクタ<p/>
	 * ルートドキュメントのタグ名称からXMLドキュメントを生成します。
	 * 
	 * @param rootName ドキュメントのルートタグ名称
	 * @throws XmlDocumentException XMLの生成に失敗した場合
	 */
	public XmlDocument(String rootName) throws XmlDocumentException {
		super(null);
		if (rootName == null || rootName.equals("")) throw new XmlDocumentException(ERROR_DOCUMENT_CAN_NOT_CREATE);
		this.document   = this.createDocument(rootName);
		this.xmlElement = new XmlElement(this.document.getDocumentElement());
	}
	
	public XmlDocument(XmlElement xmlElement) throws XmlDocumentException {
		super(null);
		if (xmlElement == null) throw new XmlDocumentException(ERROR_DOCUMENT_CAN_NOT_CREATE);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement() {
		return this.xmlElement.getElement();
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(ElementName elementName) {
		return this.xmlElement.getElement(elementName);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getElement(ElementSelector elementSelector) {
		return this.xmlElement.getElement(elementSelector);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement() {
		return this.xmlElement.getChildElement();
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement(ElementName elementName) {
		return this.xmlElement.getChildElement(elementName);
	}
	
	@Override
	public List<jp.co.dk.document.Element> getChildElement(ElementSelector elementSelector) {
		return this.xmlElement.getChildElement(elementSelector);
	}
	
	@Override
	public boolean hasChildElement() {
		return this.xmlElement.hasChildElement();
	}
	
	@Override
	public boolean isElement(jp.co.dk.document.ElementName elementName) {
		return this.xmlElement.isElement(elementName);
	}
	
	@Override
	public boolean hasElement(jp.co.dk.document.ElementName elementName) {
		return this.xmlElement.hasElement(elementName);
	}
	
	@Override
	public String getAttribute(String name) {
		return this.xmlElement.getAttribute(name);
	}
	
	@Override
	public boolean hasAttribute(String attributeName) {
		return this.xmlElement.hasAttribute(attributeName);
	}
	
	@Override
	public String getTagName() {
		return this.xmlElement.getTagName();
	}
	
	@Override
	public String getContent() {
		return this.xmlElement.getContent();
	}
	
	/**
	 * 指定された属性を追加する。
	 * 
	 * @param attribute 属性 
	 */
	public void addAttribute(XmlAttribute attribute) {
		this.xmlElement.addAttribute(attribute);
	}
	
	/**
	 * 指定された属性が存在するか判定する。
	 * 
	 * @param attribute 属性
	 * @return 判定結果（true=存在する、false=存在しない）
	 */
	public boolean hasAttribute(XmlAttribute attribute) {
		return this.xmlElement.hasAttribute(attribute);
	}
	

	/**
	 * 自身の要素が保持する属性の一覧を返却する。
	 * 
	 * @return 属性一覧
	 */
	public List<XmlAttribute> getAttribute() {
		return this.xmlElement.getAttribute();
	}
	
	/**
	 * 自身の要素の子要素として指定された要素を追加する。
	 * 
	 * @param element 要素
	 */
	public void appendChild(XmlElement element) {
		this.xmlElement.appendChild(element);
	}
	
	/**
	 * 自身の要素の子要素として指定された要素を追加する。
	 * 
	 * @param element 要素
	 * @throws XmlDocumentException XMLオブジェクトの変換に失敗した場合
	 */
	public void appendChild(XmlConvertable convertableObject) throws XmlDocumentException {
		this.xmlElement.appendChild(convertableObject);
	}
	
	/**
	 * 指定の要素が小要素に存在するか判定する。
	 * 
	 * @param element 要素
	 * @return 判定結果（true=存在する、false=存在しない）
	 */
	public boolean hasChild(Element element) {
		return this.xmlElement.hasChild(element);
	}
	
	/**
	 * 自身の小要素の一覧を返却する。
	 * 
	 * @return 小要素一覧
	 */
	public List<Element> getChild() {
		return this.xmlElement.getChild();
	}
	
	/**
	 * 自身の要素へ内容を設定する。
	 * すでに内容が設定されていた場合、上書きされる。
	 * 
	 * @param text 内容
	 */
	public void setTextNode(String text) {
		this.xmlElement.setTextNode(text);
	}
	
	/**
	 * 自身の要素へ内容を追記する。
	 * すでに内容が存在する場合、追記される。
	 * 
	 * @param text 内容
	 */
	public void appendTextNode(String text) {
		this.xmlElement.appendTextNode(text);
	}
	
	/**
	 * 自身の要素へ内容を追記する。
	 * 
	 * @return 
	 */
	public String getTextNode() {
		return this.xmlElement.getTextNode();
	}
	
	@Override
	public java.io.File save (java.io.File dir, String filename ) throws DocumentException {
		if (!dir.exists()) throw new DocumentException(ERROR_DOWNLOAD_DIR_IS_NOT_FOUND, dir.getAbsolutePath());
		if (!dir.isDirectory()) throw new DocumentException(ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY, dir.getAbsolutePath());
		
		java.io.File path = new java.io.File(new StringBuilder(dir.getAbsolutePath()).append('/').append(filename).toString());
		if (path.exists()) throw new DocumentException(ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH, path.getAbsolutePath());
		try {
			this.document.appendChild(xmlElement.combertElement(document));
			TransformerFactory tfactory = TransformerFactory.newInstance(); 
	        Transformer transformer = tfactory.newTransformer(); 
	        transformer.transform(new DOMSource(document), new StreamResult(new FileWriter(path))); 
//			pw.close();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * XMLドキュメントのインスタンス作成<p>
	 * 指定の読み込みストリームを元にXMLドキュメントのインスタンスを生成します。
	 * 
	 * @param inputStream 読み込みストリーム
	 * @return XMLドキュメントインスタンス
	 * @throws XmlDocumentException インスタンス生成に失敗した場合
	 */
	private org.w3c.dom.Document createDocument(InputStream inputStream) throws XmlDocumentException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder ;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XmlDocumentException(FAILED_TO_GENERATE_THE_XML_INSTANCE);
		}
		try {
			return builder.parse(inputStream);
		} catch (SAXException e) {
			throw new XmlDocumentException(SAX_WARNING_OR_ERROR);
		} catch (IOException e) {
			throw new XmlDocumentException(FILE_INPUT_OUTPUT_ERROR);
		}
	}
	
	/**
	 * 白紙のXMLドキュメントオブジェクトと生成します。
	 * 
	 * @param rootName 最上位タグ名称
	 * @return 白紙のXMLドキュメントオブジェクト
	 * @throws XmlDocumentException ドキュメントの生成に失敗した場合
	 */
	private org.w3c.dom.Document createDocument(String rootName) throws XmlDocumentException {
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docbuilder = null;
        org.w3c.dom.Document document = null;
		try {
			docbuilder = dbfactory.newDocumentBuilder();
			DOMImplementation domImpl=docbuilder.getDOMImplementation();
			document = domImpl.createDocument("", rootName, null);
		} catch (ParserConfigurationException e) {
			throw new XmlDocumentException(PARSE_CONFIGURATION_ERROR ,e);
		}
		return document;
	}
	
}
