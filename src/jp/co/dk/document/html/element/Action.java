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
	 * @return url 本FORM要素が表すURL
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
		return this.form.getAttribute(HtmlAttributeName.ACTION.getName());
	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	/**
	 * URIタイプ取得
	 * 
	 * 指定URI文字列が表すURIタイプを返却する。
	 * 合致するタイプがない場合、nullを返却する。
	 * 
	 * @param url URI文字列
	 * @return URIタイプ
	 */
	 private ActionType getActionType(String url) {
		for (ActionType type : ActionType.values()) {
			Pattern pattern = type.getPattern();
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 * URIインスタンス生成
	 * 
	 * 指定のURI文字列からURIインスタンスを生成する。
	 * 
	 * @param uri URI文字列
	 * @return URIインスタンス
	 * @throws HttpConnectException 生成不可能なURI文字列の場合
	 */
	private URI createURI(String uri) throws HtmlDocumentException{
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			throw new HtmlDocumentException(DocumentMessage.ERROR_FALLED_TO_CREATE_URI_INSTANCE, uri, e);
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
