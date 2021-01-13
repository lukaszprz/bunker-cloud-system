/**
 *
 */
package com.bunker.ldap.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lukasz
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	//// @formatter:off
		auth
			.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0})")
			.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource()
        			.root("dc=springframework,dc=org")
        			.ldif("classpath:test-server.ldif")
        			.url("ldap://localhost:8389/dc=springframework,dc=org")
        //			.managerDn("ben")
        //			.managerPassword("benspassword")
			.and()
			.passwordCompare()
			.passwordEncoder(new LdapShaPasswordEncoder())
			.passwordAttribute("userPassword");
		// @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**").authorizeRequests().antMatchers("/actuator/**").permitAll().anyRequest().authenticated()
//				.and().formLogin();
//	http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	http.csrf().disable().authorizeRequests().antMatchers("/users/**").authenticated();
    }

}
