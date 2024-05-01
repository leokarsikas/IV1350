package se.kth.iv1350.pointOfSale.DTO;

import se.kth.iv1350.pointOfSale.model.Item;

public class ItemDTO {
    //private ItemID itemID;
    private String ID;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private double VAT;

    public ItemDTO(String itemID, String name, double price, String description, int quantity, int VAT){
        this.ID = itemID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.VAT = VAT;
    }

    public ItemDTO(Item item){
        this.ID = item.ID;
        this.name = item.name;
        this.price = item.price;
        this.description = item.description;
        this.quantity = item.quantity;
        this.VAT = item.VAT;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public double getVAT(){
        return VAT;
    }

    public int getQuantity(){
        return quantity;
    }

}
