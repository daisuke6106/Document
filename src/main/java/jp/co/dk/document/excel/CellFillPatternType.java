package jp.co.dk.document.excel;

import org.apache.poi.ss.usermodel.CellStyle;

public enum CellFillPatternType {
	NO_FILL(CellStyle.NO_FILL),
	
	SOLID_FOREGROUND(CellStyle.SOLID_FOREGROUND),
	
	FINE_DOTS(CellStyle.SOLID_FOREGROUND),
	
	ALT_BARS(CellStyle.SOLID_FOREGROUND),
	
	SPARSE_DOTS(CellStyle.SOLID_FOREGROUND),
	
	THICK_HORZ_BANDS(CellStyle.SOLID_FOREGROUND),
	
	THICK_VERT_BANDS(CellStyle.SOLID_FOREGROUND),
	
	THICK_BACKWARD_DIAG(CellStyle.SOLID_FOREGROUND),
	
	THICK_FORWARD_DIAG(CellStyle.SOLID_FOREGROUND),
	
	BIG_SPOTS(CellStyle.SOLID_FOREGROUND),
	
	BRICKS(CellStyle.SOLID_FOREGROUND),
	
	THIN_HORZ_BANDS(CellStyle.SOLID_FOREGROUND),
	
	THIN_VERT_BANDS(CellStyle.SOLID_FOREGROUND),
	
	THIN_BACKWARD_DIAG(CellStyle.SOLID_FOREGROUND),
	
	THIN_FORWARD_DIAG(CellStyle.SOLID_FOREGROUND),
	
	SQUARES(CellStyle.SOLID_FOREGROUND),
	
	DIAMONDS(CellStyle.SOLID_FOREGROUND);
	
	private short fillPattern;
	
	private CellFillPatternType(int fillPattern) {
		this.fillPattern = (short)fillPattern;
	}
	
	public short getIndex() {
		return this.fillPattern;
	}
}
