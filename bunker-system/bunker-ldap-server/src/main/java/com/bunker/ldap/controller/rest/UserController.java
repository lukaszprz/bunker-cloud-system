/**
 *
 */
package com.bunker.ldap.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bunker.ldap.service.UserService;

/**
 * @author lukasz
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Boolean> authenticate(@RequestParam String username, @RequestParam String password) {
	boolean result = userService.authenticate(username, password);

	if (result) {
	    return new ResponseEntity<Boolean>(result, HttpStatus.ACCEPTED);
	}
	return new ResponseEntity<Boolean>(result, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
	userService.create(username, password);
	return new ResponseEntity<String>("User created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<String>> list() {
	return new ResponseEntity<List<String>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    public ResponseEntity<String> modify(@RequestParam String username, @RequestParam String password) {
	userService.modify(username, password);
	return new ResponseEntity<String>("User modified", HttpStatus.ACCEPTED);
    }

}
