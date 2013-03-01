package jp.co.dk.document;

import jp.co.dk.document.exception.DocumentException;

/**
 * ElementFactoryは、要素オブジェクトを生成する要素生成ファクトリのインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface ElementFactory {
	
	/**
	 * 指定の要素を別の要素オブジェクトを変換します。<p/>
	 * 変換に失敗した場合、例外を送出します。
	 * 
	 * @param element 変換元要素オブジェクト
	 * @return 変換後要素オブジェクト
	 * @throws DocumentException 変換に失敗した場合
	 */
	Element convert(Element element) throws DocumentException;
}
