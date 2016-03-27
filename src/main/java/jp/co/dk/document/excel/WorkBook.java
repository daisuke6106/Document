package jp.co.dk.document.excel;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.exception.DocumentException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * ワークブックを表すクラス
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class WorkBook extends jp.co.dk.document.File {
	
	/** ファイル */
	private File file; 
	
	/** ワークブックタイプ */
	private WorkBookType workBookType;
	
	/** ワークブックオブジェクト */
	private Workbook workbook;
	
	/**
	 * コンストラクタ
	 * 
	 * @param file ワークブック作成先ファイル
	 * @throws DocumentException 例外が発生した場合
	 */
	public WorkBook(File file) throws DocumentException{
		this(file, WorkBookGenerateType.AUTO);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param file ワークブック作成先ファイル
	 * @param type ワークブックタイプ
	 * @throws DocumentException 
	 */
	public WorkBook(File file, WorkBookGenerateType type) throws DocumentException{
		super(file);
		switch (type) {
			case NEW:
				this.createBook(file);
				break;
				
			case READ:
				this.readWorkBook(file);
				break;
				
			case AUTO:
				if (file.exists()) {
					this.readWorkBook(file);
				} else {
					this.createBook(file);
				}
				break;
				
			case FORCE_NEW:
				if (file.exists()) {
					if (file.delete()) {
						this.createBook(file);
					} else {
						throw new DocumentException();
					}
				} else {
					this.createBook(file);
				}
				break;
				
		}
	}
	
	/**
	 * ワークブック読込
	 * 
	 * 指定されたファイルをワークブックとして読み込み保持する。
	 * 指定されたファイルが以下の場合、例外が発生。
	 * 
	 * ・存在しない場合
	 * ・ディレクトリの場合
	 * ・ファイル拡張子が存在しない場合
	 * ・のファイル形式でない場合
	 * 
	 * @param file ワークブック作成先ファイル 
	 * @throws DocumentException 例外が発生した場合
	 */
	private void readWorkBook(File file) throws DocumentException {
		WorkBookType workBookType = this.getType(file);
		if (workBookType == null) throw new DocumentException();
		Workbook workbook = null;
		try (InputStream fi = new FileInputStream(file)){
			workbook = workBookType.getWorkBookInstance(fi);
		} catch (FileNotFoundException e) {
			throw new DocumentException(e);
		} catch (IOException e) {
			throw new DocumentException(e);
		}
		this.file         = file;
		this.workBookType = workBookType;
		this.workbook     = workbook;
	}
	
	/**
	 * ワークブック作成
	 * 
	 * 指定されたファイルへワークブックを作成する。
	 * すでにファイルが存在していた場合、例外を発生する。
	 * 
	 * @param file ワークブック作成先ファイル 
	 * @throws DocumentException 例外が発生した場合
	 */
	private void createBook(File file) throws DocumentException{
		if (file.exists()) throw new DocumentException();
		WorkBookType workBookType = this.getType(file);
		if (workBookType == null) throw new DocumentException();
		Workbook workbook = null;
		try (OutputStream fi = new FileOutputStream(file)){
			workbook = workBookType.getWorkBookInstance();
			workbook.write(fi);
		} catch (FileNotFoundException e) {
			throw new DocumentException(e);
		} catch (IOException e) {
			throw new DocumentException(e);
		}
		this.file         = file;
		this.workBookType = workBookType;
		this.workbook     = workbook;
	}

	/**
	 * ワークシートを取得する。
	 * 
	 * 指定のワークシート名を取得する。
	 * 
	 * @param sheetName 取得対象シート名
	 * @return ワークシートオブジェクト
	 */
	public WorkSheet getSheet(String sheetName) {
		Sheet sheet = this.workbook.getSheet(sheetName);
		if (sheet == null) return this.createSheet(this, this.workbook.createSheet(sheetName));
		return this.createSheet(this, sheet);
	}
	
	/**
	 * ワークシートを取得する。
	 * 
	 * 指定のワークシートインデックスにてワークシートを取得する。
	 * 存在しない場合、nullを返却する。
	 * 
	 * @param sheetName シートインデックス
	 * @return ワークシートオブジェクト
	 */
	public WorkSheet getSheetAt(int index) {
		Sheet sheet = this.workbook.getSheetAt(index);
		if (sheet == null) {
			return null;
		}
		return this.createSheet(this, sheet);
	}
	
	/**
	 * ワークシートオブジェクトを生成する。
	 * @param workBook 本オブジェクトインスタンス
	 * @param sheet    シートインスタンス
	 * @return ワークシートオブジェクトインスタンス
	 */
	protected WorkSheet createSheet(WorkBook workBook, Sheet sheet) {
		return new WorkSheet(this, sheet);
	}
	
	/**
	 * ファイルタイプ取得
	 * 
	 * 指定されたファイルのファイルタイプを取得する。
	 * 指定されたファイルインスタンスが以下の場合、null返却する。
	 * 
	 * ・ディレクトリの場合
	 * ・ファイル拡張子が存在しない
	 * ・のファイル形式でない場合
	 * 
	 * @param file タイプ取得対象ファイル 
	 * @return ワークブックタイプ
	 */
	public WorkBookType getType(File file){
		String extension = this.getExtension(file);
		for (WorkBookType type : WorkBookType.values()) {
			if (type.getFileType().equals(extension)) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 * 拡張子取得
	 * 
	 * 指定ファイルの拡張子を取得する。
	 * 指定ファイルが以下の場合、nullが返却される
	 * 
	 * ・ディレクトリの場合
	 * ・ファイル拡張子が存在しない
	 * 
	 * @param file 拡張子取得対象ファイル
	 */
	private String getExtension(File file){
		if (file == null)       return null;
		if (file.isDirectory()) return null;
		String fileName = file.getName();
		int index = fileName.lastIndexOf('.');
		if (index == -1) return null;
		return fileName.substring(index+1);
	}
	
	/**
	 * ワークブックタイプ取得
	 * 
	 * 本クラスが表すワークブックのタイプを取得する。
	 * @return ワークブックタイプ
	 */
	public WorkBookType getWorkBookType() {
		return this.workBookType;
	}
	
	public int getNumberOfSheet() {
		return this.workbook.getNumberOfSheets();
	}
	
	public List<WorkSheet> getWorkSheetList() {
		List<WorkSheet> list = new ArrayList<WorkSheet>();
		int sheetCount = this.getNumberOfSheet();
		for (int index=0; index<sheetCount; index++) {
			WorkSheet workSheet = this.getSheetAt(index);
			list.add(workSheet);
		}
		return list;
	}
	
	public void write() throws DocumentException {
		try {
			this.workbook.write(new FileOutputStream(this.file));
		} catch (FileNotFoundException e) {
			throw new DocumentException(e);
		} catch (IOException e) {
			throw new DocumentException(e);
		}
		
	}
	
	public CellStyle createCellStyle() {
		return this.workbook.createCellStyle();
	}
	
}