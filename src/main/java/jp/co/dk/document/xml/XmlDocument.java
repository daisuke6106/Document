package jp.co.dk.document.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jp.co.dk.document.Document;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.File;
import jp.co.dk.document.ElementSelector;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

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
	 * @throws DocumentException 
	 */
	public XmlDocument(InputStream inputStream) throws DocumentException{
		super(inputStream);
		this.document   = this.createDocument(super.fileData.getStream());
		this.xmlElement = new jp.co.dk.document.xml.XmlElement(this.document.getDocumentElement());
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
	
	@Override
	public java.io.File save (java.io.File dir, String filename ) throws DocumentException {
		if (!dir.exists()) throw new DocumentException(DocumentMessage.ERROR_DOWNLOAD_DIR_IS_NOT_FOUND, dir.getAbsolutePath());
		if (!dir.isDirectory()) throw new DocumentException(DocumentMessage.ERROR_SPECIFIED_PATH_IS_NOT_DIRECTORY, dir.getAbsolutePath());
		
		java.io.File path = new java.io.File(new StringBuilder(dir.getAbsolutePath()).append('/').append(filename).toString());
		if (path.exists()) throw new DocumentException(DocumentMessage.ERROR_FILE_APLREADY_EXISTS_IN_THE_SPECIFIED_PATH, dir.getAbsolutePath());
		try {
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			pw.print(this.xmlElement.toString());
			pw.close();
		} catch (MalformedURLException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FAILED_TO_SAVE_FILE, path.getPath(), e);
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
			throw new XmlDocumentException(DocumentMessage.FAILED_TO_GENERATE_THE_XML_INSTANCE, e);
		}
		try {
			return builder.parse(inputStream);
		} catch (SAXException e) {
			throw new XmlDocumentException(DocumentMessage.SAX_WARNING_OR_ERROR, e);
		} catch (IOException e) {
			throw new XmlDocumentException(DocumentMessage.FILE_INPUT_OUTPUT_ERROR, e);
		}
	}
}
