/**
 *
 */
package com.bunker.microservice1.wrappers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author lukasz
 *
 */
public class ServletOutputStreamWrapper extends ServletOutputStream {

	private OutputStream outputStream;
	private ByteArrayOutputStream copy;

	public ServletOutputStreamWrapper(OutputStream outputStream) {
		this.outputStream = outputStream;
		this.copy = new ByteArrayOutputStream();
	}

	public byte[] getCopy() {
		return copy.toByteArray();
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {

	}

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		copy.write(b);
	}

}
