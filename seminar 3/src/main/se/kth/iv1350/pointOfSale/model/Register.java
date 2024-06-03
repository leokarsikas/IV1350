package se.kth.iv1350.pointOfSale.model;

/**
 * The register keeps track of the money stored in 
 * its corresponding physical object.
 */
public class Register {
    private double amountInRegister;

    /**
     * The Register constructor. Initialises the amount to 0,
     * indicating that there is no money in the register upon start.
     */
    public Register() {
        this.amountInRegister = 0;
    }

    /**
     * Updates the amount of money in the register based on how much
     * the customer paid and the amount to give back, the change 
     * that is.
     * @param payment is an amount of money payed by the customer.
     * @param change is the amount that the customer should
     * receive in return.
     */
    public void updateAmountInRegister(double payment, double change) {
        amountInRegister += payment-change;
    }

    /**
     * This is just a getter for testing. It checks how much money
     * is currently in the register.
     * @return a double, the amount of money in the register.
     */
    public double getAmount(){
        return this.amountInRegister;
    }

}
