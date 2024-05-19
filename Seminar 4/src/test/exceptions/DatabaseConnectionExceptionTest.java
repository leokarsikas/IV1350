package test.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.exceptions.DatabaseConnectionException;

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