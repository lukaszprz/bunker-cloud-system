/**
 *
 */
package com.bunker.microservice2.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lukasz
 *
 */
@RestController
@RequestMapping("/microservice2")
public class Microservice2RestController {

	private static final Logger logger = LoggerFactory.getLogger(Microservice2RestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path = "/call", method = RequestMethod.GET)
	public String getMessage(@RequestHeader HttpHeaders headers) {
		logger.info("calling Microservice 1... {}", headers.get("X-Correlation-ID"));
		return restTemplate.getForObject("lb://Bunker-Microservice-1/microservice1/msg", String.class);
	}

}
