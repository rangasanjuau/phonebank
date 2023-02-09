package com.belong.phonebank.Exception;

import com.belong.phonebank.exception.ErrorDetails;
import org.junit.Test;
import static org.junit.Assert.*;

public class ErrorDetailsTest {
    @Test
    public void testErrorDetails() {
        Integer expectedStatus = 400;
        String expectedMessage = "Bad Request";
        ErrorDetails errorDetails = new ErrorDetails(expectedStatus, expectedMessage);

        assertEquals(expectedStatus, errorDetails.getStatus());
        assertEquals(expectedMessage, errorDetails.getMessage());
    }
}