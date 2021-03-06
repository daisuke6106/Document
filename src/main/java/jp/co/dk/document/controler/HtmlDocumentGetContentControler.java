package jp.co.dk.document.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.html.HtmlDocument;
import jp.co.dk.document.html.HtmlElement;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class HtmlDocumentGetContentControler extends AbtractDocumentControler {

	public static void main(String[] args) {
		new HtmlDocumentGetContentControler().execute(args);
	}
	
	@Override
	protected String getCommandName() {
		return "getcontent_htmldoc";
	}

	@Override
	protected String getDescription() {
		return "";
	}

	@Override
	@SuppressWarnings("all")
	protected void getOptions(Options options) {
		options.addOption(OptionBuilder.isRequired(true).hasArg(true).withArgName("ファイル名").withDescription("読込対象ファイル").withLongOpt("file").create("f"));
		options.addOption(OptionBuilder.isRequired(true).hasArg(true).withArgName("要素指定文字列").withDescription("要素指定文字列").withLongOpt("target").create("t"));
	}
	
	@Override
	public void execute() {
		
		if (cmd.hasOption("f")) {
			File readFile = new File(cmd.getOptionValue("f"));
			if (!readFile.exists()) {
				System.out.println("読込対象ファイルが存在しません。FILE=[" + readFile.toString() + "]");
				System.exit(1);
			}
			if (readFile.isDirectory()) {
				System.out.println("読込対象ファイルはディレクトリです。FILE=[" + readFile.toString() + "]");
				System.exit(1);
			}
			try (InputStream is = new FileInputStream(readFile)) {
				HtmlDocument document = new HtmlDocument(is);
				String target = cmd.getOptionValue("t");
				List<HtmlElement> htmlElementList = document.getNode(target);
				for (HtmlElement htmlElement : htmlElementList) System.out.println(htmlElement.getContent());
					
			} catch (IOException e) {
				System.out.println("読込時に異常が発生しました。");
				System.exit(1);
			} catch (DocumentException e) {
				System.out.println("読込時に異常が発生しました。");
				System.exit(1);
			} 
			
		}
		
		System.exit(0);
	}


}
