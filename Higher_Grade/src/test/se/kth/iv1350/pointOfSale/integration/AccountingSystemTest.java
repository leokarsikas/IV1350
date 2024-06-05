package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountingSystemTest {

    private ByteArrayOutputStream outPut;

    @BeforeEach
    public void setUp() {
        outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));
    }

    @AfterEach
    public void cleanUp() {
        outPut = null;
    }

    @Test
    public void testRecordSale() {
        // Arrange
        AccountingSystem accountingSystem = new AccountingSystem();
        SaleLogDTO saleLog = new SaleLogDTO(null, 100.0, 20.0, null, 120.0, 20.0);
        DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
        DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

        accountingSystem.recordSale(saleLog);

        String result = outPut.toString();
        assertTrue(result.contains(saleLog.getAmountPaid() + ""), "Accounting did no recieve the correct Amount paid");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getChange()) + ""),"Accounting did no recieve the correct change");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getRunningTotal()) + ""), "Accounting did no recieve the correct Running Total");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getTotalVAT()) + ""), "Accounting did no recieve the correct total VAT");

    }
}
