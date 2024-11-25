package io.github.bzdgn.exception;


import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testInvalidFilterExceptionHandle() {
        InvalidFilterException ex = new InvalidFilterException("error message");
        ResponseEntity<String> resp = globalExceptionHandler.handleInvalidFilterException(ex);

        assertEquals("Error: error message", resp.getBody());
    }

    @Test
    void testInvalidSearchParameterFieldHandle() {
        String inputErrorMessage = "Could not resolve attribute 'searchParam' of 'io.github.bzdgn.entity.Product'";
        String expectedErrorMessage = "'Product' does not have field: 'searchParam'";
        InvalidSearchParameterFieldException ex = new InvalidSearchParameterFieldException(inputErrorMessage);

        ResponseEntity<String> resp = globalExceptionHandler.handleInvalidSearchParameterField(ex);

        assertEquals(expectedErrorMessage, resp.getBody());
    }

}
