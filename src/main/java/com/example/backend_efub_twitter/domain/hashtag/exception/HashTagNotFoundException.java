package com.example.backend_efub_twitter.domain.hashtag.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class HashTagNotFoundException extends ResourceNotFoundException {
    public HashTagNotFoundException(String message){super(message);};
}
