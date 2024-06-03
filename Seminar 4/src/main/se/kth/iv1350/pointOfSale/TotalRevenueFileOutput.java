package se.kth.iv1350.pointOfSale;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;
import java.text.DecimalFormat;

/*
 * This class implements the RevenueObserver interface, tracking the total revenue
 * and logging it to a file. When a new update of total revenue arrives, 
 * the file shows the instance value to be added, and the accumulated revenue. 
 */

public class TotalRevenueFileOutput implements RevenueObserver{
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    private MessageCreator messageCreator = new FileLogger("Revenuelog.txt");
    private double totalRevenue = 0;
    /**
     * Updates the total revenue with the specified amount and logs the update to a file.
     * The logged message includes the added amount and the new total revenue.
     * 
     * @param total The amount of revenue to add to the total.
     */

    @Override
    public void updateRevenue(double total){
        this.totalRevenue += total;
        messageCreator.log("Added "+doubleDecimal.format(total)+" to total revenue. Accumulated revenue: "+doubleDecimal.format(totalRevenue));
    }

}
