package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;
import java.text.DecimalFormat; //Just for nice output


public class TotalRevenueView implements RevenueObserver{
    private double totalRevenue = 0;
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

    @Override
    public void updateRevenue(double total){
        this.totalRevenue += total;
        System.out.println("Added "+doubleDecimal.format(total)+" to total revenue. Accumulated revenue: "+doubleDecimal.format(totalRevenue));
    }

}
