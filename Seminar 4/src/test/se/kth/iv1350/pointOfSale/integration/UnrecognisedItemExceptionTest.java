package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.pointOfSale.FileLogger;
import se.kth.iv1350.pointOfSale.SystemOutLogger;
import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.UnrecognisedItemException;

import static org.junit.jupiter.api.Assertions.*;

public class UnrecognisedItemExceptionTest {

    @Test
    public void testConstructor() {
        String itemID = "ghi789";
        UnrecognisedItemException exception = new UnrecognisedItemException(itemID);
        assertNotNull(exception);
        assertEquals("Item identifier \"" + itemID + "\" does not exist in inventory.", exception.getMessage());
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
                assertEquals("ghi789", itemID,itemID+" should not result in an UnrecognisedItemException being thrown!");
            }
            catch (DatabaseConnectionException e){
                assertEquals("serverNotResponding", itemID, itemID+" should not result in an DatabaseConnectionException being thrown!");
            }
        }
    }

}