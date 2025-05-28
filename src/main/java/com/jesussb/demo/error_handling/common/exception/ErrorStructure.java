package com.jesussb.demo.error_handling.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum that represents the error structure by itself.
 * <p>
 *     This enum contains the necessary information of a controlled error and should be used
 *     together with {@link ServiceException} or in a controller advice class.
 * </p>
 *
 * @author jessusb
 * @version 1.0.0
 */
@Getter
public enum ErrorStructure {

    GEN_INTERNAL_ERROR("GEN-000",  "error.generic.internal-error", HttpStatus.INTERNAL_SERVER_ERROR),
    GEN_INVALID_FIELDS("GEN-001", "error.generic.fields", HttpStatus.BAD_REQUEST),
    GEN_NOT_FOUND("GEN-002", "error.generic.not-found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("USER-00", "error.user.not-found", HttpStatus.NOT_FOUND);

    private final String code;
    private final String key;
    private final HttpStatus status;

    /**
     * Constructs a custom error object with internationalization support
     * @param code Custom internal error code used for logging, debugging, or error tracking purposes.
     * @param key Internationalization key that maps to error messages in the resource files (e.g., messages.properties).
     * @param status HTTP status code (e.g., 400, 404, 500) that should be returned with this error when sent as an HTTP response.
     */
    ErrorStructure(String code, String key, HttpStatus status) {
        this.code = code;
        this.key = key;
        this.status = status;
    }
}
