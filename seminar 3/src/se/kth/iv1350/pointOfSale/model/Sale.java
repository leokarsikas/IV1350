package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;

import java.time.LocalDateTime;

public class Sale {
    private double runningTotal;
    private Item[] items;
    private int itemsCounter = 0;
    private int currentItemIndex;
    private LocalDateTime time;
    private Receipt receipt;
    private double totalVAT;
    private double amountPaid;
    private double change;
    private InventorySystem inventorySystem;

    public Sale(InventorySystem invSyst){
        this.runningTotal = 0;
        this.items = new Item[2]; //Arbitrary size for now
        setTimeOfSale();
        this.currentItemIndex = 0;
        this.receipt = new Receipt();
        this.inventorySystem = invSyst;
    }

    /**
     * The fetchSaleInfo method returns a SaleLogDTO object with information about items, running
     * total, total VAT, time, amount paid, and change.
     * 
     * @return A SaleLogDTO object is being returned, which contains information about the items,
     * running total, total VAT, time of sale, amount paid, and change.
     */
    public SaleLogDTO fetchSaleInfo(){
        return new SaleLogDTO(
                this.items,
                this.runningTotal,
                this.totalVAT,
                this.time,
                this.amountPaid,
                this.change);
    }

    /**
     * getCurrentItem returns a new ItemDTO object based on the item at the current index
     * in the items array.
     * 
     * @return An ItemDTO object containing the most recently added item is
     * returned.
     */
    public ItemDTO getCurrentItem() {
        return new ItemDTO(items[currentItemIndex]);
    }

    public double calculateChange(double payment){
        this.amountPaid = payment;
        this.change = payment-this.runningTotal;
        receipt.printReceipt(new SaleLogDTO(
                this.items,
                this.runningTotal,
                this.totalVAT,
                this.time,
                this.amountPaid,
                this.change));
        return change;
    }

    public double endSale(){
        return this.runningTotal;
    }

    private void setTimeOfSale(){
        this.time = LocalDateTime.now();
    }

    public ItemDTO addItem(String itemID){
        ItemDTO item;
        int currentItemIndex = isItemAlreadyInSale(itemID);

        if (currentItemIndex < itemsCounter)
            items[currentItemIndex].increaseQuantity();
        else
            addNewItem(itemID);

        item = new ItemDTO(items[currentItemIndex]);
        updateSale(item);
        return item;
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
        ItemDTO item =  inventorySystem.itemLookup(itemID);
        items[itemsCounter++] = new Item(item);
    }

    private void updateSale(ItemDTO item){
        double itemPrice = item.getPrice();
        double itemNumVAT = itemPrice-itemPrice/(1+ item.getVAT()/100);
        this.runningTotal += itemPrice;
        this.totalVAT += itemNumVAT;
    }

}
