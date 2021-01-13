/**
 *
 */
package com.bunker.microservice2.configuration;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

import com.bunker.microservice2.interceptors.CustomClientHttpRequestInterceptor;

/**
 * @author lukasz
 *
 */
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.getInterceptors().add(new CustomClientHttpRequestInterceptor());
	}

}
