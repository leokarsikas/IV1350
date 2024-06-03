package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.UnrecognisedItemException;

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
        assertEquals("Database " + errorMessage + " is not responding...", exception.getMessage(), "Error message is incorrect");
    }

    @Test
    public void testExceptionType() {
        String errorMessage = "InventoryDatabaseFailure";
        DatabaseConnectionException exception = new DatabaseConnectionException(errorMessage);
        assertTrue(exception instanceof RuntimeException, "Exception type is incorrect");
    }

        @Test
    public void testIdentifierToExceptions() {
        String itemID = "abc123";
        InventorySystem invSys = new InventorySystem();
        for (int i = 0; i < 3; i++){
            if (i == 1) {
                itemID = "ghi789";
            }
            else if (i == 2) {
                itemID = "serverNotResponding";
            }
            try{
                invSys.itemLookup(itemID);
                assertEquals("abc123", itemID,"Expected an exception to be thrown!");
            }
            catch (UnrecognisedItemException e){
                //assertEquals("ghi789", itemID,itemID+" should not result in an UnrecognisedItemException being thrown!");
            }
            catch (DatabaseConnectionException e){
                assertEquals("serverNotResponding", itemID, itemID+" should not result in an DatabaseConnectionException being thrown!");
            }
        }
    }
}