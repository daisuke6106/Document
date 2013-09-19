package jp.co.dk.document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.dk.document.exception.DocumentException;
import jp.co.dk.document.message.DocumentMessage;

public class ByteDump {
	protected ByteBuffer buffer ;
	ByteDump(InputStream inputStream) throws DocumentException {
		List<BytesTmp> bytes = new ArrayList<BytesTmp>();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) > 0) bytes.add(new BytesTmp(Arrays.copyOfRange(buffer, 0, len)));
		} catch (IOException e) {
			throw new DocumentException(DocumentMessage.ERROR_FILE_READ, e);
		}
		int length = 0;
		for(BytesTmp bytetmp : bytes) length += bytetmp.length();
		this.buffer = ByteBuffer.allocate(length);
		for(BytesTmp bytetmp : bytes) this.buffer.put(bytetmp.getBytes());
	}
	public int length() {
		return this.buffer.limit();
	}
	public byte[] getBytes() {
		return this.buffer.array();
	}
	
	public InputStream getStream() {
		return new  ByteArrayInputStream(buffer.array());
	}
}
class BytesTmp {
	private byte[] bytes;
	BytesTmp(byte[] bytes) {
		this.bytes = bytes;
	}
	int length() {
		return this.bytes.length;
	}
	byte[] getBytes() {
		return this.bytes;
	}
}

