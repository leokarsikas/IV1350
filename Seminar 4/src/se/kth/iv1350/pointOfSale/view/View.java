package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.exceptions.InventorySystemException;
import se.kth.iv1350.pointOfSale.exceptions.UnrecognisedItemException;

import java.text.DecimalFormat; //Just for nice output

/**
 * The View class is the user interface interacted with
 * by the cashier at the store. It simulates basic flow 
 * described in Seminar 1 and prints everything returned
 * by the controller.
 */
public class View {
    private Controller contr;
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

    /**
     * View constructor, using the controller created in main.
     * @param contr is the controller.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates the cashier performing all its actions in the flow described 
     * in Seminar 1.
     */
    public void simulate(){
        System.out.println("\nStart sale! ");
        contr.startSale();
        ItemDTO currentItem;
        SaleLogDTO salelog;

        //Add first item
        try {
            System.out.println("\nAdd 1 item with item id abc123:");
            currentItem = contr.enterInfo("abc123");
            salelog = contr.fetchSaleInfo();

            System.out.println("Item ID: " + currentItem.getID());
            System.out.println("Item name: " + currentItem.getName());
            System.out.println("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            System.out.println("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            System.out.println("Item description: " + currentItem.getDescription());
            System.out.println("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
            System.out.println("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        }
        catch (UnrecognisedItemException e){
            System.err.println(e);
        }

        //Add second item
        try {
            System.out.println("\nAdd 1 item with item id abc123:");
            currentItem = contr.enterInfo("abc123");
            salelog = contr.fetchSaleInfo();
            System.out.println("Item ID: " + currentItem.getID());
            System.out.println("Item name: " + currentItem.getName());
            System.out.println("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            System.out.println("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            System.out.println("Item description: " + currentItem.getDescription());
            System.out.println("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
            System.out.println("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        }
        catch (UnrecognisedItemException e){
            System.out.println(e.getMessage());
        }


        //Add third item
        try {
            System.out.println("\nAdd 1 item with item id def456:");
            currentItem = contr.enterInfo("def456");
            salelog = contr.fetchSaleInfo();
            System.out.println("Item ID: " + currentItem.getID());
            System.out.println("Item name: " + currentItem.getName());
            System.out.println("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            System.out.println("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            System.out.println("Item description: " + currentItem.getDescription());
            System.out.println("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
            System.out.println("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        }
        catch (UnrecognisedItemException e){
            System.out.println(e.getMessage());
        }


        try {
            System.out.println("\nAdd 1 item with item id undefined:");
            currentItem = contr.enterInfo("undefined");
            salelog = contr.fetchSaleInfo();
            System.out.println("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
            System.out.println("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        }
        catch (UnrecognisedItemException e){
            System.out.println(e.getMessage());
        }


        try {
            currentItem = contr.enterInfo("InventoryDatabaseFailure");
        }
        catch (UnrecognisedItemException e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nEnd sale! ");
        contr.endSale();
        System.out.println("Total cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");

        System.out.println("Customer pays 100:00 SEK");
        double change = contr.presentChange(100);
        System.out.println("Change to give the customer: "+doubleDecimal.format(change)+" SEK");

    }
}
