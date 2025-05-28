package com.jesussb.demo.error_handling.common.advice;

import com.jesussb.demo.error_handling.common.exception.ErrorStructure;
import com.jesussb.demo.error_handling.common.exception.ExceptionResolver;
import com.jesussb.demo.error_handling.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class that catches all the errors thrown through the service and formats them based in the RFC 7807 specification.
 *
 * @author jesussb
 * @version 1.0.0
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdvice {

    private final ExceptionResolver resolver;

    /**
     * Method that catches all the {@link ServiceException} thrown.
     * <p>
     *      The handler automatically extracts error information from the exception and formats it
     *      into a client-friendly response structure.
     * </p>
     * @param ex The {@link ServiceException} instance containing the error structure with internal code, message key and HTTP status.
     * @param locale locale The client's preferred {@link Locale} for message internationalization.
     * @return A {@link ProblemDetail} object formatted according to RFC 7807 specification.
     */
    @ExceptionHandler(ServiceException.class)
    public ProblemDetail handleServiceException(ServiceException ex, Locale locale){

        ErrorStructure error = ex.getErrorStructure();

        String title = resolver.resolveMessage("error.title", locale);
        String detail = resolver.resolveMessage(error.getKey(), locale, ex.getArgs());

        ProblemDetail problemDetail = ProblemDetail.forStatus(error.getStatus());
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setProperty("code", error.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;

    }

    /**
     * Method that catches all the {@link MethodArgumentNotValidException} thrown.
     * <p>
     *      The handler automatically extracts error information from the exception and formats it
     *      into a client-friendly response structure.
     * </p>
     * @param ex The {@link MethodArgumentNotValidException} instance containing the error structure with message and fields.
     * @param locale locale The client's preferred {@link Locale} for message internationalization.
     * @return A {@link ProblemDetail} object formatted according to RFC 7807 specification and a custom fields error data.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {

        var result = new HashMap<String, Object>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            result.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ErrorStructure error = ErrorStructure.GEN_INVALID_FIELDS;

        String title = resolver.resolveMessage("error.title", locale);
        String detail = resolver.resolveMessage(error.getKey(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatus(error.getStatus());
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setProperty("code", error.getCode());
        problemDetail.setProperty("fields", result);
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;

    }

    /**
     * Method that catches all the {@link NoHandlerFoundException} thrown by 404 wrong url.
     * <p>
     *      The handler automatically extracts error information from the exception and formats it
     *      into a client-friendly response structure.
     * </p>
     * @param ex The {@link NoHandlerFoundException} instance containing the error structure with message.
     * @param locale locale The client's preferred {@link Locale} for message internationalization.
     * @return A {@link ProblemDetail} object formatted according to RFC 7807 specification.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNotFound(NoHandlerFoundException ex, Locale locale) {

        ErrorStructure error = ErrorStructure.GEN_NOT_FOUND;

        String title = resolver.resolveMessage("error.title", locale);
        String detail = resolver.resolveMessage(error.getKey(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatus(error.getStatus());
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setProperty("code", error.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;

    }

    /**
     * Method that catches all the {@link RuntimeException} thrown.
     * <p>
     *      The handler automatically extracts error information from the exception and formats it
     *      into a client-friendly response structure.
     * </p>
     * @param ex The {@link RuntimeException} instance containing the error structure with message.
     * @param locale locale The client's preferred {@link Locale} for message internationalization.
     * @return A {@link ProblemDetail} object formatted according to RFC 7807 specification.
     */
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeException(RuntimeException ex, Locale locale) {

        ErrorStructure error = ErrorStructure.GEN_INTERNAL_ERROR;

        String title = resolver.resolveMessage("error.title", locale);
        String detail = resolver.resolveMessage(error.getKey(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatus(error.getStatus());
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setProperty("code", error.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;

    }

    /**
     * Method that catches all the {@link Exception} thrown.
     * <p>
     *      The handler automatically extracts error information from the exception and formats it
     *      into a client-friendly response structure.
     * </p>
     * @param ex The {@link Exception} instance containing the error structure with message.
     * @param locale locale The client's preferred {@link Locale} for message internationalization.
     * @return A {@link ProblemDetail} object formatted according to RFC 7807 specification.
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex, Locale locale) {

        ErrorStructure error = ErrorStructure.GEN_INTERNAL_ERROR;

        String title = resolver.resolveMessage("error.title", locale);
        String detail = resolver.resolveMessage(error.getKey(), locale);

        ProblemDetail problemDetail = ProblemDetail.forStatus(error.getStatus());
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setProperty("code", error.getCode());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;

    }

}
