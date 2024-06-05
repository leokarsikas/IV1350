package se.kth.iv1350.pointOfSale;

import java.text.DecimalFormat;

/*
 * This class extends the RevenueObserverTemplate superclass, tracking the total revenue
 * and logging it to a file. When a new update of total revenue arrives, 
 * the file shows the instance value to be added, and the accumulated revenue. 
 */
public class TotalRevenueFileOutput extends RevenueObserverTemplate {
    private DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); // Just for nice output
    private MessageCreator messageCreator = new FileLogger("Revenuelog.txt");

    @Override
    protected void doShowTotalIncome(double totalRevenue, double runningTotal) throws Exception {
        messageCreator.log("Added "+doubleDecimal.format(runningTotal)+" to total revenue. Accumulated revenue: "+doubleDecimal.format(totalRevenue));
    }

    @Override
    protected void handleErrors(Exception e) {
        e.printStackTrace(); 
    }
}
