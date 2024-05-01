package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;

public class Item {
    public String ID;
    public String name;
    public double price;
    public String description;
    public int quantity;
    public double VAT;

    public Item(ItemDTO item){
        this.ID = item.getID();
        this.name = item.getName();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.quantity = item.getQuantity();
        this.VAT = item.getVAT();
    }

    public String getID(){
        return this.ID;
    }

    public void increaseQuantity() {
        this.quantity++;
    }


}
