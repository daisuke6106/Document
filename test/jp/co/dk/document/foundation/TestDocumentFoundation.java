package jp.co.dk.document.foundation;

import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.test.template.TestCaseTemplate;

public class TestDocumentFoundation extends TestCaseTemplate {
	
	protected jp.co.dk.document.File createFileDocument() {
		return new jp.co.dk.document.File(super.getInputStreamByOwnClass("JPEG.jpg"));
	}
	
	protected jp.co.dk.document.html.HtmlDocument createHtmlDocument() {
		try {
			return new jp.co.dk.document.html.HtmlDocument(super.getInputStreamByOwnClass("HTML.html"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		return null;
	}
	
	protected jp.co.dk.document.xml.XmlDocument createXmlDocument() {
		try {
			return new jp.co.dk.document.xml.XmlDocument(super.getInputStreamByOwnClass("XML.xml"));
		} catch (XmlDocumentException e) {
			fail(e);
		}
		return null;
	}
}
