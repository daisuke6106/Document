package jp.co.dk.document;

/**
 * ElementSelectorは、HTML、XMLドキュメントから特定の要素を取り出す場合に<br/>
 * その対象を判定するためのルールを定義する際に使用するインターフェース。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface ElementSelector {
	
	/**
	 * 対象の要素かを判定する。
	 * 
	 * @param element 要素
	 * @return 判定結果（true=対象の要素の場合、false=対象の要素でない場合）
	 */
	public boolean judgment(jp.co.dk.document.Element element);
}
