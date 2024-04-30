package se.kth.iv1350.pointOfSale.model;

public class Register {
    private double amountInRegister;

    public Register() {
        Register register = new Register();
    }

    public void updateAmountInRegister(float payment) {
        amountInRegister += payment;
    }

}
