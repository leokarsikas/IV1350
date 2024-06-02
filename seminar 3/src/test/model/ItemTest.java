package test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.model.Item;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Item class.
 */
public class ItemTest {
    private Item item;

    @BeforeEach
    public void setUp() {
        // Create an ItemDTO object for testing
        ItemDTO itemDTO = new ItemDTO("testID","BigWheel Oatmeal",29.90,"Bigwheel Oatmeal 500g, whole grain oats, high fiber, gluten free", 1, 6);
        // Initialize the Item object with the ItemDTO
        item = new Item(itemDTO);
    }

    @Test
    public void testIncreaseQuantity() {

        assertEquals(1, item.quantity, "Initial quantity is incorrect");

        item.increaseQuantity();

        assertEquals(2, item.quantity, "Quantity did not increase correctly");
    }
}