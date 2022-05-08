package com.example.backend_efub_twitter.domain.user.exception;

public class DuplicateUserException extends IllegalArgumentException {
	public DuplicateUserException(String message) { super(message); }
}
