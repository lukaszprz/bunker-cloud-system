/**
 *
 */
package com.bunker.gateway.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * @author lukasz
 *
 */
@Component
public class GatewayAPIHandler {

    public Mono<ServerResponse> gateway(ServerRequest request) {
	return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
		.body(BodyInserters.fromValue("Gateway API is online."));
    }
}
