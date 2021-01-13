/**
 *
 */
package com.bunker.microservice1.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lukasz
 *
 */
@RestController
@RequestMapping("/microservice1")
public class Microservice1RestController {

	private static final Logger logger = LoggerFactory.getLogger(Microservice1RestController.class);

	@RequestMapping(path = "/msg", method = RequestMethod.GET)
	public String getMessage() {
		logger.info("Method /msg called.");
		return "micro 1";
	}

}
