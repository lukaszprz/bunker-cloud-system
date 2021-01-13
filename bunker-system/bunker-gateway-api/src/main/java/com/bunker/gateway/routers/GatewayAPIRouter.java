/**
 *
 */
package com.bunker.gateway.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bunker.gateway.handlers.GatewayAPIHandler;

/**
 * @author lukasz
 *
 */
@Configuration
public class GatewayAPIRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GatewayAPIHandler handler) {
	return RouterFunctions.route(RequestPredicates.GET("/ping").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
		handler::gateway);
    }
}
