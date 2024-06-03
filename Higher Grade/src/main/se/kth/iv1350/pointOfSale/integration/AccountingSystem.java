package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import java.text.DecimalFormat; //Just for nice output 

/**
 * Accounting system is a mockup of the External Accounting System
 */

public class AccountingSystem {
    DecimalFormat noDecimal = new DecimalFormat("#0"); //Just for nice output
    DecimalFormat doubleDecimal = new DecimalFormat("#0.00"); //Just for nice output

/** 
 *  Empty constructor 
 */
    public AccountingSystem() {

    }

/**
 * 'recordSale()' only prints out the saleLog so that 
 * the right information is passed on correctly to the External Accounting System.
 * @param saleLog represents the information of each instance of a sale. 
 *  */    
 

    public void recordSale(SaleLogDTO saleLog){
        System.out.println("\n--------ACCOUNTING INFORMATION BEGIN-------");
        System.out.println("\nAmount paid:     " + saleLog.getAmountPaid() + " SEK");
        System.out.println("Change:          " + doubleDecimal.format(saleLog.getChange()) + " SEK");
        System.out.println("Running Total:   " + doubleDecimal.format(saleLog.getRunningTotal()) + " SEK");
        System.out.println("Total VAT:        " + doubleDecimal.format(saleLog.getTotalVAT()) + " SEK");
         System.out.println("\n--------ACCOUNTING INFORMATION END-------\n");
       
    }
}
