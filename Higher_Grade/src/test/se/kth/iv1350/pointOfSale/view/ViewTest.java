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

    private ByteArrayOutputStream outPut;
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
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
       
        view.simulate();
        // Capture the output
        String result = outPut.toString();

        // Mockup values that should be present in the View simulation
        assertTrue(result.contains("Start new sale! This is sale number"), "Start sale message not found.");
        assertTrue(result.contains("abc123"), "ItemID not found during simulation");
        assertTrue(result.contains("BigWheel Oatmeal"), "Item name not found during simulation");
        assertTrue(result.contains("29,90"), "price does not exist in view simulation");
        assertTrue(result.contains("6"),"item VAT does not exist in view simulation");
        assertTrue(result.contains("Bigwheel Oatmeal 500g, whole grain oats, high fiber, gluten free"), "Item description does not exist in view simulation");
        
        assertTrue(result.contains("59,70"), "Total cost (incl VAT) does not exist in view simulation");
        assertTrue(result.contains("3,38"), "Total VAT Does not exist in view simulation");
        assertTrue(result.contains("End sale!"), "End sale message not found.");

        assertTrue(result.contains("100"), "Payment message not found.");
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
