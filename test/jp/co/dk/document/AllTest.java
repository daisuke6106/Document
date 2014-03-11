package jp.co.dk.document;

import jp.co.dk.document.html.HtmlDocumentTest;
import jp.co.dk.document.html.HtmlElementTest;
import jp.co.dk.document.html.HtmlElementFactoryTest;
import jp.co.dk.document.html.constant.HtmlCharSetNameTest;
import jp.co.dk.document.html.constant.HtmlRequestMethodNameTest;
import jp.co.dk.document.html.constant.HttpEquivNameTest;
import jp.co.dk.document.html.element.ATest;
import jp.co.dk.document.html.element.ActionTest;
import jp.co.dk.document.html.element.CheckBoxTest;
import jp.co.dk.document.html.element.FormTest;
import jp.co.dk.document.html.element.ImageTest;
import jp.co.dk.document.html.element.InputTest;
import jp.co.dk.document.html.element.MetaTest;
import jp.co.dk.document.html.element.OptionTest;
import jp.co.dk.document.html.element.PasswordTest;
import jp.co.dk.document.html.element.RadioTest;
import jp.co.dk.document.html.element.ResetTest;
import jp.co.dk.document.html.element.SelectTest;
import jp.co.dk.document.xml.XmlDocumentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ByteDumpTest.class, FileTest.class, HtmlDocumentTest.class, HtmlElementTest.class, HtmlElementFactoryTest.class, HtmlCharSetNameTest.class,
	HtmlRequestMethodNameTest.class, HttpEquivNameTest.class, 
	ATest.class, ActionTest.class, CheckBoxTest.class, FormTest.class, ImageTest.class, InputTest.class, MetaTest.class,
	OptionTest.class, PasswordTest.class, RadioTest.class, ResetTest.class, SelectTest.class,
	XmlDocumentTest.class
})
public class AllTest {}
