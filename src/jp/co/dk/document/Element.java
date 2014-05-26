package jp.co.dk.document;

import java.util.List;


/**
 * Elementは、ドキュメントの最小の要素を表すオブジェクトが実装するインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface Element {
	
	/**
	 * この要素が保持する全要素を取得する。<br/>
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getElement();
	
	/**
	 * この要素が保持する指定の要素の全要素を取得する。<br/>
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @param elementName 指定の要素
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getElement(ElementName elementName);
	
	/**
	 * この要素が保持する全要素から指定の要素選択ルールに適合する要素を取得する。<br/>
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @param elementSelector 指定の要素
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getElement(ElementSelector elementSelector);
	
	/**
	 * この要素が保持する子要素に指定の要素が存在するか判定する。<br/>
	 * 
	 * @param elementName 指定の要素
	 * @return 要素の有無(ある場合true、ない場合false)
	 */
	public boolean hasElement(ElementName elementName);
	
	/**
	 * この要素が保持する子要素を取得する。<br/>
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getChildElement();
	
	/**
	 * この要素が保持する子要素から指定の要素選択ルールに適合する要素を取得する。<br/>
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @param elementName 指定の要素
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getChildElement(ElementName elementName);
	
	/**
	 * この要素が保持する子要素を取得する。<br/>
	 * 
	 * 要素が存在しない場合、空のリストを返却する。
	 * 
	 * @param elementSelector 指定の要素
	 * @return 要素一覧
	 */
	public List<jp.co.dk.document.Element> getChildElement(ElementSelector elementSelector);
	
	/**
	 * この要素が子要素を保持しているか判定する。<br/>
	 * 
	 * @return 子要素の有無（true=要素あり、false=要素なし）
	 */
	public boolean hasChildElement();
	
	/**
	 * 指定要素チェック<br/>
	 * 指定の要素かどうかのチェックを行います。<br/>
	 * 
	 * 本要素が指定された要素の場合はtrue、それ以外の場合はfalseを返却する。
	 * 
	 * @param elementName 確認対象要素
	 * @return 判定結果
	 */
	public boolean isElement(ElementName elementName);
	
	/**
	 * 要素の指定属性取得<br/>
	 * 
	 * 本要素に設定されている指定の属性を取得します。
	 * 
	 * @param name 属性名
	 * @return 属性値
	 */
	public String getAttribute(String name);
	
	/**
	 * 属性有無の判定<br/>
	 * 
	 * 指定の属性が本要素に設定されているか判定する。
	 * 
	 * @param attributeName 属性要素
	 * @return 属性の有無判定結果
	 */
	public boolean hasAttribute(String attributeName);
	
	/**
	 * 要素の名称取得<br/>
	 * 
	 * @return 要素名称
	 */
	public String getTagName();
	
	/**
	 * 要素の内容取得<br/>
	 * 
	 * 本要素に保持する内容を取得する。
	 * 内容が指定されていなかった場合、空文字が返却される。
	 * 
	 * @return 内容を表現する文字列
	 */
	public String getContent();
}
