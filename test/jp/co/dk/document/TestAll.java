package jp.co.dk.document;

import jp.co.dk.document.html.TestHtmlDocument;
import jp.co.dk.document.html.TestHtmlElement;
import jp.co.dk.document.html.TestHtmlElementFactory;
import jp.co.dk.document.html.constant.TestHtmlCharSetName;
import jp.co.dk.document.html.constant.TestHtmlRequestMethodName;
import jp.co.dk.document.html.constant.TestHttpEquivName;
import jp.co.dk.document.html.element.TestA;
import jp.co.dk.document.html.element.TestAction;
import jp.co.dk.document.html.element.TestCheckBox;
import jp.co.dk.document.html.element.TestForm;
import jp.co.dk.document.html.element.TestImage;
import jp.co.dk.document.html.element.TestInput;
import jp.co.dk.document.html.element.TestMeta;
import jp.co.dk.document.html.element.TestOption;
import jp.co.dk.document.html.element.TestPassword;
import jp.co.dk.document.html.element.TestRadio;
import jp.co.dk.document.html.element.TestReset;
import jp.co.dk.document.html.element.TestSelect;
import jp.co.dk.document.xml.TestXmlDocument;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestByteDump.class, TestFile.class, TestHtmlDocument.class, TestHtmlElement.class, TestHtmlElementFactory.class, TestHtmlCharSetName.class,
	TestHtmlRequestMethodName.class, TestHttpEquivName.class, 
	TestA.class, TestAction.class, TestCheckBox.class, TestForm.class, TestImage.class, TestInput.class, TestMeta.class,
	TestOption.class, TestPassword.class, TestRadio.class, TestReset.class, TestSelect.class,
	TestXmlDocument.class
})
public class TestAll {}
