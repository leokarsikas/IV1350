package se.kth.iv1350.pointOfSale.exceptions;

/**
 * Exception is thrown when the External Inventory System does not respond.
 * (Simulated by calling with identifier <code>InventoryDatabaseFailure</code>)
 */
public class InventorySystemException extends RuntimeException {
    public InventorySystemException(String errorMessage) {
        super(errorMessage);
    }
}