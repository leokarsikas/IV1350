package se.kth.iv1350.pointOfSale.startup;

import se.kth.iv1350.pointOfSale.FileLogger;
import se.kth.iv1350.pointOfSale.SystemOutLogger;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.view.View;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.AccountingSystem;
import se.kth.iv1350.pointOfSale.integration.Printer;



/**
 * Starts up instances of Printer, AccountingSystem, InventorySystem, Controller, and
 * View. Then it initiates a simulation via the View class.
*/

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        AccountingSystem accountingSystem= new AccountingSystem();
        InventorySystem inventorySystem = new InventorySystem();
        Controller contr = new Controller(new FileLogger("Errorlog.txt"));
        View view = new View(contr, new SystemOutLogger());

        view.simulate();
    }
}