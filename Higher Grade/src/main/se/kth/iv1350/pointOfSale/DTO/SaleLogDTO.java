package se.kth.iv1350.pointOfSale.DTO;

import java.time.LocalDateTime;
import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;

//After creation: READ ONLY
public class SaleLogDTO {
    private double runningTotal;
    private double totalVAT;
    private LocalDateTime time;
    private double amountPaid;
    private double change;
    private Item[] items;

    public SaleLogDTO(Item[] items, double runningTotal, double totalVAT, LocalDateTime time, double amountPaid, double change){
        this.items = items;
        this.runningTotal = runningTotal;
        this.totalVAT = totalVAT;
        this.time = time;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * Get the running total of the sale
     */
    public double getRunningTotal() {
        return this.runningTotal;
    }

    /**
     * Get the total VAT of the sale
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }

    /**
     * Get the amount paid by the customer
     */
    public double getAmountPaid() {
        return this.amountPaid;
    }
    /**
     * Get the change to be returned to the customer
     */
    public double getChange() {
        return this.change;
    }
    /**
     * Gets the time of the sale
     */
    public LocalDateTime getTimeOfSale(){ return this.time; }

    /**
     * length of the list of items
     */
    public int getLength(){ return this.items.length; }

    /**
     * Get an ItemDTO from the items list at the specified index
     * @param index the index of the item in the list
     * @return an ItemDTO of the item at the specified index
     */
    public ItemDTO getItemFromList(int index) { 
        if (items[index] != null)
            return new ItemDTO(items[index]); 
        return null;
    }


}
