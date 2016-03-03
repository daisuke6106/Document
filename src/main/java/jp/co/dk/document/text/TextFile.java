package jp.co.dk.document.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.document.File;
import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

public class TextFile extends File {
	
	public TextFile(java.io.File file) throws DocumentException {
		super(file);
	}
	
	public TextFile(InputStream inputStream) throws DocumentException {
		super(inputStream);
	}
	
	public List<String> getLines() throws DocumentException {
		List<String> lines = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.fileData.getStream()))) {
			String line ;
			while((line = bufferedReader.readLine())!=null) lines.add(line);
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.FILE_INPUT_OUTPUT_ERROR);
		} 
		return lines;
	}
}
