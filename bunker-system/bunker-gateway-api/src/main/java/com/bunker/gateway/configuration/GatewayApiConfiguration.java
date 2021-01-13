/**
 *
 */
package com.bunker.gateway.configuration;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bunker.gateway.filters.global.LoggingFilter;

/**
 * @author lukasz
 *
 */
@Configuration
public class GatewayApiConfiguration {

	@Bean
	public GlobalFilter globalFilterLogging() {
		return new LoggingFilter();
	}

}
