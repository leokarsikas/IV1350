package se.kth.iv1350.pointOfSale;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;
import java.text.DecimalFormat;

public class TotalRevenueFileOutput implements RevenueObserver{
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    private MessageCreator messageCreator = new FileLogger("Revenuelog.txt");
    private double totalRevenue = 0;

    public void updateRevenue(double total){
        this.totalRevenue += total;
        messageCreator.log("Added "+doubleDecimal.format(total)+" to total revenue. Accumulated revenue: "+doubleDecimal.format(totalRevenue));
    }

}
