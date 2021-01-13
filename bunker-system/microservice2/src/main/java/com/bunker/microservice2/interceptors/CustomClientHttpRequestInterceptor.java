/**
 *
 */
package com.bunker.microservice2.interceptors;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @author lukasz
 *
 */
@Component
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-ID";
	private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

	private final static Logger logger = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}

	private String getCorrelationIdFromHeader(final HttpRequest request) {
		List<String> correlationIdList = request.getHeaders().get(CORRELATION_ID_HEADER_NAME);
		String correlationId = null;
		if ((correlationIdList != null) && !correlationIdList.isEmpty()) {
			correlationId = correlationIdList.get(0);
		}
		if (StringUtils.isBlank(correlationId)) {
			correlationId = generateUniqueCorrelationId();
		}
		return correlationId;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String correlationId = getCorrelationIdFromHeader(request);
		request.getHeaders().set(CORRELATION_ID_HEADER_NAME, generateUniqueCorrelationId());
		MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
		logRequestDetails(request);
		return execution.execute(request, body);
	}

	private void logRequestDetails(HttpRequest request) {
		logger.info("Headers: {}", request.getHeaders());
		logger.info("Request Method: {}", request.getMethod());
		logger.info("Request URI: {}", request.getURI());
	}

}
