package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.integration.Printer;

/**
 * The Receipt class represents the Receipt that shows each instance of a saleLog 
 * that later will be printed. 
 *  
 */
public class Receipt {
    private Printer printer;

    /**
     * Constructor that create a new instance of a printer in order for 
     * the printout to operate correctly
     */
    public Receipt(){
        this.printer = new Printer();
    }

    /**
     * printReceipt calls the printer to print out the saleLog
     * @param saleLog consists of the current sale information. 
     */
    public void printReceipt(SaleLogDTO saleLog){
        printer.printReceipt(saleLog);
    }

}
