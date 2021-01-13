/**
 *
 */
package com.bunker.microservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Microservice2Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Microservice2Application.class, args);
	}

}
