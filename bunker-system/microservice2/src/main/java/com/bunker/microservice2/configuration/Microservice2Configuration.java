/**
 *
 */
package com.bunker.microservice2.configuration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bunker.microservice2.interceptors.CorrelationInterceptor;

/**
 * @author lukasz
 *
 */
@Configuration
public class Microservice2Configuration {

	@Autowired
	private CorrelationInterceptor correlationInterceptor;

	@Bean
	@LoadBalanced
	public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
		return new CustomRestTemplateCustomizer();
	}

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getInterceptors().add((request, body, execution) -> {
			if (request.getHeaders().get("X-Correlation-ID") != null) {
				request.getHeaders().set("X-Correlation-ID", generateUniqueCorrelationId());
			}
			return execution.execute(request, body);
		});
		return restTemplate;
	}
}
