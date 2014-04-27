package jp.co.dk.document;

import jp.co.dk.document.exception.DocumentExceptionTest;
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
import jp.co.dk.document.html.element.LinkTest;
import jp.co.dk.document.html.element.MetaTest;
import jp.co.dk.document.html.element.OptionTest;
import jp.co.dk.document.html.element.PasswordTest;
import jp.co.dk.document.html.element.RadioTest;
import jp.co.dk.document.html.element.ResetTest;
import jp.co.dk.document.html.element.ScriptTest;
import jp.co.dk.document.html.element.SelectTest;
import jp.co.dk.document.html.element.SubmitTest;
import jp.co.dk.document.xml.XmlDocumentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	/* jp.co.dk.document */
	ByteDumpTest.class, 
	FileTest.class, 
	
	/* jp.co.dk.document.exception */
	DocumentExceptionTest.class,
	
	/* jp.co.dk.document.html */
	HtmlDocumentTest.class, 
	HtmlElementTest.class, 
	HtmlElementFactoryTest.class,  
	
	/* jp.co.dk.document.html.constant */
	HtmlCharSetNameTest.class, 
	HtmlRequestMethodNameTest.class, 
	HttpEquivNameTest.class,
	
	/* jp.co.dk.document.html.element */
	ActionTest.class, 
	ATest.class, 
	CheckBoxTest.class, 
	FormTest.class, 
	ImageTest.class, 
	InputTest.class, 
	LinkTest.class, 
	MetaTest.class,
	OptionTest.class, 
	PasswordTest.class, 
	RadioTest.class, 
	ResetTest.class, 
	SelectTest.class, 
	ScriptTest.class, 
	SubmitTest.class, 
	
	/* jp.co.dk.document.xml */
	XmlDocumentTest.class
})
public class AllTest {}
