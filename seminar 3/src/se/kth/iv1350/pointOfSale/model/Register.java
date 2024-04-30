package se.kth.iv1350.pointOfSale.model;

public class Register {
    private double amountInRegister;

    public Register() {
        this.amountInRegister = 0;
    }

    public void updateAmountInRegister(double payment, double change) {
        amountInRegister += payment-change;
    }

}
