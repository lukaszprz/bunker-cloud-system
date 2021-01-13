/**
 *
 */
package com.bunker.ldap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lukasz
 *
 */
@SpringBootApplication
public class LDAPServerApplication {

	private final static Logger logger = LoggerFactory.getLogger(LDAPServerApplication.class);

//	@Autowired
//	private static Environment environment;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(LDAPServerApplication.class);

//		SpringApplication.run(LDAPServerApplication.class, args);
		String port = application.run(args).getEnvironment().getProperty("local.server.port");
		logger.info("PORT: {}", port);
	}

}
