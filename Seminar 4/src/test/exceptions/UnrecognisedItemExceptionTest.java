package test.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.exceptions.UnrecognisedItemException;

public class UnrecognisedItemExceptionTest {

    @Test
    public void testConstructor() {
        String itemID = "undefined";
        UnrecognisedItemException exception = new UnrecognisedItemException(itemID);
        assertNotNull(exception);
        assertEquals("Item identifier \"" + itemID + "\" does not exist in inventory.", exception.getMessage());
    }
}