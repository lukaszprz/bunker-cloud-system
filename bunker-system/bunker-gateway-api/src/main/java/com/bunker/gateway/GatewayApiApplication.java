/**
 *
 */
package com.bunker.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bunker.gateway.web.clients.GatewayAPIWebClient;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
public class GatewayApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayApiApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(GatewayApiApplication.class, args);

	GatewayAPIWebClient client = new GatewayAPIWebClient();
	logger.info(client.getResult());
    }

}
