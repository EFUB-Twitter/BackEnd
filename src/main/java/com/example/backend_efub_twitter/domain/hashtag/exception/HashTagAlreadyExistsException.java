package com.example.backend_efub_twitter.domain.hashtag.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class HashTagAlreadyExistsException extends DataIntegrityViolationException {
    public HashTagAlreadyExistsException(String message){super(message);}
}
