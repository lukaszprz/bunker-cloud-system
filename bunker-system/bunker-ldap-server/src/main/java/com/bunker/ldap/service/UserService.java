/**
 *
 */
package com.bunker.ldap.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.bunker.ldap.model.User;
import com.bunker.ldap.repository.UserRepository;

/**
 * @author lukasz
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean authenticate(final String username, final String password) {
	User user = userRepository.findByUsernameAndPassword(username, password);
	return user != null;
    }

    public void create(final String username, final String password) {
	User newUser = new User(username, digestSHA(password));
	newUser.setId(LdapUtils.emptyLdapName());
	userRepository.save(newUser);

    }

    private String digestSHA(final String password) {
	String base64;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA");
	    digest.update(password.getBytes());
	    base64 = Base64.getEncoder().encodeToString(digest.digest());
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException(e);
	}
	return "{SHA}" + base64;
    }

    public List<String> findAllUsers() {
	Iterable<User> its = userRepository.findAll();
	Iterator<User> it = its.iterator();
	List<String> result = new ArrayList<>();
	while (it.hasNext()) {
	    result.add(it.next().toString());
	}
	return result;
    }

    public void modify(final String username, final String password) {
	User user = userRepository.findByUsername(username);
	user.setPassword(password);
	userRepository.save(user);
    }

    public List<String> search(final String username) {
	List<User> userList = userRepository.findByUsernameLikeIgnoreCase(username);
	if (userList == null) {
	    return Collections.emptyList();
	}

	return userList.stream().map(User::getUsername).collect(Collectors.toList());
    }

}
