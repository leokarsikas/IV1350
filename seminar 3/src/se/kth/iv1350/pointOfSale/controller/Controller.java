package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;
import se.kth.iv1350.pointOfSale.model.Sale;

public class Controller {

    public Controller(){

    }

    public void startSale(){
        Sale sale = new Sale();
    }

    public SaleLogDTO enterInfo(String itemID, int quantity){
        return sale.isItemAlreadyInSale(itemID, quantity);
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