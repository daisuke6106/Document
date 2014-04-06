package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.DocumentFoundationTest;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.HtmlElementFactory;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.constant.HtmlRequestMethodName;
import jp.co.dk.document.html.exception.HtmlDocumentException;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class ImageTest extends DocumentFoundationTest{
	
	
	@Test
	public void getSrc() {
		// srcタグが設定されている場合、その値を取得できること
		try {
			String tag = "<img src=\"text\">";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getSrc(), is ("text"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// srcタグが設定されていない場合、空文字を取得できること
		try {
			String tag = "<img>";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getSrc(), is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// srcタグが設定されているが、値が空文字である場合、空文字が取得できること
		try {
			String tag = "<img src=\"\">";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getSrc(), is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getUrl() {
		// srcタグが設定されている場合、その値を取得できること
		try {
			String tag = "<img src=\"text\">";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is ("text"));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// srcタグが設定されていない場合、空文字を取得できること
		try {
			String tag = "<img>";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
		
		// srcタグが設定されているが、値が空文字である場合、空文字が取得できること
		try {
			String tag = "<img src=\"\">";
			Image sut = new Image(new HtmlElement(tag, new HtmlElementFactory()));
			assertThat(sut.getUrl(), is (""));
		} catch (HtmlDocumentException e) {
			fail(e);
		}
	}
}
