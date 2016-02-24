package jp.co.dk.document.excel.sheet;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import jp.co.dk.document.excel.book.WorkBook;
import jp.co.dk.document.excel.cell.Cell;
import jp.co.dk.document.excel.cell.CellCompare;
import jp.co.dk.document.excel.row.Row;

public class WorkSheet {
	
	private WorkBook workBook;
	
	private Sheet sheet;
	
	public WorkSheet(WorkBook workBook, Sheet sheet) {
		this.workBook = workBook;
		this.sheet    = sheet;
	}
	
	public Row getRow(int rowIndex) {
		org.apache.poi.ss.usermodel.Row row = this.sheet.getRow(rowIndex);
		if (row == null) {
			row = this.sheet.createRow(rowIndex);
		} 
		return new Row(this, row);
	}
	
	public void removeRow(int rowIndex) {
		this.sheet.removeRowBreak(rowIndex);
	}
	
	public List<Row> getRowList() {
		List<Row> list = new ArrayList<Row>(); 
		int firstRowIndex = this.getFirstRowNum();
		int lastRowIndex  = this.getLastRowNum();
		for (; firstRowIndex <= lastRowIndex; firstRowIndex++ ) {
			list.add(this.getRow(firstRowIndex));
		}
		return list;
	}
	
	public int getFirstRowNum() {
		return this.sheet.getFirstRowNum();
	}
	
	public Row getFirstRow() {
		return this.getRow(this.getFirstRowNum());
	}
	
	public int getLastRowNum() {
		return this.sheet.getLastRowNum();
	}
	
	public int getLastBrankRowNum() {
		return this.sheet.getLastRowNum() +1;
	}
	
	public Row getLastRow() {
		return this.getRow(this.getLastRowNum());
	}
	
	public Row getLastBrankRow() {
		return this.getRow(this.getLastBrankRowNum());
	}
	
	public String getSheetName() {
		return this.sheet.getSheetName();
	}
	
	public List<Cell> find(CellCompare comparetor) {
		List<Cell> cellList = new ArrayList<Cell>();
		List<Row>  rowList  = this.getRowList();
		for (Row row : rowList) {
			cellList.addAll(row.find(comparetor));
		}
		return cellList;
	}
	
	public WorkBook getWorkBook() {
		return this.workBook;
	}

}
