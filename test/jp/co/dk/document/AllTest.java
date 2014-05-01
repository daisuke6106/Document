package jp.co.dk.document;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	/* jp.co.dk.document */
	jp.co.dk.document.ByteDumpTest.class, 
	jp.co.dk.document.FileTest.class, 
	
	/* jp.co.dk.document.exception */
	jp.co.dk.document.exception.DocumentExceptionTest.class,
	
	/* jp.co.dk.document.html */
	jp.co.dk.document.html.HtmlDocumentTest.class, 
	jp.co.dk.document.html.HtmlElementTest.class, 
	jp.co.dk.document.html.HtmlElementFactoryTest.class,  
	
	/* jp.co.dk.document.html.constant */
	jp.co.dk.document.html.constant.HtmlCharSetNameTest.class, 
	jp.co.dk.document.html.constant.HtmlRequestMethodNameTest.class, 
	jp.co.dk.document.html.constant.HttpEquivNameTest.class,
	
	/* jp.co.dk.document.html.element */
	jp.co.dk.document.html.element.ActionTest.class, 
	jp.co.dk.document.html.element.ATest.class, 
	jp.co.dk.document.html.element.CheckBoxTest.class, 
	jp.co.dk.document.html.element.FormTest.class, 
	jp.co.dk.document.html.element.ImageTest.class, 
	jp.co.dk.document.html.element.InputTest.class, 
	jp.co.dk.document.html.element.LinkTest.class, 
	jp.co.dk.document.html.element.MetaTest.class,
	jp.co.dk.document.html.element.OptionTest.class, 
	jp.co.dk.document.html.element.PasswordTest.class, 
	jp.co.dk.document.html.element.RadioTest.class, 
	jp.co.dk.document.html.element.ResetTest.class, 
	jp.co.dk.document.html.element.SelectTest.class, 
	jp.co.dk.document.html.element.ScriptTest.class, 
	jp.co.dk.document.html.element.SubmitTest.class, 
	
	/* jp.co.dk.document.html.element.form */
	jp.co.dk.document.html.element.form.CheckBoxTest.class,
	jp.co.dk.document.html.element.form.FileTest.class,
	jp.co.dk.document.html.element.form.HiddenTest.class,
	jp.co.dk.document.html.element.form.PasswordTest.class, 
	jp.co.dk.document.html.element.form.RadioTest.class, 
	jp.co.dk.document.html.element.form.ResetTest.class, 
	jp.co.dk.document.html.element.form.SelectTest.class, 
	jp.co.dk.document.html.element.form.SubmitTest.class,
	jp.co.dk.document.html.element.form.TextTest.class, 
	
	/* jp.co.dk.document.html.exception */
	jp.co.dk.document.html.exception.HtmlDocumentExceptionTest.class,
	
	/* jp.co.dk.document.xml */
	jp.co.dk.document.xml.DocumentPropertyTypeTest.class,
	jp.co.dk.document.xml.XmlDocumentTest.class
})
public class AllTest {}
