package se.kth.iv1350.pointOfSale.exceptions;

/**
 * Exception is thrown when a given item identifier is not present
 * in the External Inventory System. (Simulated by calling with
 * identifier <code>undefined</code>)
 */
public class UnrecognisedItemException extends Exception {
    public UnrecognisedItemException(String itemID) {
        super("Item identifier \"" + itemID + "\" does not exist in inventory.");
    }
}
