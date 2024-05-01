package test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.integration.*;

public class SaleTest {
    private Sale sale;

    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        sale = new Sale(inventorySystem);
    }

    @Test
    public void testRunningTotal() {
        // Add some items
        sale.addItem("abc123");
        sale.addItem("abc123");
        sale.addItem("def456");
        
        // Assert that the running total is correct
        assertEquals(74.7, sale.getRunningTotal(), "Running total is not correct");
    }


    @Test
    public void calculateChange(){
        sale.addItem("abc123");
        sale.addItem("abc123");
        sale.addItem("def456");

        assertEquals(25.3,Math.round(sale.calculateChange(100)), "Change amount is not correct");
    }
    
}

