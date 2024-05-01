package se.kth.iv1350.pointOfSale.integration;

import java.text.DecimalFormat; //Just for nice output
import java.time.format.DateTimeFormatter; //Just for nice output
import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

public class Printer {
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"); //Just for nice output

    public Printer() {

    }

    public void printReceipt(SaleLogDTO saleLog){
        System.out.println("------------Begin receipt------------");

        System.out.println("Time of sale: "+saleLog.getTimeOfSale().format(dateFormat)+"\n");

        for (int i = 0; i < saleLog.getLength(); i++) {
            ItemDTO item = saleLog.getItemFromList(i);
            System.out.print(item.getName()+"          ");
            System.out.print(item.getQuantity()+" x ");
            System.out.print(item.getPrice()+"    ");
            System.out.println(item.getPrice()* item.getQuantity()+" SEK");
        }

        System.out.println("\nTotal:                                "+doubleDecimal.format(saleLog.getRunningTotal())+" SEK");
        System.out.println("Total VAT:                            "+doubleDecimal.format(saleLog.getTotalVAT())+"  SEK\n");
        System.out.println("Cash:                                 "+noDecimal.format(saleLog.getAmountPaid())+"   SEK");
        System.out.println("Change:                               "+doubleDecimal.format(saleLog.getChange())+" SEK");

        System.out.println("------------End receipt------------\n");
    }
}
