package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.test.template.TestCaseTemplate;

public class DocumentFoundationTest extends TestCaseTemplate {
	
	protected jp.co.dk.document.File createFileDocument() throws DocumentException {
		return new jp.co.dk.document.File(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
	}
	
	protected jp.co.dk.document.html.HtmlDocument createHtmlDocument() throws DocumentException {
		try {
			return new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/html/HTML.html"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		return null;
	}
	
	protected jp.co.dk.document.xml.XmlDocument createXmlDocument() throws DocumentException {
		try {
			return new jp.co.dk.document.xml.XmlDocument(super.getInputStreamBySystemResource("jp/co/dk/document/xml/XML.xml"));
		} catch (XmlDocumentException e) {
			fail(e);
		}
		return null;
	}
	
	protected InputStream createDocumentStream(String documentStr) {
		try {
			return new ByteArrayInputStream(documentStr.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			fail(e);
		}
		return null;
	}
}
