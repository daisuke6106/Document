package jp.co.dk.document.html.constant;

/**
 * HttpAttributeNameは、HTMLの要素が保持する属性を定義する定数クラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum HtmlAttributeName {
	ABBR("abbr"),
	ACCEPT_CHARSET("accept-charset"),
	ACCEPT("accept"),
	ACCESSKEY("accesskey"),
	ACTION("action"),
	ALIGN("align"),
	ALINK("alink"),
	ALT("alt"),
	ARCHIVE("archive"),
	AXIS("axis"),
	BACKGROUND("background"),
	BGCOLOR("bgcolor"),
	BORDER("border"),
	CELLPADDING("cellpadding"),
	CELLSPACING("cellspacing"),
	CHAR("char"),
	CHAROFF("charoff"),
	CHARSET("charset"),
	CHECKED("checked"),
	CITE("cite"),
	CLASS("class"),
	CLASSID("classid"),
	CLEAR("clear"),
	CODE("code"),
	CODEBASE("codebase"),
	CODETYPE("codetype"),
	COLOR("color"),
	COLS("cols"),
	COLSPAN("colspan"),
	COMPACT("compact"),
	CONTENT("content"),
	COORDS("coords"),
	DATA("data"),
	DATETIME("datetime"),
	DECLARE("declare"),
	DEFER("defer"),
	DIR("dir"),
	DISABLED("disabled"),
	ENCTYPE("enctype"),
	FACE("face"),
	FOR("for"),
	FRAME("frame"),
	FRAMEBORDER("frameborder"),
	HEADERS("headers"),
	HEIGHT("height"),
	HREF("href"),
	HREFLANG("hreflang"),
	HSPACE("hspace"),
	HTTP_EQUIV("http-equiv"),
	ID("id"),
	ISMAP("ismap"),
	LABEL("label"),
	LANG("lang"),
	LANGUAGE("language"),
	LINK("link"),
	LONGDESC("longdesc"),
	MARGINHEIGHT("marginheight"),
	MARGINWIDTH("marginwidth"),
	MAXLENGTH("maxlength"),
	MEDIA("media"),
	METHOD("method"),
	MULTIPLE("multiple"),
	NAME("name"),
	NOHREF("nohref"),
	NORESIZE("noresize"),
	NOSHADE("noshade"),
	NOWRAP("nowrap"),
	OBJECT("object"),
	PROFILE("profile"),
	PROMPT("prompt"),
	READONLY("readonly"),
	REL("rel"),
	REV("rev"),
	ROWS("rows"),
	ROWSPAN("rowspan"),
	RULES("rules"),
	SCHEME("scheme"),
	SCOPE("scope"),
	SCROLLING("scrolling"),
	SELECTED("selected"),
	SHAPE("shape"),
	SIZE("size"),
	SPAN("span"),
	SRC("src"),
	STANDBY("standby"),
	START("start"),
	STYLE("style"),
	SUMMARY("summary"),
	TABINDEX("tabindex"),
	TARGET("target"),
	TEXT("text"),
	TITLE("title"),
	TYPE("type"),
	USEMAP("usemap"),
	VALIGN("valign"),
	VALUE("value"),
	VALUETYPE("valuetype"),
	VERSION("version"),
	VLINK("vlink"),
	VSPACE("vspace"),
	WIDTH("width");
	
	/** 属性名称 */
	private String name;
	
	/**
	 * コンストラクタ<p/>
	 * 指定の属性名称を元にHTMLの要素が保持する属性名称のインスタンスを生成します。
	 * 
	 * @param name 属性名称
	 */
	private HtmlAttributeName(String name) {
		this.name = name;
	}
	
	/**
	 * この属性名称の文字列表現を返却します。
	 * 
	 * @return 属性名称
	 */
	public String getName() {
		return this.name;
	}
	
}
