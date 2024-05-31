package test.controller;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.*;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

//import com.oracle.webservices.internal.api.message.MessageContext;

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
        //assertNotNull(controller.getSale(), "Sale object was not created");
    }

    @Test
    public void testEnterInfo() throws DatabaseConnectionException, UnrecognisedItemException {
        controller.startSale();
        ItemDTO item = controller.enterInfo("abc123");
        assertEquals("abc123", item.getID(), "Item ID is not correct");
    }

   
}
