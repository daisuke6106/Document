package jp.co.dk.document;

import jp.co.dk.xml.exception.XmlDocumentException;
import static jp.co.dk.xml.exception.XmlDocumentMessage.*;

/**
 * Attributeは、XMLの属性を表すオブジェクトです。
 * 
 * @author D.Kanno
 * @version 1.0
 */
public class Attribute {
	
	private String key;
	
	private String value;
	
	/**
	 * 指定の属性のキー名称、属性の値を元に新しい属性オブジェクトを生成します。
	 * 
	 * @param key 属性のキー値
	 * @param value 属性の値
	 * @throws XmlDocumentException 属性オブジェクトの生成に失敗した場合
	 */
	public Attribute (String key, String value) throws XmlDocumentException {
		if (key == null || value == null) throw new XmlDocumentException(ERROR_VALUE_CA_NOT_BE_SPECIFIED_KEY_OR_NULL);
		this.key = key;
		this.value = value;
	}
	
	/**
	 * XMLの属性のキー値を返却します。
	 * @return キー値
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * XMLの属性の値を返却します。
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof Attribute)) return false;
		Attribute attribute = (Attribute) object;
		if (this.key.equals(attribute.key) && this.value.equals(attribute.value)) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return 17 * this.key.hashCode() * this.value.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.key);
		sb.append('=');
		sb.append('"');
		sb.append(this.value);
		sb.append('"');
		return sb.toString();
	}
}
