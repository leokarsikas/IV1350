package se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.*;


/**
 * Test the Sale class in the model package.
 */
public class SaleTest {
    private Sale sale;


    /*
    Sets up an sale object before eact test iteration
     */
    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        sale = new Sale(inventorySystem, printer);
    }

    /*abc123
    Test for checking the running total is correct 
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
    Tests if the change amount i correct 
    */
    @Test
    public void calculateChange(){
        sale.addItem("abc123");
        sale.addItem("abc123");
        sale.addItem("def456");

        assertEquals(25,Math.round(sale.calculateChange(100)), "Change amount is not correct");
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
        sale.addNewItem("abc123");

        Item item = sale.getItems().get(0);  // Assuming you have a getter for items
        assertNotNull(item, "Item should not be null");
        assertEquals("abc123", item.getID(), "Item ID is not correct");
        assertEquals(1, item.getQuantity(), "Item quantity should be 1");
    }

    
    @Test
    public void testUpdateSale() {
        ItemDTO itemDTO = new ItemDTO("abc123", "TestItem", 10.0, "TestDescription", 1, 25.0);
        sale.updateSale(itemDTO);

        assertEquals(10.0, sale.getRunningTotal(), "Running total is not correct");
        assertEquals(2.0, sale.getTotalVAT(), "Total VAT is not correct");
    }
}

    


