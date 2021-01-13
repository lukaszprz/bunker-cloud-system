/**
 *
 */
package com.bunker.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import com.netflix.discovery.EurekaClient;

/**
 * @author lukasz
 *
 */
@WebAppConfiguration
@SpringBootTest(classes = { ConfigServerApplication.class })
@AutoConfigureMockMvc(addFilters = false)
class ConfigServerApplicationTest {

	@MockBean
	private EurekaClient eurekaClient;

	@Test
	void contextLoads() {

	}

}
