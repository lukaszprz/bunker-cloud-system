/**
 *
 */
package com.bunker.microservice1.interceptors;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author lukasz
 *
 */
public class RestTemplateSetHeaderInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().add("X-Correlation-ID", MDC.get("X-Correlation-ID"));
		request.getHeaders().add("X-Request-ID", MDC.get("X-Request-ID"));
		return execution.execute(request, body);
	}

}
