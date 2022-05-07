package com.example.backend_efub_twitter.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "COMMON_001", "Invalid input value"),
    METHOD_NOT_ALLOWED(405, "COMMON_002", "Method not allowed"),
    HANDLE_ACCESS_DENIED(403, "COMMON_003", "Access is denied"),
    NOT_FOUND(404, "COMMON_004", "Resource not found"),
    UNAUTHORIZED(401, "COMMON_005", "Unauthorized"),

    // Standard
    ILLEGAL_STATE(400, "STANDARD_001", "Illegal state"),
    ILLEGAL_ARGUMENT(400, "STANDARD_002", "Illegal argument"),

    // Exception
    EXCEPTION(500, "EXCEPTION", "Exception");

    private final int status;
    private final String code;
    private final String message;
}
