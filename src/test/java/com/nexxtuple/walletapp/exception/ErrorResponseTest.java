package com.nexxtuple.walletapp.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {
    @Test
    public void testEqualsAndHashCode() {
        ErrorResponse errorResponse1 = new ErrorResponse(404, "Not Found");
        ErrorResponse errorResponse2 = new ErrorResponse(404, "Not Found");
        ErrorResponse errorResponse3 = new ErrorResponse(500, "Internal Server Error");

        // Test equals()
        assertTrue(errorResponse1.equals(errorResponse2));
        assertFalse(errorResponse1.equals(errorResponse3));

        // Test hashCode()
        assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        assertFalse(errorResponse1.hashCode() == errorResponse3.hashCode());
    }

    @Test
    public void testToString() {
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad Request");
        String expectedString = "ErrorResponse(StatusCode=400, message=Bad Request)";

        assertEquals(expectedString, errorResponse.toString());
    }

}
