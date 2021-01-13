/**
 *
 */
package com.bunker.gateway.filters;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * @author lukasz
 *
 */
@Component
public class CorrelationFilter implements WebFilter {

	private static final Logger logger = LoggerFactory.getLogger(CorrelationFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		exchange.getResponse().getHeaders().add("X-Correlation-ID", UUID.randomUUID().toString());
		return chain.filter(exchange);
	}

}
