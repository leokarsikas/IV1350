package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;

public class Controller {
    private Sale sale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private Register register;

    public Controller() {
        this.inventorySystem = new InventorySystem();
        this.accountingSystem = new AccountingSystem();
        this.register = new Register();
    }

    // startSale initializes a new Sale object with a reference to the
    // inventorySystem.
    public void startSale() {
        this.sale = new Sale(this.inventorySystem);
    }

    /**
     * enterInfofunction adds an item to the sale and returns the information of
     * that item.
     * @param itemID parameter is a unique identifier for the item being added to
     * the sale.
     * @return An ItemDTO object containing the most recently added item is
     * returned.
     */
    public ItemDTO enterInfo(String itemID) {
        sale.addItem(itemID);
        return sale.getCurrentItem();
    }

    /**
     * @return A SaleLogDTO object with the information of the sale is being
     *         returned.
     */
    public SaleLogDTO fetchSaleInfo() {
        return sale.fetchSaleInfo();
    }

    public double endSale(){
        return sale.getRunningTotal();
    }

    /**
     * The `presentChange` function records a sale, calculates change for a payment,
     * updates the register,
     * and returns the change amount.
     * 
     * @param payment represents the amount of money that a customer gives when
     *                making a purchase.
     * @return The method `presentChange` is returning the amount of change
     *         calculated by the `sale.calculateChange(payment)` method.
     */
    public double presentChange(double payment) {
        double change = sale.calculateChange(payment);
        inventorySystem.recordSale(sale.fetchSaleInfo());
        accountingSystem.recordSale(sale.fetchSaleInfo());
        register.updateAmountInRegister(payment, change);
        sale.printReceipt();
        return change;
    }
}
