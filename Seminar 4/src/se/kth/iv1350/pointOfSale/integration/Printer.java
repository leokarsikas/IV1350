package se.kth.iv1350.pointOfSale.integration;

import java.text.DecimalFormat; //Just for nice output
import java.time.format.DateTimeFormatter; //Just for nice output
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

/**
 * Represents a printer that prints the receipt of a sale.
 */
public class Printer {
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); //Just for nice output

    /**
     * Creates a new instance of a printer.
     */
    public Printer() {

    }

    /**
     * Prints the receipt of a sale.
     * @param saleLog The sale log that contains the information to be printed.
     */
    public void printReceipt(SaleLogDTO saleLog){
        System.out.println("------------Begin receipt------------");

        System.out.println("Time of sale: "+saleLog.getTimeOfSale().format(dateFormat)+"\n");

        for (int i = 0; i < saleLog.getLength(); i++) {
            ItemDTO item = saleLog.getItemFromList(i);
            if (item != null){
                System.out.print(item.getName()+"         ");
                System.out.print(item.getQuantity()+" x ");
                System.out.print(doubleDecimal.format(item.getPrice())+"    ");
                System.out.println(doubleDecimal.format(item.getPrice()*item.getQuantity())+" SEK");
            }
        }

        System.out.println("\nTotal:                                "+doubleDecimal.format(saleLog.getRunningTotal())+" SEK");
        System.out.println("Total VAT:                            "+doubleDecimal.format(saleLog.getTotalVAT())+"  SEK\n");
        System.out.println("Cash:                                 "+saleLog.getAmountPaid()+" SEK");
        System.out.println("Change:                               "+doubleDecimal.format(saleLog.getChange())+" SEK");

        System.out.println("------------End receipt------------\n");
    }
}
