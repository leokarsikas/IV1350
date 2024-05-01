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

    public double getRunningTotal() {
        return this.runningTotal;
    }

    public void setRunningTotal(double runningTotal) {
        this.runningTotal = runningTotal;
    }

    public double getTotalVAT() {
        return this.totalVAT;
    }

    public void setTotalVAT(double totalVAT) {
        this.totalVAT += totalVAT;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChange() {
        return this.change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public LocalDateTime getTimeOfSale(){ return this.time; }

    public int getLength(){ return this.items.length; }

    public ItemDTO getItemFromList(int index) { return new ItemDTO(items[index]); }

    public void setItemToList(Item item, int index) { items[index] = item; }

}
