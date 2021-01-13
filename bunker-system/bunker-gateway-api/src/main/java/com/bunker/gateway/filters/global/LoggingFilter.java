/**
 *
 */
package com.bunker.gateway.filters.global;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author lukasz
 *
 */
public class LoggingFilter implements GlobalFilter {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		Set<URI> uris = exchange.getAttributeOrDefault(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR,
				Collections.emptySet());
		String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
		Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

		URI routeUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
		logger.info("Incomming request {} is routed to id: {}, uri: {}", originalUri, route.getId(), routeUri);
		return chain.filter(exchange);
	}

}
