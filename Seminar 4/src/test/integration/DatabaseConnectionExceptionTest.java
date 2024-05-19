package test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class DatabaseConnectionExceptionTest contains unit tests for the DatabaseConnectionException
 * class. The tests are testing the constructor of the class and the exception type. 
 */
public class DatabaseConnectionExceptionTest {

    @Test
    public void testErrorMessage() {
        String errorMessage = "InventoryDatabaseFailure";
        DatabaseConnectionException exception = new DatabaseConnectionException(errorMessage);
        assertEquals("Database " + errorMessage + " is not responding.", exception.getMessage(), "Error message is incorrect");
    }

    @Test
    public void testExceptionType() {
        String errorMessage = "InventoryDatabaseFailure";
        DatabaseConnectionException exception = new DatabaseConnectionException(errorMessage);
        assertTrue(exception instanceof RuntimeException, "Exception type is incorrect");
    }
}