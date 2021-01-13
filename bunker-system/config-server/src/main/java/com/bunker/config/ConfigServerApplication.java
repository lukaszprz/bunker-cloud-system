/**
 *
 */
package com.bunker.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@EnableConfigServer
public class ConfigServerApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(ConfigServerApplication.class, args);

    }

}
