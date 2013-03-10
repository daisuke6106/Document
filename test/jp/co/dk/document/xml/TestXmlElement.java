package jp.co.dk.document.xml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.Element;
import jp.co.dk.document.ElementName;
import jp.co.dk.document.xml.exception.XmlDocumentException;
import jp.co.dk.test.template.TestCaseTemplate;

import static jp.co.dk.document.message.DocumentMessage.*;

import org.junit.Test;

public class TestXmlElement extends TestCaseTemplate{

	@Test
	public void constractor() {
		// 指定の文字列から要素を作成できること。
		try {
			XmlElement xmlElement = new XmlElement("test");
		} catch (XmlDocumentException e) {
			fail(e);
		}
		
		// nullを元に文字列を要素を作成した場合、例外を送出すること。
		try {
			String nullStr = null;
			XmlElement xmlElement = new XmlElement(nullStr);
			fail();
		} catch (XmlDocumentException e) {
			assertEquals(e.getMessageObj(), ERROR_CREATE_ELEMENT);
		}
		
		// 空文字を元に文字列を要素を作成した場合、例外を送出すること。
		try {
			String nullStr = null;
			XmlElement xmlElement = new XmlElement("");
			fail();
		} catch (XmlDocumentException e) {
			assertEquals(e.getMessageObj(), ERROR_CREATE_ELEMENT);
		}
	}
	
	@Test
	public void getElement() {
		
		try {
			XmlElement element0 = new XmlElement("element0");
			assertEquals(element0.getElement().size(), 0);
		} catch (XmlDocumentException e) {
			fail(e);
		}
		
		try {
			List<Element> list0 = new ArrayList<Element>();
			XmlElement element0 = new XmlElement("element0");
			XmlElement element1_1 = new XmlElement("element1-1");
			XmlElement element1_2 = new XmlElement("element1-2");
			XmlElement element2_1 = new XmlElement("element2-1");
			XmlElement element2_2 = new XmlElement("element2-2");
			XmlElement element2_3 = new XmlElement("element2-3");
			element0.appendChild(element1_1);
			element0.appendChild(element1_2);
			element1_1.appendChild(element2_1);
			element1_1.appendChild(element2_2);
			element1_1.appendChild(element2_3);
			
			list0.add(element1_1);
			list0.add(element1_2);
			list0.add(element2_1);
			list0.add(element2_2);
			list0.add(element2_3);
			
			List<Element> list1 = element0.getElement();
			assertEquals(list1, list0);
		} catch (XmlDocumentException e) {
			fail(e);
		}
		
		try {
			XmlElement element0 = new XmlElement("element0");
			List<Element> list1 = element0.getElement(new ElementName() {
				@Override
				public String getName() {
					return "element2-2";
				}
			});
			assertEquals(list1.size(), 0);
		} catch (XmlDocumentException e) {
			fail(e);
		}
		
		try {
			XmlElement element0 = new XmlElement("element0");
			XmlElement element1_1 = new XmlElement("element1-1");
			XmlElement element1_2 = new XmlElement("element1-2");
			XmlElement element2_1 = new XmlElement("element2-1");
			XmlElement element2_2 = new XmlElement("element2-2");
			XmlElement element2_3 = new XmlElement("element2-3");
			element0.appendChild(element1_1);
			element0.appendChild(element1_2);
			element1_1.appendChild(element2_1);
			element1_1.appendChild(element2_2);
			element1_1.appendChild(element2_3);
			
			List<Element> list1 = element0.getElement(new ElementName() {
				@Override
				public String getName() {
					return "element2-2";
				}
			});
			assertEquals(1, list1.size());
			assertEquals(element2_2, list1.get(0));
		} catch (XmlDocumentException e) {
			fail(e);
		}
	}
	
}

