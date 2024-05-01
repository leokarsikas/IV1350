package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.controller.Controller;
import java.text.DecimalFormat; //Just for nice output

/*
    Placeholder View
 */
public class View {
    private Controller contr;
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

    /*
     * View constructor, using the controller created in main.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /*
     * Sumulates the cashier performing all its actions in the flow described in Seminar 1.
     */
    public void simulate(){
        System.out.println("Start sale! ");
        contr.startSale();

        //Add first item
        System.out.println("\nAdd 1 item with item id abc123:");
        ItemDTO currentItem = contr.enterInfo("abc123");
        SaleLogDTO salelog = contr.fetchSaleInfo();

        System.out.println("Item ID: "+currentItem.getID());
        System.out.println("Item name: "+currentItem.getName());
        System.out.println("Item price: "+doubleDecimal.format(currentItem.getPrice())+" SEK");
        System.out.println("Item VAT: "+noDecimal.format(currentItem.getVAT())+"%");
        System.out.println("Item description: "+currentItem.getDescription());
        System.out.println("\nTotal cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");
        System.out.println("Total VAT: "+doubleDecimal.format(salelog.getTotalVAT())+" SEK");

        //Add second item
        System.out.println("\nAdd 1 item with item id abc123:");
        currentItem = contr.enterInfo("abc123");
        salelog = contr.fetchSaleInfo();
        System.out.println("Item ID: "+currentItem.getID());
        System.out.println("Item name: "+currentItem.getName());
        System.out.println("Item price: "+doubleDecimal.format(currentItem.getPrice())+" SEK");
        System.out.println("Item VAT: "+noDecimal.format(currentItem.getVAT())+"%");
        System.out.println("Item description: "+currentItem.getDescription());
        System.out.println("\nTotal cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");
        System.out.println("Total VAT: "+doubleDecimal.format(salelog.getTotalVAT())+" SEK");

        //Add third item
        System.out.println("\nAdd 1 item with item id abc123:");
        currentItem = contr.enterInfo("def456");
        salelog = contr.fetchSaleInfo();
        System.out.println("Item ID: "+currentItem.getID());
        System.out.println("Item name: "+currentItem.getName());
        System.out.println("Item price: "+doubleDecimal.format(currentItem.getPrice())+" SEK");
        System.out.println("Item VAT: "+noDecimal.format(currentItem.getVAT())+"%");
        System.out.println("Item description: "+currentItem.getDescription());
        System.out.println("\nTotal cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");
        System.out.println("Total VAT: "+doubleDecimal.format(salelog.getTotalVAT())+" SEK");

        System.out.println("\nEnd sale! ");
        System.out.println("Total cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");

        System.out.println("Customer pays 100:00 SEK");
        double change = contr.presentChange(100);
        System.out.println("Change to give the customer: "+doubleDecimal.format(change)+" SEK");

    }
}
