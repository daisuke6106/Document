package jp.co.dk.document.excel;

public enum CellColorType {
	BLACK(org.apache.poi.ss.usermodel.IndexedColors.BLACK),
    WHITE(org.apache.poi.ss.usermodel.IndexedColors.WHITE),
    RED(org.apache.poi.ss.usermodel.IndexedColors.RED),
    BRIGHT_GREEN(org.apache.poi.ss.usermodel.IndexedColors.BRIGHT_GREEN),
    BLUE(org.apache.poi.ss.usermodel.IndexedColors.BLUE),
    YELLOW(org.apache.poi.ss.usermodel.IndexedColors.YELLOW),
    PINK(org.apache.poi.ss.usermodel.IndexedColors.PINK),
    TURQUOISE(org.apache.poi.ss.usermodel.IndexedColors.TURQUOISE),
    DARK_RED(org.apache.poi.ss.usermodel.IndexedColors.DARK_RED),
    GREEN(org.apache.poi.ss.usermodel.IndexedColors.GREEN),
    DARK_BLUE(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE),
    DARK_YELLOW(org.apache.poi.ss.usermodel.IndexedColors.DARK_YELLOW),
    VIOLET(org.apache.poi.ss.usermodel.IndexedColors.VIOLET),
    TEAL(org.apache.poi.ss.usermodel.IndexedColors.TEAL),
    GREY_25_PERCENT(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT),
    GREY_50_PERCENT(org.apache.poi.ss.usermodel.IndexedColors.GREY_50_PERCENT),
    CORNFLOWER_BLUE(org.apache.poi.ss.usermodel.IndexedColors.CORNFLOWER_BLUE),
    MAROON(org.apache.poi.ss.usermodel.IndexedColors.MAROON),
    LEMON_CHIFFON(org.apache.poi.ss.usermodel.IndexedColors.LEMON_CHIFFON),
    ORCHID(org.apache.poi.ss.usermodel.IndexedColors.ORCHID),
    CORAL(org.apache.poi.ss.usermodel.IndexedColors.CORAL),
    ROYAL_BLUE(org.apache.poi.ss.usermodel.IndexedColors.ROYAL_BLUE),
    LIGHT_CORNFLOWER_BLUE(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_CORNFLOWER_BLUE),
    SKY_BLUE(org.apache.poi.ss.usermodel.IndexedColors.SKY_BLUE),
    LIGHT_TURQUOISE(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_TURQUOISE),
    LIGHT_GREEN(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_GREEN),
    LIGHT_YELLOW(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_YELLOW),
    PALE_BLUE(org.apache.poi.ss.usermodel.IndexedColors.PALE_BLUE),
    ROSE(org.apache.poi.ss.usermodel.IndexedColors.ROSE),
    LAVENDER(org.apache.poi.ss.usermodel.IndexedColors.LAVENDER),
    TAN(org.apache.poi.ss.usermodel.IndexedColors.TAN),
    LIGHT_BLUE(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_BLUE),
    AQUA(org.apache.poi.ss.usermodel.IndexedColors.AQUA),
    LIME(org.apache.poi.ss.usermodel.IndexedColors.LIME),
    GOLD(org.apache.poi.ss.usermodel.IndexedColors.GOLD),
    LIGHT_ORANGE(org.apache.poi.ss.usermodel.IndexedColors.LIGHT_ORANGE),
    ORANGE(org.apache.poi.ss.usermodel.IndexedColors.ORANGE),
    BLUE_GREY(org.apache.poi.ss.usermodel.IndexedColors.BLUE_GREY),
    GREY_40_PERCENT(org.apache.poi.ss.usermodel.IndexedColors.GREY_40_PERCENT),
    DARK_TEAL(org.apache.poi.ss.usermodel.IndexedColors.DARK_TEAL),
    SEA_GREEN(org.apache.poi.ss.usermodel.IndexedColors.SEA_GREEN),
    DARK_GREEN(org.apache.poi.ss.usermodel.IndexedColors.DARK_GREEN),
    OLIVE_GREEN(org.apache.poi.ss.usermodel.IndexedColors.OLIVE_GREEN),
    BROWN(org.apache.poi.ss.usermodel.IndexedColors.BROWN),
    PLUM(org.apache.poi.ss.usermodel.IndexedColors.PLUM),
    INDIGO(org.apache.poi.ss.usermodel.IndexedColors.INDIGO),
    GREY_80_PERCENT(org.apache.poi.ss.usermodel.IndexedColors.GREY_80_PERCENT),
    AUTOMATIC(org.apache.poi.ss.usermodel.IndexedColors.AUTOMATIC);

    private org.apache.poi.ss.usermodel.IndexedColors color;

    CellColorType(org.apache.poi.ss.usermodel.IndexedColors color){
        this.color = color;
    }

    /**
     * Returns index of this color
     *
     * @return index of this color
     */
    public short getIndex(){
        return color.getIndex();
    }
}
