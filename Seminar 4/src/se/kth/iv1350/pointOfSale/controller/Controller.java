package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.MessageCreator;
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.UnrecognisedItemException;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.model.RevenueObserver;
import se.kth.iv1350.pointOfSale.integration.Printer;



/**
 * The controller of the point of sale program. The controller
 * connects the user interface of the cashier (the view) to the
 * model and integration packages.
 */
public class Controller {
    private Printer printer;
    private Sale sale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private Register register;
    private MessageCreator messageCreator;
    private RevenueObserver[] revenueObservers;

    
    /**
     * The constructor of the controller. Creates a new inventory system, 
     * accounting system and register.
     */
    public Controller(MessageCreator messageCreator, Printer printer, InventorySystem inventorySystem, AccountingSystem accountingSystem) {
        this.printer = printer;
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.register = new Register();
        this.messageCreator = messageCreator;
        this.revenueObservers = new RevenueObserver[10]; //Arbitrary size for now
    }

    /**
     * startSale initializes a new Sale object with a reference to the inventorySystem
     */
    public void startSale() {
        this.sale = new Sale(this.inventorySystem, this.printer);
        this.sale.addAllObservers(this.revenueObservers);
    }

    /**
     * enterInfo adds an item to the sale and returns the information of
     * that item.
     * @param itemID parameter is a unique identifier for the item being added to
     * the sale.
     * @return an ItemDTO containing the most recently added item is
     * returned.
     * @throws UnrecognisedItemException is thrown if the itemID is not found in the inventory.
     * @throws DatabaseConnectionException is thrown if the database is not responding.
     */
    public ItemDTO enterInfo(String itemID) throws UnrecognisedItemException, DatabaseConnectionException {
        try{
            return sale.addItem(itemID);
        } 
        catch (UnrecognisedItemException e) {
            messageCreator.log(e.getMessage());
            throw e;
        }
        catch (DatabaseConnectionException e) {
            //messageCreator.log(e.getMessage());
            throw e;
        }
    }

    /**
     * fetchSaleInfo calls sale to get all current sale information.
     * @return a SaleLogDTO with the information of the sale is being returned.
     */
    public SaleLogDTO fetchSaleInfo() {
        return sale.fetchSaleInfo();
    }

    /**
     * endSale calls sale to get the total price of the sale.
     * @return a double, the amount of the total price of the sale.
     */
    public double endSale(){
        return sale.getRunningTotal();
    }

    /**
     * presentChange calls sale in order to calculate the change based on the 
     * payment and the running total. It then sends all current sale info to the
     * inventory system and accounting system to be recorded externally. It then
     * updates the amount of cash in the register based on the payment and change.
     * It then contacts the sale to print the receipt. Then it returns the change
     * to the view.
     * @param payment is an amount of money payed by the customer.
     * @return a double, the amount to give back to the customer
     */
    public double presentChange(double payment) {
        double change = sale.calculateChange(payment);
        inventorySystem.recordSale(sale.fetchSaleInfo());
        accountingSystem.recordSale(sale.fetchSaleInfo());
        register.updateAmountInRegister(payment, change);
        sale.printReceipt();
        return change;
    }

    public void addRevenueObserver(RevenueObserver revObserver){
        for (int i = 0; i < revenueObservers.length; i++){
            if (revenueObservers[i] == null){
                revenueObservers[i] = revObserver;
                break;
            }
        }
    }

}
