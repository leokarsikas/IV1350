package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

/**
 * The InventorySystem class represents a mockup of a system for managing inventory and recording sales.
 * It provides methods for recording sales and looking up items in the inventory database.
 */
public class InventorySystem {

/** empty constructor */
    public InventorySystem() {

    }

    /**
     * Records a sale and validates its functionality by printing out a statement. 
     * @param saleLog The SaleLogDTO object containing information about the sale.
     */

    public void recordSale(SaleLogDTO saleLog){
        System.out.println("Recorded sale in Inventory System:\n ");
    }


/**
 * The 'itemLookup' does a lookup in the inventory system based on the itemID
 * @param itemID represents the ID of the item
 * @return a new instance of an ItemDTO based on the specified itemID.
 */
    public ItemDTO itemLookup(String itemID) throws UnrecognisedItemException, DatabaseConnectionException{
            if (itemID == "abc123") {
                return new ItemDTO(itemID, "BigWheel Oatmeal", 29.90, "Bigwheel Oatmeal 500g, whole grain oats, high fiber, gluten free", 1, 6);
            } else if (itemID == "def456") {
                return new ItemDTO(itemID, "YoGoGo Blueberry", 14.90, "YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour", 1, 6);
            } else if (itemID == "undefined") {
                throw new UnrecognisedItemException(itemID);
            }
            else if (itemID == "serverNotResponding"){
                throw new DatabaseConnectionException("\"InventorySystem\"");
            }
            return null;
    }

}
