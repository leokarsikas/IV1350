package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.DTO.ItemDTO;
import se.kth.iv1350.pointOfSale.DTO.SaleLogDTO;

public class InventorySystem {

    public InventorySystem() {

    }

    public void recordSale(SaleLogDTO saleLog){
        //"Record in Database"
        System.out.println("Recorded sale in Inventory System:\n Reduced quantity of ");
    }

    public ItemDTO itemLookup(String itemID){
        //"Inventory Database"
        if(itemID == "abc123"){
            return new ItemDTO(itemID,"BigWheel Oatmeal",29.90,"Bigwheel Oatmeal 500g, whole grain oats, high fiber, gluten free", 1, 6);
        }
        else{
            return new ItemDTO(itemID,"YouGoGo Blueberry",14.90,"YouGoGo Blueberry 240g, low sugar yoghurt, blueberry flavour", 1, 6);
        }
    }

    //"Database

}
