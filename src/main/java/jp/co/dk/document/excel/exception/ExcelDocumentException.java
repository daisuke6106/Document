package jp.co.dk.document.excel.exception;

public class ExcelDocumentException extends Exception{
	
	/** シリアルバージョンID */
	private static final long serialVersionUID = 3118889551002183253L;
	
	public ExcelDocumentException(){
		super();
	}
	public ExcelDocumentException(String str){
		super(str);
	}
	public ExcelDocumentException(String str,Throwable throwable){
		super(str, throwable);
	}
	public ExcelDocumentException(Throwable throwable){
		super(throwable);
	}
}
