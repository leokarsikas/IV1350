package test.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.SystemOutLogger;
import se.kth.iv1350.pointOfSale.integration.Printer;

import se.kth.iv1350.pointOfSale.MessageCreator;
import se.kth.iv1350.pointOfSale.view.View;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;

/**
 * Test the View class in the view package.
 */
public class ViewTest {
    private View view;

    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        Printer printer = new Printer();
        AccountingSystem accountingSystem = new AccountingSystem();
        MessageCreator messageCreator = new SystemOutLogger();
        Controller controller = new Controller(messageCreator, printer, inventorySystem, accountingSystem);
        
        view = new View(controller, messageCreator);

    }

    @Test
    public void testsimulateSale() {
        view.simulate();
    }
}
