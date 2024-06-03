package se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.*;

/**
 * Test the Sale class in the model package.
 */
public class SaleTest {
    private Sale sale;

    /*
     * Sets up an sale object before eact test iteration
     */
    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        sale = new Sale(inventorySystem, printer);
    }

    /*
     * abc123
     * Test for checking the running total is correct
     */
    @Test
    public void testRunningTotal() {
        // Add some items
        sale.addItem("abc123");
        sale.addItem("abc123");
        sale.addItem("def456");

        // Assert that the running total is correct
        assertEquals(74.7, sale.getRunningTotal(), "Running total is not correct");
    }

    /*
     * Tests if the change amount i correct
     */
    @Test
    public void testCalculateChange() {
        sale.addItem("abc123");
        sale.addItem("abc123");
        sale.addItem("def456");

        assertEquals(25, Math.round(sale.calculateChange(100)), "Change amount is not correct");
    }

    @Test 
    public void testSetTimeOfSale(){
        sale.fetchSaleInfo().getTimeOfSale();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); //Just for nice output

        assertEquals(time.format(dateFormat), sale.fetchSaleInfo().getTimeOfSale().format(dateFormat), "time is not correct");
    }

    @Test
    public void testAddItem() {
        ItemDTO itemDTO = sale.addItem("abc123");

        assertNotNull(itemDTO, "ItemDTO is null");
        assertEquals("abc123", itemDTO.getID(), "Item ID is not correct");
        assertEquals(1, itemDTO.getQuantity(), "Item quantity is not one");
        assertEquals(29.9, itemDTO.getPrice(), "Item price is not correct");
        assertEquals(6.0, itemDTO.getVAT(), "Item VAT is not correct");
    }

    @Test
    public void testIsItemAlreadyInSale() {
        sale.addItem("abc123");
        ItemDTO item = sale.addItem("abc123");

        SaleLogDTO saleLogDTO = sale.fetchSaleInfo();

        assertEquals(1, saleLogDTO.getLength(), "Items in the array should be 1");

        assertEquals(2, item.getQuantity(), "Item quantity should be 2");

        assertEquals("abc123", saleLogDTO.getItemFromList(0).getID(), "Item ID should be abc123");

        assertEquals(null, saleLogDTO.getItemFromList(1), "There should be no items at index 1");
    }

    @Test
    public void testAddNewItem() {
        sale.addItem("abc123");
        sale.addItem("def456");

        SaleLogDTO saleLogDTO = sale.fetchSaleInfo(); 

        assertNotNull(saleLogDTO.getItemFromList(0), "Item should not be null");
        assertNotNull(saleLogDTO.getItemFromList(1), "Item should not be null");
        assertEquals("abc123", saleLogDTO.getItemFromList(0).getID(), "Item ID is not correct");
        assertEquals("def456", saleLogDTO.getItemFromList(1).getID(), "Item ID is not correct");
        assertEquals(1, saleLogDTO.getItemFromList(0).getQuantity(), "Item quantity should be 1");
        assertEquals(1, saleLogDTO.getItemFromList(1).getQuantity(), "Item quantity should be 1");
    }

    @Test
    public void testUpdateSale() {
       sale.addItem("abc123");
       SaleLogDTO saleLogDTO = sale.fetchSaleInfo();

        assertEquals(29.90, saleLogDTO.getRunningTotal(), "Running total is not correct");
        assertEquals(1.6924528301886816, saleLogDTO.getTotalVAT(), "Total VAT is not correct");
    }
}
