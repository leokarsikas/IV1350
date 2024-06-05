package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.model.Item;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrinterTest {

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
    public void testPrintReceipt() {
        // Create sample ItemDTO objects
        ItemDTO item1DTO = new ItemDTO("item1", "Item 1", 10.0, "A test item", 2, 25);
        ItemDTO item2DTO = new ItemDTO("item2", "Item 2", 20.0, "Another test item", 3, 25);

        // Create sample Item objects
        Item item1 = new Item(item1DTO);
        Item item2 = new Item(item2DTO);

        // Create an array of items
        ArrayList<Item>  items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
       LocalDateTime time = LocalDateTime.now();
       DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); //Just for nice output
       DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
        // Create a sample SaleLogDTO
        SaleLogDTO saleLog = new SaleLogDTO(items, 75.0, 25.0, time, 100.0, 25.0);

        // Create Printer instance
        Printer printer = new Printer();
        printer.printReceipt(saleLog);

        // Capture the output
        String result = outContent.toString();

        // Verify the output contains expected strings
        assertTrue(result.contains("------------Begin receipt------------"), "Receipt did not start correctly.");
        assertTrue(result.contains(saleLog.getTimeOfSale().format(dateFormat)), "Time of sale not found.");
        //ITEM 1
        assertTrue(result.contains(saleLog.getItemFromList(0).getName()), "Item 1 not found.");
        assertTrue(result.contains(saleLog.getItemFromList(0).getQuantity() + ""), "Item 1 quantity incorrect.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getItemFromList(0).getPrice())), "Item 1 Price incorrect.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getItemFromList(0).getPrice()*saleLog.getItemFromList(0).getQuantity())), "Item 1 Price times Quantity incorrect.");
        //ITEM 2
        assertTrue(result.contains(saleLog.getItemFromList(1).getName()), "Item 2 not found.");
        assertTrue(result.contains(saleLog.getItemFromList(1).getQuantity() + ""), "Item 2 quantity incorrect.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getItemFromList(1).getPrice())), "Item 2 Price incorrect.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getItemFromList(1).getPrice()*saleLog.getItemFromList(1).getQuantity())), "Item 2 Price times Quantity incorrect.");
        //assertTrue(result.contains("Item 1"), "Item 1 not found.");
        //assertTrue(result.contains("3 x 20.00    60.00 SEK"), "Item 2 details incorrect.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getRunningTotal())), "Total cost not found.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getTotalVAT())), "Total VAT not found.");
        assertTrue(result.contains(saleLog.getAmountPaid() + ""), "Amount paid not found.");
        assertTrue(result.contains(doubleDecimal.format(saleLog.getChange())), "Change not found.");
        //assertTrue(result.contains("Change:                               25.00 SEK"), "Change not found.");
        assertTrue(result.contains("------------End receipt------------"), "Receipt did not end correctly.");
    }
}
