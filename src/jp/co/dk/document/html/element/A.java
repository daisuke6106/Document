package jp.co.dk.document.html.element;

import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;
import jp.co.dk.message.exception.AbstractMessageException;

/**
 * Aは、HTMLのアンカー要素を表す要素クラス。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class A extends HtmlElement {

	/**
	 * コンストラクタ
	 * 
	 * HTMLの要素のインスタンスを生成する。
	 * 
	 * @param element HTMLパーサの要素インスタンス
	 */
	public A(HtmlElement element) {
		super(element);
	}
	
	/**
	 * このアンカーが示すHREF属性の設定値を取得する。<br/>
	 * 
	 * HREFが設定されていない場合、空文字を返却する。
	 * 
	 * @return href設定値
	 */
	public String getHref() {
		String href = super.getAttribute(HtmlAttributeName.HREF.getName());
		if (href == null || href.equals("")) {
			return "";
		} else {
			return href;
		}
	}
	
	/**
	 * ファイル名を取得する。<br/>
	 * 
	 * 指定のURLからファイル名称を取得する。
	 * 
	 * @param url
	 * @return ファイル名
	 */
	private String getFileName(String url) {
		return url.substring(url.lastIndexOf('/') + 1);
	}
	
	/**
	 * 引数に指定されたファイル名から拡張子を取得する。<br//>
	 * 
	 * @param ファイル名
	 * @return 拡張子
	 */
	private String getExtension(String fileName) {
		if (fileName == null || fileName.equals("")) {
			return "";
		}
		int index = fileName.indexOf('.');
		if (index == -1) {
			return "";
		}
		return fileName.substring(index+1);
	}
}
