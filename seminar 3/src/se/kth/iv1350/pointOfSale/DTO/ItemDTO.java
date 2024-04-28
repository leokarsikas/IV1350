package se.kth.iv1350.pointOfSale.DTO;

public class ItemDTO {
    //private ItemID itemID;
    private String itemID;
    private double price;
    private String description;
    private int quantity;
    private float VAT;

    public ItemDTO createItem(String itemID, double price, String description, int quantity, float VAT){
        this.itemID = itemID;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.VAT = VAT;
        return this;
    }

    public String getItemID() {
        return itemID;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setQuantity() {
        this.quantity++;
    }

    public float getVAT() {
        return VAT;
    }
}