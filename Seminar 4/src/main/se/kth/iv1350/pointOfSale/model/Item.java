package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
/**
 * Represents an item for the PoS system
 */
public class Item {
    /** The unique identifier of an item */
    public String ID;
    /** The name of an item */
    public String name;
    /** The price of an item */
    public double price;
    /** The description of an item */
    public String description;
    /** The current quantity of an item */
    public int quantity;
    /** The VAT of an item */
    public double VAT;

    /**
     * Creates a new item
     * @param ID the ID of the item
     */
    public Item(ItemDTO item){
        this.ID = item.getID();
        this.name = item.getName();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.quantity = item.getQuantity();
        this.VAT = item.getVAT();
    }
    /**
     * Gets the ID of the item
     * @return the ID of the item
     */
    public String getID(){
        return this.ID;
    }

    /**
     * Increments the quantity counter 
     */
    public void increaseQuantity() {
        this.quantity++;
    }

}
