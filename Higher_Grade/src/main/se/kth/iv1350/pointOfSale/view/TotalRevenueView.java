package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.RevenueObserverTemplate;
import java.text.DecimalFormat; // Just for nice output

/*
 * This class extends the RevenueObserverTemplate superclass, tracking the total revenue
 * writing it to the console. When a new update of total revenue arrives, 
 * the console output shows the instance value to be added, and the accumulated revenue. 
 * 
 */

public class TotalRevenueView extends RevenueObserverTemplate {
    private DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); // Just for nice output

    @Override
    protected void doShowTotalIncome(double totalRevenue, double runningTotal) throws Exception {
        System.out.println("Added "+doubleDecimal.format(runningTotal)+" to total revenue. Accumulated revenue: "+doubleDecimal.format(totalRevenue));
    }

    @Override
    protected void handleErrors(Exception e) {
        e.printStackTrace(); 
    }
}
