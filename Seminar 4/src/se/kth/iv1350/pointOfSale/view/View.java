package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.exceptions.UnrecognisedItemException;
import se.kth.iv1350.pointOfSale.model.RevenueObserver;
import se.kth.iv1350.pointOfSale.MessageCreator;
import se.kth.iv1350.pointOfSale.SystemOutLogger; import se.kth.iv1350.pointOfSale.FileLogger;
// N.B. imported for the sole purpose of simulating logger changes. This would in a real world scenario not be imported, 
//and instead the user would press a button or something in that manner to switch logger, sending it from the main method most likely.

import java.text.DecimalFormat; //Just for nice output

/**
 * The View class is the user interface interacted with
 * by the cashier at the store. It simulates basic flow 
 * described in Seminar 1 and prints everything returned
 * by the controller.
 */
public class View {
    private Controller contr;
    private MessageCreator messageCreator;
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

    private void setLogger(MessageCreator messageLogger){
        this.messageCreator = messageLogger;
    }

    /**
     * View constructor, using the controller created in main.
     * @param contr is the controller.
     */
    public View(Controller contr, MessageCreator messageCreator) {
        this.contr = contr;
        this.messageCreator = messageCreator;
        //contr.addRevenueObserver(new RevenueObserver());
    }

    /**
     * Simulates the cashier performing all its actions in the flow described 
     * in Seminar 1.
     */
    public void simulate(){
        messageCreator.log("\nStart sale! ");
        contr.startSale();
        ItemDTO currentItem = null;
        SaleLogDTO salelog = null;
        String[] itemIDs = {"abc123", "abc123", "def456", "undefined", "serverNotResponding"};

        for (int i = 0; i < itemIDs.length; i++){
            try {
                messageCreator.log("\nAdding 1 item with item id "+itemIDs[i]);
                currentItem = contr.enterInfo(itemIDs[i]);
                salelog = contr.fetchSaleInfo();
                messageCreator.log("Item ID: " + currentItem.getID());
                messageCreator.log("Item name: " + currentItem.getName());
                messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
                messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
                messageCreator.log("Item description: " + currentItem.getDescription());

                messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
                messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
            }
            catch (UnrecognisedItemException e){
                setLogger(new FileLogger()); // See the comment in the corresponding import
                messageCreator.log(e.getMessage());
                setLogger(new SystemOutLogger()); // See the comment in the corresponding import
                messageCreator.log(e.getMessage());
            }
            messageCreator.log("________________________________________________________");
        }

/*
        //Add first item
        try {
            messageCreator.log("\nAdd 1 item with item id abc123:");
            currentItem = contr.enterInfo("abc123");
            salelog = contr.fetchSaleInfo();
            messageCreator.log("Item ID: " + currentItem.getID());
            messageCreator.log("Item name: " + currentItem.getName());
            messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            messageCreator.log("Item description: " + currentItem.getDescription());
        }
        catch (UnrecognisedItemException e){
            setLogger(new FileLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
            setLogger(new SystemOutLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
        }
        messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
        messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        messageCreator.log("________________________________________________________");


        //Add second item
        try {
            messageCreator.log("\nAdd 1 item with item id abc123:");
            currentItem = contr.enterInfo("abc123");
            salelog = contr.fetchSaleInfo();
            messageCreator.log("Item ID: " + currentItem.getID());
            messageCreator.log("Item name: " + currentItem.getName());
            messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            messageCreator.log("Item description: " + currentItem.getDescription());
        }
        catch (UnrecognisedItemException e){
            setLogger(new FileLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
            setLogger(new SystemOutLogger()); // See the comment in the corresponding import   
            messageCreator.log(e.getMessage());     
        }

        messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
        messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");

        messageCreator.log("________________________________________________________");


        //Add third item
        try {
            messageCreator.log("\nAdd 1 item with item id def456:");
            currentItem = contr.enterInfo("def456");
            salelog = contr.fetchSaleInfo();
            messageCreator.log("Item ID: " + currentItem.getID());
            messageCreator.log("Item name: " + currentItem.getName());
            messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            messageCreator.log("Item description: " + currentItem.getDescription());
        }
        catch (UnrecognisedItemException e){
            setLogger(new FileLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
            setLogger(new SystemOutLogger()); // See the comment in the corresponding import 
            messageCreator.log(e.getMessage());       
        }
        messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
        messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        messageCreator.log("________________________________________________________");

        //Add third item
        messageCreator.log("\nAdd 1 item with item id undefined:");
        try {
            currentItem = contr.enterInfo("undefined");
            salelog = contr.fetchSaleInfo();
            messageCreator.log("Item ID: " + currentItem.getID());
            messageCreator.log("Item name: " + currentItem.getName());
            messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            messageCreator.log("Item description: " + currentItem.getDescription());
        }
        catch (UnrecognisedItemException e){
            setLogger(new FileLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
            setLogger(new SystemOutLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
        }
        //catch (InventorySystemException e){
        //    messageCreator.log(e.getMessage());
        //}
        messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
        messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        messageCreator.log("________________________________________________________");


        messageCreator.log("\nAdd 1 item with item id serverNotResponding:");
        try {
            currentItem = contr.enterInfo("serverNotResponding");
            salelog = contr.fetchSaleInfo();
            messageCreator.log("Item ID: " + currentItem.getID());
            messageCreator.log("Item name: " + currentItem.getName());
            messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
            messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
            messageCreator.log("Item description: " + currentItem.getDescription());
        }
        catch (UnrecognisedItemException e){
            setLogger(new FileLogger()); // See the comment in the corresponding import
            messageCreator.log(e.getMessage());
            setLogger(new SystemOutLogger()); // See the comment in the corresponding import        
        }
        //catch (InventorySystemException e){
        //    messageCreator.log(e.getMessage());
        //}
        messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
        messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
        messageCreator.log("________________________________________________________");
 */


        messageCreator.log("\nEnd sale! ");
        contr.endSale();
        messageCreator.log("Total cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");

        messageCreator.log("Customer pays 100:00 SEK");
        double change = contr.presentChange(100);
        messageCreator.log("Change to give the customer: "+doubleDecimal.format(change)+" SEK");

    }
}
