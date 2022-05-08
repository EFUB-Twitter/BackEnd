package com.example.backend_efub_twitter.domain.user.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
	public UserNotFoundException(String message) { super(message); }
}
