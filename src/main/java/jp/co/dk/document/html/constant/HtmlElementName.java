package jp.co.dk.document.html.constant;

import jp.co.dk.document.ElementName;


/**
 * HttpElementNameは、HTMLの要素名称を定義する定数クラス。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum HtmlElementName implements ElementName{
	
	A("a"),
	ABBR("abbr"),
	ACRONYM("acronym"),
	ADDRESS("address"),
	APPLET("applet"),
	AREA("area"),
	ARTICLE("article"),
	ASIDE("aside"),
	AUDIO("audio"),
	B("b"),
	BASE("base"),
	BASEFONT("basefont"),
	BDI("bdi"),
	BDO("bdo"),
	BIG("big"),
	BLOCKQUOTE("blockquote"),
	BODY("body"),
	BR("br"),
	BUTTON("button"),
	CANVAS("canvas"),
	CAPTION("caption"),
	CENTER("center"),
	CITE("cite"),
	CODE("code"),
	COL("col"),
	COLGROUP("colgroup"),
	COMMAND("command"),
	DATALIST("datalist"),
	DD("dd"),
	DEL("del"),
	DETAILS("details"),
	DFN("dfn"),
	DIR("dir"),
	DIV("div"),
	DL("dl"),
	DT("dt"),
	EM("em"),
	EMBED("embed"),
	FIELDSET("fieldset"),
	FIGCAPTION("figcaption"),
	FIGURE("figure"),
	FONT("font"),
	FOOTER("footer"),
	FORM("form"),
	FRAME("frame"),
	FRAMESET("frameset"),
	H1("h1"),
	H2("h2"),
	H3("h3"),
	H4("h4"),
	H5("h5"),
	H6("h6"),
	HEAD("head"),
	HEADER("header"),
	HGROUP("hgroup"),
	HR("hr"),
	HTML("html"),
	I("i"),
	IFRAME("iframe"),
	IMG("img"),
	INPUT("input"),
	INS("ins"),
	ISINDEX("isindex"),
	KBD("kbd"),
	KEYGEN("keygen"),
	LABEL("label"),
	LEGEND("legend"),
	LI("li"),
	LINK("link"),
	MAP("map"),
	MARK("mark"),
	MENU("menu"),
	META("meta"),
	METER("meter"),
	NAV("nav"),
	NOFRAMES("noframes"),
	NOSCRIPT("noscript"),
	OBJECT("object"),
	OL("ol"),
	OPTGROUP("optgroup"),
	OPTION("option"),
	OUTPUT("output"),
	P("p"),
	PARAM("param"),
	PRE("pre"),
	PROGRESS("progress"),
	Q("q"),
	ROOT("#root"),
	RP("rp"),
	RT("rt"),
	RUBY("ruby"),
	S("s"),
	SAMP("samp"),
	SCRIPT("script"),
	SECTION("section"),
	SELECT("select"),
	SMALL("small"),
	SOURCE("source"),
	SPAN("span"),
	STRIKE("strike"),
	STRONG("strong"),
	STYLE("style"),
	SUB("sub"),
	SUMMARY("summary"),
	SUP("sup"),
	TABLE("table"),
	TBODY("tbody"),
	TD("td"),
	TEXTAREA("textarea"),
	TFOOT("tfoot"),
	TH("th"),
	THEAD("thead"),
	TIME("time"),
	TITLE("title"),
	TR("tr"),
	TT("tt"),
	U("u"),
	UL("ul"),
	VAR("var"),
	VIDEO("video"),
	WBR("wbr");
	
	/** 要素名称 */
	private String name;
	
	/**
	 * コンストラクタ<p/>
	 * 指定の要素名称を元にHTMLの要素が保持する要素名称のインスタンスを生成します。
	 * 
	 * @param name 要素名称
	 */
	private HtmlElementName(String name){
		this.name = name;
	}
	
	/**
	 * この属性名称の文字列表現を返却します。
	 * 
	 * @return 要素名称
	 */
	public String getName(){
		return this.name;
	}
}
