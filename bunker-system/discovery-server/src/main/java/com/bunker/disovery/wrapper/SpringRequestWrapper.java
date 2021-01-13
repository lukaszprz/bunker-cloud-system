/**
 *
 */
package com.bunker.disovery.wrapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/**
 * @author lukasz
 *
 */
public class SpringRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;

	public SpringRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			body = IOUtils.toByteArray(request.getInputStream());
		} catch (IOException ex) {
			body = new byte[0];
		}
	}

	public Map<String, String> getAllHeaders() {
		final Map<String, String> headers = new HashMap<>();
		Collections.list(getHeaderNames()).forEach(it -> headers.put(it, getHeader(it)));
		return headers;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStream() {
			ByteArrayInputStream byteArray = new ByteArrayInputStream(body);

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public int read() throws IOException {
				return byteArray.read();
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}

}
