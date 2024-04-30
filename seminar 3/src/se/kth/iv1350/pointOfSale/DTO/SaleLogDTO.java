package se.kth.iv1350.pointOfSale.DTO;

import java.time.LocalTime;

public class SaleLogDTO {
    private double runningTotal;
    private LocalTime time;
    //private Amount amountPaid;
    private double amountPaid;
    //private float change;
    private double change;

    public SaleLogDTO(){

    }

    public double getRunningTotal() {
        return runningTotal;
    }

}
