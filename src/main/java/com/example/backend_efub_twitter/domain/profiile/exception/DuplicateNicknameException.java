package com.example.backend_efub_twitter.domain.profiile.exception;

public class DuplicateNicknameException extends IllegalArgumentException {
	public DuplicateNicknameException(String message) { super(message); }
}
