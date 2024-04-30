package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

public class Printer {
    public Printer() {

    }

    public void printReceipt(SaleLogDTO saleLog){
        System.out.println("------------Begin receipt------------");

        System.out.println("Time of sale: "+saleLog.getTimeOfSale());



        System.out.println("------------End receipt------------");
    }
}
