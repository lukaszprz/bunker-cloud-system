/**
 *
 */
package com.bunker.ldap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;

/**
 * @author lukasz
 *
 */
@Configuration
@EnableLdapRepositories(basePackages = { "com.bunker.ldap.repository", "com.bunker.ldap.model" })
public class LDAPServerConfiguration {

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {
	LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
	ldapTemplate.setIgnoreNameNotFoundException(true);

	return ldapTemplate;
    }

}
