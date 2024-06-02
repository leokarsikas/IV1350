package test.controller;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.*;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        MessageCreator messageCreator = new SystemOutLogger();
        Printer printer = new Printer();
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        controller = new Controller(messageCreator, printer, inventorySystem, accountingSystem);
    }

    @Test
    public void testStartSale() {
        controller.startSale();
        // Uncomment the assertion below once the getSale method is implemented in the controller
        // assertNotNull(controller.getSale(), "Sale object was not created");
    }

    @Test
    public void testEnterInfo() {
        controller.startSale();
        try {
            ItemDTO item = controller.enterInfo("abc123");
            assertEquals("abc123", item.getID(), "Item ID is not correct");
        } catch (DatabaseConnectionException | UnrecognisedItemException e) {
            e.printStackTrace();
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
