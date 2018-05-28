package com.abhishekd.restapi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Custom Resource Not Found Exception Handler
     * @param exception
     * @param webRequest
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException exception, HttpServletRequest webRequest) {

       ErrorInfo errorInfo =  new ErrorInfo(404, HttpStatus.NOT_FOUND, "Resource Not Found", webRequest.getRequestURI());

       return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * Method Argument not valid exception handler
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();

        log.info(error.getDefaultMessage());
        log.info(error.getField());

        String message = error.getField() + " : " + error.getDefaultMessage();

        ErrorInfo errorInfo =  new ErrorInfo(400, HttpStatus.BAD_REQUEST, message, request.getContextPath());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

    }

    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, HttpServletRequest request) {

        ErrorInfo errorInfo =  new ErrorInfo(400, HttpStatus.BAD_REQUEST, "Bad Request", request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

    }


}
