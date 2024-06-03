package se.kth.iv1350.pointOfSale;
import se.kth.iv1350.pointOfSale.model.RevenueObserver;

    /*
 * Serves as a template to the classes
 * TotalRevenueView and TotalRevenueFileOutput.
 */
public abstract class ObserverTemplate implements RevenueObserver{
    /*
     * Adds money from a new sale into the total revenue.
     * 
     * @param total is the sale total which will be added to the total revenue.
     */
    @Override
    public void updateRevenue(double runningTotal) {
        showTotalIncome(runningTotal);
    }

    private void showTotalIncome(double total){
        try{
            doShowTotalIncome(total);
        } catch (Exception e){
            handleErrors(e);
        }
    }
        
    protected abstract void doShowTotalIncome(double total) throws Exception;

    protected abstract void handleErrors(Exception e);
}
