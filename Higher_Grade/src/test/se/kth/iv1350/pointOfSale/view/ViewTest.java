package test.se.kth.iv1350.pointOfSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.view.View;
import se.kth.iv1350.pointOfSale.MessageCreator;
import se.kth.iv1350.pointOfSale.integration.*;
import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.model.Sale;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.text.DecimalFormat;

public class ViewTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
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
    public void testSimulate() {
        // Create a custom implementation of MessageCreator
        MessageCreator messageCreator = new TestMessageCreator();
        Printer printer = new Printer();
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        // Create a Controller instance
        Controller controller = new Controller(messageCreator, printer,inventorySystem,accountingSystem);
    
        // Create a View instance with the Controller
        View view = new View(controller, messageCreator);
        LocalDateTime time = LocalDateTime.now();
        // Run the simulate method
        view.simulate();
        ItemDTO currentItem = new ItemDTO("asd123", "Item 1", 10.0, "A test item", 2, 25);
        
        Item item1 = new Item(currentItem);
        ArrayList<Item>  items = new ArrayList<Item>();
        items.add(item1);
        
        SaleLogDTO salelog = new SaleLogDTO(items, 20.0, 5.0,time, 25.0, 5.0);
        // Capture the output
        String result = outContent.toString();

        // Verify the output contains expected messages
        assertTrue(result.contains("Start new sale! This is sale number"), "Start sale message not found.");
        assertEquals("Item ID: " + currentItem.getID(), "Item ID: " + "asd123");
        assertEquals("Item name:" + currentItem.getName(), "Item name:" + "Item 1");
        assertEquals("Item price:" + doubleDecimal.format(currentItem.getPrice()), "Item price:" + "10,00");
        assertEquals("Item VAT:" + noDecimal.format(currentItem.getVAT()), "Item VAT:" + "25");
        assertEquals("Item description:" + currentItem.getDescription(), "Item description:" + "A test item");
        
        
        
        assertEquals("Total cost (incl VAT):"+ doubleDecimal.format(salelog.getRunningTotal()) , "Total cost (incl VAT):"+ "20,00");
        assertEquals(("Total VAT:" + doubleDecimal.format(salelog.getTotalVAT())), "Total VAT:" + "5,00");
        assertTrue(result.contains("End sale!"), "End sale message not found.");


        assertTrue(result.contains("Customer pays"), "Payment message not found.");
        assertTrue(result.contains("Change to give the customer:"), "Change message not found.");
    }

    // Custom implementation of MessageCreator for testing purposes
    private static class TestMessageCreator implements MessageCreator {
        @Override
        public void log(String message) {
            // Simply print the message to the console
            System.out.println(message);
        }
    }
}
