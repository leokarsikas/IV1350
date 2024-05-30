package se.kth.iv1350.pointOfSale.integration;

/**
 * Exception is thrown when the External Inventory System does not respond.
 * (Simulated by calling with identifier <code>InventoryDatabaseFailure</code>)
 */
public class DatabaseConnectionException extends RuntimeException {
    public String printStackTrace;

    public DatabaseConnectionException(String errorMessage) {
        super("Database "+errorMessage+" is not responding.");
    }
}