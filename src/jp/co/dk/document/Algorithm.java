package jp.co.dk.document;

/**
 * ハッシュコードを生成する際に使用するアルゴリズムを定義する定数クラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
enum Algorithm {
	
	/** SHA-256 */
	SHA_256("SHA-256"),
	
	/** SHA-512 */
	SHA_512("SHA-512"),
	
	/** MD5 */
	MD5("MD5"),
	
	;
	
	/** アルゴリズム名称 */
	private String algorithmName;
	
	/**
	 * コンストラクタ<p/>
	 * 
	 * @param algorithmName
	 */
	Algorithm(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	
	/**
	 * この定数が保持しているアルゴリズム名称を取得します。<p/>
	 * 
	 * @return アルゴリズム名称
	 */
	String getAlgorithmName() {
		return this.algorithmName;
	}
}