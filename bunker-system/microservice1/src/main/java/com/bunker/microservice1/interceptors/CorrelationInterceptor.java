/**
 *
 */
package com.bunker.microservice1.interceptors;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author lukasz
 *
 */
@Component
public class CorrelationInterceptor implements HandlerInterceptor {
	private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-ID";
	private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
	}

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}

	private String getCorrelationIdFromHeader(final HttpServletRequest request) {
		String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
		if (StringUtils.isBlank(correlationId)) {
			correlationId = generateUniqueCorrelationId();
		}
		return correlationId;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		final String correlationId = getCorrelationIdFromHeader(request);
		response.setHeader(CORRELATION_ID_HEADER_NAME, correlationId);
		MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
		return true;
	}
}
