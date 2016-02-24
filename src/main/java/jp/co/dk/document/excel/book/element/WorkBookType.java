package jp.co.dk.document.excel.book.element;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jp.co.dk.document.excel.exception.ExcelDocumentException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public enum WorkBookType {
	/** 
	 * HSSファイルタイプ
	 * Excel2003までのファイルフォーマット形式でのファイル形式
	 */
	HSS(HSSFWorkbook.class,"xls"),
	
	/** 
	 * XSSファイルタイプ
	 * Excel2007におけるOOXML(Office Open XML)形式のファイルフォーマットを扱うためファイル形式
	 */
	XSS(XSSFWorkbook.class, "xlsx");
	
	// =================================================================================================================
	
	/** ワークブック操作クラス */
	private Class<? extends Workbook> workbookClass;
	
	/** ファイル拡張子  */
	private String fileTile;
	
	/**
	 * コンストラクタ
	 * @param fileType ファイル拡張子
	 */
	WorkBookType(Class<? extends Workbook> workBookClass,String fileType){
		this.workbookClass = workBookClass;
		this.fileTile      = fileType;
	}
	
	/**
	 * ワークブッククラスインスタンス取得
	 * ワークブッククラスインスタンスを生成し、返却する。
	 */
	public Workbook getWorkBookInstance() throws ExcelDocumentException {
		try {
			return this.workbookClass.newInstance();
		} catch (InstantiationException e) {
			throw new ExcelDocumentException(e);
		} catch (IllegalAccessException e) {
			throw new ExcelDocumentException(e);
		}
	}
	
	/**
	 * ワークブッククラスインスタンス取得
	 * ワークブッククラスインスタンスを生成し、返却する。
	 * 
	 * @param inputStream ファイルストリーム
	 * @exception ExcelDocumentException 例外が発生した場合
	 */
	public Workbook getWorkBookInstance(InputStream inputStream) throws ExcelDocumentException {
		try {
			Constructor<? extends Workbook> contructor =  this.workbookClass.getConstructor(new Class<?>[]{InputStream.class});
			Workbook obj = contructor.newInstance(inputStream);
			return obj;
		} catch (NoSuchMethodException e) {
			throw new ExcelDocumentException(e);
		} catch (SecurityException e) {
			throw new ExcelDocumentException(e);
		} catch (InstantiationException e) {
			throw new ExcelDocumentException(e);
		} catch (IllegalAccessException e) {
			throw new ExcelDocumentException(e);
		} catch (IllegalArgumentException e) {
			throw new ExcelDocumentException(e);
		} catch (InvocationTargetException e) {
			throw new ExcelDocumentException(e);
		}
		
	}
	
	/**
	 * ファイル拡張子取得
	 * @return ファイル拡張子
	 */
	public String getFileType(){
		return this.fileTile;
	}
	
}
