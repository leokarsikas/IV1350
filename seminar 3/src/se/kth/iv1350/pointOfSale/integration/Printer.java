package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

public class Printer {
    public Printer() {

    }

    public void printReceipt(SaleLogDTO saleLog){
        System.out.println("------------Begin receipt------------");

        System.out.println("Time of sale: "+saleLog.getTimeOfSale()+"\n");

        for (int i = 0; i < saleLog.getLength(); i++) {
            ItemDTO item = saleLog.getItemFromList(i);
            System.out.print(item.getName()+"          ");
            System.out.print(item.getQuantity()+" x ");
            System.out.print(item.getPrice()+"    ");
            System.out.println(item.getPrice()* item.getQuantity()+" SEK");
        }

        System.out.println("\nTotal:                    "+saleLog.getRunningTotal()+" SEK");
        System.out.println("Total VAT:                "+saleLog.getTotalVAT()+" SEK\n");
        System.out.println("Cash:                     "+saleLog.getAmountPaid()+" SEK");
        System.out.println("Change:                   "+saleLog.getChange()+" SEK");

        System.out.println("------------End receipt------------\n");
    }
}
