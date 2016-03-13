package jp.co.dk.document.excel;

import org.apache.poi.ss.usermodel.CellStyle;

public enum CellBorderType {
	BORDER_NONE(CellStyle.BORDER_NONE),
	BORDER_THIN(CellStyle.BORDER_THIN),
	BORDER_MEDIUM(CellStyle.BORDER_MEDIUM),
	BORDER_DASHED(CellStyle.BORDER_DASHED),
	BORDER_DOTTED(CellStyle.BORDER_DOTTED),
	BORDER_THICK(CellStyle.BORDER_THICK),
	BORDER_DOUBLE(CellStyle.BORDER_DOUBLE),
	BORDER_HAIR(CellStyle.BORDER_HAIR),
	BORDER_MEDIUM_DASHED(CellStyle.BORDER_MEDIUM_DASHED),
	BORDER_DASH_DOT(CellStyle.BORDER_DASH_DOT),
	BORDER_MEDIUM_DASH_DOT(CellStyle.BORDER_MEDIUM_DASH_DOT),
	BORDER_DASH_DOT_DOT(CellStyle.BORDER_DASH_DOT_DOT),
	BORDER_MEDIUM_DASH_DOT_DOT(CellStyle.BORDER_MEDIUM_DASH_DOT_DOT),
	BORDER_SLANTED_DASH_DOT(CellStyle.BORDER_SLANTED_DASH_DOT);
	
	private short borderType;
	
	private CellBorderType(short borderType) {
		this.borderType = borderType;
	}
	
	public short getIndex() {
		return this.borderType;
	}
}
