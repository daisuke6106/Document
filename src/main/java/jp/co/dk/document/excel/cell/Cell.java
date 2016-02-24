package jp.co.dk.document.excel.cell;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

import jp.co.dk.document.excel.row.Row;
import jp.co.dk.document.excel.cell.Cell;
import jp.co.dk.document.excel.cell.CellBorderType;
import jp.co.dk.document.excel.cell.CellColorType;
import jp.co.dk.document.excel.cell.CellCompare;
import jp.co.dk.document.excel.cell.CellFillPatternType;
import jp.co.dk.document.excel.cell.CellType;

public class Cell {
	
	private Row row;
	
	private org.apache.poi.ss.usermodel.Cell cell;
	
	private org.apache.poi.ss.usermodel.CellStyle cellStyle;
	
	public Cell(Row row, org.apache.poi.ss.usermodel.Cell cell){
		this.row       = row;
		this.cell      = cell;
		this.cellStyle = cell.getCellStyle();
	}
	
	public Cell setValue(boolean value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(int value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(Calendar value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(Date value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(double value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(String value) {
		this.cell.setCellValue(value);
		return this;
	}
	
	public Cell setValue(boolean value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public Cell setValue(int value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public Cell setValue(Calendar value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public Cell setValue(Date value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public Cell setValue(double value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public Cell setValue(String value, String format) {
		this.cell.setCellValue(value);
		this.setDataFormat(format);
		return this;
	}
	
	public CellType getCellType() {
		for (CellType cellType : CellType.values()) {
			if (cellType.isType(this)) {
				return cellType;
			}
		}
		return null;
	}
	
	public int getCellIndex() {
		return this.cell.getColumnIndex();
	}
	
	public Cell setFillForegroundColor(CellColorType cellColorType) {
		CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setFillForegroundColor(cellColorType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setFillBackgroundColor(CellColorType cellColorType) {
		CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setFillBackgroundColor(cellColorType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setFillPattern(CellFillPatternType cellColor) {
		CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setFillBackgroundColor(cellColor.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setBorder(CellBorderType borderType) {
		this.setBorderTop(borderType);
		this.setBorderRight(borderType);
		this.setBorderBottom(borderType);
		this.setBorderLeft(borderType);
		return this;
	}
	
	public Cell setBorderTop(CellBorderType borderType) {
		// CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setBorderTop(borderType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setBorderRight(CellBorderType borderType) {
		// CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setBorderRight(borderType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setBorderBottom(CellBorderType borderType) {
		CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setBorderBottom(borderType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setBorderLeft(CellBorderType borderType) {
		// CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		cellStyle.setBorderLeft(borderType.getIndex());
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public Cell setDataFormat(String format) {
		// CellStyle cellStyle = this.row.getWorkSheet().getWorkBook().createCellStyle();
		CreationHelper createHelper = this.cell.getRow().getSheet().getWorkbook().getCreationHelper();
		short style = createHelper.createDataFormat().getFormat(format);
		cellStyle.setDataFormat(style);
		this.cell.setCellStyle(cellStyle);
		return this;
	}
	
	public boolean compare(CellCompare comparetor) {
		return comparetor.compare(this);
	}
	
	public Row getRow() {
		return this.row;
	}
	
	public String toString() {
		return this.cell.toString();
	}
	
	org.apache.poi.ss.usermodel.Cell getCellObject() {
		return this.cell;
	}
	
}
