package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;

import java.time.LocalDateTime;

public class Sale {
    private SaleLogDTO saleLog;
    private double runningTotal;
    private ItemDTO[] items;
    private int itemsCounter = 0;
    private ItemDTO currentItem;
    private LocalDateTime time;
    private Receipt receipt;

    public Sale(){
        this.receipt = new Receipt(saleLog);
    }

    public SaleLogDTO fetchSaleInfo(){
        return this.saleLog;
    }

    public void printReceipt() {

    }

    public double getRunningTotal(){
        return this.runningTotal;
    }

    private void setTimeOfSale(){
        this.time = LocalDateTime.now();
    }

    public SaleLogDTO addItem(String itemID){
        int index = isItemAlreadyInSale(itemID);
        if (index < itemsCounter) {
            items[index].setQuantity();
            currentItem = items[index];
        }
        else {
            addNewItem(itemID);
            currentItem = items[itemsCounter];
        }
        updateSale();
        return saleLog;
    }

    private int isItemAlreadyInSale(String itemID){
        for (int i = 0; i < items.length; i++) {
            if (items[i].getItemID() == itemID) {
                return i;
            }
        }
        return itemsCounter;
    }

    private void addNewItem(String itemID){
        //Wtf gör man här?
        ItemDTO item = new InventorySystem().itemLookup(itemID);
        items[++itemsCounter] = item;
    }

    private void updateSale(){
        this.runningTotal += currentItem.getPrice()+currentItem.getVAT();
    }

}
