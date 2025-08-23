package com.invent.management.api.advices;

import com.invent.management.domain.exception.DuplicateItemException;
import com.invent.management.domain.exception.ItemNotFoundException;
import com.invent.management.domain.exception.ManagementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * API global advice.
 */
@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Handles DataIntegrityViolationException thrown by Rest API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        log.warn("An error occurred while execution of operation {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    /**
     * Handles DuplicateItemException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<Object> handleDuplicateItemException(DuplicateItemException ex, WebRequest request) {
        log.warn("Similar object already exists on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    /**
     * Handles ItemNotFoundException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        log.warn("Object does not exist on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }

    /**
     * Handles ManagementException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ManagementException.class)
    public ResponseEntity<Object> handleManagementException(ManagementException ex, WebRequest request) {
        log.warn("{}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
    }
}
