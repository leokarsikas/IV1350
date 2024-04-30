package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.model.Sale;

public class Controller {
    private Sale sale;

    public Controller(){

    }

    public void startSale(){
        new Sale();
    }

    public SaleLogDTO enterInfo(String itemID){
        return sale.addItem(itemID);
    }

    public double endSale(){
        return sale.getRunningTotal();
    }

/*
    public double endSale(){
        Sale().getRunningTotal();

    }



    public double presentChange(double payment){
        get change
    }
*/
}
