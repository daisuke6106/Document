package jp.co.dk.document.html.element;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.document.html.exception.HtmlDocumentException;
import jp.co.dk.document.message.DocumentMessage;

/**
 * Actionは、FORM要素のアクションの要素を表すクラス
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class Action {
	
	protected Form form;
	
	/**
	 * コンストラクタ
	 * @param form
	 */
	protected Action(Form form){
		this.form = form;
	}
	
	/**
	 * URL取得
	 * 本FORM要素のactionが示すURLを返却する。
	 * 
	 * @return 本FORM要素が表すURL
	 */
	public URL getURL() throws HtmlDocumentException {
		String url = this.toString();
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_AN_INVALID_URL_WAS_SPECIFIED, url, e);
		}
	}
	
	/**
	 * Action取得
	 * 本FORM要素に設定されているアクション文字列を取得する。
	 * 
	 * @return アクション文字列
	 */
	@Override
	public String toString(){
		String action = this.form.getAttribute(HtmlAttributeName.ACTION.getName());
		if (action == null) {
			return "";
		} else {
			return action;
		}
	}

}

enum ActionType {
	
	HTTP("http:.*"),
	
	HTTPS("https:.*"),
	
	MAILTO("mailto:.*"),
	
	FTP("ftp:.*"),
	;
	
	/** 各URIを表すURIパターン */
	private Pattern pattern;
	
	/**
	 * コンストラクタ
	 * @param pattern URIを表す正規表現文字列
	 */
	private ActionType(String pattern){
		this.pattern = Pattern.compile(pattern);
	}
	
	/**
	 * パターン取得
	 * URIを表すパターンを返却する。
	 * 
	 * 
	 */
	Pattern getPattern() {
		return this.pattern;
	}
}
