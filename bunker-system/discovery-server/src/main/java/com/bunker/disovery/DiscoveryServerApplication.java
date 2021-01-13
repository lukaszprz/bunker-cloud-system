/**
 *
 */
package com.bunker.disovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableEurekaServer
public class DiscoveryServerApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}
