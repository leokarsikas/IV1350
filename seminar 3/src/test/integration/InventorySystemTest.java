package test.integration;

import se.kth.iv1350.pointOfSale.integration.InventorySystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InventorySystemTest {

    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    @Test
    public void testItemLookupFound() {
        String itemID = "abc123";
        ItemDTO item = inventorySystem.itemLookup(itemID);
        assertNotNull(item, "Item should not be null");
        assertEquals(itemID, item.getID(), "Item ID should match");
        assertEquals("BigWheel Oatmeal", item.getName(), "Item name should match");
        assertEquals(29.90, item.getPrice(), 0.01, "Item price should match");
        assertEquals("Bigwheel Oatmeal 500g, whole grain oats, high fiber, gluten free", item.getDescription(), "Item description should match");
        assertEquals(1, item.getQuantity(), "Item quantity should match");
        assertEquals(6, item.getVAT(), "Item VAT rate should match");
    }

    @Test
    public void testItemLookupAlternativeFound() {
        String itemID = "xyz789";
        ItemDTO item = inventorySystem.itemLookup(itemID);
        assertNotNull(item, "Item should not be null");
        assertEquals(itemID, item.getID(), "Item ID should match");
        assertEquals("YoGoGo Blueberry", item.getName(), "Item name should match");
        assertEquals(14.90, item.getPrice(), "Item price should match");
        assertEquals("YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour", item.getDescription(), "Item description should match");
        assertEquals(1, item.getQuantity(), "Item quantity should match");
        assertEquals(6, item.getVAT(), "Item VAT rate should match");
    }
}
