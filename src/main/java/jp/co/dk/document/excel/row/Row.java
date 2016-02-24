package jp.co.dk.document.excel.row;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.co.dk.document.excel.cell.Cell;
import jp.co.dk.document.excel.cell.CellCompare;
import jp.co.dk.document.excel.cell.Cellable;
import jp.co.dk.document.excel.sheet.WorkSheet;

public class Row {
	
	private WorkSheet sheet;
	
	private org.apache.poi.ss.usermodel.Row row;
	
	public Row (WorkSheet sheet, org.apache.poi.ss.usermodel.Row row) {
		this.sheet = sheet;
		this.row   = row;
	}
	
	public Cell getCell(int cellIndex) {
		org.apache.poi.ss.usermodel.Cell cell = this.row.getCell(cellIndex);
		if (cell == null) {
			cell = this.row.createCell(cellIndex);
		}
		return new Cell(this, cell);
	}
	
	public int getRowIndex() {
		return this.row.getRowNum();
	}
	
	public List<Cell> getCellList() {
		List<Cell> list = new ArrayList<Cell>();
		int firstCellNum = this.getFirstCellNum();
		int lastCellNum  = this.getLastCellNum();
		for (; firstCellNum <= lastCellNum; firstCellNum++ ) {
			list.add(this.getCell(firstCellNum));
		}
		return list;
	}
	
	public void addCell(List<Object> dataList, Cellable cellable) {
		for (Object object : dataList) {
			Cell cell = cellable.convert(object);
		}
	}
	
	public Cell addCell(boolean value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(int value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(Calendar value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(Date value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(double value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(String value) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		return cell;
	}
	
	public Cell addCell(boolean value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public Cell addCell(int value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public Cell addCell(Calendar value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public Cell addCell(Date value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public Cell addCell(double value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public Cell addCell(String value, String format) {
		Cell cell = this.getLastBrankCell();
		cell.setValue(value);
		cell.setDataFormat(format);
		return cell;
	}
	
	public int getFirstCellNum() {
		return this.row.getFirstCellNum();
	}
	
	public Cell getFirstCell() {
		int firstIndex = this.getFirstCellNum();
		return this.getCell(firstIndex);
	}
	
	public int getLastCellNum() {
		int lastCellIndex = this.row.getLastCellNum();
		if (lastCellIndex < 0) {
			return 0;
		}
		return  lastCellIndex -1;
	}
	
	public Cell getLastCell() {
		int lastIndex = this.getLastCellNum();
		return this.getCell(lastIndex);
	}
	
	public int getLastBrankCellNum() {
		int lastCellIndex = this.row.getLastCellNum();
		if (lastCellIndex < 0) {
			return 0;
		}
		return lastCellIndex;
	}
	
	public Cell getLastBrankCell() {
		int lastIndex = this.getLastBrankCellNum();
		return this.getCell(lastIndex);
	}
	
	public List<Cell> find(CellCompare comparetor) {
		List<Cell> list = new ArrayList<Cell>();
		List<Cell> cellList = this.getCellList();
		for (Cell cell : cellList) {
			if (cell.compare(comparetor)) {
				list.add(cell);
			}
		}
		return list;
	}
	
	public WorkSheet getWorkSheet() {
		return this.sheet;
	}
	
	public String toString() {
		return this.row.toString();
	}
}
