package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.Printer;

public class Receipt {
    private Printer printer;

    public Receipt(){
        this.printer = new Printer();
    }

    public void printReceipt(SaleLogDTO saleLog){
        printer.printReceipt(saleLog);
    }

}
