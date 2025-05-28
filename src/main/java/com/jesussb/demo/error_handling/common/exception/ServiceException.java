package com.jesussb.demo.error_handling.common.exception;

import lombok.Getter;

/**
 * Class that represents the project's main exception. Thrown in all controlled cases.
 * <p>
 *      This exception should be thrown in all controlled error scenarios,
 *      typically where business rules are violated or known conditions fail.
 *      It encapsulates a predefined {@link ErrorStructure} and optional arguments
 *      for message interpolation or localization.
 * </p>
 *
 * @author jesussb
 * @version 1.0.0
 */
@Getter
public class ServiceException extends RuntimeException {

    private final ErrorStructure errorStructure;
    private final Object[] args;

    /**
     * Constructs the exception with optional arguments for interpolation.
     * @param errorStructure The error structure (key, status, code).
     * @param args Optional arguments for dynamic message resolution (e.g. i18n templates)
     */
    public ServiceException(ErrorStructure errorStructure, Object... args) {
        this.errorStructure = errorStructure;
        this.args = args;
    }
}
