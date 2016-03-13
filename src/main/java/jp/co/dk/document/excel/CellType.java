package jp.co.dk.document.excel;

import jp.co.dk.document.excel.Cell;
import jp.co.dk.document.excel.CellTypeDecision;

import org.apache.poi.ss.usermodel.DateUtil;

public enum CellType {
	/** 空欄 */
	BLANK("空欄", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK == cell.getCellObject().getCellType();
		}
	}),
	
	/** 真偽値 */
	BOOLEAN("真偽値", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN == cell.getCellObject().getCellType();
		}
	}),
	
	/** 標準 */
	FORMULA("標準", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA == cell.getCellObject().getCellType();
		}
	}),
	
	/** 数値 */
	NUMERIC("数値", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC == cell.getCellObject().getCellType() &&
					! DateUtil.isCellDateFormatted(cell.getCellObject());
		}
	}),
	
	/** 日付 */
	DATE("日付", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC == cell.getCellObject().getCellType() &&
					DateUtil.isCellDateFormatted(cell.getCellObject());
		}
	}),
	
	/** 文字列 */
	STRING("文字列", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING == cell.getCellObject().getCellType();
		}
	}),
	
	/** エラー値 */
	ERROR("エラー", new CellTypeDecision() {
		public boolean isType(Cell cell) {
			return org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR == cell.getCellObject().getCellType();
		}
	});
	
	private String           name;
	private CellTypeDecision cellTypeDecision;
	
	private CellType(String name, CellTypeDecision cellTypeDecision) {
		this.name             = name;
		this.cellTypeDecision = cellTypeDecision;
	}
	
	public CellTypeDecision getCellTypeDecision() {
		return this.cellTypeDecision;
	}
	
	public boolean isType(Cell cell) {
		return this.cellTypeDecision.isType(cell);
	}
	
	public String toString() {
		return this.name;
	}
}

interface CellTypeDecision {
	public boolean isType(Cell cell);
}
