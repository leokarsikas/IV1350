package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;
import se.kth.iv1350.pointOfSale.integration.UnrecognisedItemException;
import se.kth.iv1350.pointOfSale.FileLogger;
import se.kth.iv1350.pointOfSale.MessageCreator;
import se.kth.iv1350.pointOfSale.SystemOutLogger;
import se.kth.iv1350.pointOfSale.TotalRevenueFileOutput;

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

    /**
     * View constructor, using the controller created in main.
     * @param contr is the controller.
     */
    public View(Controller contr, MessageCreator messageCreator) {
        this.contr = contr;
        this.messageCreator = messageCreator;
        contr.addRevenueObserver(new TotalRevenueFileOutput());
        contr.addRevenueObserver(new TotalRevenueView());
    }

    private void switchLogger(MessageCreator newMessager){
        this.messageCreator = newMessager;
    }

    /**
     * Simulates the cashier performing all its actions in the flow described 
     * in Seminar 1.
     */
    public void simulate(){
        for (int j = 0; j < 3; j++){
            messageCreator.log("\nStart new sale! This is sale number "+(j+1)+" today.");
            contr.startSale();
            ItemDTO currentItem = null;
            SaleLogDTO salelog = null;
            String[] itemIDs = {"abc123", "abc123", "def456", "ghi789", "def456", "serverNotResponding"};
            switchLogger(new FileLogger("Errorlog.txt"));
            messageCreator.log("\nSale "+(j+1)+" errors: ");
            switchLogger(new SystemOutLogger());

            for (int i = 0+j; i < itemIDs.length-2+j; i++){
                currentItem = null;
                try {
                    messageCreator.log("\nAdding 1 item with item id "+itemIDs[i]);
                    currentItem = contr.enterInfo(itemIDs[i]);
                    salelog = contr.fetchSaleInfo();
                }
                catch (UnrecognisedItemException e){
                    System.out.println("Invalid ItemID please try another id");
                    messageCreator.log(e.getMessage());
                }
                catch (DatabaseConnectionException e){
                    switchLogger(new FileLogger("Errorlog.txt"));
                    messageCreator.log(e.getStackTraceAsString());
                    switchLogger(new SystemOutLogger());
                    messageCreator.log(e.getMessage());
                }
                if (currentItem != null){
                    messageCreator.log("Item ID: " + currentItem.getID());
                    messageCreator.log("Item name: " + currentItem.getName());
                    messageCreator.log("Item price: " + doubleDecimal.format(currentItem.getPrice()) + " SEK");
                    messageCreator.log("Item VAT: " + noDecimal.format(currentItem.getVAT()) + "%");
                    messageCreator.log("Item description: " + currentItem.getDescription());

                    messageCreator.log("\nTotal cost (incl VAT): " + doubleDecimal.format(salelog.getRunningTotal()) + " SEK");
                    messageCreator.log("Total VAT: " + doubleDecimal.format(salelog.getTotalVAT()) + " SEK");
                }
                messageCreator.log("________________________________________________________");
            }

            messageCreator.log("\nEnd sale! ");
            contr.endSale();
            messageCreator.log("Total cost (incl VAT): "+doubleDecimal.format(salelog.getRunningTotal())+" SEK");

            messageCreator.log("Customer pays 100:00 SEK");
            double change = contr.presentChange(100);
            messageCreator.log("Change to give the customer: "+doubleDecimal.format(change)+" SEK");
            messageCreator.log("*********************************************************************************************");
        }

    }
}
