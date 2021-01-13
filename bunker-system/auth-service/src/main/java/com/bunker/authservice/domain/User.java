package com.bunker.authservice.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bunker.authservice.enums.Authorities;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 8920109566273575808L;

    @Id
    @Column(name = "id", columnDefinition = "serial")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "activation_key")
    private String activationKey;

    @Column(name = "reset_password_key")
    private String resetPasswordKey;

    @Column(name = "authorities")
    private String authorities;

    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}
	if ((o == null) || (getClass() != o.getClass())) {
	    return false;
	}
	User user = (User) o;
	return Objects.equals(id, user.id);
    }

    public String getActivationKey() {
	return activationKey;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
	return authorities != null
		? Arrays.stream(authorities.split(",")).map(Authorities::valueOf).collect(Collectors.toList())
		: null;
    }

    public String getId() {
	return id;
    }

    @Override
    public String getPassword() {
	return password;
    }

    public String getResetPasswordKey() {
	return resetPasswordKey;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    public boolean isActivated() {
	return activated;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return activated;
    }

    public void setActivated(boolean activated) {
	this.activated = activated;
    }

    public void setActivationKey(String activationKey) {
	this.activationKey = activationKey;
    }

    public void setAuthorities(Set<Authorities> authorities) {
	this.authorities = authorities.stream().map(Authorities::getAuthority).collect(Collectors.joining(","));
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
	this.resetPasswordKey = resetPasswordKey;
    }

    public void setUsername(String username) {
	this.username = username;
    }

}
