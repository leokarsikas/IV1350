package se.kth.iv1350.pointOfSale.DTO;

import java.time.LocalTime;

//After creation: READ ONLY
public class SaleLogDTO {
    private double runningTotal;
    private double totalVAT;
    private LocalTime time;
    private double amountPaid;
    private double change;
    private ItemDTO[] items;

    public SaleLogDTO(){
        this.items = new ItemDTO[2];
        this.runningTotal = 0;
        this.totalVAT = 0;
        this.time = null;
        this.amountPaid = 0;
        this.change = 0;
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

    public LocalTime getTimeOfSale(){ return this.time; }

    public void setTimeOfSale() {
        this.time = java.time.LocalTime.now();
    }

    public int getLength(){ return this.items.length; }

    public ItemDTO getItemFromList(int index) { return items[index]; }

    public void setItemToList(ItemDTO item, int index) { items[index] = item; }

}
