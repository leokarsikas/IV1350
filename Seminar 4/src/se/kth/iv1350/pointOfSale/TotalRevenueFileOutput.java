package se.kth.iv1350.pointOfSale;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;

public class TotalRevenueFileOutput implements RevenueObserver{
    private MessageCreator messageCreator;
    private double totalRevenue = 0;

    public void updateRevenue(double total){
        this.totalRevenue += total;
        messageCreator.log("Added "+total+" to total revenue. Accumulated revenue is: "+totalRevenue);
    }
}
