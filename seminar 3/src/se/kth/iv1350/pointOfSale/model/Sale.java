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
        this.saleLog = new SaleLogDTO();
        this.runningTotal = 0;
        this.items = new ItemDTO[2]; //Fixlater
        this.time = LocalDateTime.now();
        this.currentItem = null;
        this.receipt = new Receipt();
    }

    public SaleLogDTO fetchSaleInfo(){
        return this.saleLog;
    }

    public ItemDTO getCurrentItem() {
        System.out.println("Current item: " + currentItem.getName());
        return this.currentItem;
    }

    public double calculateChange(double payment){
        double change = payment-saleLog.getRunningTotal();
        saleLog.setAmountPaid(payment);
        saleLog.setChange(change);
        receipt.printReceipt(this.saleLog);
        return change;
    }

    public double endSale(){
        return this.runningTotal;
    }

    private void setTimeOfSale(){
        this.time = LocalDateTime.now();
    }

    public SaleLogDTO addItem(String itemID){
        System.out.println("Adding item " + itemID);
        int index = isItemAlreadyInSale(itemID);
        if (index < itemsCounter) {
            items[index].setQuantity();
            currentItem = items[index];
        }
        else {
            addNewItem(itemID);
            currentItem = items[itemsCounter++];
        }
        updateSale();
        return saleLog;
    }

    private int isItemAlreadyInSale(String itemID){
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getID() == itemID) {
                return i;
            }
        }
        return itemsCounter;
    }

    private void addNewItem(String itemID){
        //Wtf gör man här?
        ItemDTO item = new InventorySystem().itemLookup(itemID);
        saleLog.setItemToList(item,itemsCounter);
        items[itemsCounter] = item;
    }

    private void updateSale(){
        double itemPrice = currentItem.getPrice();
        double itemNumVAT = itemPrice-itemPrice/(1+ currentItem.getVAT()/100);
        this.runningTotal += itemPrice;
        this.saleLog.setRunningTotal(this.runningTotal);
        this.saleLog.setTotalVAT(itemNumVAT);
    }

}
