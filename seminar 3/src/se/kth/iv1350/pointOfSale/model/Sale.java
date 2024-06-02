package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.Printer;

import java.time.LocalDateTime;

/**
 * The sale class keeps track of sale instance of a
 * point of sale in a retail store.
 */

public class Sale {
    private double runningTotal;
    private Item[] items;
    private int itemsCounter = 0;
    private LocalDateTime time;
    private Receipt receipt;
    private double totalVAT;
    private double amountPaid;
    private double change;
    private InventorySystem inventorySystem;
    private Printer printer;

/**
 * Constructor that creates a new instance of a Sale
 * @param invSyst represents the inventory system so that 
 */
    public Sale(InventorySystem invSyst, Printer print){
        this.runningTotal = 0;
        this.items = new Item[2]; //Arbitrary size for now
        this.printer = print;
        setTimeOfSale();
        this.receipt = new Receipt();
        this.inventorySystem = invSyst;
    }

    /**
     * fetchSaleInfo records the current sale information in
     * a SaleLogDTO ready for transport.
     * @return a SaleLogDTO snapshot of the current sale info.
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
     * printReceipt transforms a snapshot of the current
     * sale information into a DTO and sends it
     * to the receipt class in order for it to be printed.
     */
    public void printReceipt(){
        receipt.printReceipt(new SaleLogDTO(
                this.items,
                this.runningTotal,
                this.totalVAT,
                this.time,
                this.amountPaid,
                this.change), this.printer
        );

    }

    /**
    * calculateChange calculates the change to be given back to the customer.
    * @param payment is the amount of money the customer has paid.
    * @return a double, the amount of change to be given back to the customer.
    */    
    public double calculateChange(double payment){
        this.amountPaid = payment;
        this.change = payment-this.runningTotal;
        return change;
    }

    /**
     * getRunningTotal returns the current total price of the sale.
     * @return a double, the amount of the current total of the 
     * sale.
     */
    public double getRunningTotal(){
        return this.runningTotal;
    }

    private void setTimeOfSale(){
        this.time = LocalDateTime.now();
    }

    /**
     * 'addItem' gets an itemID, checks whether the itemID already exists, if so
     *  it increases the items quantity. Otherwise, it adds the new item to the array of items.
     * 
     * @param itemID is the ID of the item to be added. 
     * @return ItemDTO that represents the added or updated item.     
     * 
     * */
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
            if (items[i] != null && items[i].getID().equals(itemID)) {
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
