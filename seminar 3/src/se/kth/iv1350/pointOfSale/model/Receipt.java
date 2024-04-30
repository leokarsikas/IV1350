package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.Printer;

public class Receipt {
    private SaleLogDTO saleLog;
    private Printer printer;

    public Receipt(){
        this.saleLog = new SaleLogDTO();
        this.printer = new Printer();
    }

    public void printReceipt(SaleLogDTO salelog){
        printer.printReceipt(salelog);
    }

}
