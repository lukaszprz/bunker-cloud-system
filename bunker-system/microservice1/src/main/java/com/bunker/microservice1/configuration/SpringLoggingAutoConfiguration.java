/**
 *
 */
package com.bunker.microservice1.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.bunker.microservice1.filters.SpringLoggingFilter;
import com.bunker.microservice1.interceptors.RestTemplateSetHeaderInterceptor;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.ssl.KeyStoreFactoryBean;
import ch.qos.logback.core.net.ssl.SSLConfiguration;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;

/**
 * @author lukasz
 *
 */
@Configuration
@ConfigurationProperties(prefix = "logging.logstash")
public class SpringLoggingAutoConfiguration {

	private static final String LOGSTASH_APPENDER_NAME = "LOGSTASH";

	private String url = "localhost:8500";
	private String ignorePatterns;
	private boolean logHeaders;
	private String trustStoreLocation;
	private String trustStorePassword;
	@Value("${spring.application.name:-}")
	String name;
	@Autowired(required = false)
	Optional<RestTemplate> template;

	@Bean
	public UniqueIDGenerator generator() {
		return new UniqueIDGenerator();
	}

	public String getIgnorePatterns() {
		return ignorePatterns;
	}

	public String getTrustStoreLocation() {
		return trustStoreLocation;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public String getUrl() {
		return url;
	}

	@PostConstruct
	public void init() {
		template.ifPresent(restTemplate -> {
			List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<ClientHttpRequestInterceptor>();
			interceptorList.add(new RestTemplateSetHeaderInterceptor());
			restTemplate.setInterceptors(interceptorList);
		});
	}

	public boolean isLogHeaders() {
		return logHeaders;
	}

	@Bean
	public SpringLoggingFilter loggingFilter() {
		return new SpringLoggingFilter(generator(), ignorePatterns, logHeaders);
	}

	@Bean
	@ConditionalOnProperty("logging.logstash.enabled")
	public LogstashTcpSocketAppender logstashAppender() {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
		logstashTcpSocketAppender.setName(LOGSTASH_APPENDER_NAME);
		logstashTcpSocketAppender.setContext(loggerContext);
		logstashTcpSocketAppender.addDestination(url);
		if (trustStoreLocation != null) {
			SSLConfiguration sslConfiguration = new SSLConfiguration();
			KeyStoreFactoryBean factory = new KeyStoreFactoryBean();
			factory.setLocation(trustStoreLocation);
			if (trustStorePassword != null) {
				factory.setPassword(trustStorePassword);
			}
			sslConfiguration.setTrustStore(factory);
			logstashTcpSocketAppender.setSsl(sslConfiguration);
		}
		LogstashEncoder encoder = new LogstashEncoder();
		encoder.setContext(loggerContext);
		encoder.setIncludeContext(true);
		encoder.setCustomFields("{\"appname\":\"" + name + "\"}");
		encoder.start();
		logstashTcpSocketAppender.setEncoder(encoder);
		logstashTcpSocketAppender.start();
		loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(logstashTcpSocketAppender);
		return logstashTcpSocketAppender;
	}

	@Bean
	@ConditionalOnMissingBean(RestTemplate.class)
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<ClientHttpRequestInterceptor>();
		interceptorList.add(new RestTemplateSetHeaderInterceptor());
		restTemplate.setInterceptors(interceptorList);
		return restTemplate;
	}

	public void setIgnorePatterns(String ignorePatterns) {
		this.ignorePatterns = ignorePatterns;
	}

	public void setLogHeaders(boolean logHeaders) {
		this.logHeaders = logHeaders;
	}

	public void setTrustStoreLocation(String trustStoreLocation) {
		this.trustStoreLocation = trustStoreLocation;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}