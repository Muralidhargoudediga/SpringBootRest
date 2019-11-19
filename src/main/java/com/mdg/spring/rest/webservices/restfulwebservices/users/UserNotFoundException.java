package com.mdg.spring.rest.webservices.restfulwebservices.users;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
	