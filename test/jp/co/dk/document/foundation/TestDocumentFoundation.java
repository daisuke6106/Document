package jp.co.dk.document.foundation;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.test.template.TestCaseTemplate;

public class TestDocumentFoundation extends TestCaseTemplate {
	
	protected jp.co.dk.document.File createFileDocument() throws DocumentException {
		return new jp.co.dk.document.File(super.getInputStreamBySystemResource("jp/co/dk/document/JPEG.jpg"));
	}
	
	protected jp.co.dk.document.html.HtmlDocument createHtmlDocument(String path) throws DocumentException {
		try {
			return new jp.co.dk.document.html.HtmlDocument(super.getInputStreamBySystemResource(path));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		return null;
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
}
