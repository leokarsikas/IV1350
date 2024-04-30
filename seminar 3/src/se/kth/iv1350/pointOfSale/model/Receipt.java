package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

public class Receipt {
    private SaleLogDTO saleLog;

    public Receipt(SaleLogDTO salelog){
        this.saleLog = salelog;
        System.out.println("Salelog: "+salelog.getRunningTotal());
    }



}
