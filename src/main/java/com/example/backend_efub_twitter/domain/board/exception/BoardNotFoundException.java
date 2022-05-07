package com.example.backend_efub_twitter.domain.board.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class BoardNotFoundException extends ResourceNotFoundException{
    public BoardNotFoundException(String message) {super(message);}
}
