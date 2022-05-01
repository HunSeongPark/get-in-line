package com.hunseong.corona.exception;

import com.hunseong.corona.constant.ErrorCode;
import com.hunseong.corona.domain.dto.APIErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Hunseong on 2022/05/02
 */
@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
        return handleExceptionInternal(e, e.getErrorCode(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ErrorCode.fromStatus(status), headers, status, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception ex, ErrorCode errorCode, WebRequest request
    ) {
        return handleExceptionInternal(ex, errorCode, HttpHeaders.EMPTY, errorCode.getStatus(), request);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception ex, ErrorCode errorCode, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(
                ex,
                APIErrorResponse.of(false, errorCode, ex),
                headers,
                status,
                request
        );
    }
}
