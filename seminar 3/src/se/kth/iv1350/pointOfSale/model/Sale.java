package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;

import java.time.LocalDateTime;

public class Sale {
    private SaleLogDTO saleLog;
    private float runningTotal;
    private ItemDTO[] items;
    private int itemsCounter = 0;
    private ItemDTO currentItem;
    private LocalDateTime time;

    private Sale(){
        Sale sale = new Sale();
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

    private void updateSale(String itemID){
        this.runningTotal += currentItem.getPrice()+currentItem.getVAT();
    }

    public SaleLogDTO isItemAlreadyInSale(String itemID){
        for (int i = 0; i < items.length; i++) {
            if (items[i].getItemID() == itemID) {
                items[i].setQuantity();
                currentItem = items[i];
                updateSale(itemID);
                return saleLog;
            }
        }
        addItem(itemID);
        currentItem = items[itemsCounter];
        updateSale(itemID);
        return saleLog;
    }

    private void addItem(String itemID){
        ItemDTO item = new ItemDTO();
        item = new InventorySystem().itemLookup(itemID);;
        items[++itemsCounter] = item;
    }

}
