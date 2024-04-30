package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;
/*
    Placeholder View
 */
public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void simulate(){
        System.out.println("Start sale! ");
        System.out.println("Added abc123 "+contr.enterInfo("abc123"));
        System.out.println("Added abc123 "+contr.enterInfo("abc123"));
        System.out.println("Added def456 "+contr.enterInfo("def456"));
        System.out.println("End sale! "+contr.endSale());
    }
}
