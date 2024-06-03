package se.kth.iv1350.pointOfSale.integration;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception is thrown when the External Inventory System does not respond.
 * (Simulated by calling with identifier <code>InventoryDatabaseFailure</code>)
 */
public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String errorMessage) {
        super("Database "+errorMessage+" is not responding...\nPlease try again or contact support");
    }

      public String getStackTraceAsString() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        super.printStackTrace(pw);
        return sw.toString();
    }
}