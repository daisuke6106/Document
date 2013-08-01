package jp.co.dk.document.xml;

import javax.xml.transform.OutputKeys;

public enum DocumentPropertyType {
	
	SHIFT_JIS(OutputKeys.ENCODING, "Shift_JIS"),
	
	UTF_8(OutputKeys.ENCODING, "UTF-8");
	
	private String key;
	
	private String type;
	
	private DocumentPropertyType(String key, String type) {
		this.key  = key;
		this.type = type;
	}
	
	String getKey() {
		return this.key;
	}
	
	String getType() {
		return this.type;
	}
}
