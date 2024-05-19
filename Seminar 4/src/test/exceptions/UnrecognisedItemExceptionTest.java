package test.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.integration.UnrecognisedItemException;

/**
 * The UnrecognisedItemExceptionTest class tests the constructor of the UnrecognisedItemException class
 * in Java. The test is testing the constructor of the class and the exception message.
 */
public class UnrecognisedItemExceptionTest {



    /**
     * Test the constructor of the UnrecognisedItemException class
     */
    @Test
    public void testConstructor() {
        String itemID = "undefined";
        UnrecognisedItemException exception = new UnrecognisedItemException(itemID);
        assertNotNull(exception);
        assertEquals("Item identifier \"" + itemID + "\" does not exist in inventory.", exception.getMessage());
    }
}