package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountingSystemTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(originalSysOut);
        outContent = null;
    }

    @Test
    public void testRecordSale() {
        // Arrange
        AccountingSystem accountingSystem = new AccountingSystem();
        SaleLogDTO saleLog = new SaleLogDTO(null, 100.0, 20.0, null, 120.0, 20.0);

        // Act
        accountingSystem.recordSale(saleLog);

        // Assert
        String result = outContent.toString();

        assertTrue(result.contains("\nAmount paid:"+ saleLog.getAmountPaid() + " SEK\n"));
        assertTrue(result.contains("\nChange:          " + saleLog.getChange() + " SEK\n"));
        assertTrue(result.contains("\nRunning Total:   " + saleLog.getRunningTotal() + " SEK\n"));
        assertTrue(result.contains("\nTotal VAT:        " + saleLog.getTotalVAT() + " SEK\n"));

        /*  "\n--------ACCOUNTING INFORMATION BEGIN-------\n" +
                "\nAmount paid:     120.0 SEK\n" +
                "Change:          20,00 SEK\n" +
                "Running Total:   100,00 SEK\n" +
                "Total VAT:        20,00 SEK\n" +
                "\n--------ACCOUNTING INFORMATION END-------\n\n";*/

    //    assertEquals(expectedOutput, outContent.toString(), "Output did not match expected");
    }
}
