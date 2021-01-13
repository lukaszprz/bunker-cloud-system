/**
 *
 */
package com.bunker.gateway.web.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * @author lukasz
 *
 */
public class GatewayAPIWebClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> result = client.get().uri("/ping").accept(MediaType.TEXT_PLAIN).exchange();

    public String getResult() {
	return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
