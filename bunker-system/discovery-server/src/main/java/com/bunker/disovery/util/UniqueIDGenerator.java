/**
 *
 */
package com.bunker.disovery.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

/**
 * @author lukasz
 *
 */
public class UniqueIDGenerator {

	private static final String REQUEST_ID_HEADER_NAME = "X-Request-ID";
	private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-ID";

	public void generateAndSetMDC(HttpServletRequest request) {
		MDC.clear();
		String requestId = request.getHeader(REQUEST_ID_HEADER_NAME);
		if (requestId == null) {
			requestId = UUID.randomUUID().toString();
		}
		MDC.put(REQUEST_ID_HEADER_NAME, requestId);

		String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
		if (correlationId == null) {
			correlationId = UUID.randomUUID().toString();
		}
		MDC.put(CORRELATION_ID_HEADER_NAME, correlationId);
	}
}
