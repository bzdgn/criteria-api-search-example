package io.github.bzdgn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<String> handleInvalidFilterException(InvalidFilterException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidSearchParameterFieldException.class)
    public ResponseEntity<String> handleInvalidSearchParameterField(InvalidSearchParameterFieldException ex) {
        String[] args = ex.getMessage().split("'");
        String searchParam = args[1];
        String[] packageElements = args[3].split("\\.");
        String clazz = packageElements[packageElements.length-1];

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(String.format("'%s' does not have field: '%s'", clazz, searchParam));
    }

}
