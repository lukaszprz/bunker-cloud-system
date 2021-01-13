/**
 *
 */
package com.bunker.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Microservice1Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.setProperty("hawtio.authenticationEnabled", "false");

	SpringApplication.run(Microservice1Application.class, args);
    }

}
