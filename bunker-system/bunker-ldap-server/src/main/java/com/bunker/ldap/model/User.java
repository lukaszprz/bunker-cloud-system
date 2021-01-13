/**
 *
 */
package com.bunker.ldap.model;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

/**
 * @author lukasz
 *
 */
@Entry(base = "dc=springframework,dc=org", objectClasses = { "person", "inetOrgPerson", "top" })
public class User {

    @Id
    private Name id;

    @Attribute(name = "cn")
    private String username;

    @Attribute(name = "sn")
    private String password;

    /**
     *
     */
    public User() {
    }

    /**
     * @param username
     * @param password
     */
    public User(String username, String password) {
	this.username = username;
	this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	User other = (User) obj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	if (password == null) {
	    if (other.password != null) {
		return false;
	    }
	} else if (!password.equals(other.password)) {
	    return false;
	}
	if (username == null) {
	    if (other.username != null) {
		return false;
	    }
	} else if (!username.equals(other.username)) {
	    return false;
	}
	return true;
    }

    /**
     * @return the id
     */
    public Name getId() {
	return id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((id == null) ? 0 : id.hashCode());
	result = (prime * result) + ((password == null) ? 0 : password.hashCode());
	result = (prime * result) + ((username == null) ? 0 : username.hashCode());
	return result;
    }

    /**
     * @param id the id to set
     */
    public void setId(Name id) {
	this.id = id;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

}
