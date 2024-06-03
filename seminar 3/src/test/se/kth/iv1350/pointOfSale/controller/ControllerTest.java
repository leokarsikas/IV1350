package se.kth.iv1350.pointOfSale.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.Printer;


import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;


    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem(); 
        AccountingSystem accountingSystem = new AccountingSystem(); 
        Printer printer = new Printer(); 
           
        controller = new Controller(inventorySystem, accountingSystem, printer);
    }

    @Test
    public void testStartSale() {
        controller.startSale();
    }

    @Test
    public void testEnterInfo() {
        controller.startSale();
        ItemDTO item = controller.enterInfo("abc123");
        assertNotNull(item, "ItemDTO should not be null");
        assertEquals("abc123", item.getID(), "Item ID is not correct");
    }

    @Test
    public void testFetchSaleInfo() {
        controller.startSale();
        controller.enterInfo("abc123");
        SaleLogDTO saleInfo = controller.fetchSaleInfo();
        assertNotNull(saleInfo, "SaleLogDTO should not be null");
        assertTrue(saleInfo.getLength() > 0, "SaleLogDTO should contain items");
    }

    @Test
    public void testFetchSaleInfoForZeroItems() {
        controller.startSale();
        SaleLogDTO saleInfo = controller.fetchSaleInfo();
        assertNotNull(saleInfo, "SaleLogDTO should not be null");
        assertTrue(saleInfo.getLength() == 0, "SaleLogDTO should not contain any items");
    }

    

    @Test
    public void testEndSale() {
        controller.startSale();
        controller.enterInfo("abc123");
        double total = controller.endSale();
        assertTrue(total > 0, "Total price should be greater than 0");
    }

    @Test
    public void testPresentChange() {
        controller.startSale();
        controller.enterInfo("abc123");
        double payment = 100;
        double change = controller.presentChange(payment);
        assertTrue(change >= 0, "Change should be non-negative");
        SaleLogDTO saleInfo = controller.fetchSaleInfo();
        assertEquals(100, saleInfo.getAmountPaid(), "Amount paid should be recorded correctly");
        assertEquals(change, saleInfo.getChange(), "Change should be recorded correctly");
    }
}
