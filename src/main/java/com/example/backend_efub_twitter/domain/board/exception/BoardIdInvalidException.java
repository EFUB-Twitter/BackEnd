package com.example.backend_efub_twitter.domain.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BoardIdInvalidException extends ResponseStatusException {
    public BoardIdInvalidException(String message) {super(HttpStatus.UNPROCESSABLE_ENTITY, message);}
}
