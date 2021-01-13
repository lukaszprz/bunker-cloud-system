/**
 *
 */
package com.bunker.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class HystrixApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(HystrixApplication.class, args);

    }

}
