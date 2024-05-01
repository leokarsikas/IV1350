package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;

public class Controller {
    private Sale sale;
    private SaleLogDTO saleLog;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private Register register;


    public Controller(){
        this.saleLog = new SaleLogDTO();
        this.sale = new Sale();
        this.inventorySystem = new InventorySystem();
        this.accountingSystem = new AccountingSystem();
        this.register = new Register();
    }

    public void startSale(){
        this.sale = new Sale();
        this.saleLog = new SaleLogDTO();
        saleLog.setTimeOfSale();
        System.out.println(saleLog.getTimeOfSale());
    }

    public ItemDTO enterInfo(String itemID){
        System.out.println("Entering information");
        sale.addItem(itemID);
        return sale.getCurrentItem();
    }

    public SaleLogDTO fetchSaleInfo() {
        return sale.fetchSaleInfo();
    }

    public double presentChange(double payment){
        inventorySystem.recordSale(this.saleLog);
        accountingSystem.recordSale(this.saleLog);
        double change = sale.calculateChange(payment);
        register.updateAmountInRegister(payment, change);
        return change;
    }
}
