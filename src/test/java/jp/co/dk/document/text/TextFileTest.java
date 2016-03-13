package jp.co.dk.document.text;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jp.co.dk.document.exception.DocumentException;

import org.junit.Test;

public class TextFileTest {

	@Test
	public void test() throws DocumentException, FileNotFoundException {
		File file = new File("/tmp/123");
		TextFile textFile = new TextFile(file);
		System.out.println(textFile.getLines());
	}

}
