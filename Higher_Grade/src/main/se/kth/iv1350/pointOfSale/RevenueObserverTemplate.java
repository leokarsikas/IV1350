package se.kth.iv1350.pointOfSale;

import se.kth.iv1350.pointOfSale.model.RevenueObserver;

/*
 * This abstract superclass acts as a template for the files:
 *  - TotalRevenueView 
 *  - TotalRevenueFileOutput.
 */
public abstract class RevenueObserverTemplate implements RevenueObserver {
    private double totalRevenue = 0;

    /*
     * Adds running total when a sale is done into the total revenue.
     * 
     * @param runningTotal is the sale total which will be added to the total revenue.
     */
    @Override
    public void updateRevenue(double runningTotal) {
        totalRevenue += runningTotal; // Calculate the total amount paid since the program started.
        showTotalIncome(totalRevenue, runningTotal);
    }

    private void showTotalIncome(double totalRevenue, double runningTotal) {
        try {
            doShowTotalIncome(totalRevenue, runningTotal);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome(double totalRevenue, double runningTotal) throws Exception;

    protected abstract void handleErrors(Exception e);
}
