package com.example.backend_efub_twitter.domain.user.exception;

public class UserNotFoundException extends ClassNotFoundException {
	public UserNotFoundException(String message) { super(message); }
}
