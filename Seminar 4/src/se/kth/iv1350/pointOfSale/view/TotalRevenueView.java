package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;


public class TotalRevenueView implements RevenueObserver{
    private double totalRevenue = 0;

    public void updateRevenue(double total){
        this.totalRevenue += total;
    }

}
