package se.kth.iv1350.pointOfSale.DTO;

import java.time.LocalTime;

public class SaleLogDTO {
    private double runningTotal;
    private double totalVAT;
    private LocalTime time;
    //private Amount amountPaid;
    private double amountPaid;
    private double change;

    public SaleLogDTO(){

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

    public void setTimeOfSale() {
        this.time = java.time.LocalTime.now();
    }

    public LocalTime getTimeOfSale() {
        return this.time;
    }

}
