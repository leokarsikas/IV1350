package test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.model.Register;

/**
 * Test the Register class in the model
 */
public class RegisterTest {
    private Register register;
    /*
    Sets up a new register before each test
     */
    @BeforeEach
    public void setUp() {
        register = new Register();
    }

    /*
    Test the update of amount in the Regiser class
    */
    @Test
    public void testUpdateAmountInRegister() {

        register.updateAmountInRegister(100.0, 0.0);
        register.updateAmountInRegister(50.0, 10.0);

        assertEquals(140.0, register.getAmount(), "Incorrect Amount result in register");
    }

}
